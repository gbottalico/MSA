angular.module('msa').service(
    'BaremesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            $svc.getBaremes = function () {
                return $http.get(msaServicesApiUrls.baremes);
            };

        }
    ]
);