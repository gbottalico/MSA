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
                //TODO: elaborare un metodo per cachare.
                if ($rootScope.domain.casaRegole) {
                    DebugSvc.log("getElencoRegole, using cache.");
                    return UtilSvc.createPromise($rootScope.domain.casaRegole);
                    //return UtilSvc.createPromise([{idCompagnia: "37", descrizioneCompagnia: "Cache"}]);
                } else {
                    DebugSvc.log("getElencoRegole, using $http.");
                    return $http.get(msaServicesApiUrls.casaregole);
                }

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
                // $http.get(msaServicesApiUrls.casaregole + "GGGGG").then(function(data){
                //     deferred.resolve(data.result);
                //     $rootScope.domain.casaRegole = data.result;
                // }, function(data, status, headers, config) {
                //     deferred.reject("Error: request returned status " + status);
                // });
                $http.get(msaServicesApiUrls.casaregole + "GGGGG").then(function successCallback(response) {
                    DebugSvc.log("successCallback");
                }, function errorCallback(response) {
                    DebugSvc.log("errorCallback");
                });
                return deferred.promise;
            };


        }
    ]
);