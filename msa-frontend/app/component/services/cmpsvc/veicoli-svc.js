angular.module('msa').service(
    'VeicoliSvc',
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

            $svc.getTipoTarghe = function () {
                return $http.get(msaServicesApiUrls.tipotarghe);
            };

            $svc.getTipoVeicoli = function () {
                return $http.get(msaServicesApiUrls.tipoveicoli);
            };

        }
    ]
);