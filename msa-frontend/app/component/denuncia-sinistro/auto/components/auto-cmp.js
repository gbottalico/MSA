(function () {
    "use strict";

    app.component('msaAuto', {
        templateUrl: '../../app/component/denuncia-sinistro/auto/components/templates/auto-tpl.html',
        bindings: {},
        controller: ("autoController", ['$rootScope', '$scope', 'SinistriSvc', 'UtilSvc',
            function ($rootScope, $scope, SinistriSvc, UtilSvc) {

                var ctrl = this;

        }])
    });

}());