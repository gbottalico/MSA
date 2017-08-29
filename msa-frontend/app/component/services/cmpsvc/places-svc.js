angular.module('msa').service(
    'PlacesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            var codice = {
                nazione: "N",
                provincia: "P",
                comune: "C",
                cap: "A"
            };

            $svc.getGeoconding = function (indirizzo) {
              var url = UtilSvc.stringFormat(msaServicesApiUrls.geocoding, indirizzo);
              return $http.get(url);
            };

            $svc.getNazioni = function (nomeNazione) {
                return $http.get(msaServicesApiUrls.nazione + nomeNazione);
            };

            $svc.getProvince = function (idNazione, nomeProvincia) {
                return $http.get(msaServicesApiUrls.provincia + idNazione + "/" + nomeProvincia);
            };

            $svc.getComuni = function (idNazione, idProvincia, nomeComune) {
                return $http.get(msaServicesApiUrls.comune + idNazione + "/" + idProvincia + "/" + nomeComune);
            };

            $svc.getNazioneById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.nazione);
                return $http.get(url);
            };

            $svc.getProvinciaById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.provincia);
                return $http.get(url);
            };

            $svc.getComuneById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.comune);
                return $http.get(url);
            };

            $svc.getCapsByIdComune = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.cap);
                return $http.get(url);
            };

            $svc.getTipiStrada = function () {
                return $http.get(msaServicesApiUrls.toponomastiche);
            };

            $svc.isValidPlace = function (nazione, provincia, comune) {
                if (hasId(nazione)) {
                    if (nazione.id > 1) {
                        return true;
                    } else {
                        return hasId(provincia) && hasId(comune);
                    }
                } else {
                    return false;
                }
            };

            var hasId = function (obj) {
                return (
                    obj !== undefined &&
                    obj !== null &&
                    obj.id !== undefined &&
                    obj.id !== null);
            };

        }
    ]
);