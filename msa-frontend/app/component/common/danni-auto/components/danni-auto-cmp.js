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
        controller: ("autoController", ['$rootScope', '$scope',
            function ($rootScope, $scope) {

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
                };
                $ctrl.indicatoretopcenter = function (lato) {
                    $ctrl.auto.topcenter = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatoretopright = function (lato) {
                    $ctrl.auto.topright = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatoremiddleleft = function (lato) {
                    $ctrl.auto.middleleft = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatoremiddleright = function (lato) {
                    $ctrl.auto.middleright = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatorebottomleft = function (lato) {
                    $ctrl.auto.bottomleft = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatorebottomcenter = function (lato) {
                    $ctrl.auto.bottomcenter = !lato;
                    $ctrl.hasError();
                };
                $ctrl.indicatorebottomright = function (lato) {
                    $ctrl.auto.bottomright = !lato;
                    $ctrl.hasError();
                };
                
                $ctrl.hasError = function () {
                    if(!$ctrl.required){
                        return true;
                    }
                    // TODO gestire validità della form
                    $ctrl.error = !($ctrl.auto.topleft || $ctrl.auto.topcenter ||
                    $ctrl.auto.topright || 
                    $ctrl.auto.middleleft || 
                    $ctrl.auto.middleright || 
                    $ctrl.auto.bottomleft || 
                    $ctrl.auto.bottomcenter || 
                    $ctrl.auto.bottomright);
                    return $ctrl.error;
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

                        if (newValues.input !== undefined && newValues.input !== null && !$ctrl.isInputConsumed) {
                            // todo fare un convert 
                            $ctrl.auto = {
                                topleft: newValues.input.adx,
                                topcenter: newValues.input.cdx,
                                topright: newValues.input.ddx,
                                middleleft: newValues.input.a,
                                middleright: newValues.input.d,
                                bottomleft: newValues.input.asx,
                                bottomcenter: newValues.input.csx,
                                bottomright: newValues.input.dsx

                            };

                            $ctrl.hasError();
                            $ctrl.isInputConsumed = true;

                        }

                    }, true
                );

            }])
    });

}());