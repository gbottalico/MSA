(function () {
    "use strict";

    app.component('msaAuto', {
        templateUrl: '../../app/component/common/danni-auto/components/templates/danni-auto-tpl.html',
        bindings: {
            auto: "=",
            input: "="
        },
        controller: ("autoController", ['$rootScope', '$scope',
            function ($rootScope, $scope) {

                var $ctrl = this;

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
                };

                $ctrl.indicatoretopcenter = function (lato) {
                    $ctrl.auto.topcenter = !lato;
                };
                $ctrl.indicatoretopright = function (lato) {
                    $ctrl.auto.topright = !lato;
                };
                $ctrl.indicatoremiddleleft = function (lato) {
                    $ctrl.auto.middleleft = !lato;
                };
                $ctrl.indicatoremiddleright = function (lato) {
                    $ctrl.auto.middleright = !lato;
                };
                $ctrl.indicatorebottomleft = function (lato) {
                    $ctrl.auto.bottomleft = !lato;
                };
                $ctrl.indicatorebottomcenter = function (lato) {
                    $ctrl.auto.bottomcenter = !lato;
                };
                $ctrl.indicatorebottomright = function (lato) {
                    $ctrl.auto.bottomright = !lato;
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            input: $ctrl.input
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.input !== undefined && newValues.input !== null && !$ctrl.isInputConsumed) {

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

                            $ctrl.isInputConsumed = true;

                        }

                    }, true
                );

            }])
    });

}());