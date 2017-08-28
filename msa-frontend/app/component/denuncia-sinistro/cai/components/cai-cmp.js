(function () {
    "use strict";

    app.component('msaCai', {
        templateUrl: '../../app/component/denuncia-sinistro/cai/components/templates/cai-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("constatazioneAmichevoleController", ['$rootScope', '$scope', 'BaremesSvc',
            function ($rootScope, $scope, BaremesSvc) {

                var ctrl = this;

                ctrl.baremes = undefined;
                ctrl.baremeAssicurato = undefined;
                ctrl.baremeControparte = undefined;

                ctrl.responsabilita = {};
                ctrl.responsabilita.cliente = false;
                ctrl.responsabilita.controparte = false;
                ctrl.responsabilita.concorsuale = false;
                ctrl.responsabilita.nonClassificabile = false;

                ctrl.osservazioniAssicurato = undefined;
                ctrl.osservazioniControparte = undefined;


                BaremesSvc.getBaremes().then(function (response) {
                    ctrl.baremes = response.data.result;
                });

                ctrl.salvaCai = function () {

                    var bControparte = undefined;
                    var oControparte = undefined;

                    if (ctrl.tempSegnalazione.nveicoli > 1) {
                        bControparte = ctrl.baremeControparte;
                        oControparte = ctrl.osservazioniControparte;
                    } else {
                        bControparte = null;
                        oControparte = null;
                    }

                    BaremesSvc.saveBaremesAndGetResponsabilita(ctrl.numeroSinistroProvvisorio, ctrl.baremeAssicurato, bControparte, ctrl.osservazioniAssicurato, oControparte).then(function (response) {
                        if (response.data.result !== undefined && response.data.result !== null) {
                            ctrl.setResponsabilitaUI(response.data.result.responsabilita);
                        }
                    });
                };

                ctrl.setResponsabilitaUI = function (responsabilita) {

                    responsabilita = responsabilita.toUpperCase();

                    switch (responsabilita) {
                        case "NON CLASSIFICABILE":
                            ctrl.responsabilita.cliente = false;
                            ctrl.responsabilita.controparte = false;
                            ctrl.responsabilita.concorsuale = false;
                            ctrl.responsabilita.nonClassificabile = true;
                            break;
                        case "CLIENTE":
                            ctrl.responsabilita.cliente = true;
                            ctrl.responsabilita.controparte = false;
                            ctrl.responsabilita.concorsuale = false;
                            ctrl.responsabilita.nonClassificabile = false;
                            break;
                        case "CONTROPARTE":
                            ctrl.responsabilita.cliente = false;
                            ctrl.responsabilita.controparte = true;
                            ctrl.responsabilita.concorsuale = false;
                            ctrl.responsabilita.nonClassificabile = false;
                            break;
                        case "CONCORSUALE":
                            ctrl.responsabilita.cliente = false;
                            ctrl.responsabilita.controparte = false;
                            ctrl.responsabilita.concorsuale = true;
                            ctrl.responsabilita.nonClassificabile = false;
                            break;
                        default:
                            ctrl.responsabilita.cliente = false;
                            ctrl.responsabilita.controparte = false;
                            ctrl.responsabilita.concorsuale = false;
                            ctrl.responsabilita.nonClassificabile = false;
                            break;
                    }

                };

                ctrl.bindCai = function () {
                    ctrl.baremeAssicurato = ctrl.sinistroProvvisorio.cai.baremesCliente.id;
                    ctrl.osservazioniAssicurato = ctrl.sinistroProvvisorio.cai.noteCliente;

                    if (ctrl.sinistroProvvisorio.cai.baremesControparte !== undefined && ctrl.sinistroProvvisorio.cai.baremesControparte !== null) {
                        ctrl.baremeControparte = ctrl.sinistroProvvisorio.cai.baremesControparte.id;
                        ctrl.osservazioniControparte = ctrl.sinistroProvvisorio.cai.noteControparte;
                    }
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined) {
                            ctrl.bindCai();
                        }

                    }, true
                );


            }])
    });

}());