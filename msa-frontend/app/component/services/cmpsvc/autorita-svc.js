angular.module('msa').service(
    'AutoritaSvc',
    [
        '$http',
        'msaServicesApiUrls',
        function ($http, msaServicesApiUrls) {

            var $svc = this;

            $svc.getAutorita = function () {
                return $http.get(msaServicesApiUrls.autorita);
            };


        }
    ]
);