angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            $svc.apriSinistroProvvisorio = function (datiContraente) {


                var data = {};
                data.contraente = {};
                data.contraente.nome = datiContraente.nome;
                data.contraente.cognome = datiContraente.cognome;
                data.contraente.cf = datiContraente.cf;
                if(UtilSvc.isDefined(datiContraente.nascita.comune)) {
                    data.contraente.codComuneNascita = datiContraente.nascita.comune.codComune;
                    data.contraente.desComuneNascita = datiContraente.nascita.comune.descrizione;
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

                return $http.put(msaServicesApiUrls.aperturasinitro, data);

            };


        }
    ]
);