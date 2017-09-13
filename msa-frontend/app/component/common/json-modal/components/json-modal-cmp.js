(function () {
    "use strict";

    app.component('msaJsonModal', {
        templateUrl: '../../app/component/common/json-modal/components/templates/json-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope', '$scope', '$debugMode', 'UtilSvc', 'DebugSvc', 'DomainSvc', 'RegexSvc',
            function ($rootScope, $scope, $debugMode, UtilSvc, DebugSvc, DomainSvc, RegexSvc) {
            var $ctrl = this;




                $ctrl.json = $ctrl.resolve.sinistroProvvisorio;

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };
            }])
    });

}());
 