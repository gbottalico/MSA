(function () {
    "use strict";

    app.component('msaHome', {
        templateUrl: '../../app/component/home/components/templates/home-tpl.html',
        bindings: {},
        controller: ("homeController", ['_', '$MSAC', '$scope', '$filter', '$location', 'toastr', 'DebugSvc', 'SinistriSvc', '$localStorage',
            function (_, $MSAC, $scope, $filter, $location, toastr, DebugSvc, SinistriSvc, $localStorage) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$storage = $localStorage;

                const DOC_ID_KEY = "docId";

                var qs = $location.search();
                DebugSvc.log("queryString", qs);

                var docIds = qs[DOC_ID_KEY];
                if(docIds !== undefined) {
                    docIds = _.isArray(docIds) ? docIds : [docIds];
                }
                $scope.$storage.idDocsMsa = docIds;
                DebugSvc.log("docIds", docIds);


                $scope.getIdDocsMsa = function () {
                    var ids = _.cloneDeep($scope.$storage.idDocsMsa);
                    $scope.$storage.idDocsMsa = undefined;
                    return ids;
                };

            }])
    });

}());
 