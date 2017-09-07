(function () {
    "use strict";

    app.component('msaUploadModal', {
        templateUrl: '../../app/component/common/upload-modal/components/templates/upload-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("uploadModalController", ['$scope', '$rootScope', '$filter', '$timeout', 'UtilSvc', 'DebugSvc', 'DocumentiSvc', 'toastr',
            function ($scope, $rootScope, $filter, $timeout, UtilSvc, DebugSvc, DocumentiSvc, toastr) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $ctrl.file = undefined;
                $ctrl.error = false;
                $ctrl.success = false;
                $ctrl.inProgress = false;
                $ctrl.buttonsEnabled = true;

                $ctrl.numeroSinistroProvvisorio = undefined;

                $ctrl.$onInit = function () {
                    $ctrl.numeroSinistroProvvisorio = $ctrl.resolve.numeroSinistroProvvisorio;
                };

                $ctrl.upload = function () {
                    $ctrl.inProgress = true;
                    $ctrl.buttonsEnabled = false;
                    DocumentiSvc.upload($ctrl.numeroSinistroProvvisorio, $ctrl.file).then(function (response) {
                        DebugSvc.log("upload", response);
                        if (response.status === 200) { //TODO condizione moccata
                            toastr.success($translate('global.generic.saveok'));
                            $ctrl.success = true;
                            $timeout(function () {
                                $ctrl.ok(); //TODO restituire la roba.
                            }, 1500);
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                            $ctrl.error = true;
                            $timeout(function () {
                                $ctrl.cancel();
                            }, 1500);
                        }
                        $ctrl.inProgress = false;
                    });
                };

                $ctrl.ok = function () {
                    $ctrl.close({$value: undefined});
                };

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            file: JSON.stringify($ctrl.file)
                        };
                    },
                    function handleChanges(newValues, oldValues) {
                        DebugSvc.log("fileChanged", newValues);
                        $ctrl.error = false;
                    }, true
                );

            }])
    });

}());
 