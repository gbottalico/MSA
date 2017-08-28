(function () {
    "use strict";

    app.component('msaAutoDepre', {
        templateUrl: '../../app/component/denuncia-sinistro/auto/components/templates/auto-tpl.html',
        bindings: {},
        controller: ("autoControllerDepre", ['$rootScope', '$scope', 'SinistriSvc', 'UtilSvc',
            function ($rootScope, $scope, SinistriSvc, UtilSvc) {

                var ctrl = this;

                ctrl.toplefta = false;
                ctrl.topcentera = false;
                ctrl.toprighta = false;
                ctrl.middlelefta = false;
                ctrl.middlerighta = false;
                ctrl.bottomlefta = false;
                ctrl.bottomcentera = false;
                ctrl.bottomrighta = false;

                ctrl.topleftb = false;
                ctrl.topcenterb = false;
                ctrl.toprightb = false;
                ctrl.middleleftb = false;
                ctrl.middlerightb = false;
                ctrl.bottomleftb = false;
                ctrl.bottomcenterb = false;
                ctrl.bottomrightb = false;

                ctrl.indicatoretoplefta = function(lato) {
                    if (lato === false) {
                        ctrl.toplefta = true;
                    } else {
                        ctrl.toplefta = false;
                    }
                };
                ctrl.indicatoretopcentera = function(lato) {
                    if (lato === false) {
                        ctrl.topcentera = true;
                    } else {
                        ctrl.topcentera = false;
                    }
                };
                ctrl.indicatoretoprighta = function(lato) {
                    if (lato === false) {
                        ctrl.toprighta = true;
                    } else {
                        ctrl.toprighta = false;
                    }
                };
                ctrl.indicatoremiddlelefta = function(lato) {
                    if (lato === false) {
                        ctrl.middlelefta = true;
                    } else {
                        ctrl.middlelefta = false;
                    }
                };
                ctrl.indicatoremiddlerighta = function(lato) {
                    if (lato === false) {
                        ctrl.middlerighta = true;
                    } else {
                        ctrl.middlerighta = false;
                    }
                };
                ctrl.indicatorebottomlefta = function(lato) {
                    if (lato === false) {
                        ctrl.bottomlefta = true;
                    } else {
                        ctrl.bottomlefta = false;
                    }
                };
                ctrl.indicatorebottomcentera = function(lato) {
                    if (lato === false) {
                        ctrl.bottomcentera = true;
                    } else {
                        ctrl.bottomcentera = false;
                    }
                };
                ctrl.indicatorebottomrighta = function(lato) {
                    if (lato === false) {
                        ctrl.bottomrighta = true;
                    } else {
                        ctrl.bottomrighta = false;
                    }
                };

                ctrl.indicatoretopleftb = function(lato) {
                    if (lato === false) {
                        ctrl.topleftb = true;
                    } else {
                        ctrl.topleftb = false;
                    }
                };
                ctrl.indicatoretopcenterb = function(lato) {
                    if (lato === false) {
                        ctrl.topcenterb = true;
                    } else {
                        ctrl.topcenterb = false;
                    }
                };
                ctrl.indicatoretoprightb = function(lato) {
                    if (lato === false) {
                        ctrl.toprightb = true;
                    } else {
                        ctrl.toprightb = false;
                    }
                };
                ctrl.indicatoremiddleleftb = function(lato) {
                    if (lato === false) {
                        ctrl.middleleftb = true;
                    } else {
                        ctrl.middleleftb = false;
                    }
                };
                ctrl.indicatoremiddlerightb = function(lato) {
                    if (lato === false) {
                        ctrl.middlerightb = true;
                    } else {
                        ctrl.middlerightb = false;
                    }
                };
                ctrl.indicatorebottomleftb = function(lato) {
                    if (lato === false) {
                        ctrl.bottomleftb = true;
                    } else {
                        ctrl.bottomleftb = false;
                    }
                };
                ctrl.indicatorebottomcenterb = function(lato) {
                    if (lato === false) {
                        ctrl.bottomcenterb = true;
                    } else {
                        ctrl.bottomcenterb = false;
                    }
                };
                ctrl.indicatorebottomrightb = function(lato) {
                    if (lato === false) {
                        ctrl.bottomrightb = true;
                    } else {
                        ctrl.bottomrightb = false;
                    }
                };

        }])
    });

}());