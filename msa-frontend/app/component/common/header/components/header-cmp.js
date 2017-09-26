(function () {
    "use strict";

    app.component('msaHeader', {
        templateUrl: '../../app/component/common/header/components/templates/header-tpl.html',
        bindings: {},
        controller: ("headerController", ['$MSAC', '$location', '$ngBootbox', 'DebugSvc',
            function ($MSAC, $location, $ngBootbox, DebugSvc) {

                var $ctrl = this;

                $ctrl.reloadRoute = function () {
                    var homePath = $MSAC.PATHS.HOME;
                    var path = $location.path();
                    if (homePath !== path) {
                        $ngBootbox.confirm('Sei sicuro di voler tornare alla homepage? i dati non salvati andranno persi.').then(function () {
                            //TODO formattare + numero sinistro.
                            $location.search({});
                            $location.path(homePath);
                        }, function () {/* NOPE */});
                    } else {
                        $location.search({});
                        $location.path(homePath);
                    }

                };

            }])
    });

}());
 