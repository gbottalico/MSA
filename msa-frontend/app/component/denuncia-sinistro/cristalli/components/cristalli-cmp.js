(function () {
    "use strict";

    app.component('msaCristalli', {
        templateUrl: '../../app/component/cristalli/incendio-veicolo/components/templates/cristalli-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("cristalliController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location', '$timeout', 'toastr', 'SinistriSvc', 'DebugSvc', 'DomainSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, $timeout, toastr, SinistriSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M32';

                $ctrl.isInputConsumed = false;
                $ctrl.causeRotturaCristalli = undefined;

                DomainSvc.getCauseRotturaCristalli().then(function (response) {
                   $ctrl.causeRotturaCristalli = response.data.result;
                });

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
