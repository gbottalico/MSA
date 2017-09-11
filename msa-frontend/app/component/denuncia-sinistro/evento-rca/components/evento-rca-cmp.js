(function () {
    "use strict";

    app.component('msaEventoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/evento-rca/components/templates/evento-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("eventoRcaController", ['_', '$rootScope', '$scope', '$filter', '$debugMode', '$location', 'toastr', 'SinistriSvc', 'DomainSvc', 'DebugSvc', 'PathSvc',
            function (_, $rootScope, $scope, $filter, $debugMode, $location, toastr, SinistriSvc, DomainSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M12';

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

                DomainSvc.getAutorita().then(function (response) {
                    $ctrl.autorita = response.data.result;
                });

                $ctrl.salvaEventoRca = function () {
                    SinistriSvc.salvaEventoRca($ctrl.numeroSinistroProvvisorio, $ctrl.eventoRca).then(function (response) {
                        DebugSvc.log("salvaEventoRca", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe();
                            toastr.success($translate('global.generic.saveok'));
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            collisione: $ctrl.eventoRca.collisione,
                            nveicoli: $ctrl.eventoRca.nveicoli
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if(!$ctrl.isInputConsumed) {
                            if (_.isObject(newValues.sinistroProvvisorio) && _.isObject(newValues.sinistroProvvisorio.eventoRca)) {
                                $ctrl.eventoRca.collisione = newValues.sinistroProvvisorio.eventoRca.collisione;
                                $ctrl.eventoRca.interventoAutorita = newValues.sinistroProvvisorio.eventoRca.interventoAutorita;
                                $ctrl.eventoRca.nveicoli = newValues.sinistroProvvisorio.eventoRca.numVeicoli;
                                $ctrl.eventoRca.autoritaIntervenuta = newValues.sinistroProvvisorio.eventoRca.codAutorita;
                                $ctrl.eventoRca.comandoAutorita = newValues.sinistroProvvisorio.eventoRca.comandoAutorita;
                                $ctrl.persistence.dataDenuncia = newValues.sinistroProvvisorio.eventoRca.dataDenuncia ? new Date(newValues.sinistroProvvisorio.eventoRca.dataDenuncia) : undefined;
                                $ctrl.isInputConsumed = true;
                            }
                        }

                        if(newValues.collisione !== undefined && newValues.collisione !== null && newValues.collisione === false) {
                            $ctrl.eventoRca.nveicoli = 1;
                        }

                        if(newValues.nveicoli !== undefined) {
                            $ctrl.tempSegnalazione.nveicoli = newValues.nveicoli;
                        }

                    }, true
                );


            }])
    });

}());