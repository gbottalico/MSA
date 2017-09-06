(function () {
    "use strict";

    app.component('msaDocumenti', {
        templateUrl: '../../app/component/denuncia-sinistro/documenti/components/templates/documenti-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("documentiController", ['_', '$rootScope', '$scope', '$debugMode', '$filter', '$location','toastr', 'SinistriSvc', 'DebugSvc', 'DocumentiSvc',
            function (_, $rootScope, $scope, $debugMode, $filter, $location, toastr, SinistriSvc, DebugSvc, DocumentiSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $ctrl.mapId = 'M26';

                $scope.theFile = null;
                $ctrl.file = null;

                $ctrl.$onInit = function () {
                    parent.mappaCaricata($ctrl.mapId);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            file: $scope.theFile
                        };
                    },
                    function handleChanges(newValues, oldValues) {
                        DebugSvc.log("newFile", newValues.file);
                        $ctrl.file = newValues.file;
                    }, true
                );


            }])
    }).directive('bindFile', [function () {
        return {
            require: "ngModel",
            restrict: 'A',
            link: function ($scope, el, attrs, ngModel) {
                el.bind('change', function (event) {
                    ngModel.$setViewValue(event.target.files[0]);
                    $scope.$apply();
                });

                $scope.$watch(function () {
                    return ngModel.$viewValue;
                }, function (value) {
                    if (!value) {
                        el.val("");
                    }
                });
            }
        };
    }]);

}());