(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope',
            function ($rootScope) {

                var $ctrl = this;
                $ctrl.anagrafica = {};

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {
                    // Per prendere input
                    $ctrl.items = $ctrl.resolve.items;

                    $ctrl.anagrafica.tipologia = $ctrl.TipologiaEnum.FISICA;

                };

                $ctrl.ok = function () {
                    $ctrl.close({$value: $ctrl.anagrafica});
                };

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: "BAD"});
                };
            }])
    });

}());
 