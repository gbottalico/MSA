(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope', 'UtilSvc', 'DebugSvc',
            function ($rootScope, UtilSvc, DebugSvc) {

                var $ctrl = this;
                $ctrl.anagrafica = {};

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {
                    // Per prendere input
                    $ctrl.items = $ctrl.resolve.items;

                    $ctrl.anagrafica.tipoPersona = $ctrl.TipologiaEnum.FISICA;

                };

                $ctrl.calcolaCf = function () {
                    var luogoNascita = $ctrl.anagrafica.nascita.comune ?
                        $ctrl.anagrafica.nascita.comune.descrizione :
                        $ctrl.anagrafica.nascita.nazione.descrizione;

                    UtilSvc.calcolaCf($ctrl.anagrafica.cognome, $ctrl.anagrafica.nome, $ctrl.anagrafica.sesso, $ctrl.anagrafica.nascita.data.date, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        $ctrl.anagrafica.cf = response.data.result;
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    return !($ctrl.anagrafica &&
                        $ctrl.anagrafica.cognome &&
                        $ctrl.anagrafica.nome &&
                        $ctrl.anagrafica.sesso &&
                        $ctrl.anagrafica.nascita.data && $ctrl.anagrafica.nascita.data.$valid &&
                        $ctrl.anagrafica.nascita.$valid);
                };

                $ctrl.ok = function () {
                    $ctrl.close({$value: $ctrl.anagrafica});
                };

                $ctrl.cancel = function () {
                    $ctrl.dismiss({$value: undefined});
                };
            }])
    });

}());
 