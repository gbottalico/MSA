angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        'DebugSvc',
        function ($http, msaServicesApiUrls, UtilSvc, DebugSvc) {

            var $svc = this;
            var getOggettoRicerca = function () {

                return {
                    "cognome": undefined,
                    "nome": undefined,
                    "tipoPersona": undefined,
                    "numeroPolizza": undefined,
                    "numeroSinistro": undefined,
                    "dataEvento": undefined,
                    "targa": undefined,
                    "numeroProvvisorio": undefined,
                    "numeroPreapertura": undefined,
                    "compagnia": undefined
                };

            };

            $svc.apriSinistroProvvisorio = function (datiContraente, compagnia) {

                var dataObj = {};
                dataObj.compagnia = compagnia;

                /* TODO: Comune a tutti, creare metodo */
                dataObj.contraente = {};
                dataObj.contraente.nome = datiContraente.nome;
                dataObj.contraente.cognome = datiContraente.cognome;
                dataObj.contraente.ragioneSociale = datiContraente.ragioneSociale;
                dataObj.contraente.cf = datiContraente.cf;

                dataObj.contraente.luogoNascita = {};
                if (UtilSvc.isDefined(datiContraente.nascita.nazione)) {
                    dataObj.contraente.luogoNascita.codNazione = datiContraente.nascita.nazione.id;
                    dataObj.contraente.luogoNascita.descrizioneNazione = datiContraente.nascita.nazione.descrizione;
                }
                if (UtilSvc.isDefined(datiContraente.nascita.provincia)) {
                    dataObj.contraente.luogoNascita.codProvincia = datiContraente.nascita.provincia.codProvincia;
                    dataObj.contraente.luogoNascita.descrizioneProvincia = datiContraente.nascita.provincia.desProv;
                }
                if (UtilSvc.isDefined(datiContraente.nascita.comune)) {
                    dataObj.contraente.luogoNascita.codComune = datiContraente.nascita.comune.codComune;
                    dataObj.contraente.luogoNascita.descrizioneComune = datiContraente.nascita.comune.descrizione;
                }

                dataObj.contraente.dataNascita = datiContraente.nascita.data.date;

                dataObj.contraente.tracking = {};
                if (UtilSvc.isDefined(datiContraente.residenza.nazione)) {
                    dataObj.contraente.tracking.nazione = datiContraente.residenza.nazione.id;
                }
                if (UtilSvc.isDefined(datiContraente.residenza.provincia)) {
                    dataObj.contraente.tracking.provincia = datiContraente.residenza.provincia.codProvincia;
                }
                if (UtilSvc.isDefined(datiContraente.residenza.comune)) {
                    dataObj.contraente.tracking.comune = datiContraente.residenza.comune.codComune;
                }

                dataObj.contraente.tracking.indirizzo = datiContraente.residenza.indirizzo;
                dataObj.contraente.tracking.telefono = datiContraente.telefono;
                dataObj.contraente.tracking.mail = datiContraente.mail;

                //TODO fix
                return $http({
                    method: 'PUT',
                    url: msaServicesApiUrls.aperturasinitro,
                    data: dataObj,
                    headers: {
                        "Content-Type": "application/json",
                        "user": '{"idUser": 1,"amministratore": true}'
                    }
                });

            };

            $svc.cercaSinistroProvvisorio = function (numeroSinistroProvvisorio) {

                var dataObj = getOggettoRicerca();
                dataObj.numeroProvvisorio = numeroSinistroProvvisorio;

                var stringUrl = UtilSvc.stringFormat(msaServicesApiUrls.ricercaprovvisorio, numeroSinistroProvvisorio);

                //TODO fix rimuovere login e idCompagnia
                return $http({
                    method: 'GET',
                    url: stringUrl,
                    data: dataObj,
                    headers: {
                        "Content-Type": "application/json",
                        "user": '{"idUser": 1,"amministratore": true}'
                    }
                });

            };

            $svc.apriSegnalazione = function (idSinistroProvvisorio, datiSegnalazione) {

                var data = {};
                data.denunciante = {};

                data.denunciante.nome = datiSegnalazione.segnalazione.nome;
                data.denunciante.cognome = datiSegnalazione.segnalazione.cognome;
                data.denunciante.telefono = datiSegnalazione.segnalazione.telefono;
                data.denunciante.codRuolo = datiSegnalazione.segnalazione.ruolo;

                data.denunciante.tracking = {};
                data.denunciante.tracking.cellulare = datiSegnalazione.tracking.cellulare;
                data.denunciante.tracking.mail = datiSegnalazione.tracking.email;

                data.codMezzo = datiSegnalazione.provenienza.mezzoComunicazione;
                data.dataDenuncia = datiSegnalazione.provenienza.dataDenuncia.date;
                data.dataOraSinistro = datiSegnalazione.provenienza.dataSinistro.date;
                data.oraSinistro = datiSegnalazione.provenienza.oraSinistro;

                data.codNazione = datiSegnalazione.luogo.nazione.id;
                if (UtilSvc.hasId(datiSegnalazione.luogo.provincia)) {
                    data.codProvincia = datiSegnalazione.luogo.provincia.codProvincia;
                } else {
                    data.codProvincia = -1;
                }
                if (UtilSvc.hasId(datiSegnalazione.luogo.comune)) {
                    data.codComune = datiSegnalazione.luogo.comune.codComune;
                } else {
                    data.codComune = -1;
                }
                data.cap = datiSegnalazione.luogo.cap;
                data.indirizzo = datiSegnalazione.luogo.indirizzo;

                data.garanziaSelected = datiSegnalazione.garanzia;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.aprisegnalazione, idSinistroProvvisorio);


                return $http.post(url, data);

            };

            $svc.salvaEventoRca = function (idSinistroProvvisorio, datiEventoRca) {

                var dataObj = {};
                dataObj.collisione = datiEventoRca.collisione;
                dataObj.numVeicoli = datiEventoRca.nveicoli;
                dataObj.interventoAutorita = datiEventoRca.interventoAutorita;

                if (datiEventoRca.interventoAutorita) {
                    dataObj.codAutorita = datiEventoRca.autoritaIntervenuta;
                    dataObj.comandoAutorita = datiEventoRca.comandoAutorita;
                    dataObj.dataDenuncia = datiEventoRca.dataDenuncia.date
                }

                var stringUrl = UtilSvc.stringFormat(msaServicesApiUrls.rca, idSinistroProvvisorio);

                return $http({
                    method: 'POST',
                    url: stringUrl,
                    data: dataObj,
                    headers: {
                        "Content-Type": "application/json",
                        "user": '{"idUser": 1,"amministratore": true}'
                    }
                });

            };

            $svc.salvaCa = function (idSinistroProvvisorio, ca) {

                var data = {};
                data.caCompilata = ca.constatazioneAmichevole;
                data.caCompilataControparte = ca.constatazioneAmichevoleControparte;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.ca, idSinistroProvvisorio);

                return $http.post(url, data);

            };

            //TODO togliere AndGetResponsabilita perché è non vero
            $svc.saveCaiAndGetResponsabilita = function (numeroSinistroProvvisorio, cai, nveicoli) {

                var data = {};
                data.baremesCliente = {};
                data.baremesCliente.id = cai.baremeAssicurato;

                data.baremesControparte = {};

                if (nveicoli > 1 && cai.baremeControparte !== undefined && cai.baremeControparte !== null) {
                    data.baremesControparte.id = cai.baremeControparte;
                } else {
                    data.baremesControparte = null;
                }

                data.noteCliente = cai.osservazioniAssicurato;
                data.noteControparte = cai.osservazioniControparte;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.cai, numeroSinistroProvvisorio);

                return $http.post(url, data);

            };

            $svc.getResponsabilita = function (bCliente, bControparte) {

                var data = {
                    baremesCliente: {
                        id: bCliente
                    },
                    baremesControparte: {
                        id: bControparte
                    }
                };

                return $http.post(msaServicesApiUrls.colpa, data);

            };

            $svc.salvaDannoRcaCliente = function (idSinistroProvvisorio, dannoRca) {

                var dataObj = {};
                dataObj.anagraficaDanniCliente = {};
                dataObj.anagraficaDanniCliente.danni = {
                    a: dannoRca.dannoCliente.middleleft,
                    adx: dannoRca.dannoCliente.topleft,
                    asx: dannoRca.dannoCliente.bottomleft,
                    cdx: dannoRca.dannoCliente.topcenter,
                    csx: dannoRca.dannoCliente.bottomcenter,
                    d: dannoRca.dannoCliente.middleright,
                    ddx: dannoRca.dannoCliente.topright,
                    descrizioneDanno: dannoRca.descrizioneDannoCliente,
                    dsx: dannoRca.dannoCliente.bottomright
                };
                dataObj.lesioniConducente = dannoRca.lesioniConducente;

                /* TODO: Comune a tutti, creare metodo */
                if(dannoRca.conducente) {

                    dataObj.anagraficaDanniCliente.anagrafica = {};
                    dataObj.anagraficaDanniCliente.anagrafica.nome = dannoRca.conducente.nome;
                    dataObj.anagraficaDanniCliente.anagrafica.cognome = dannoRca.conducente.cognome;
                    dataObj.anagraficaDanniCliente.anagrafica.ragioneSociale = dannoRca.conducente.ragioneSociale;
                    dataObj.anagraficaDanniCliente.anagrafica.cf = dannoRca.conducente.cf;

                    dataObj.anagraficaDanniCliente.anagrafica.luogoNascita = {};
                    if (UtilSvc.isDefined(dannoRca.conducente.nascita.nazione)) {
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.codNazione = dannoRca.conducente.nascita.nazione.id;
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.descrizioneNazione = dannoRca.conducente.nascita.nazione.descrizione;
                    }
                    if (UtilSvc.isDefined(dannoRca.conducente.nascita.provincia)) {
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.codProvincia = dannoRca.conducente.nascita.provincia.codProvincia;
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.descrizioneProvincia = dannoRca.conducente.nascita.provincia.desProv;
                    }
                    if (UtilSvc.isDefined(dannoRca.conducente.nascita.comune)) {
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.codComune = dannoRca.conducente.nascita.comune.codComune;
                        dataObj.anagraficaDanniCliente.anagrafica.luogoNascita.descrizioneComune = dannoRca.conducente.nascita.comune.descrizione;
                    }

                    dataObj.anagraficaDanniCliente.anagrafica.dataNascita = dannoRca.conducente.nascita.data.date;

                    dataObj.anagraficaDanniCliente.anagrafica.tracking = {};
                    if (UtilSvc.isDefined(dannoRca.conducente.residenza.nazione)) {
                        dataObj.anagraficaDanniCliente.anagrafica.tracking.nazione = dannoRca.conducente.residenza.nazione.id;
                    }
                    if (UtilSvc.isDefined(dannoRca.conducente.residenza.provincia)) {
                        dataObj.anagraficaDanniCliente.anagrafica.tracking.provincia = dannoRca.conducente.residenza.provincia.codProvincia;
                    }
                    if (UtilSvc.isDefined(dannoRca.conducente.residenza.comune)) {
                        dataObj.anagraficaDanniCliente.anagrafica.tracking.comune = dannoRca.conducente.residenza.comune.codComune;
                    }

                    dataObj.anagraficaDanniCliente.anagrafica.tracking.indirizzo = dannoRca.conducente.residenza.indirizzo;
                    dataObj.anagraficaDanniCliente.anagrafica.tracking.telefono = dannoRca.conducente.telefono;
                    dataObj.anagraficaDanniCliente.anagrafica.tracking.mail = dannoRca.conducente.mail;
                }

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcacliente, idSinistroProvvisorio);

                return $http.post(url, dataObj);

            };


            $svc.salvaDannoRcaControparti = function (idSinistroProvvisorio, dannoRca) {

                var dataObj = [];

                dannoRca.controparti.forEach(function (element, index) {
                    var temp = {
                        anagrafica : {
                            nome: element.nome,
                            cognome: element.cognome,
                            sesso: element.sesso ? element.sesso.toUpperCase() : null,
                            cf: element.cf,
                            tipoPersona: element.tipoPersona,
                            dataNascita: element.nascita.data.date,
                            luogoNascita: {
                                codComune: element.nascita.comune ? element.nascita.comune.codComune : -1,
                                codProvincia: element.nascita.provincia ? element.nascita.comune.codProvincia : -1,
                                codNazione: element.nascita.nazione.id
                            },
                            tracking: {
                                comune: element.residenza.comune ? element.residenza.comune.codComune : -1,
                                provincia: element.residenza.provincia ? element.residenza.comune.codProvincia : -1,
                                nazione: element.residenza.nazione.id,
                                cap: element.residenza.cap,
                                indirizzo: element.residenza.indirizzo,
                                telefono: element.telefono,
                                mail: element.mail
                            }
                        }
                    };

                    if(index === 0 && dannoRca.dannoControparte) {
                        temp.danni = {
                            a: dannoRca.dannoControparte.middleleft,
                            adx: dannoRca.dannoControparte.topleft,
                            asx: dannoRca.dannoControparte.bottomleft,
                            cdx: dannoRca.dannoControparte.topcenter,
                            csx: dannoRca.dannoControparte.bottomcenter,
                            d: dannoRca.dannoControparte.middleright,
                            ddx: dannoRca.dannoControparte.topright,
                            descrizioneDanno: dannoRca.descrizioneDannoControparte,
                            dsx: dannoRca.dannoControparte.bottomright
                        };
                    }

                    //TODO aggiungere danni veicolo

                    dataObj.push(temp)
                });

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcaterzeparti, idSinistroProvvisorio);
                return $http.post(url, dataObj);

            }


        }
    ]
);