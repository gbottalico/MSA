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
                var polizza = $ctrl.resolve.polizza;
                polizza.dataCopertura = UtilSvc.dateFormat($ctrl.dettagliopolizza.dataCopertura);
                polizza.dataScadenza = UtilSvc.dateFormat($ctrl.dettagliopolizza.dataScadenza);
                polizza.dataImmatricolazione = UtilSvc.dateFormat($ctrl.dettagliopolizza.dataImmatricolazione);
                $scope.polizza = polizza;


                //$ctrl.dettagliopolizza = $rootScope.polizza;
                DebugSvc.log($ctrl.dettagliopolizza);
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
 