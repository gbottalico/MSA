angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

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

                dataObj.contraente = {};
                dataObj.contraente.nome = datiContraente.nome;
                dataObj.contraente.cognome = datiContraente.cognome;
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
                if (UtilSvc.isDefined(datiContraente.residenza.indirizzo)) {
                    dataObj.contraente.tracking.tipoStrada = datiContraente.residenza.indirizzo.tipostrada;
                    dataObj.contraente.tracking.denominazioneStrada = datiContraente.residenza.indirizzo.denominazione;
                    dataObj.contraente.tracking.civicoStrada = datiContraente.residenza.indirizzo.civico;
                }

                console.log(JSON.stringify(dataObj));

                //return $http.put(msaServicesApiUrls.aperturasinitro, data);

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
                data.tipoStrada = datiSegnalazione.luogo.tipostrada;
                data.denominazioneStrada = datiSegnalazione.luogo.denominazione;
                data.civicoStrada = datiSegnalazione.luogo.civico;

                data.garanziaSelected = datiSegnalazione.garanzia;

                console.log(JSON.stringify(data));

                var url = UtilSvc.stringFormat(msaServicesApiUrls.aprisegnalazione, idSinistroProvvisorio);

                return $http.post(url, data);

            };

            $svc.salvaEventoRca = function (idSinistroProvvisorio, datiEventoRca) {

                var dataObj = {};
                dataObj.collisione = datiEventoRca.collisione;
                dataObj.interventoAutorita = datiEventoRca.interventoAutorita;
                dataObj.numVeicoli = datiEventoRca.nveicoli;
                dataObj.codAutorita = datiEventoRca.autoritaIntervenuta;
                dataObj.comandoAutorita = datiEventoRca.comandoAutorita;
                dataObj.dataDenuncia = datiEventoRca.dataDenuncia.date;

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

        }
    ]
);