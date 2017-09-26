angular.module('msa').service(
    'DomainSvc',
    [   '_',
        '$http',
        '$rootScope',
        '$q',
        '$localStorage',
        'DebugSvc',
        'UtilSvc',
        'msaServicesApiUrls',
        function (_, $http, $rootScope, $q, $localStorage, DebugSvc, UtilSvc, msaServicesApiUrls) {

            var $svc = this;

            const KEYS = {
                ELENCO_REGOLE: {name: "elencoRegole", url: msaServicesApiUrls.casaregole},
                AUTORITA: {name: "autorita", url: msaServicesApiUrls.autorita},
                BAREMES: {name: "baremes", url: msaServicesApiUrls.baremes},
                MEZZI_COMUNICAZIONE: {name: "mezziComunicazione", url: msaServicesApiUrls.mezzicomunicazione},
                RUOLI: {name: "ruoli", url: msaServicesApiUrls.ruoli},
                TIPO_TARGHE: {name: "tipoTarghe", url: msaServicesApiUrls.tipotarghe},
                TIPO_VEICOLI: {name: "tipoVeicoli", url: msaServicesApiUrls.tipoveicoli},
                CAUSE_ROTTURA_CRISTALLI: {name: "causeRotturaCristalli", url: msaServicesApiUrls.causerotturacristalli},
            };

            var process = function (key) {
                var deferred = $q.defer();
                if ($localStorage[key.name]) {
                    deferred.resolve($localStorage[key.name]);
                    DebugSvc.log("DomainSvc: " + key.name + ", returing cached.", $localStorage[key.name]);
                    return deferred.promise;
                }

                $http.get(key.url).then(function (response) {
                    DebugSvc.log("DomainSvc: " + key.name + ", returing $http", response.data);
                    if (response.status === 200 && _.isObject(response.data) && response.data.status === 200) {
                        deferred.resolve(response);
                        $localStorage[key.name] = response;
                    } else {
                        deferred.reject("DomainSvc error, " + [key.name]);
                    }
                }, function (data, status, headers, config) {
                    deferred.reject("Error: request returned status " + status);
                });

                return deferred.promise;
            };

            $svc.getAutorita = function () {
                return $http.get(msaServicesApiUrls.autorita);
            };

            $svc.getBaremes = function () {
                return $http.get(msaServicesApiUrls.baremes);
            };

            $svc.getElencoRegole = function () {
                return process(KEYS.ELENCO_REGOLE);
            };

            $svc.getCompagnie = function (desc) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.compagnia, desc);
                return $http.get(url);
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

        }
    ]
);