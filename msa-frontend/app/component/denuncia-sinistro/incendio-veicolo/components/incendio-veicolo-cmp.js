(function () {
    "use strict";

    app.component('msaIncendioVeicolo', {
        templateUrl: '../../app/component/denuncia-sinistro/incendio-veicolo/components/templates/incendio-veicolo-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("incendioVeicoloController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'PathSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, PathSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M27';

                $ctrl.isInputConsumed = false;
                $ctrl.incendio = {};


                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio) && !$ctrl.isInputConsumed) {

                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());