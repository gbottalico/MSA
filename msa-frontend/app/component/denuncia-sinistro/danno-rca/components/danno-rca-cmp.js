(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['_', '$MSAC', '$rootScope', '$scope', '$debugMode', '$filter', '$uibModal', '$timeout', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc', 'UtilSvc', 'ConvertSvc', 'RegexSvc',
            function (_, $MSAC, $rootScope, $scope, $debugMode, $filter, $uibModal, $timeout, toastr, DomainSvc, SinistriSvc, DebugSvc, UtilSvc, ConvertSvc, RegexSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                var TIPO_AUTOVEICOLO = 1; // todo spostare
                var TIPO_CICLOMOTORE = 6;
                var TIPO_MOTOCICLO = 5;
                $scope.$debugMode = $debugMode;
                $scope.$regex = RegexSvc;
                $scope.$MSAC = $MSAC;

                $ctrl.mapId = 'M15';
                $ctrl.isInputConsumed = false;
                $ctrl.persistence = {};

                $ctrl.dannoRca = {
                    dannoCliente: undefined,
                    danniControparte: undefined,
                    conducente: {},
                    veicoloControparte: {},
                    controparti: []
                };
                $ctrl.dannoRca.lesioniConducente = undefined;
                $ctrl.dannoRca.conducenteIsNotContraente = undefined;

                $ctrl.tipoVeicoli = undefined;

                DomainSvc.getTipoVeicoli().then(function (response) {
                    $ctrl.tipoVeicoli = response.data.result;
                });

                $ctrl.bindDannoRca = function () {
                    if (_.isObject($ctrl.sinistroProvvisorio.dannoRca)) {


                        $ctrl.dannoRca.lesioniConducente = $ctrl.sinistroProvvisorio.dannoRca.lesioniConducente;
                        $ctrl.dannoRca.conducenteIsNotContraente = $ctrl.sinistroProvvisorio.dannoRca.conducenteDiverso;
                        if (_.isObject($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente)) {
                            $ctrl.persistence.dannoCliente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.danni;
                            $ctrl.dannoRca.descrizioneDannoCliente = $ctrl.persistence.dannoCliente.descrizioneDanno;
                            if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica && $ctrl.dannoRca.conducenteIsNotContraente === true) {
                                //FIXME wrappare tutta la funzione e fare anche agli altri
                                var anagrafica = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica;

                                $ctrl.dannoRca.conducente.cognome = anagrafica.cognome;
                                $ctrl.dannoRca.conducente.nome = anagrafica.nome;
                                $ctrl.dannoRca.conducente.sesso = anagrafica.sesso;
                                $ctrl.dannoRca.conducente.cf = anagrafica.cf;
                                $ctrl.dannoRca.conducente.nascita = {};

                                if (anagrafica.dataNascita) {
                                    $ctrl.dannoRca.conducente.nascita.data = new Date(anagrafica.dataNascita);
                                }

                                var tempLuogo = {};

                                $timeout(function () {
                                    tempLuogo.idNazione = anagrafica.luogoNascita.codNazione;
                                    tempLuogo.idProvincia = anagrafica.luogoNascita.codProvincia;
                                    tempLuogo.idComune = anagrafica.luogoNascita.codComune;
                                    tempLuogo.cap = anagrafica.luogoNascita.cap;
                                    $ctrl.persistence.luogoNascita = tempLuogo;
                                });


                                $ctrl.dannoRca.conducente.telefono = anagrafica.tracking.telefono;
                                $ctrl.dannoRca.conducente.mail = anagrafica.tracking.mail;

                                $timeout(function () {
                                    tempLuogo.idNazione = anagrafica.tracking.nazione;
                                    tempLuogo.idProvincia = anagrafica.tracking.provincia;
                                    tempLuogo.idComune = anagrafica.tracking.comune;
                                    tempLuogo.cap = anagrafica.tracking.cap;
                                });


                                $ctrl.persistence.residenza = tempLuogo;

                                $ctrl.dannoRca.conducente.residenza = {};
                                $ctrl.dannoRca.conducente.residenza.indirizzo = anagrafica.tracking.indirizzo;

                                $ctrl.dannoRca.veicoloCliente = {};
                                if (anagrafica.veicolo) {
                                    $ctrl.dannoRca.veicoloCliente.veicolo = anagrafica.veicolo;
                                    $ctrl.dannoRca.veicoloCliente.targa = anagrafica.targa;
                                    $ctrl.dannoRca.veicoloCliente.speciale = anagrafica.targaEstera.toString();
                                    $ctrl.dannoRca.veicoloCliente.estera = anagrafica.targaSpeciale.toString();
                                }

                            } else {
                                $ctrl.dannoRca.conducenteIsNotContraente = false;
                            }


                        }

                        if (_.isObject($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte)) {

                            var firstElem = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte[0];
                            $ctrl.persistence.dannoControparte = firstElem.danni;
                            $ctrl.dannoRca.descrizioneDannoControparte = firstElem.danni.descrizioneDanno;
                            $ctrl.dannoRca.veicoloControparte.veicolo = firstElem.anagrafica.veicolo;
                            $ctrl.dannoRca.veicoloControparte.targa = firstElem.anagrafica.targa;
                            $ctrl.dannoRca.veicoloControparte.estera = firstElem.anagrafica.targaEstera.toString();
                            $ctrl.dannoRca.veicoloControparte.speciale = firstElem.anagrafica.targaSpeciale.toString();


                            $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte.forEach(function (element, index) {
                                $ctrl.dannoRca.controparti.push(ConvertSvc.dtoToAnagrafica(element.anagrafica));
                            });

                        }

                        $timeout(function () {
                            $scope.dannoRcaForm.$setPristine();
                        });


                    }
                };

                $ctrl.selectPattern = (function () {
                    return {
                        test: function () {
                            if ($ctrl.dannoRca.veicoloControparte.veicolo === TIPO_AUTOVEICOLO) {
                                return RegexSvc.getTargaRegex().test($ctrl.dannoRca.veicoloControparte.veicolo);
                            }
                            else if ($ctrl.dannoRca.veicoloControparte.veicolo === TIPO_CICLOMOTORE) {
                                return RegexSvc.getCiclomotoreRegex().test($ctrl.dannoRca.veicoloControparte.veicolo);
                            }
                            else if ($ctrl.dannoRca.veicoloControparte.veicolo === TIPO_MOTOCICLO) {
                                return RegexSvc.getMotocicloRegex().test($ctrl.dannoRca.veicoloControparte.veicolo);
                            }
                            else return true;
                        }
                    }
                })();

                $ctrl.salvaDannoRca = function () {

                    var dannoRca = $ctrl.dannoRca;
                    if (!$ctrl.dannoRca.conducenteIsNotContraente) {
                        dannoRca.conducente = undefined;
                    }

                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, dannoRca)
                        .then(function (response) {
                            DebugSvc.log("salvaDannoRcaCliente", response);
                            if (response.data.status === 200) {
                                if ($ctrl.tempSegnalazione.nveicoli > 1) {
                                    return SinistriSvc.salvaDannoRcaControparti($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca);
                                } else {
                                    return UtilSvc.createSuccessStatePromise()
                                }
                            } else {
                                return UtilSvc.createErrorStatePromise();
                            }
                        }).then(function (response) {
                        DebugSvc.log("salvaDannoRcaControparti", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe($ctrl.mapId);
                            toastr.success($translate('global.generic.saveok'));
                            $scope.dannoRcaForm.$setPristine();
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });

                };

                $ctrl.aggiungiControparte = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            hasCompagnia: function () {

                                return true;
                            }

                        }
                    });

                    modalInstance.result.then(function (controparte) {
                        DebugSvc.log("aggiungiControparte", controparte);
                        $ctrl.dannoRca.controparti.push(controparte);
                        $scope.dannoRcaForm.$setDirty();
                    }, function () {
                        DebugSvc.log("aggiungiControparte dismiss.");
                    });
                };

                $ctrl.rimuoviControparte = function (index) {
                    $ctrl.dannoRca.controparti.splice(index, 1);
                    $scope.dannoRcaForm.$setDirty();
                };

                $ctrl.calcolaCf = function () {
                    var luogoNascita = $ctrl.dannoRca.conducente.nascita.comune ?
                        $ctrl.dannoRca.conducente.nascita.comune.descrizione :
                        $ctrl.dannoRca.conducente.nascita.nazione.descrizione;

                    UtilSvc.calcolaCf($ctrl.dannoRca.conducente.cognome, $ctrl.dannoRca.conducente.nome, $ctrl.dannoRca.conducente.sesso, $ctrl.dannoRca.conducente.nascita.data, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        if (response.data.status === 200) {
                            $ctrl.dannoRca.conducente.cf = response.data.result;
                        } else {
                            toastr.error($translate('global.generic.cfko'));
                        }
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    try {
                        return !($ctrl.dannoRca.conducente &&
                            $ctrl.dannoRca.conducente.cognome &&
                            $ctrl.dannoRca.conducente.nome &&
                            $ctrl.dannoRca.conducente.sesso &&
                            $ctrl.dannoRca.conducente.nascita.data);
                    } catch (error) {
                        return false;
                    }
                };

                $timeout(function () {
                    parent.mappaCaricata($ctrl.mapId);
                });

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio,
                            targa: $ctrl.dannoRca.veicoloControparte.targa,
                            danniCliente: $ctrl.dannoRca.dannoCliente,
                            danniControparte: $ctrl.dannoRca.danniControparte
                        };
                    },
                    function handleChanges(newValues, oldValues) {


                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindDannoRca();
                            $ctrl.isInputConsumed = true;
                        }

                        if (newValues.targa !== undefined) {
                            $ctrl.tempSegnalazione.targa = newValues.targa;
                        }

                        if ((_.isObject(newValues.danniCliente) && newValues.danniCliente !== oldValues.danniCliente) ||
                            (_.isObject(newValues.danniControparte) && newValues.danniControparte !== oldValues.danniControparte)) {
                            // $scope.dannoRcaForm.$setDirty();
                        }

                    }, true
                );


            }])
    });

}());