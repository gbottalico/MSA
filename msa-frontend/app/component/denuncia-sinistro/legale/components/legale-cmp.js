(function () {
    "use strict";

    app.component('msaLegale', {
        templateUrl: '../../app/component/denuncia-sinistro/legale/components/templates/legale-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("legaleController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$uibModal', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'ConvertSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $uibModal, $timeout, toastr, SinistriSvc, DebugSvc, ConvertSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M20';

                $ctrl.presenzaLegali = undefined;
                $ctrl.legali = [];

                $ctrl.aggiungiLegale = function (index) {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            hasRole: function () {
                                return false;
                            },
                            hasPatrocinante: function () {
                                return true;
                            },
                            numeroSinistroProvvisorio: function () {
                                return $ctrl.numeroSinistroProvvisorio;
                            },
                            input: function () {
                                try {
                                    $ctrl.legali[index].index = index;
                                } catch (err) {
                                }
                                return $ctrl.legali[index];
                            }
                        }
                    });

                    modalInstance.result.then(function (legale) {
                        DebugSvc.log("aggiungiLegale", legale);
                        if (_.isNumber(legale.index)) {
                            $ctrl.legali[legale.index] = legale;
                        } else {
                            $ctrl.legali.push(legale);
                        }
                        $scope.legaleForm.$setDirty();
                    }, function () {
                        DebugSvc.log("aggiungiLegale dismiss.");
                    });
                };

                $ctrl.rimuoviLegale = function (index) {
                    $ctrl.legali.splice(index, 1);
                    $scope.legaleForm.$setDirty();
                };

                $ctrl.salvaLegali = function () {
                    if($ctrl.presenzaLegali === false) {
                        //Se dico che non ci sono legali quando la lista dei legali è già piena, la svuoto prima di salvare.
                        $ctrl.legali = [];
                    }
                    SinistriSvc.salvaLegali($ctrl.numeroSinistroProvvisorio, $ctrl.legali).then(function (response) {
                        DebugSvc.log("salvaLegali", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.legaleForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $ctrl.bindLegali = function () {
                    $ctrl.legali = [];
                    $ctrl.sinistroProvvisorio.legali.forEach(function (element, index) {
                        $ctrl.legali.push(ConvertSvc.dtoToAnagrafica(element));
                    });
                    $ctrl.presenzaLegali = $ctrl.legali.length > 0;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) &&
                            _.isObject(newValues.sinistroProvvisorio.legali)) {

                            $ctrl.bindLegali();

                        }

                    }, true
                );


            }])
    });

}());