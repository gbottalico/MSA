angular.module('msa').service(
    'CompagnieSvc',
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

            $svc.getCompagnie = function (nomeCompagnia) {
                return $http.get(msaServicesApiUrls.compagnia + nomeCompagnia);
            };
        }
    ]
);