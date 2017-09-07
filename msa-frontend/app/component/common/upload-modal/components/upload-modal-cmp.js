(function () {
    "use strict";

    app.component('msaUploadModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("uploadModalController", ['$rootScope', 'UtilSvc', 'DebugSvc', 'DomainSvc',
            function ($rootScope, UtilSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                $ctrl.file = undefined;

                $ctrl.$onInit = function () {};

                $ctrl.upload = function () {
                    DocumentiSvc.upload($ctrl.numeroSinistroProvvisorio, $ctrl.file).then(function (response) {
                        DebugSvc.log("salvaFile", response);

                    });
                };

                $ctrl.ok = function () {
                    $ctrl.close({$value: undefined});
                };

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };
            }])
    });

}());
 