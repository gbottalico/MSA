(function () {
    "use strict";

    app.component('msaTerzeParti', {
        templateUrl: '../../app/component/denuncia-sinistro/terze-parti/components/templates/terze-parti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("terzePartiController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$uibModal', 'toastr', 'SinistriSvc', 'DebugSvc', 'ConvertSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $uibModal, toastr, SinistriSvc, DebugSvc, ConvertSvc) {

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
                    }, function () {
                        DebugSvc.log("aggiungiTerzeParti dismiss.");
                    });
                };

                $ctrl.salvaTerzeParti = function () {
                    SinistriSvc.salvaTerzeParti($ctrl.numeroSinistroProvvisorio, $ctrl.terzeParti).then(function (response) {
                        DebugSvc.log("salvaTerzeParti", response);
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