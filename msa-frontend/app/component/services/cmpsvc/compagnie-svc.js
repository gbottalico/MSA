angular.module('msa').service(
    'CompagnieSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getCompagnie = function (nomeCompagnia) {
                return $http.get(msaServicesApiUrls.compagnia + nomeCompagnia);
            };
        }
    ]
);