(function () {
    "use strict";

    app.component('msaCai', {
        templateUrl: '../../app/component/denuncia-sinistro/cai/components/templates/cai-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, toastr, DomainSvc, SinistriSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M14';

                $ctrl.baremes = undefined;
                $ctrl.cai = {};
                $ctrl.cai.baremeAssicurato = 0;
                $ctrl.cai.baremeControparte = 0;
                $ctrl.cai.osservazioniAssicurato = undefined;
                $ctrl.cai.osservazioniControparte = undefined;

                $ctrl.responsabilita = {
                    cliente: false,
                    controparte: false,
                    concorsuale: false,
                    nonClassificabile: false
                };

                $ctrl.isSaved = false;
                $ctrl.isInputConsumed = false;

                DomainSvc.getBaremes().then(function (response) {
                    $ctrl.baremes = response.data.result;
                });

                $ctrl.salvaCai = function () {

                    SinistriSvc.saveCai($ctrl.numeroSinistroProvvisorio, $ctrl.cai, $ctrl.tempSegnalazione.nveicoli).then(function (response) {
                        DebugSvc.log("saveCai", response);
                        if (response.data.status === 200) {
                            if (_.isObject(response.data.result)) {
                                // Il servizio restituisce un result solo se i veicoli son 2 o più.
                                $ctrl.setResponsabilitaUI(response.data.result.responsabilita);
                            }
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.caiForm.$setPristine(true);
                            $ctrl.isSaved = true;
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });

                };

                $ctrl.calcolaResponsabilita = function () {
                    SinistriSvc.getResponsabilita($ctrl.cai.baremeAssicurato, $ctrl.cai.baremeControparte).then(function (response) {
                        DebugSvc.log("getResponsabilita", response);
                        if (response.data.status === 200) {
                            $ctrl.setResponsabilitaUI(response.data.result);
                        } else {
                            toastr.error($translate('global.cai.messages.responsabilita'));
                        }
                    });
                };

                $ctrl.setResponsabilitaUI = function (responsabilita) {

                    responsabilita = responsabilita.toUpperCase();

                    switch (responsabilita) {
                        case "NON CLASSIFICABILE":
                            $ctrl.responsabilita.cliente = false;
                            $ctrl.responsabilita.controparte = false;
                            $ctrl.responsabilita.concorsuale = false;
                            $ctrl.responsabilita.nonClassificabile = true;
                            break;
                        case "CLIENTE":
                            $ctrl.responsabilita.cliente = true;
                            $ctrl.responsabilita.controparte = false;
                            $ctrl.responsabilita.concorsuale = false;
                            $ctrl.responsabilita.nonClassificabile = false;
                            break;
                        case "CONTROPARTE":
                            $ctrl.responsabilita.cliente = false;
                            $ctrl.responsabilita.controparte = true;
                            $ctrl.responsabilita.concorsuale = false;
                            $ctrl.responsabilita.nonClassificabile = false;
                            break;
                        case "CONCORSUALE":
                            $ctrl.responsabilita.cliente = false;
                            $ctrl.responsabilita.controparte = false;
                            $ctrl.responsabilita.concorsuale = true;
                            $ctrl.responsabilita.nonClassificabile = false;
                            break;
                        default:
                            $ctrl.responsabilita.cliente = false;
                            $ctrl.responsabilita.controparte = false;
                            $ctrl.responsabilita.concorsuale = false;
                            $ctrl.responsabilita.nonClassificabile = false;
                            break;
                    }
                };

                $ctrl.bindCai = function () {
                    if (_.isObject($ctrl.sinistroProvvisorio.cai)) {

                        $ctrl.cai.baremeAssicurato = $ctrl.sinistroProvvisorio.cai.baremesCliente.id;
                        $ctrl.cai.osservazioniAssicurato = $ctrl.sinistroProvvisorio.cai.noteCliente;

                        if (_.isObject($ctrl.sinistroProvvisorio.cai.baremesControparte)) {
                            $ctrl.cai.baremeControparte = $ctrl.sinistroProvvisorio.cai.baremesControparte.id;
                            $ctrl.cai.osservazioniControparte = $ctrl.sinistroProvvisorio.cai.noteControparte;
                        }
                        $ctrl.isSaved = true;
                    }
                };

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            baremeAssicurato: $ctrl.cai.baremeAssicurato,
                            baremeControparte: $ctrl.cai.baremeControparte
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindCai();
                            $ctrl.isInputConsumed = true;
                        }

                        if (newValues.baremeAssicurato !== undefined && newValues.baremeControparte !== undefined) {
                            $ctrl.calcolaResponsabilita();
                        }

                    }, true
                );


            }])
    });

}());