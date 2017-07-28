angular.module('msa').service(
    'CauseRotturaCristalliSvc',
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

            $svc.getCauseRotturaCristalli = function () {
                return $http.get(msaServicesApiUrls.causerotturacristalli);
            };


        }
    ]
);