(function () {
    "use strict";

    app.component('msaConstatazioneAmichevole', {
        templateUrl: '../../app/component/denuncia-sinistro/constatazione-amichevole/components/templates/constatazione-amichevole-tpl.html',
        bindings: {},
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

                ctrl.setResponsabilitaUI = function (responsabilita) {

                    responsabilita = responsabilita.toUpperCase();

                    switch(responsabilita) {
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

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            bAssicurato: ctrl.baremeAssicurato,
                            bControparte: ctrl.baremeControparte,
                            oAssicurato: ctrl.osservazioniAssicurato,
                            oControparte: ctrl.osservazioniControparte
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if(newValues !== oldValues &&
                        newValues.bAssicurato !== undefined &&
                        newValues.bControparte !== undefined) {
                            //FIXME: rimuovere il 2, è mockup
                            BaremesSvc.saveBaremesAndGetResponsabilita(2, newValues.bAssicurato, newValues.bControparte, newValues.oAssicurato, newValues.oControparte).then(function (response) {
                                ctrl.setResponsabilitaUI(response.data.result.responsabilita);
                            })
                        }

                    }, true
                );


            }])
    });

}());