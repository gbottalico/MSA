(function () {
    "use strict";

    app.component('msaHome', {
        templateUrl: '../../app/component/home/components/templates/home-tpl.html',
        bindings: {},
        controller: ("homeController", ['$MSAC', '$scope', '$filter', '$uibModal', '$location', 'toastr', 'DebugSvc', 'SinistriSvc',
            function ($MSAC, $scope, $filter, $uibModal, $location, toastr, DebugSvc, SinistriSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;

            }])
    });

}());
 