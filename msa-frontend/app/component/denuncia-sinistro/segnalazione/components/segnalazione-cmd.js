(function () {
    "use strict";

    app.component('msaSegnalazione', {
        templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("segnalazioneController", ['_', '$MSAC', '$window', '$scope', '$rootScope', '$debugMode', '$filter', '$timeout', '$ngBootbox', 'toastr', 'DomainSvc', 'PlacesSvc', 'SinistriSvc', 'UtilSvc', 'RegexSvc', 'DebugSvc',
            function (_, $MSAC, $window, $scope, $rootScope, $debugMode, $filter, $timeout, $ngBootbox, toastr, DomainSvc, PlacesSvc, SinistriSvc, UtilSvc, RegexSvc, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.$regex = RegexSvc;
                $scope.$MSAC = $MSAC;

                $ctrl.mapId = "M11";

                // Variabile utilizzata per gestire gli input da DB che devono essere passati
                // alle componenti che richiedono ulteriori elaborazioni.
                $ctrl.persistence = {};
                $ctrl.persistence.luogo = undefined;
                $ctrl.persistence.dataDenuncia = undefined;
                $ctrl.persistence.dataSinistro = undefined;

                $ctrl.mezzicomunicazione = undefined;
                $ctrl.ruoli = undefined;

                // Inizializzazione delle variabili bindate ai campi della form;
                // Luogo non è gestita qui perché è gestita da msaPlaces;
                $ctrl.sinistro = {
                    segnalazione: {},
                    tracking: {},
                    provenienza: {},
                    luogo: {}
                };

                DomainSvc.getMezziComunicazione().then(function (response) {
                    $ctrl.mezzicomunicazione = response.data.result;
                });

                DomainSvc.getRuoli().then(function (response) {
                    $ctrl.ruoli = response.data.result;
                });

                $ctrl.apriSegnalazione = function () {
                    return SinistriSvc.apriSegnalazione($ctrl.numeroSinistroProvvisorio, $ctrl.sinistro).then(function (response) {
                        DebugSvc.log("apriSegnalazione", response);
                        if (response.data.status === 200) {
                            $ctrl.tempSegnalazione.tipoSinistro = response.data.result;
                            $ctrl.tempSegnalazione.garanzia = $ctrl.sinistro.garanzia;
                            $ctrl.tempSegnalazione.dataEvento = new Date($ctrl.sinistro.provenienza.dataSinistro);
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.segnalazioneForm.$setPristine();
                            return true;
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                            return false;
                        }
                    });
                };

                $ctrl.confermaSegnalazione = function () {
                    //TODO stile popup e stringhe scolpite
                    if (!$ctrl.tempSegnalazione.garanzia || $ctrl.tempSegnalazione.garanzia === $ctrl.sinistro.garanzia) {
                        $ctrl.apriSegnalazione();
                    } else {
                        var opts = UtilSvc.buildBootBoxOptions(
                            $translate('global.generic.attenzione'),
                            $translate('global.generic.cambiogaranzia'),
                            function success() {
                                var result = $ctrl.apriSegnalazione();
                                result.then(function (response) {
                                    if (response === true) {
                                        $window.location.reload();
                                    }
                                });
                            }
                        );
                        $ngBootbox.customDialog(opts);
                    }
                };

                $ctrl.bindSinitroProvvisorio = function (sinitroProvvisorio) {
                    if (_.isObject(sinitroProvvisorio.segnalazione)) {

                        $ctrl.sinistro.segnalazione.nome = sinitroProvvisorio.segnalazione.denunciante.nome;
                        $ctrl.sinistro.segnalazione.cognome = sinitroProvvisorio.segnalazione.denunciante.cognome;
                        $ctrl.sinistro.segnalazione.ruolo = sinitroProvvisorio.segnalazione.denunciante.codRuolo;

                        if (sinitroProvvisorio.segnalazione.denunciante.tracking) {
                            $ctrl.sinistro.tracking.cellulare = sinitroProvvisorio.segnalazione.denunciante.tracking.cellulare;
                            $ctrl.sinistro.tracking.email = sinitroProvvisorio.segnalazione.denunciante.tracking.mail;
                            $ctrl.sinistro.segnalazione.telefono = sinitroProvvisorio.segnalazione.denunciante.tracking.telefono;
                        }

                        $ctrl.sinistro.provenienza.mezzoComunicazione = sinitroProvvisorio.segnalazione.codMezzo;

                        if (sinitroProvvisorio.segnalazione.dataDenuncia) {
                            $ctrl.sinistro.provenienza.dataDenuncia = new Date(sinitroProvvisorio.segnalazione.dataDenuncia);
                        }

                        if (sinitroProvvisorio.segnalazione.dataOraSinistro) {
                            $ctrl.sinistro.provenienza.dataSinistro = new Date(sinitroProvvisorio.segnalazione.dataOraSinistro);
                        }

                        $ctrl.sinistro.provenienza.oraSinistro = sinitroProvvisorio.segnalazione.oraSinistro;

                        $ctrl.persistence.luogo = sinitroProvvisorio.segnalazione.luogoSinistro;

                        $ctrl.sinistro.luogo.indirizzo = sinitroProvvisorio.segnalazione.indirizzo;
                        $ctrl.sinistro.garanzia = sinitroProvvisorio.segnalazione.garanziaSelected;
                    } else {
                        // Imposto la data di oggi come data denuncia, e la data della ricerca (se presente) come data sinistro.
                        $ctrl.sinistro.provenienza.dataDenuncia = new Date();
                        if (_.isObject($ctrl.tempSegnalazione.lastSearch)) {
                            $ctrl.sinistro.provenienza.dataSinistro = new Date($ctrl.tempSegnalazione.lastSearch.dataEvento);
                        }

                    }

                };

                $ctrl.formatHour = function () {
                    if ($scope.segnalazioneForm.orasinistro.$valid && $ctrl.sinistro.provenienza.oraSinistro) {
                        var pieces = $ctrl.sinistro.provenienza.oraSinistro.split(":");
                        if (pieces[0].length === 1) {
                            var hh = parseInt(pieces[0]);
                            if (!_.isNaN(hh) && hh >= 0 && hh <= 9) {
                                var newTime = "0" + hh.toString() + ":" + pieces[1];
                                $ctrl.sinistro.provenienza.oraSinistro = newTime;
                            }
                        }
                    }
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (_.isObject(newValues.sinistroProvvisorio)) {
                            $ctrl.bindSinitroProvvisorio(newValues.sinistroProvvisorio);
                        }

                    }, true
                );

            }])
    });

}());