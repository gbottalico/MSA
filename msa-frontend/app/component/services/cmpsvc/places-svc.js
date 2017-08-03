angular.module('msa').service(
    'PlacesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        '$sessionStorage',
        '$cookieStore',
        '$rootScope',
        '$q',
        '$timeout',
        '$log',
        function ($http, msaServicesApiUrls, $sessionStorage, $cookieStore, $rootScope, $q, $timeout, $log) {

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
            }

        }
    ]
);