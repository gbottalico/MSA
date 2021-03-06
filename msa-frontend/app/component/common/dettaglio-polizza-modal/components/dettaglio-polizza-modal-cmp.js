(function () {
    "use strict";

    app.component('msaDettaglioPolizzaModal', {
        templateUrl: '../../app/component/common/dettaglio-polizza-modal/components/templates/dettaglio-polizza-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("dettaglioPolizzaModalController", ['$rootScope', '$scope', 'UtilSvc', 'DebugSvc', 'DomainSvc',
            function ($rootScope, $scope, UtilSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                $ctrl.polizza = $ctrl.resolve.polizza;

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };

            }])
    });
}());
 