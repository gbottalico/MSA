angular.module('msa').service(
    'DomainSvc',
    [
        '$http',
        '$rootScope',
        '$q',
        'DebugSvc',
        'UtilSvc',
        'msaServicesApiUrls',
        function ($http, $rootScope, $q, DebugSvc, UtilSvc, msaServicesApiUrls) {

            var $svc = this;

            $svc.getAutorita = function () {
                return $http.get(msaServicesApiUrls.autorita);
            };

            $svc.getBaremes = function () {
                return $http.get(msaServicesApiUrls.baremes);
            };

            $svc.getElencoRegole = function () {
                // TODO estendere a tutti la cache.
                var deferred = $q.defer();
                if ($rootScope.domain.casaRegole) {
                    deferred.resolve($rootScope.domain.casaRegole);
                    DebugSvc.log("getElencoRegole, returing cached.", $rootScope.domain.casaRegole);
                    return deferred.promise;
                }

                $http.get(msaServicesApiUrls.casaregole).then(function(response){
                    DebugSvc.log("getElencoRegole, returing $http", response.data);
                    if(response.status === 200 && response.data.status === 200) {
                        deferred.resolve(response.data);
                        $rootScope.domain.casaRegole = response.data;
                    }
                }, function(data, status, headers, config) {
                    deferred.reject("Error: request returned status " + status);
                });

                return deferred.promise;

            };

            $svc.getMezziComunicazione = function () {
                return $http.get(msaServicesApiUrls.mezzicomunicazione);
            };

            $svc.getRuoli = function () {
                return $http.get(msaServicesApiUrls.ruoli);
            };

            $svc.getTipoTarghe = function () {
                return $http.get(msaServicesApiUrls.tipotarghe);
            };

            $svc.getTipoVeicoli = function () {
                return $http.get(msaServicesApiUrls.tipoveicoli);
            };

            $svc.getCauseRotturaCristalli = function () {
                return $http.get(msaServicesApiUrls.causerotturacristalli);
            };

            //TODO MOCKUP
            $svc.successCall = function () {
                var deferred = $q.defer();
                if ($rootScope.domain.casaRegole) {
                    deferred.resolve($rootScope.domain.casaRegole);
                    DebugSvc.log("Returning cached.");
                    return deferred.promise;
                }
                $http.get(msaServicesApiUrls.casaregole + "GGGGG").then(function(data){
                    deferred.resolve(data.result);
                    $rootScope.domain.casaRegole = data.result;
                }, function(data, status, headers, config) {
                    deferred.reject("Error: request returned status " + status);
                });

                return deferred.promise;
            };


        }
    ]
);