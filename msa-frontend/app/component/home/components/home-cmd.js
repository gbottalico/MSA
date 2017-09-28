(function () {
    "use strict";

    app.component('msaHome', {
        templateUrl: '../../app/component/home/components/templates/home-tpl.html',
        bindings: {},
        controller: ("homeController", ['_', '$MSAC', '$scope', '$filter', '$location', 'toastr', 'DebugSvc', 'SinistriSvc', '$sessionStorage',
            function (_, $MSAC, $scope, $filter, $location, toastr, DebugSvc, SinistriSvc, $sessionStorage) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$storage = $sessionStorage;
                $ctrl.ricercaPolizza = undefined;

                const DOC_ID_KEY = "docId";

                var qs = $location.search();
                DebugSvc.log("queryString", qs);

                var docIds = qs[DOC_ID_KEY];
                if(docIds !== undefined) {
                    docIds = _.isArray(docIds) ? docIds : [docIds];
                }
                $scope.$storage.idDocsMsa = docIds;
                DebugSvc.log("docIds", docIds);

                $scope.cerca = function(ricercaPolizza) {
                    $ctrl.ricercaPolizza = ricercaPolizza;
                    $scope.$storage.lastSearch = ricercaPolizza;
                };

                $scope.getIdDocsMsa = function () {
                    var ids = _.cloneDeep($scope.$storage.idDocsMsa);
                    $scope.$storage.idDocsMsa = undefined;
                    return ids;
                };

            }])
    });

}());
 