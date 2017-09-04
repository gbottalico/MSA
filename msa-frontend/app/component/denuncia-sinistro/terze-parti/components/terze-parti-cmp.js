(function () {
    "use strict";

    app.component('msaTerzeParti', {
        templateUrl: '../../app/component/denuncia-sinistro/terze-parti/components/templates/terze-parti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("terzePartiController", ['$rootScope', '$scope', '$debugMode', '$filter', '$location', '$uibModal', 'toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc',
            function ($rootScope, $scope, $debugMode, $filter, $location, $uibModal, toastr, SinistriSvc, DebugSvc, PathSvc) {

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
                            items: function () {
                                return [{id: 1}, {id: 2}];
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

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined) {

                        }

                    }, true
                );


            }])
    });

}());