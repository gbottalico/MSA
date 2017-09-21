angular.module('msa').service(
    'SinistriSvc',
    [
        '_',
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        'DebugSvc',
        'ConvertSvc',
        function (_, $http, msaServicesApiUrls, UtilSvc, DebugSvc, ConvertSvc) {

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

            $svc.apriSinistroProvvisorio = function (datiContraente, compagnia, polizza) {

                var dataObj = {};
                dataObj.compagnia = compagnia;
                dataObj.contraente = ConvertSvc.anagraficaToDTO(datiContraente);
                if (polizza !== null) {
                    dataObj.numeroPolizza = polizza.numPolizza;
                    dataObj.proprietario = {
                        cf: polizza.codfiscProprietario,
                        cognome: polizza.cognomeProprietario,
                        nome: polizza.nomeProprietario,
                        ragioneSociale: null,//TODO CHECK
                        tipoPersona: "PF", //TODO CHECK
                        tracking: {
                            cap: polizza.capProprietario,
                            comune: null,
                            descComune: polizza.cittaProprietario,
                            descNazione: "ITALIA", //TODO CHECK
                            descProvincia: null,
                            nazione: "1", // TODO MOCK
                            provincia: polizza.provinciaProprietario
                        }
                    };
                }

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
                data.denunciante.codRuolo = datiSegnalazione.segnalazione.ruolo;

                data.denunciante.tracking = {};
                data.denunciante.tracking.cellulare = datiSegnalazione.tracking.cellulare;
                data.denunciante.tracking.mail = datiSegnalazione.tracking.email;
                data.denunciante.tracking.telefono = datiSegnalazione.segnalazione.telefono;

                data.codMezzo = datiSegnalazione.provenienza.mezzoComunicazione;
                data.dataDenuncia = datiSegnalazione.provenienza.dataDenuncia;
                data.dataOraSinistro = datiSegnalazione.provenienza.dataSinistro;
                data.oraSinistro = datiSegnalazione.provenienza.oraSinistro;

                //TODO creare un metodo di questa roba.
                data.luogoSinistro = {};
                data.luogoSinistro.codNazione = datiSegnalazione.luogo.nazione.codNazione;
                data.luogoSinistro.descrizioneNazione = datiSegnalazione.luogo.nazione.descrizione;
                if(_.isObject(datiSegnalazione.luogo.provincia)) {
                    data.luogoSinistro.codProvincia = datiSegnalazione.luogo.provincia.codProvincia;
                    data.luogoSinistro.descrizioneProvincia = datiSegnalazione.luogo.provincia.descProvincia;
                }
                if(_.isObject(datiSegnalazione.luogo.comune)) {
                    data.luogoSinistro.codComune = datiSegnalazione.luogo.comune.codComune;
                    data.luogoSinistro.descrizioneComune = datiSegnalazione.luogo.comune.descrizione;
                    data.luogoSinistro.cap = datiSegnalazione.luogo.cap;
                }

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
                    dataObj.dataDenuncia = datiEventoRca.dataDenuncia;
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
            $svc.salvaFurtoTotale = function (numeroSinistroProvvisorio, furtoTotale) {
                var dataObj = {

                    descrizioneDanni: furtoTotale.descrizionedanni,
                    interventoAutorita: furtoTotale.autorita.interventoAutorita,
                };

                if (furtoTotale.autorita.interventoAutorita) {
                    dataObj.codAutorita = furtoTotale.autorita.autoritaIntervenuta;
                    dataObj.comandoAutorita = furtoTotale.autorita.comandoAutorita;
                    dataObj.dataDenuncia = furtoTotale.autorita.dataDenuncia.date;
                }

                var url = UtilSvc.stringFormat(msaServicesApiUrls.furtoincendio, numeroSinistroProvvisorio);
                return $http.post(url, dataObj);

            };
            $svc.salvaDannoRcaCliente = function (idSinistroProvvisorio, dannoRca) {

                var dataObj = {};
                dataObj.anagraficaDanniCliente = {
                    anagrafica: {}
                };

                dataObj.anagraficaDanniCliente.danni = ConvertSvc.danniAutoToDTO(dannoRca.dannoCliente, dannoRca.descrizioneDannoCliente);
                dataObj.lesioniConducente = dannoRca.lesioniConducente;
                dataObj.conducenteDiverso = dannoRca.conducenteIsNotContraente;

                if (dannoRca.conducente) {
                    dataObj.anagraficaDanniCliente.anagrafica = ConvertSvc.anagraficaToDTO(dannoRca.conducente);
                }

                if (dannoRca.veicoloCliente) {
                    dataObj.anagraficaDanniCliente.anagrafica.targa = dannoRca.veicoloCliente.targa;
                    dataObj.anagraficaDanniCliente.anagrafica.targaEstera = dannoRca.veicoloCliente.estera;
                    dataObj.anagraficaDanniCliente.anagrafica.targaSpeciale = dannoRca.veicoloCliente.speciale;
                    dataObj.anagraficaDanniCliente.anagrafica.veicolo = dannoRca.veicoloCliente.veicolo;
                }

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcacliente, idSinistroProvvisorio);

                return $http.post(url, dataObj);

            };
            $svc.saveCai = function (numeroSinistroProvvisorio, cai, nveicoli) {

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




            $svc.salvaDannoRcaControparti = function (idSinistroProvvisorio, dannoRca) {

                var dataObj = [];

                dannoRca.controparti.forEach(function (element, index) {

                    var temp = {};

                    temp.anagrafica = ConvertSvc.anagraficaToDTO(element);

                    if (index === 0 && dannoRca.dannoControparte) {
                        temp.danni = ConvertSvc.danniAutoToDTO(dannoRca.dannoControparte, dannoRca.descrizioneDannoControparte);
                    }

                    if (index === 0 && dannoRca.veicoloControparte) {
                        temp.anagrafica.targa = dannoRca.veicoloControparte.targa;
                        temp.anagrafica.targaEstera = dannoRca.veicoloControparte.estera;
                        temp.anagrafica.targaSpeciale = dannoRca.veicoloControparte.speciale;
                        temp.anagrafica.veicolo = dannoRca.veicoloControparte.veicolo;
                    }

                    dataObj.push(temp);

                });

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcacontroparte, idSinistroProvvisorio);
                return $http.post(url, dataObj);

            };

            $svc.salvaTerzeParti = function (idSinistroProvvisorio, anagrafiche) {

                var data = [];
                anagrafiche.forEach(function (element, index) {
                    var temp = ConvertSvc.anagraficaToDTO(element);
                    data.push(temp);
                });

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcaterzeparti, idSinistroProvvisorio);
                return $http.post(url, data);
            };

            $svc.salvaLegali = function (idSinistroProvvisorio, anagrafiche) {

                var data = [];
                anagrafiche.forEach(function (element, index) {
                    var temp = ConvertSvc.anagraficaToDTO(element);
                    data.push(temp);
                });

                var url = UtilSvc.stringFormat(msaServicesApiUrls.legali, idSinistroProvvisorio);
                return $http.post(url, data);
            };

            $svc.getCarrozzerie = function (indirizzo) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.carrozzerie, indirizzo);
                return $http.get(url);
            };

            $svc.salvaCarrozzeria = function (idSinistroProvvisorio, carrozzeria) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.salvacarrozzeria, idSinistroProvvisorio);
                return $http.post(url, carrozzeria);
            };

            $svc.getPerito = function (indirizzo) {
                var data = {input: indirizzo};
                return $http.post(msaServicesApiUrls.perito, data);
            };

            $svc.salvaPerito = function (idSinistroProvvisorio, perito) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.salvaperito, idSinistroProvvisorio);
                return $http.post(url, perito);
            };

            $svc.salvaIncendio = function (idSinistroProvvisorio, incendio) {
                var dataObj = {
                    sviluppoFiamme: incendio.sviluppoFiamme,
                    responsabilita: incendio.responsabilitaTerzi,
                    descrizioneDanni: incendio.descrizioneDanni,
                    osservazioniCliente: incendio.osservazioniCliente,
                    interventoAutorita: incendio.interventoAutorita,
                };

                if (incendio.interventoAutorita) {
                    dataObj.codAutorita = incendio.autoritaIntervenuta;
                    dataObj.comandoAutorita = incendio.comandoAutorita;
                    dataObj.dataDenuncia = incendio.dataDenuncia;
                }

                var url = UtilSvc.stringFormat(msaServicesApiUrls.furtoincendio, idSinistroProvvisorio);
                return $http.post(url, dataObj);
            };

            $svc.salvaCristalli = function (idSinistroProvvisorio, cristalli) {

                var dataObj = {
                    desCristalloRotto: cristalli.desCristalloRotto,
                    codRotturaCristalli: cristalli.codRotturaCristalli,
                    flagRiparazione: cristalli.flagRiparazione,
                    flagFattura: cristalli.flagFattura,
                    interventoAutorita: cristalli.interventoAutorita,
                };

                if (cristalli.interventoAutorita) {
                    dataObj.codAutorita = cristalli.autoritaIntervenuta;
                    dataObj.comandoAutorita = cristalli.comandoAutorita;
                    dataObj.dataDenuncia = cristalli.dataDenuncia;
                }

                var url = UtilSvc.stringFormat(msaServicesApiUrls.cristalli, idSinistroProvvisorio);
                return $http.post(url, dataObj);
            };

            $svc.getPartiteDanno = function (idSinistroProvvisorio) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.partitedanno, idSinistroProvvisorio);
                return $http.get(url);
            };

            $svc.getAnagraficheAssociabili = function (idSinistroProvvisorio) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.anagraficheass, idSinistroProvvisorio);
                return $http.get(url);
            };

            $svc.getTipoSinistro = function (idSinistroProvvisorio) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.tipoSinistro, idSinistroProvvisorio);
                return $http.get(url);
            };

        }
    ]
);