(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope', 'UtilSvc', 'DebugSvc', 'DomainSvc',
            function ($rootScope, UtilSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                $ctrl.anagrafica = {};
                $ctrl.ruoli = undefined;

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {
                    // Per prendere input
                    $ctrl.hasRole = $ctrl.resolve.hasRole;
                    $ctrl.anagrafica.tipoPersona = $ctrl.TipologiaEnum.FISICA;

                    if($ctrl.hasRole) {
                        DomainSvc.getRuoli().then(function (response) {
                           $ctrl.ruoli = response.data.result;
                        });
                    }

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
 