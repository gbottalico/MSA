(function () {
    "use strict";

    app.component('msaEventoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/evento-rca/components/templates/evento-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "=",
            callback: "@"
        },
        compile: function(element, attributes){

            return {
                pre: function(scope, element, attributes, controller, transcludeFn){
                    console.log("pre");
                },
                post: function(scope, element, attributes, controller, transcludeFn){
                    console.log("post");
                }
            }
        },
        controller: ("eventoRcaController", ['_', '$MSAC', '$rootScope', '$scope', '$filter', '$debugMode', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DomainSvc', 'DebugSvc', 'PathSvc',
            function (_, $MSAC, $rootScope, $scope, $filter, $debugMode, $location, $timeout, toastr, SinistriSvc, DomainSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.$MSAC = $MSAC;
                $ctrl.mapId = 'M12';

                $ctrl.eventoRca = {
                    collisione: undefined,
                    interventoAutorita: undefined,
                    nveicoli: undefined,
                    autoritaIntervenuta: undefined,
                    comandoAutorita: undefined,
                    dataDenuncia: {}
                };


                $ctrl.isInputConsumed = false;

                DomainSvc.getAutorita().then(function (response) {
                    $ctrl.autorita = response.data.result;
                });

                $ctrl.salvaEventoRca = function () {
                    SinistriSvc.salvaEventoRca($ctrl.numeroSinistroProvvisorio, $ctrl.eventoRca).then(function (response) {
                        DebugSvc.log("salvaEventoRca", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.eventoRcaForm.$setPristine();
                            $ctrl.tempSegnalazione.nveicoli = $ctrl.eventoRca.nveicoli;
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.$postLink = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            collisione: $ctrl.eventoRca.collisione,
                            nveicoli: $ctrl.eventoRca.nveicoli,
                            autorita: $ctrl.eventoRca.interventoAutorita
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (!$ctrl.isInputConsumed) {
                            if (_.isObject(newValues.sinistroProvvisorio) && _.isObject(newValues.sinistroProvvisorio.eventoRca)) {
                                $ctrl.eventoRca.collisione = newValues.sinistroProvvisorio.eventoRca.collisione;
                                $ctrl.eventoRca.interventoAutorita = newValues.sinistroProvvisorio.eventoRca.interventoAutorita;
                                $ctrl.eventoRca.nveicoli = newValues.sinistroProvvisorio.eventoRca.numVeicoli;
                                $ctrl.tempSegnalazione.nveicoli = $ctrl.eventoRca.nveicoli
                                $ctrl.eventoRca.autoritaIntervenuta = newValues.sinistroProvvisorio.eventoRca.codAutorita;
                                $ctrl.eventoRca.comandoAutorita = newValues.sinistroProvvisorio.eventoRca.comandoAutorita;
                                $ctrl.eventoRca.dataDenuncia = newValues.sinistroProvvisorio.eventoRca.dataDenuncia ? new Date(newValues.sinistroProvvisorio.eventoRca.dataDenuncia) : undefined;
                                $ctrl.isInputConsumed = true;
                            }
                        }

                        if (newValues.autorita !== undefined && newValues.autorita !== null && newValues.autorita === false) {
                            $ctrl.eventoRca.autoritaIntervenuta = undefined;
                            $ctrl.eventoRca.dataDenuncia = undefined;
                            $ctrl.eventoRca.comandoAutorita = undefined;
                        }

                        // if (newValues.nveicoli !== undefined) {
                        //     $ctrl.tempSegnalazione.nveicoli = newValues.nveicoli;
                        // }

                    }, true
                );


            }])
    });

}());