angular.module('msa').service(
    'PathSvc',
    [
        '$http',
        '$location',
        'msaServicesApiUrls',
        'UtilSvc',
        function ($http, $location, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            $svc.getNextPath = function (garanziaSelected, numSinistroProvvisiorio) {

                var data = {};

                data.garanziaSelected = garanziaSelected;
                data.numSinistroProvv = numSinistroProvvisiorio;

                return $http.post(msaServicesApiUrls.nextpath, data);

            };

            $svc.getPath = function (numSinistroProvvisiorio) {

                var url = UtilSvc.stringFormat(msaServicesApiUrls.path, numSinistroProvvisiorio);
                return $http.get(url);

            };

        }
    ]
);