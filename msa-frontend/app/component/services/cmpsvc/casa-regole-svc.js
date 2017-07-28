angular.module('msa').service(
    'CasaRegoleSvc',
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

            $svc.getElencoRegole = function () {
                return $http.get(msaServicesApiUrls.casaregole);
            };
            
        }
    ]
);