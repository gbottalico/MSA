angular.module('msa').service(
    'PathSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getNextPath = function (garanziaSelected, numSinistroProvvisiorio) {

                var data = {};

                data.garanziaSelected = garanziaSelected;
                data.numSinistroProvv = numSinistroProvvisiorio;

                return $http.post(msaServicesApiUrls.nextpath, data);

            };

            $svc.getPath = function (numSinistroProvvisiorio) {

                return $http.post(msaServicesApiUrls.nextpath, data);

            };

        }
    ]
);