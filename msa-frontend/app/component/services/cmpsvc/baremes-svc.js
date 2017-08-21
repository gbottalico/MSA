angular.module('msa').service(
    'BaremesSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getBaremes = function () {
                return $http.get(msaServicesApiUrls.baremes);
            };


        }
    ]
);