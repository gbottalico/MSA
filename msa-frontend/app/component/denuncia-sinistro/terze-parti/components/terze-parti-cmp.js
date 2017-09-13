(function () {
    "use strict";

    app.component('msaTerzeParti', {
        templateUrl: '../../app/component/denuncia-sinistro/terze-parti/components/templates/terze-parti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("terzePartiController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$uibModal', '$timeout','toastr', 'SinistriSvc', 'DebugSvc', 'ConvertSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $uibModal, $timeout, toastr, SinistriSvc, DebugSvc, ConvertSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M18';

                $ctrl.presenzaTerzeParti = undefined;
                $ctrl.terzeParti = [];

                $ctrl.aggiungiTerzaParte = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            hasRole: function () {
                                return true;
                            }
                        }
                    });

                    modalInstance.result.then(function (terzaParte) {
                        DebugSvc.log("aggiungiTerzeParti", terzaParte);
                        $ctrl.terzeParti.push(terzaParte);
                        $scope.formTerzeParti.$setPristine(false);
                    }, function () {
                        DebugSvc.log("aggiungiTerzeParti dismiss.");
                    });
                };

                $ctrl.rimuoviTerzaParte = function (index) {
                    $ctrl.terzeParti.splice(index, 1);
                    $scope.formTerzeParti.$setPristine(false);
                };

                $ctrl.salvaTerzeParti = function () {
                    if($ctrl.presenzaTerzeParti === false) {
                        //Se dico che non ci sono terze parti quando la lista delle terze parti è già piena, la svuoto prima di salvare.
                        $ctrl.terzeParti = [];
                    }
                    SinistriSvc.salvaTerzeParti($ctrl.numeroSinistroProvvisorio, $ctrl.terzeParti).then(function (response) {
                        DebugSvc.log("salvaTerzeParti", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.formTerzeParti.$setPristine(true);
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId)
                });

                $ctrl.bindTerzeParti = function () {
                    $ctrl.terzeParti = [];
                    $ctrl.sinistroProvvisorio.dannoRca.terzeParti.forEach(function (element, index) {
                        $ctrl.terzeParti.push(ConvertSvc.dtoToAnagrafica(element));
                    });
                    $ctrl.presenzaTerzeParti = $ctrl.terzeParti.length > 0;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) &&
                            _.isObject(newValues.sinistroProvvisorio.dannoRca) &&
                            _.isObject(newValues.sinistroProvvisorio.dannoRca.terzeParti)) {

                            $ctrl.bindTerzeParti();

                        }

                    }, true
                );


            }])
    });

}());