angular.module('msa').service(
    'RuoliSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getRuoli = function () {
                return $http.get(msaServicesApiUrls.ruoli);
            };
        }
    ]
);