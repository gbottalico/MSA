angular.module('msa').service(
    'PlacesSvc',
    [
        '_',
        '$http',
        'msaServicesApiUrls',
        'UtilSvc',
        function (_, $http, msaServicesApiUrls, UtilSvc) {

            var $svc = this;

            var codice = {
                nazione: "N",
                provincia: "P",
                comune: "C",
                cap: "A"
            };

            $svc.getGeoconding = function (indirizzo) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.geocoding, indirizzo);
                return $http.get(url);
            };

            $svc.getNazioni = function (nomeNazione) {
                return $http.get(msaServicesApiUrls.nazione + nomeNazione);
            };

            $svc.getProvince = function (idNazione, nomeProvincia) {
                return $http.get(msaServicesApiUrls.provincia + idNazione + "/" + nomeProvincia);
            };

            $svc.getComuni = function (idNazione, idProvincia, nomeComune) {
                return $http.get(msaServicesApiUrls.comune + idNazione + "/" + idProvincia + "/" + nomeComune);
            };

            $svc.getNazioneById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.nazione);
                return $http.get(url);
            };

            $svc.getProvinciaById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.provincia);
                return $http.get(url);
            };

            $svc.getComuneById = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.comune);
                return $http.get(url);
            };

            $svc.getCapsByIdComune = function (id) {
                var url = UtilSvc.stringFormat(msaServicesApiUrls.luogoById, id, codice.cap);
                return $http.get(url);
            };

            $svc.getTipiStrada = function () {
                return $http.get(msaServicesApiUrls.toponomastiche);
            };

            $svc.isValidPlace = function (nazione, provincia, comune, cap) {
                if (_.isObject(nazione)) {
                    if (nazione.codNazione > 1) {
                        return true;
                    } else {
                        // Per validare la presenza del cap, basta aggiungere && cap a questa espressione.
                        return !!(_.isObject(provincia) && provincia.codProvincia && _.isObject(comune) && comune.codComune);
                    }
                } else {
                    return false;
                }
            };

            $svc.getZoomLevel = function (northEast, southWest) {
                var WORLD_DIM = {height: 256, width: 256};
                var ZOOM_MAX = 21;

                function latRad(lat) {
                    var sin = Math.sin(lat * Math.PI / 180);
                    var radX2 = Math.log((1 + sin) / (1 - sin)) / 2;
                    return Math.max(Math.min(radX2, Math.PI), -Math.PI) / 2;
                }

                function zoom(mapPx, worldPx, fraction) {
                    return Math.floor(Math.log(mapPx / worldPx / fraction) / Math.LN2);
                }

                var latFraction = (latRad(northEast.lat) - latRad(southWest.lat)) / Math.PI;

                var lngDiff = northEast.lng - southWest.lng;
                var lngFraction = ((lngDiff < 0) ? (lngDiff + 360) : lngDiff) / 360;

                var latZoom = zoom(400, WORLD_DIM.height, latFraction);
                var lngZoom = zoom(800, WORLD_DIM.width, lngFraction);

                return Math.min(latZoom, lngZoom, ZOOM_MAX);
            };

            $svc.buildIndirizzo = function (luogoDto, indirizzo) {
                
                var via = indirizzo || "";
                var location = "";

                if (_.isObject(luogoDto)) {
                    if (luogoDto.descrizioneComune) {
                        location = luogoDto.descrizioneComune;
                    } else {
                        location = luogoDto.descrizioneNazione;
                    }
                }

                return (via ? via + ", " + location : location);
                
            };

        }
    ]
);