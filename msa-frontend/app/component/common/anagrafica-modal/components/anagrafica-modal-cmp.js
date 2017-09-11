(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['$rootScope', '$scope', '$debugMode', 'UtilSvc', 'DebugSvc', 'DomainSvc',
            function ($rootScope, $scope, $debugMode, UtilSvc, DebugSvc, DomainSvc) {

                var $ctrl = this;
                $ctrl.anagrafica = {};
                $ctrl.ruoli = undefined;
                $scope.$debugMode = $debugMode;

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {
                    // Per prendere input
                    $ctrl.hasRole = $ctrl.resolve.hasRole;
                    $ctrl.hasCompagnia = $ctrl.resolve.hasCompagnia;
                    $ctrl.anagrafica.tipoPersona = $ctrl.TipologiaEnum.FISICA;

                    if($ctrl.hasRole) {
                        DomainSvc.getRuoli().then(function (response) {
                           $ctrl.ruoli = response.data.result;
                        });
                    }

                    if($ctrl.hasCompagnia) {
                        DomainSvc.getElencoRegole().then(function (response) {
                            $ctrl.casaRegole = response.data.result;
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
 