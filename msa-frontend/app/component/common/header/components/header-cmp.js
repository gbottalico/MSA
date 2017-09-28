(function () {
    "use strict";

    app.component('msaHeader', {
        templateUrl: '../../app/component/common/header/components/templates/header-tpl.html',
        bindings: {},
        controller: ("headerController", ['$MSAC', '$location', '$ngBootbox', '$filter', 'DebugSvc', 'UtilSvc',
            function ($MSAC, $location, $ngBootbox, $filter, DebugSvc, UtilSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var homePath = $MSAC.PATHS.HOME;

                $ctrl.navigateHome = function () {
                    $location.search({});
                    $location.path(homePath);
                };

                $ctrl.reloadRoute = function () {
                    var path = $location.path();
                    if (homePath !== path) {
                        var opts = UtilSvc.buildBootBoxOptions(
                            $translate('global.generic.attenzione'),
                            $translate('global.generic.navigazionehome'),
                            function success() {
                                $ctrl.navigateHome();
                            }
                        );
                        $ngBootbox.customDialog(opts);
                    } else {
                        $ctrl.navigateHome();
                    }
                };

            }])
    });

}());
 