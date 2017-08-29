(function () {
    "use strict";

    app.component('msaEventoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/evento-rca/components/templates/evento-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("eventoRcaController", ['$rootScope', '$scope', 'SinistriSvc', 'AutoritaSvc', '$log',
            function ($rootScope, $scope, SinistriSvc, AutoritaSvc, $log) {

                var $ctrl = this;
                var parent = $scope.$parent;

                $ctrl.eventoRca = {};
                $ctrl.eventoRca.collisione = true;
                $ctrl.eventoRca.interventoAutorita = true;
                $ctrl.eventoRca.nveicoli = 2;
                $ctrl.eventoRca.autoritaIntervenuta = undefined;
                $ctrl.eventoRca.comandoAutorita = undefined;
                $ctrl.eventoRca.dataDenuncia = {};
                $ctrl.isInputConsumed = false;

                $ctrl.persistence = {
                    dataDenuncia: {}
                };

                AutoritaSvc.getAutorita().then(function (response) {
                    $ctrl.autorita = response.data.result;
                });

                $ctrl.salvaEventoRca = function () {
                    SinistriSvc.salvaEventoRca($ctrl.numeroSinistroProvvisorio, $ctrl.eventoRca).then(function (response) {
                        console.log(response);
                    });
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            nveicoli: $ctrl.eventoRca.nveicoli
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if(!$ctrl.isInputConsumed) {
                            if (newValues.sinistroProvvisorio !== undefined && newValues.sinistroProvvisorio.eventoRca !== undefined && newValues.sinistroProvvisorio.eventoRca !== null) {
                                $ctrl.eventoRca.collisione = newValues.sinistroProvvisorio.eventoRca.collisione;
                                $ctrl.eventoRca.interventoAutorita = newValues.sinistroProvvisorio.eventoRca.interventoAutorita;
                                $ctrl.eventoRca.nveicoli = newValues.sinistroProvvisorio.eventoRca.numVeicoli;
                                $ctrl.eventoRca.autoritaIntervenuta = newValues.sinistroProvvisorio.eventoRca.codAutorita;
                                $ctrl.eventoRca.comandoAutorita = newValues.sinistroProvvisorio.eventoRca.comandoAutorita;
                                $ctrl.persistence.dataDenuncia = new Date(newValues.sinistroProvvisorio.eventoRca.dataDenuncia);
                                $ctrl.isInputConsumed = true;
                            }
                        }


                        if(newValues.nveicoli !== undefined) {
                            $ctrl.tempSegnalazione.nveicoli = newValues.nveicoli;
                        }

                    }, true
                );


            }])
    });

}());