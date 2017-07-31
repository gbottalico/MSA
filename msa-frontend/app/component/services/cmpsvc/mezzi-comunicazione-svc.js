angular.module('msa').service(
    'MezziComunicazioneSvc',
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

            $svc.getMezziComunicazione = function () {
                return $http.get(msaServicesApiUrls.mezzicomunicazione);
            };


        }
    ]
);