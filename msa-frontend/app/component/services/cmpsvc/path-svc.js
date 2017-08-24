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

                console.log(data);

                return $http.post(msaServicesApiUrls.nextpath, data);

            };

        }
    ]
);