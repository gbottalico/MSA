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
                }

            };

            $svc.apriSinistroProvvisorio = function (datiContraente, compagnia) {

                var data = {};
                data.compagnia = compagnia;

                data.contraente = {};
                data.contraente.nome = datiContraente.nome;
                data.contraente.cognome = datiContraente.cognome;
                data.contraente.cf = datiContraente.cf;

                data.contraente.luogonascita = {};
                if(UtilSvc.isDefined(datiContraente.nascita.nazione)) {
                    data.contraente.luogonascita.codNazione = datiContraente.nascita.nazione.id;
                    data.contraente.luogonascita.descrizioneNazione = datiContraente.nascita.nazione.descrizione;
                }
                if(UtilSvc.isDefined(datiContraente.nascita.provincia)) {
                    data.contraente.luogonascita.codProvincia = datiContraente.nascita.provincia.codProvincia;
                    data.contraente.luogonascita.descrizioneProvincia = datiContraente.nascita.provincia.desProv;
                }
                if(UtilSvc.isDefined(datiContraente.nascita.comune)) {
                    data.contraente.luogonascita.codComune = datiContraente.nascita.comune.codComune;
                    data.contraente.luogonascita.descrizioneComune = datiContraente.nascita.comune.descrizione;
                }

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


        }
    ]
);