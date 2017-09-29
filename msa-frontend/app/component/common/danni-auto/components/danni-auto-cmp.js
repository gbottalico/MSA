(function () {
    "use strict";

    app.component('msaAuto', {
        templateUrl: '../../app/component/common/danni-auto/components/templates/danni-auto-tpl.html',
        bindings: {
            auto: "=",
            input: "=",
            required: "<",
            name: "<"
        },
        controller: ("autoController", ['_', '$rootScope', '$scope', '$timeout', 'ConvertSvc',
            function (_, $rootScope, $scope, $timeout, ConvertSvc) {

                var $ctrl = this;
                $ctrl.error = false;
                $scope.name = $ctrl.name || "auto" + Date.now();


                $ctrl.auto = {};
                $ctrl.auto.topleft = false;
                $ctrl.auto.topcenter = false;
                $ctrl.auto.topright = false;
                $ctrl.auto.middleleft = false;
                $ctrl.auto.middleright = false;
                $ctrl.auto.bottomleft = false;
                $ctrl.auto.bottomcenter = false;
                $ctrl.auto.bottomright = false;

                $ctrl.isInputConsumed = false;

                $ctrl.indicatoretopleft = function (lato) {
                    $ctrl.auto.topleft = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatoretopcenter = function (lato) {
                    $ctrl.auto.topcenter = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatoretopright = function (lato) {
                    $ctrl.auto.topright = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatoremiddleleft = function (lato) {
                    $ctrl.auto.middleleft = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatoremiddleright = function (lato) {
                    $ctrl.auto.middleright = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatorebottomleft = function (lato) {
                    $ctrl.auto.bottomleft = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatorebottomcenter = function (lato) {
                    $ctrl.auto.bottomcenter = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.indicatorebottomright = function (lato) {
                    $ctrl.auto.bottomright = !lato;
                    $ctrl.hasError();
                    $scope[$scope.name].$setDirty();
                };

                $ctrl.hasError = function () {
                    var error;
                    if (!$ctrl.required) {
                        error = false;
                    } else {
                        error = !($ctrl.auto.topleft || $ctrl.auto.topcenter ||
                            $ctrl.auto.topright ||
                            $ctrl.auto.middleleft ||
                            $ctrl.auto.middleright ||
                            $ctrl.auto.bottomleft ||
                            $ctrl.auto.bottomcenter ||
                            $ctrl.auto.bottomright);
                    }

                    $scope[$scope.name].$setValidity($scope.name, !error);
                    $ctrl.error = error;
                };

                $ctrl.$onInit = function () {
                    $ctrl.hasError();
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            input: $ctrl.input
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (!$ctrl.isInputConsumed && _.isObject(newValues.input)) {
                            // todo eventualmente spostarlo fuori di qui. Non urgente.
                            $ctrl.auto = ConvertSvc.dtoToDanniAuto(newValues.input);
                            $ctrl.hasError();
                            $ctrl.isInputConsumed = true;

                        }

                    }, true
                );

            }])
    });

}());