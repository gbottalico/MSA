(function () {
    "use strict";

    app.component('msaAnagraficaModal', {
        templateUrl: '../../app/component/common/anagrafica-modal/components/templates/anagrafica-modal-tpl.html',
        bindings: {
            resolve: '<',
            close: '&',
            dismiss: '&'
        },
        controller: ("headerController", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', 'UtilSvc', 'DebugSvc', 'DomainSvc', 'RegexSvc', 'ConvertSvc', 'SinistriSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, UtilSvc, DebugSvc, DomainSvc, RegexSvc, ConvertSvc, SinistriSvc) {

                var $ctrl = this;
                $scope.$MSAC = $MSAC;

                $ctrl.anagrafica = {
                    ruolo: undefined,
                    lesioni: false,
                    compagnia: undefined
                };
                $ctrl.persistence = {};
                $ctrl.ruoli = [];
                $ctrl.anagraficheAssociabili = [];
                $ctrl.ruoliKeyValue = {};
                $ctrl.ruoloConLesioni = undefined;
                $ctrl.compagniaSelezionata = undefined;

                $scope.$debugMode = $debugMode;
                $scope.$regex = RegexSvc;

                $ctrl.TipologiaEnum = {
                    FISICA: "PF",
                    GIURIDICA: "PG"
                };

                $ctrl.$onInit = function () {

                    $ctrl.hasRole = $ctrl.resolve.hasRole;
                    $ctrl.hasCompagnia = $ctrl.resolve.hasCompagnia;
                    $ctrl.anagrafica.tipoPersona = $ctrl.TipologiaEnum.FISICA;

                    $ctrl.hasPatrocinante = $ctrl.resolve.hasPatrocinante;

                    if ($ctrl.hasRole || $ctrl.hasPatrocinante) {
                        DomainSvc.getRuoli().then(function (response) {
                            $ctrl.ruoli = response.data.result;
                            $ctrl.ruoliKeyValue = UtilSvc.arrayWithIdToMap($ctrl.ruoli);
                        });

                        $ctrl.hasAssociato = $ctrl.resolve.hasAssociato;

                        if ($ctrl.hasAssociato || $ctrl.hasPatrocinante) {
                            SinistriSvc.getAnagraficheAssociabili($ctrl.resolve.numeroSinistroProvvisorio).then(function (response) {
                                $ctrl.anagraficheAssociabili = response.data.result;
                            });
                        }

                    }

                    if ($ctrl.resolve.input) {
                        $ctrl.anagrafica = $ctrl.resolve.input;
                        $ctrl.persistence.luogoNascita = ConvertSvc.luogoToDTO($ctrl.resolve.input.nascita);
                        $ctrl.persistence.residenza = ConvertSvc.luogoToDTO($ctrl.resolve.input.residenza);

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

                    UtilSvc.calcolaCf($ctrl.anagrafica.nome, $ctrl.anagrafica.cognome, $ctrl.anagrafica.sesso, $ctrl.anagrafica.nascita.data, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        $ctrl.anagrafica.cf = response.data.result;
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    return !($ctrl.anagrafica &&
                        $ctrl.anagrafica.cognome &&
                        $ctrl.anagrafica.nome &&
                        $ctrl.anagrafica.sesso &&
                        $ctrl.anagrafica.nascita.data);
                };

                $ctrl.isAssociatoVisible = function () {
                    return $ctrl.hasPatrocinante ||
                        ($ctrl.hasAssociato &&
                            $ctrl.anagrafica.ruolo &&
                            $ctrl.ruoliKeyValue[$ctrl.anagrafica.ruolo] &&
                            $ctrl.ruoliKeyValue[$ctrl.anagrafica.ruolo].pdAss === 'ASS');
                };

                $ctrl.getNomeFromAssociato = function (associato) {
                    return (associato.nome ? associato.nome + " " + associato.cognome : associato.ragioneSociale) + (associato.cf ? ", " + associato.cf : "");
                };

                $ctrl.ok = function () {
                    if($ctrl.anagrafica.tipoPersona === 'PF') {
                        $ctrl.anagrafica.ragioneSociale = null;
                    } else {
                        $ctrl.anagrafica.cognome = null;
                        $ctrl.anagrafica.nome = null;
                        $ctrl.anagrafica.sesso = null;
                        $ctrl.anagrafica.nascita = null;
                    }
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

                        if (newValues.ruolo !== undefined && $ctrl.hasRole) {
                            $ctrl.ruoloConLesioni = $ctrl.ruoliKeyValue[$ctrl.anagrafica.ruolo].lesioni;
                        }
                        if (newValues.compagnia !== undefined && newValues.compagnia !== oldValues.compagnia) {
                            $ctrl.anagrafica.compagnia = $ctrl.compagniaSelezionata;
                        }

                    }, true
                );

                $ctrl.compagniaValid = function (compagnia) {
                    return (_.isObject(compagnia));
                };

                $ctrl.$valid = $ctrl.compagniaValid($ctrl.compagniaSelezionata);
            }])
    });

}());
 