angular.module('msa').service(
    'VeicoliSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

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