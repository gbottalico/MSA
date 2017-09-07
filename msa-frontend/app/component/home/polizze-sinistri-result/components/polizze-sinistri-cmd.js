(function () {
    "use strict";

    app.component('msaPolizzeSinistri', {
        templateUrl: '../../app/component/home/polizze-sinistri-result/components/templates/polizze-sinistri-tpl.html',
        bindings: {
            valoriRicerca: '=',
            bannerSearch: '=',
            bannerDenuncia: '='
        },
        controller: ("polizzeSinistriController", ['$rootScope','$scope', '$translate','$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 'DebugSvc','$uibModal',
            function ($rootScope,$scope, $translate, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage, DebugSvc, $uibModal) {

                var $ctrl = this;

                $ctrl.denuncia = function () {
                    ctrl.bannerSearch = false;
                    ctrl.bannerDenuncia = true;
                };
                $ctrl.clearbox = "clear box";
                $ctrl.polizze = [
                    {
                        numpoli: '100012058380',
                        attivazione: '22/02/2016',
                        scadenza: '22/02/2017',
                        prodotto: 'Prodotto viaggia con me (clear box)',
                        compagnia: 'Aviva',
                        targabene: 'df832xb',
                        contraente: 'Piras Dario',
                        stato: 'Attivo',
                        variazione: '18/02/2016',
                        sinistri: [
                            {
                                nome: 'Sinistro',
                                numsin: '100012058380',
                                data: '23/03/2016',
                                tipo: 'A',
                                evento: 'Amet',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Sinistro provvisorio',
                                numsin: '100017432380',
                                data: '20/05/2016',
                                tipo: 'B',
                                evento: 'Consectetur',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Pre-codifica',
                                numsin: '103212058363',
                                data: '03/01/2017',
                                tipo: 'C',
                                evento: 'Super alta',
                                denunciatoda: 'Anna Rossi',
                                stato: 'Attivo'
                            }
                        ]
                    },
                    {
                        numpoli: '100012058380',
                        attivazione: '22/02/2016',
                        scadenza: '22/02/2017',
                        prodotto: 'Prodotto viaggia con me (clear box)',
                        compagnia: 'Aviva',
                        targabene: 'df832xb',
                        contraente: 'Piras Dario',
                        stato: 'Attivo',
                        variazione: '18/02/2016',
                        sinistri: [
                            {
                                nome: 'Sinistro',
                                numsin: '100012058380',
                                data: '23/03/2016',
                                tipo: 'A',
                                evento: 'Amet',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Sinistro provvisorio',
                                numsin: '100017432380',
                                data: '20/05/2016',
                                tipo: 'B',
                                evento: 'Consectetur',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Pre-codifica',
                                numsin: '103212058363',
                                data: '03/01/2017',
                                tipo: 'C',
                                evento: 'Super alta',
                                denunciatoda: 'Anna Rossi',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Pre-codifica',
                                numsin: '103212058363',
                                data: '03/01/2017',
                                tipo: 'C',
                                evento: 'Super alta',
                                denunciatoda: 'Anna Rossi',
                                stato: 'Attivo'
                            }
                        ]
                    },
                    {
                        numpoli: '100012058380',
                        attivazione: '22/02/2016',
                        scadenza: '22/02/2017',
                        prodotto: 'Prodotto viaggia con me (clear box)',
                        compagnia: 'Aviva',
                        targabene: 'df832xb',
                        contraente: 'Piras Dario',
                        stato: 'Attivo',
                        variazione: '18/02/2016',
                        sinistri: [
                            {
                                nome: 'Sinistro',
                                numsin: '100012058380',
                                data: '23/03/2016',
                                tipo: 'A',
                                evento: 'Amet',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Sinistro provvisorio',
                                numsin: '100017432380',
                                data: '20/05/2016',
                                tipo: 'B',
                                evento: 'Consectetur',
                                denunciatoda: 'Piras Dario',
                                stato: 'Attivo'
                            },
                            {
                                nome: 'Pre-codifica',
                                numsin: '103212058363',
                                data: '03/01/2017',
                                tipo: 'C',
                                evento: 'Super alta',
                                denunciatoda: 'Anna Rossi',
                                stato: 'Attivo'
                            }
                        ]
                    }
                ];
                $ctrl.dettagliopolizza = {
                    "copertura" : true,
                    "id" : 601569,
                    "codErr" : 0,
                    "errore" : "",
                    "numPolizza" : "PRP8821973",
                    "prodotto" : "Prodotto viaggia con me (clear box)",
                    "dataCopertura" : "2016-11-06T00:00:00+0100",
                    "dataScadenza" : "2017-11-06T00:00:00+0100",
                    "frazionamento" : "ANNUALE",
                    "dataImmatricolazione" : "2009-07-23T00:00:00+0200",
                    "dataAcquisto" : "2009-07-23T00:00:00+0200",
                    "classeVeicolo" : "AUTOVETTURA",
                    "targa" : "DW192RT",
                    "telaio" : "TEL000044423AIO",
                    "marca" : "OPEL",
                    "modello" : "Zafira 2^A Serie",
                    "allestimento" : "Zafira 1.6 ecoM 94 CV Cosmo",
                    "hp" : 94,
                    "ql" : 1590,
                    "cc" : 1598,
                    "valoreAssicurato" : 5200,
                    "tipoGuida" : "Guida esperta - tutti i guidatori hanno piÃÂ¹ di 26 anni",
                    "progVeicolo" : null,
                    "codfiscContraente" : "NGLFBA53E16L736S",
                    "cognomeContraente" : "Angelini",
                    "nomeContraente" : "Fabio",
                    "indirizzoContraente" : "Via Berchet 10/A",
                    "capContraente" : "30175",
                    "cittaContraente" : "Venezia",
                    "provinciaContraente" : "VE",
                    "codfiscProprietario" : "NGLFBA53E16L736S",
                    "cognomeProprietario" : "Angelini",
                    "nomeProprietario" : "Fabio",
                    "indirizzoProprietario" : "Via Berchet 10/A",
                    "capProprietario" : "30175",
                    "cittaProprietario" : "Venezia",
                    "provinciaProprietario" : "VE",
                    "codPacchetto" : null,
                    "codPacchetto2" : null,
                    "cellulare" : "3881824740",
                    "email" : "angelini.alvise@hotmail.it",
                    "brokerAgenzia" : null,
                    "collabor" : null,
                    "brokerAnagrafica" : null,
                    "brokerIndirizzo" : null,
                    "brokerCap" : null,
                    "brokerCitta" : null,
                    "brokerProvincia" : null,
                    "utilizzatore" : null,
                    "listaGaranzie" : [{
                        "codice" : "rca",
                        "descrizione" : "rca",
                        "sommaAssicurata" : 0.0,
                        "franchigia" : 0.0,
                        "scoperto" : 0.0,
                        "massimalePersone" : 1.0E7,
                        "massimaleCose" : 5000000.0,
                        "massimaleVeicolo" : 0.0,
                        "dataCoperturaGaranzia" : null,
                        "dataScadenzaGaranzia" : null,
                        "isRcaConFranchigia" : false
                    }, {
                        "codice" : "furto_incendio",
                        "descrizione" : "furto incendio",
                        "sommaAssicurata" : 0.0,
                        "franchigia" : 200.0,
                        "scoperto" : 10.0,
                        "massimalePersone" : 0.0,
                        "massimaleCose" : 0.0,
                        "massimaleVeicolo" : 0.0,
                        "dataCoperturaGaranzia" : null,
                        "dataScadenzaGaranzia" : null,
                        "isRcaConFranchigia" : false
                    }, {
                        "codice" : "assistenza_stradale",
                        "descrizione" : "assistenza stradale",
                        "sommaAssicurata" : 0.0,
                        "franchigia" : 0.0,
                        "scoperto" : 0.0,
                        "massimalePersone" : 0.0,
                        "massimaleCose" : 0.0,
                        "massimaleVeicolo" : 0.0,
                        "dataCoperturaGaranzia" : null,
                        "dataScadenzaGaranzia" : null,
                        "isRcaConFranchigia" : false
                    }, {
                        "codice" : "eventi_naturali",
                        "descrizione" : "eventi naturali",
                        "sommaAssicurata" : 0.0,
                        "franchigia" : 500.0,
                        "scoperto" : 10.0,
                        "massimalePersone" : 0.0,
                        "massimaleCose" : 0.0,
                        "massimaleVeicolo" : 0.0,
                        "dataCoperturaGaranzia" : null,
                        "dataScadenzaGaranzia" : null,
                        "isRcaConFranchigia" : false
                    }, {
                        "codice" : "eventi_sociopolitici",
                        "descrizione" : "eventi sociopolitici",
                        "sommaAssicurata" : 0.0,
                        "franchigia" : 500.0,
                        "scoperto" : 10.0,
                        "massimalePersone" : 0.0,
                        "massimaleCose" : 0.0,
                        "massimaleVeicolo" : 0.0,
                        "dataCoperturaGaranzia" : null,
                        "dataScadenzaGaranzia" : null,
                        "isRcaConFranchigia" : false
                    }
                    ]
                };


                $ctrl.dettaglioPolizza = function (index ) {
//Qui bisogna cercare in base al numero di polizza


                    var modalInstance = $uibModal.open({
                        animation: true,
                        backdrop: 'static', // Evita che il modal sia chiuso cliccando sullo sfondo.
                        windowClass: 'msaModal',
                        size: 'lg',
                        component: 'msaDettaglioPolizzaModal',
                        resolve: {
                            polizza: function () {
                               return $ctrl.dettagliopolizza;
                            }
                        }
                    });

                    modalInstance.result.then(function (dettagliopolizza) {
                        DebugSvc.log("dettaglioPolizza" );
                    }, function () {
                        DebugSvc.log("dettaglio polizza chiuso dismiss.");
                    });

                }




            }])
    });

}());