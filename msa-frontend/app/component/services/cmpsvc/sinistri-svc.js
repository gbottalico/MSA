angular.module('msa').service(
    'SinistriSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.apriSinistroProvvisorio = function (datiContraente) {

              var data = {};
              data.contraente = {};
              data.contraente.nome = datiContraente.nome;
              data.contraente.cognome = datiContraente.cognome;
              data.contraente.cf = datiContraente.cf;
              data.contraente.codComuneNascita = datiContraente.residenza.comune.codComune;
              data.contraente.desComuneNascita = datiContraente.residenza.comune.descrizione;
              data.contraente.tracking = {};
              data.contraente.tracking.nazione = datiContraente.residenza.nazione.id;
              data.contraente.tracking.provincia = datiContraente.residenza.provincia.codProvincia;
              data.contraente.tracking.comune = datiContraente.residenza.comune.codComune;
              data.contraente.tracking.indirizzo = datiContraente.residenza.indirizzo.denominazione + ", " + datiContraente.residenza.indirizzo.civico;

              return $http.put(msaServicesApiUrls.aperturasinitro, data);

            };
        }
    ]
);