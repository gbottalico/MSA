(function () {
    "use strict";

    app.component('msaDannoRca', {
        templateUrl: '../../app/component/denuncia-sinistro/danno-rca/components/templates/danno-rca-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("dannoRcaContoller", ['$rootScope', '$scope', '$debugMode', '$filter', '$uibModal', '$timeout', 'toastr', 'DomainSvc', 'SinistriSvc', 'DebugSvc', 'UtilSvc',
            function ($rootScope, $scope, $debugMode, $filter, $uibModal, $timeout, toastr, DomainSvc, SinistriSvc, DebugSvc, UtilSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;

                $ctrl.mapId = 'M16';
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

                //TODO mockup
                $ctrl.dannoRca.controparti = [
                    {
                        "tipoPersona": "PF",
                        "residenza": {
                            "cap": "70025",
                            "nazione": {
                                "id": "1",
                                "inizioValidita": -2208988800000,
                                "fineValidita": null,
                                "descrizione": "ITALIA",
                                "sigla": "I",
                                "codFornitore": null
                            },
                            "provincia": {
                                "id": "5971b860f2f59717a813994c",
                                "codNazione": 1,
                                "codProvincia": 79,
                                "iniValidita": -2208988800000,
                                "finValidita": null,
                                "descProvincia": "BARI",
                                "siglaProv": "BA",
                                "codFornitore": null
                            },
                            "comune": {
                                "id": "597206bbf2f59737b8e25155",
                                "codNazione": "1",
                                "codProvincia": "79",
                                "codComune": "18510",
                                "fineValidita": null,
                                "descrizione": "GRUMO APPULA",
                                "codFornitore": null,
                                "cap": ["70025"]
                            },
                            "indirizzo": "Grumpppp!!"
                        },
                        "nascita": {
                            "data": {"date": "2017-08-24T22:00:00.000Z", "$valid": true},
                            "cap": "70020",
                            "$valid": true,
                            "nazione": {
                                "id": "1",
                                "inizioValidita": -2208988800000,
                                "fineValidita": null,
                                "descrizione": "ITALIA",
                                "sigla": "I",
                                "codFornitore": null
                            },
                            "provincia": {
                                "id": "5971b860f2f59717a813994c",
                                "codNazione": 1,
                                "codProvincia": 79,
                                "iniValidita": -2208988800000,
                                "finValidita": null,
                                "descProvincia": "BARI",
                                "siglaProv": "BA",
                                "codFornitore": null
                            },
                            "comune": {
                                "id": "597206bbf2f59737b8e25147",
                                "codNazione": "1",
                                "codProvincia": "79",
                                "codComune": "18491",
                                "fineValidita": null,
                                "descrizione": "BINETTO",
                                "codFornitore": null,
                                "cap": ["70020"]
                            }
                        },
                        "nome": "Dello Russo",
                        "cognome": "Corrado",
                        "sesso": "m",
                        "cf": "DLLCRD17M25A874G",
                        "telefono": "£45",
                        "mail": "@@@"
                    }
                ];

                $ctrl.bindDannoRca = function () {

                    $ctrl.dannoRca.lesioniConducente = $ctrl.sinistroProvvisorio.dannoRca.lesioniConducente;
                    if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== undefined && $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente !== null) {
                        $ctrl.persistence.dannoCliente = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.danni;
                        $ctrl.dannoRca.descrizioneDannoCliente = $ctrl.persistence.dannoCliente.descrizioneDanno;
                        if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica) {

                            $ctrl.dannoRca.conducenteIsNotContraente = true;

                            $timeout(function () {
                                //FIXME timeout di 1ms per triggerare il $digest
                                var anagrafica = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniCliente.anagrafica;

                                $ctrl.dannoRca.conducente.cognome = anagrafica.cognome;
                                $ctrl.dannoRca.conducente.nome = anagrafica.nome;
                                $ctrl.dannoRca.conducente.sesso = anagrafica.sesso;
                                $ctrl.dannoRca.conducente.cf = anagrafica.cf;

                                $ctrl.persistence.dataNascita = new Date(anagrafica.dataNascita);

                                var tempLuogo = {};
                                tempLuogo.idNazione = anagrafica.luogoNascita.codNazione;
                                tempLuogo.idProvincia = anagrafica.luogoNascita.codProvincia;
                                tempLuogo.idComune = anagrafica.luogoNascita.codComune;
                                tempLuogo.cap = anagrafica.luogoNascita.cap;
                                $ctrl.persistence.luogoNascita = tempLuogo;

                                $ctrl.dannoRca.conducente.telefono = anagrafica.tracking.telefono;
                                $ctrl.dannoRca.conducente.mail = anagrafica.tracking.mail;

                                tempLuogo = {};
                                tempLuogo.idNazione = anagrafica.tracking.nazione;
                                tempLuogo.idProvincia = anagrafica.tracking.provincia;
                                tempLuogo.idComune = anagrafica.tracking.comune;
                                tempLuogo.cap = anagrafica.tracking.cap;

                                $ctrl.persistence.residenza = tempLuogo;

                                $ctrl.dannoRca.conducente.indirizzo = anagrafica.tracking.indirizzo;

                            }, 1);


                        } else {
                            $ctrl.dannoRca.conducenteIsNotContraente = false;
                        }
                    }

                    if ($ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte !== undefined && $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte !== null) {

                        var firstElem = $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte[0];
                        $ctrl.persistence.dannoControparte = firstElem.danni;
                        $ctrl.dannoRca.descrizioneDannoControparte = firstElem.danni.descrizioneDanno;
                        $ctrl.dannoRca.veicoloControparte.veicolo = firstElem.anagrafica.veicolo;
                        $ctrl.dannoRca.veicoloControparte.targa = firstElem.anagrafica.targa;
                        $ctrl.dannoRca.veicoloControparte.estera = firstElem.anagrafica.targaEstera.toString();
                        $ctrl.dannoRca.veicoloControparte.speciale = firstElem.anagrafica.targaSpeciale.toString();

                        $ctrl.sinistroProvvisorio.dannoRca.anagraficaDanniControparte.forEach(function (element, index) {
                            $ctrl.dannoRca.controparti.push(element.anagrafica);
                        });

                    }

                };

                $ctrl.salvaDannoRca = function () {

                    var dannoRca = $ctrl.dannoRca;
                    if (!$ctrl.dannoRca.conducenteIsNotContraente) {
                        dannoRca.conducente = undefined;
                    }

                    SinistriSvc.salvaDannoRcaCliente($ctrl.numeroSinistroProvvisorio, dannoRca)
                        .then(function (response) {
                            DebugSvc.log("salvaDannoRcaCliente", response);
                            if (response.data.status === 200) {
                                return SinistriSvc.salvaDannoRcaControparti($ctrl.numeroSinistroProvvisorio, $ctrl.dannoRca);
                            } else {
                                return UtilSvc.createErrorStatePromise();
                            }
                        }).then(function (response) {
                        DebugSvc.log("salvaDannoRcaControparti", response);
                        if (response.data.status === 200) {
                            parent.aggiornaMappe();
                            toastr.success($translate('global.generic.saveok'));
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    })

                };

                $ctrl.aggiungiControparte = function () {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {
                            items: function () {
                                return [{id: 1}, {id: 2}];
                            }
                        }
                    });

                    modalInstance.result.then(function (controparte) {
                        DebugSvc.log("aggiungiControparte", controparte);
                        $ctrl.dannoRca.controparti.push(controparte);
                    }, function () {
                        DebugSvc.log("aggiungiControparte dismiss.");
                    });
                };

                $ctrl.calcolaCf = function () {
                    var luogoNascita = $ctrl.dannoRca.conducente.nascita.comune ?
                        $ctrl.dannoRca.conducente.nascita.comune.descrizione :
                        $ctrl.dannoRca.conducente.nascita.nazione.descrizione;

                    UtilSvc.calcolaCf($ctrl.dannoRca.conducente.cognome, $ctrl.dannoRca.conducente.nome, $ctrl.dannoRca.conducente.sesso, $ctrl.dannoRca.conducente.nascita.data.date, luogoNascita).then(function (response) {
                        DebugSvc.log("calcolaCf", response);
                        if (response.data.status === 200) {
                            $ctrl.dannoRca.conducente.cf = response.data.result;
                        } else {
                            toastr.error($translate('global.generic.cfko'));
                        }
                    });
                };

                $ctrl.isCalcolaCfDisabled = function () {
                    return !($ctrl.dannoRca.conducente &&
                        $ctrl.dannoRca.conducente.cognome &&
                        $ctrl.dannoRca.conducente.nome &&
                        $ctrl.dannoRca.conducente.sesso &&
                        $ctrl.dannoRca.conducente.nascita.data &&
                        $ctrl.dannoRca.conducente.nascita.data.$valid &&
                        $ctrl.dannoRca.conducente.nascita.$valid);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined && !$ctrl.isInputConsumed) {
                            $ctrl.bindDannoRca();
                            $ctrl.isInputConsumed = true;
                        }

                    }, true
                );


            }])
    });

}());