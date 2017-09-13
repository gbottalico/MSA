angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        'DebugSvc',
        'ConvertSvc',
        function ($http, msaServicesApiUrls, UtilSvc, DebugSvc, ConvertSvc) {

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
                if (polizza != null) {
                    dataObj.numeroPolizza = polizza.numPolizza;
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
                    dataObj.dataDenuncia = datiEventoRca.dataDenuncia.date;
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

                dataObj.anagraficaDanniCliente.anagrafica.targa = dannoRca.veicoloCliente.targa;
                dataObj.anagraficaDanniCliente.anagrafica.targaEstera = dannoRca.veicoloCliente.estera;
                dataObj.anagraficaDanniCliente.anagrafica.targaSpeciale = dannoRca.veicoloCliente.speciale;
                dataObj.anagraficaDanniCliente.anagrafica.veicolo = dannoRca.veicoloCliente.veicolo;

                var url = UtilSvc.stringFormat(msaServicesApiUrls.dannorcacliente, idSinistroProvvisorio);

                return $http.post(url, dataObj);

            };


            $svc.salvaDannoRcaControparti = function (idSinistroProvvisorio, dannoRca) {

                var dataObj = [];

                dannoRca.controparti.forEach(function (element, index) {
                    // var temp = {
                    //     anagrafica: {
                    //         nome: element.nome,
                    //         cognome: element.cognome,
                    //         sesso: element.sesso ? element.sesso.toUpperCase() : null,
                    //         cf: element.cf,
                    //         tipoPersona: element.tipoPersona,
                    //         dataNascita: element.nascita.data.date,
                    //         luogoNascita: {
                    //             codComune: element.nascita.comune ? element.nascita.comune.codComune : -1,
                    //             codProvincia: element.nascita.provincia ? element.nascita.comune.codProvincia : -1,
                    //             codNazione: element.nascita.nazione.id
                    //         },
                    //         tracking: {
                    //             comune: element.residenza.comune ? element.residenza.comune.codComune : -1,
                    //             provincia: element.residenza.provincia ? element.residenza.comune.codProvincia : -1,
                    //             nazione: element.residenza.nazione.id,
                    //             cap: element.residenza.cap,
                    //             indirizzo: element.residenza.indirizzo,
                    //             telefono: element.telefono,
                    //             mail: element.mail
                    //         }
                    //     }
                    // };

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

            $svc.getPerito = function (indirizzo) {
                var data = {input: indirizzo};
                return $http.post(msaServicesApiUrls.perito, data);
            };

            $svc.salvaPerito = function (idSinistroProvvisorio, perito) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.salvaperito, idSinistroProvvisorio);
                return $http.post(url, perito);
            };

        }
    ]
);