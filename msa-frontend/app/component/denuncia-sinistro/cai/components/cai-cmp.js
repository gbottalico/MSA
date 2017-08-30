(function () {
    "use strict";

    app.component('msaCai', {
        templateUrl: '../../app/component/denuncia-sinistro/cai/components/templates/cai-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['$rootScope', '$scope', '$debugMode', 'DomainSvc', 'SinistriSvc',
            function ($rootScope, $scope, $debugMode, DomainSvc, SinistriSvc) {

                var $ctrl = this;
                $scope.$debugMode = $debugMode;

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


                $ctrl.isInputConsumed = false;

                DomainSvc.getBaremes().then(function (response) {
                    $ctrl.baremes = response.data.result;
                });

                $ctrl.salvaCai = function () {

                    SinistriSvc.saveCaiAndGetResponsabilita($ctrl.numeroSinistroProvvisorio, $ctrl.cai, $ctrl.tempSegnalazione.nveicoli).then(function (response) {
                        if (response.data.result !== undefined && response.data.result !== null) {
                            $ctrl.setResponsabilitaUI(response.data.result.responsabilita);
                        }
                    });

                };

                $ctrl.calcolaResponsabilita = function () {
                    SinistriSvc.getResponsabilita($ctrl.cai.baremeAssicurato, $ctrl.cai.baremeControparte).then(function (response) {
                        if (response.data.result !== undefined && response.data.result !== null) {
                            $ctrl.setResponsabilitaUI(response.data.result);
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
                    if ($ctrl.sinistroProvvisorio.cai !== undefined && $ctrl.sinistroProvvisorio.cai !== null) {

                        $ctrl.cai.baremeAssicurato = $ctrl.sinistroProvvisorio.cai.baremesCliente.id;
                        $ctrl.cai.osservazioniAssicurato = $ctrl.sinistroProvvisorio.cai.noteCliente;

                        if ($ctrl.sinistroProvvisorio.cai.baremesControparte !== undefined && $ctrl.sinistroProvvisorio.cai.baremesControparte !== null) {
                            $ctrl.cai.baremeControparte = $ctrl.sinistroProvvisorio.cai.baremesControparte.id;
                            $ctrl.cai.osservazioniControparte = $ctrl.sinistroProvvisorio.cai.noteControparte;
                        }
                    }
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