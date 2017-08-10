angular.module('msa').service(
    'PlacesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        '$sessionStorage',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getNazioni = function (nomeNazione) {
                return $http.get(msaServicesApiUrls.nazione + nomeNazione);
            };

            $svc.getProvince = function (idNazione, nomeProvincia) {
                return $http.get(msaServicesApiUrls.provincia + idNazione + "/" + nomeProvincia);
            };

            $svc.getComuni = function (idNazione, idProvincia, nomeComune) {
                return $http.get(msaServicesApiUrls.comune + idNazione + "/" + idProvincia + "/" + nomeComune);
            };

            $svc.getTipiStrada = function () {
                return ['Via', 'Viale', 'Piazza'];
            };

            $svc.isValidPlace = function (nazione, provincia, comune) {
                if(hasId(nazione)) {
                    if(nazione.id > 1) {
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