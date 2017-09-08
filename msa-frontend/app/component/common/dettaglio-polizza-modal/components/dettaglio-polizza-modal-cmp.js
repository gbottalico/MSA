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
                $ctrl.clearbox = "clear box";
                $ctrl.polizza = $ctrl.resolve.polizza;
                $ctrl.polizza.dataCopertura = UtilSvc.dateFormat($ctrl.polizza.dataCopertura);
                $ctrl.polizza.dataScadenza = UtilSvc.dateFormat($ctrl.polizza.dataScadenza);
                $ctrl.polizza.dataImmatricolazione = UtilSvc.dateFormat($ctrl.polizza.dataImmatricolazione);


                //$ctrl.dettagliopolizza = $rootScope.polizza;
                DebugSvc.log($ctrl.polizza);
                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };

            }])
    });

    //TODO spostare in app.js
    //TODO si può sostituire col CSS @andrea
    app.filter('capitalize', function () {
        return function (input) {
            return (!!input) ? input.charAt(0).toUpperCase() + input.substr(1).toLowerCase() : '';
        };
    });
}());
 