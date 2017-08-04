angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;
            var getOggettoRicerca = function() {

                return {
                    "cognome" : undefined,
                    "nome" : undefined,
                    "tipoPersona" : undefined,
                    "numeroPolizza" : undefined,
                    "numeroSinistro" : undefined,
                    "dataEvento" : undefined,
                    "targa" : undefined,
                    "numeroProvvisorio" : undefined,
                    "numeroPreapertura" : undefined,
                    "compagnia" : undefined
                };

            };

            $svc.apriSinistroProvvisorio = function (datiContraente, compagnia) {

                var data = {};
                data.compagnia = compagnia;

                data.contraente = {};
                data.contraente.nome = datiContraente.nome;
                data.contraente.cognome = datiContraente.cognome;
                data.contraente.cf = datiContraente.cf;

                data.contraente.luogoNascita = {};
                if(UtilSvc.isDefined(datiContraente.nascita.nazione)) {
                    data.contraente.luogoNascita.codNazione = datiContraente.nascita.nazione.id;
                    data.contraente.luogoNascita.descrizioneNazione = datiContraente.nascita.nazione.descrizione;
                }
                if(UtilSvc.isDefined(datiContraente.nascita.provincia)) {
                    data.contraente.luogoNascita.codProvincia = datiContraente.nascita.provincia.codProvincia;
                    data.contraente.luogoNascita.descrizioneProvincia = datiContraente.nascita.provincia.desProv;
                }
                if(UtilSvc.isDefined(datiContraente.nascita.comune)) {
                    data.contraente.luogoNascita.codComune = datiContraente.nascita.comune.codComune;
                    data.contraente.luogoNascita.descrizioneComune = datiContraente.nascita.comune.descrizione;
                }

                data.contraente.dataNascita = datiContraente.nascita.data;

                data.contraente.tracking = {};
                if(UtilSvc.isDefined(datiContraente.residenza.nazione)) {
                    data.contraente.tracking.nazione = datiContraente.residenza.nazione.id;
                }
                if(UtilSvc.isDefined(datiContraente.residenza.provincia)) {
                    data.contraente.tracking.provincia = datiContraente.residenza.provincia.codProvincia;
                }
                if(UtilSvc.isDefined(datiContraente.residenza.comune)) {
                    data.contraente.tracking.comune = datiContraente.residenza.comune.codComune;
                }
                if(UtilSvc.isDefined(datiContraente.residenza.indirizzo)) {
                    data.contraente.tracking.indirizzo = datiContraente.residenza.indirizzo.denominazione + ", " + datiContraente.residenza.indirizzo.civico;
                }

                console.log(JSON.stringify(data));

                return $http.put(msaServicesApiUrls.aperturasinitro, data);

            };

            $svc.cercaSinistroProvvisorio = function(idCompagnia, numeroSinistroProvvisorio) {

                var data = getOggettoRicerca();
                data.compagnia = idCompagnia;
                data.numeroProvvisorio = numeroSinistroProvvisorio;

                return $http.post(msaServicesApiUrls.ricercasinitro, data);

            };

            $svc.apriSegnalazione = function (idSinistroProvvisorio, datiSegnalazione) {

                var data = {};
                data.denunciante = {};

                data.denunciante.nome = datiSegnalazione.segnalazione.nome;
                data.denunciante.cognome = datiSegnalazione.segnalazione.cognome;
                data.denunciante.telefono = datiSegnalazione.segnalazione.telefono;
                data.denunciante.codRuolo = datiSegnalazione.segnalazione.ruolo;

                data.denunciante.cellulare = datiSegnalazione.tracking.cellulare;
                data.denunciante.email = datiSegnalazione.tracking.email;

                data.codMezzo = datiSegnalazione.provenienza.mezzoComunicazione;
                data.dataDenuncia = datiSegnalazione.provenienza.dataDenuncia.date;
                data.dataOraSinistro = datiSegnalazione.provenienza.dataSinistro.date;
                data.oraSinistro = datiSegnalazione.provenienza.oraSinistro;

                data.codNazione = datiSegnalazione.luogo.nazione.id;
                if(UtilSvc.hasId(datiSegnalazione.luogo.provincia)) {
                    data.codProvincia = datiSegnalazione.luogo.provincia.codProvincia;
                } else {
                    data.codProvincia = -1;
                }
                if(UtilSvc.hasId(datiSegnalazione.luogo.comune)) {
                    data.codComune = datiSegnalazione.luogo.comune.codComune;
                } else {
                    data.codComune = -1;
                }
                data.cap = datiSegnalazione.luogo.cap;
                data.indirizzo = datiSegnalazione.luogo.tipostrada + " " + datiSegnalazione.luogo.denominazione + ", " + datiSegnalazione.luogo.civico;
                data.garanziaSelected = datiSegnalazione.garanzia;

                console.log(JSON.stringify(data));

                var url = UtilSvc.stringFormat(msaServicesApiUrls.aprisegnalazione, idSinistroProvvisorio);
                console.log(url);
                return $http.post(url, data);

            };

        }
    ]
);