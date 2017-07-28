angular.module('msa').service(
    'AutoritaSvc',
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

            $svc.getAutorita = function () {
                return $http.get(msaServicesApiUrls.autorita);
            };


        }
    ]
);