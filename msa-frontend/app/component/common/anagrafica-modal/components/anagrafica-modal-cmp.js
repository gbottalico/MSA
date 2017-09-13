(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['_', '$rootScope', '$scope', '$debugMode', 'UtilSvc', 'DebugSvc', 'DomainSvc', 'RegexSvc',
            function (_, $rootScope, $scope, $debugMode, UtilSvc, DebugSvc, DomainSvc, RegexSvc) {

                var $ctrl = this;
                $ctrl.anagrafica = {
                    ruolo: undefined,
                    lesioni: false,
                    compagnia:undefined
                };
                $ctrl.ruoli = undefined;
                $ctrl.ruoliKeyValue = undefined;
                $ctrl.ruoloConLesioni = undefined;
                $ctrl.compagniaSelezionata = undefined;

                $scope.$debugMode = $debugMode;
                $scope.$regex = RegexSvc;

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {
                    // Per prendere input
                    $ctrl.hasRole = $ctrl.resolve.hasRole;
                    $ctrl.hasCompagnia = $ctrl.resolve.hasCompagnia;
                    $ctrl.anagrafica.tipoPersona = $ctrl.TipologiaEnum.FISICA;

                    if ($ctrl.hasRole) {
                        DomainSvc.getRuoli().then(function (response) {
                            $ctrl.ruoli = response.data.result;
                            $ctrl.ruoliKeyValue = UtilSvc.arrayWithIdToMap($ctrl.ruoli);
                        });
                    }


                };


                $ctrl.getCompagnie = function (desc) {
                    return DomainSvc.getCompagnie(desc).then(function (response) {
                        return response.data.result;
                    });
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

                $scope.$watch(

                    function watchScope(scope) {
                        return {
                            ruolo: $ctrl.anagrafica.ruolo,
                            compagnia: $ctrl.compagniaSelezionata
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.ruolo !== undefined) {
                            $ctrl.ruoloConLesioni = $ctrl.ruoliKeyValue[$ctrl.anagrafica.ruolo].lesioni;
                        }
                        if(newValues.compagnia!==undefined && newValues.compagnia!==oldValues.compagnia){
                            $ctrl.anagrafica.compagnia = $ctrl.compagniaSelezionata;
                        }

                    }, true
                );

                $ctrl.compagniaValid = function (compagnia) {
                    return (_.isObject(compagnia));

                }

                $ctrl.$valid = $ctrl.compagniaValid($ctrl.compagniaSelezionata);
            }])
    });

}());
 