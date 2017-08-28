(function () {
    "use strict";

    app.component('msaAuto', {
        templateUrl: '../../app/component/denuncia-sinistro/danni-auto/components/templates/danni-auto-tpl.html',
        bindings: {
            auto: "="
        },
        controller: ("autoController", ['$rootScope', '$scope',
            function ($rootScope, $scope) {

                var ctrl = this;

                ctrl.auto = {};
                ctrl.auto.topleft = false;
                ctrl.auto.topcenter = false;
                ctrl.auto.topright = false;
                ctrl.auto.middleleft = false;
                ctrl.auto.middleright = false;
                ctrl.auto.bottomleft = false;
                ctrl.auto.bottomcenter = false;
                ctrl.auto.bottomright = false;


                ctrl.indicatoretoplefta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.topleft = true;
                    } else {
                        ctrl.auto.topleft = false;
                    }
                };
                ctrl.indicatoretopcentera = function(lato) {
                    if (lato === false) {
                        ctrl.auto.topcenter = true;
                    } else {
                        ctrl.auto.topcenter = false;
                    }
                };
                ctrl.indicatoretoprighta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.topright = true;
                    } else {
                        ctrl.auto.topright = false;
                    }
                };
                ctrl.indicatoremiddlelefta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.middleleft = true;
                    } else {
                        ctrl.auto.middleleft = false;
                    }
                };
                ctrl.indicatoremiddlerighta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.middleright = true;
                    } else {
                        ctrl.auto.middleright = false;
                    }
                };
                ctrl.indicatorebottomlefta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.bottomleft = true;
                    } else {
                        ctrl.auto.bottomleft = false;
                    }
                };
                ctrl.indicatorebottomcentera = function(lato) {
                    if (lato === false) {
                        ctrl.auto.bottomcenter = true;
                    } else {
                        ctrl.auto.bottomcenter = false;
                    }
                };
                ctrl.indicatorebottomrighta = function(lato) {
                    if (lato === false) {
                        ctrl.auto.bottomright = true;
                    } else {
                        ctrl.auto.bottomright = false;
                    }
                };

        }])
    });

}());