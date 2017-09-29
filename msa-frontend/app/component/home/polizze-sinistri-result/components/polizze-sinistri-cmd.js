(function () {
    "use strict";

    app.component('msaPolizzeSinistri', {
        templateUrl: '../../app/component/home/polizze-sinistri-result/components/templates/polizze-sinistri-tpl.html',
        bindings: {
            risultati: "<",
            valoriRicerca: "="
        },
        controller: ("polizzeSinistriController", ['_', '$MSAC', '$location', '$rootScope', '$scope', '$filter', '$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 'DebugSvc', '$uibModal', '$debugMode', 'SinistriSvc',
            function (_, $MSAC, $location, $rootScope, $scope, $filter, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage, DebugSvc, $uibModal, $debugMode, SinistriSvc) {

                var $ctrl = this;
                var parent = $scope.$parent;
                var $translate = $filter('translate');

                $scope.nuovoSinitroDisabled = false;

                $ctrl.numeroSinistroProvvisorio = undefined;
                $ctrl.polizze = [
                    {
                        "copertura": true,
                        "id": 601569,
                        "codErr": 0,
                        "errore": "",
                        "numPolizza": "PRP8821973",
                        "prodotto": null,
                        "dataCopertura": "2016-11-06T00:00:00+0100",
                        "dataScadenza": "2017-11-06T00:00:00+0100",
                        "frazionamento": "ANNUALE",
                        "dataImmatricolazione": "2009-07-23T00:00:00+0200",
                        "dataAcquisto": "2009-07-23T00:00:00+0200",
                        "classeVeicolo": "AUTOVETTURA",
                        "targa": "DW192RT",
                        "telaio": null,
                        "marca": "OPEL",
                        "modello": "Zafira 2Âª Serie",
                        "allestimento": "Zafira 1.6 ecoM 94 CV Cosmo",
                        "hp": 94,
                        "ql": 1590,
                        "cc": 1598,
                        "valoreAssicurato": 5200,
                        "tipoGuida": "Guida esperta - tutti i guidatori hanno piÃ¹ di 26 anni",
                        "progVeicolo": null,
                        "codfiscContraente": "NGLFBA53E16L736S",
                        "cognomeContraente": "Angelini",
                        "nomeContraente": "Fabio",
                        "indirizzoContraente": "Via Berchet 10/A",
                        "capContraente": "30175",
                        "cittaContraente": "Venezia",
                        "provinciaContraente": "VE",
                        "codfiscProprietario": "NGLFBA53E16L736S",
                        "cognomeProprietario": "Angelini",
                        "nomeProprietario": "Fabio",
                        "indirizzoProprietario": "Via Berchet 10/A",
                        "capProprietario": "30175",
                        "cittaProprietario": "Venezia",
                        "provinciaProprietario": "VE",
                        "codPacchetto": null,
                        "codPacchetto2": null,
                        "cellulare": "3881824740",
                        "email": "angelini.alvise@hotmail.it",
                        "brokerAgenzia": null,
                        "collabor": null,
                        "brokerAnagrafica": null,
                        "brokerIndirizzo": null,
                        "brokerCap": null,
                        "brokerCitta": null,
                        "brokerProvincia": null,
                        "utilizzatore": null,
                        "listaGaranzie": [{
                            "codice": "rca",
                            "descrizione": "rca",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 1.0E7,
                            "massimaleCose": 5000000.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "furto_incendio",
                            "descrizione": "furto_incendio",
                            "sommaAssicurata": 0.0,
                            "franchigia": 200.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "assistenza_stradale",
                            "descrizione": "assistenza_stradale",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_naturali",
                            "descrizione": "eventi_naturali",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_sociopolitici",
                            "descrizione": "eventi_sociopolitici",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }
                        ]
                    },
                    {
                        "copertura": true,
                        "id": 601569,
                        "codErr": 0,
                        "errore": "",
                        "numPolizza": "PRP8821973",
                        "prodotto": null,
                        "dataCopertura": "2016-11-06T00:00:00+0100",
                        "dataScadenza": "2017-11-06T00:00:00+0100",
                        "frazionamento": "ANNUALE",
                        "dataImmatricolazione": "2009-07-23T00:00:00+0200",
                        "dataAcquisto": "2009-07-23T00:00:00+0200",
                        "classeVeicolo": "AUTOVETTURA",
                        "targa": "DW192RT",
                        "telaio": null,
                        "marca": "OPEL",
                        "modello": "Zafira 2Âª Serie",
                        "allestimento": "Zafira 1.6 ecoM 94 CV Cosmo",
                        "hp": 94,
                        "ql": 1590,
                        "cc": 1598,
                        "valoreAssicurato": 5200,
                        "tipoGuida": "Guida esperta - tutti i guidatori hanno piÃ¹ di 26 anni",
                        "progVeicolo": null,
                        "codfiscContraente": "NGLFBA53E16L736S",
                        "cognomeContraente": "Angelini",
                        "nomeContraente": "Fabio",
                        "indirizzoContraente": "Via Berchet 10/A",
                        "capContraente": "30175",
                        "cittaContraente": "Venezia",
                        "provinciaContraente": "VE",
                        "codfiscProprietario": "NGLFBA53E16L736S",
                        "cognomeProprietario": "Angelini",
                        "nomeProprietario": "Fabio",
                        "indirizzoProprietario": "Via Berchet 10/A",
                        "capProprietario": "30175",
                        "cittaProprietario": "Venezia",
                        "provinciaProprietario": "VE",
                        "codPacchetto": null,
                        "codPacchetto2": null,
                        "cellulare": "3881824740",
                        "email": "angelini.alvise@hotmail.it",
                        "brokerAgenzia": null,
                        "collabor": null,
                        "brokerAnagrafica": null,
                        "brokerIndirizzo": null,
                        "brokerCap": null,
                        "brokerCitta": null,
                        "brokerProvincia": null,
                        "utilizzatore": null,
                        "listaGaranzie": [{
                            "codice": "rca",
                            "descrizione": "rca",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 1.0E7,
                            "massimaleCose": 5000000.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "furto_incendio",
                            "descrizione": "furto_incendio",
                            "sommaAssicurata": 0.0,
                            "franchigia": 200.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "assistenza_stradale",
                            "descrizione": "assistenza_stradale",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_naturali",
                            "descrizione": "eventi_naturali",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_sociopolitici",
                            "descrizione": "eventi_sociopolitici",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }
                        ]
                    },
                    {
                        "copertura": true,
                        "id": 601569,
                        "codErr": 0,
                        "errore": "",
                        "numPolizza": "PRP8821973",
                        "prodotto": null,
                        "dataCopertura": "2016-11-06T00:00:00+0100",
                        "dataScadenza": "2017-11-06T00:00:00+0100",
                        "frazionamento": "ANNUALE",
                        "dataImmatricolazione": "2009-07-23T00:00:00+0200",
                        "dataAcquisto": "2009-07-23T00:00:00+0200",
                        "classeVeicolo": "AUTOVETTURA",
                        "targa": "DW192RT",
                        "telaio": null,
                        "marca": "OPEL",
                        "modello": "Zafira 2Âª Serie",
                        "allestimento": "Zafira 1.6 ecoM 94 CV Cosmo",
                        "hp": 94,
                        "ql": 1590,
                        "cc": 1598,
                        "valoreAssicurato": 5200,
                        "tipoGuida": "Guida esperta - tutti i guidatori hanno piÃ¹ di 26 anni",
                        "progVeicolo": null,
                        "codfiscContraente": "NGLFBA53E16L736S",
                        "cognomeContraente": "Angelini",
                        "nomeContraente": "Fabio",
                        "indirizzoContraente": "Via Berchet 10/A",
                        "capContraente": "30175",
                        "cittaContraente": "Venezia",
                        "provinciaContraente": "VE",
                        "codfiscProprietario": "NGLFBA53E16L736S",
                        "cognomeProprietario": "Angelini",
                        "nomeProprietario": "Fabio",
                        "indirizzoProprietario": "Via Berchet 10/A",
                        "capProprietario": "30175",
                        "cittaProprietario": "Venezia",
                        "provinciaProprietario": "VE",
                        "codPacchetto": null,
                        "codPacchetto2": null,
                        "cellulare": "3881824740",
                        "email": "angelini.alvise@hotmail.it",
                        "brokerAgenzia": null,
                        "collabor": null,
                        "brokerAnagrafica": null,
                        "brokerIndirizzo": null,
                        "brokerCap": null,
                        "brokerCitta": null,
                        "brokerProvincia": null,
                        "utilizzatore": null,
                        "listaGaranzie": [{
                            "codice": "rca",
                            "descrizione": "rca",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 1.0E7,
                            "massimaleCose": 5000000.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "furto_incendio",
                            "descrizione": "furto_incendio",
                            "sommaAssicurata": 0.0,
                            "franchigia": 200.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "assistenza_stradale",
                            "descrizione": "assistenza_stradale",
                            "sommaAssicurata": 0.0,
                            "franchigia": 0.0,
                            "scoperto": 0.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_naturali",
                            "descrizione": "eventi_naturali",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }, {
                            "codice": "eventi_sociopolitici",
                            "descrizione": "eventi_sociopolitici",
                            "sommaAssicurata": 0.0,
                            "franchigia": 500.0,
                            "scoperto": 10.0,
                            "massimalePersone": 0.0,
                            "massimaleCose": 0.0,
                            "massimaleVeicolo": 0.0,
                            "dataCoperturaGaranzia": null,
                            "dataScadenzaGaranzia": null,
                            "isRcaConFranchigia": false
                        }
                        ]
                    }
                ];

                $ctrl.selezionaPolizza = function (polizza) {
                    if ($ctrl.polizzaSelected === polizza) {
                        $ctrl.polizzaSelected = undefined;
                    } else {
                        $ctrl.polizzaSelected = polizza;
                    }
                };

                $ctrl.dettaglioPolizza = function (polizza) {
                    //Qui bisogna cercare in base al numero di polizza
                    $ctrl.polizzaSelected = polizza;
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaDettaglioPolizzaModal',
                        resolve: {
                            polizza: function () {
                                return $ctrl.polizzaSelected;
                            }
                        }
                    });

                    modalInstance.result.then(function (dettagliopolizza) {
                        DebugSvc.log("dettaglioPolizza");
                    }, function () {
                        DebugSvc.log("dettaglio polizza chiuso dismiss.");
                    });

                };

                $ctrl.openSinistro = function () {

                    DebugSvc.log("Click nuovo sinistro");
                    if ($ctrl.polizzaSelected !== undefined) {

                        $scope.nuovoSinitroDisabled = true;

                        var contraente = {};
                        contraente.cf = $ctrl.polizzaSelected.codfiscContraente;
                        contraente.cognome = $ctrl.polizzaSelected.cognomeContraente;
                        contraente.mail = $ctrl.polizzaSelected.email;
                        contraente.nome = $ctrl.polizzaSelected.nomeContraente;
                        contraente.residenza = {};
                        contraente.residenza.cap = $ctrl.polizzaSelected.capContraente;
                        contraente.residenza.provincia = {};
                        contraente.residenza.provincia.siglaProv = $ctrl.polizzaSelected.provinciaContraente;
                        contraente.residenza.comune = {};
                        contraente.residenza.comune.descrizione = $ctrl.polizzaSelected.cittaContraente;
                        contraente.residenza.indirizzo = $ctrl.polizzaSelected.indirizzoContraente;
                        contraente.telefono = $ctrl.polizzaSelected.cellulare;
                        contraente.veicolo = $ctrl.polizzaSelected.classeVeicolo;
                        contraente.targa = $ctrl.polizzaSelected.targa;
                        contraente.targaEstera = false;
                        contraente.targaSpeciale = false;
                        $ctrl.apriSinistroProvvisorio(contraente, $ctrl.valoriRicerca.compagniaSelezionata.idCompagnia, $ctrl.polizzaSelected);
                    } else {
                        $ctrl.openAnagrafica($ctrl.valoriRicerca.compagniaSelezionata.idCompagnia);
                    }
                };

                $ctrl.openAnagrafica = function (compagnia) {
                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaAnagraficaModal',
                        resolve: {}
                    });

                    modalInstance.result.then(function (contraente, compagnia) {
                        DebugSvc.log("openAnagrafica", contraente);
                        contraente.compagnia = {id: "38"};
                        $ctrl.apriSinistroProvvisorio(contraente, compagnia, null);
                    }, function () {
                        DebugSvc.log("openAnagrafica dismiss.");
                    });
                };

                $ctrl.navigaSinistroProvvisorio = function (index) {
                    parent.navigaAlSinistro($ctrl.sinistriProvvisori[index].numSinistroProvv);
                };

                //FIXME rimuovere 37, mockup
                $ctrl.apriSinistroProvvisorio = function (datiContraente, codiceCompagnia, datiPolizza) {
                    codiceCompagnia = codiceCompagnia || 37;
                    SinistriSvc.apriSinistroProvvisorio(datiContraente, codiceCompagnia, datiPolizza, parent.getIdDocsMsa()).then(function (response) {
                        DebugSvc.log("apriSinistroProvvisorio", response);
                        if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                            $ctrl.numeroSinistroProvvisorio = response.data.result.numSinistroProvvisorio;
                            parent.navigaAlSinistro($ctrl.numeroSinistroProvvisorio)
                            toastr.success($translate('global.generic.saveok'));
                        } else {
                            toastr.error($translate('global.generic.saveko'));
                        }
                    });
                };

                $ctrl.$doCheck = function () {
                    DebugSvc.log('checkRisultati', $ctrl.risultati);
                    //$ctrl.polizze = $ctrl.risultati.polizze; //FIXME
                    $ctrl.sinistriProvvisori = $ctrl.risultati.sinistriProvvisori;
                };

            }])
    });

}());