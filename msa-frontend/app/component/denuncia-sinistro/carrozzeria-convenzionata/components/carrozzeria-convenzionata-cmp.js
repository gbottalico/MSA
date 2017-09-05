(function () {
    "use strict";

    app.component('msaCarrozzeriaConvenzionata', {
        templateUrl: '../../app/component/denuncia-sinistro/carrozzeria-convenzionata/components/templates/carrozzeria-convenzionata-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "<",
            sinistroProvvisorio: "<",
            tempSegnalazione: "="
        },
        controller: ("carrozzeriaConvenzionataController", ['$MSAC', '$rootScope', '$scope', '$debugMode', 'PlacesSvc', 'DebugSvc', 'SinistriSvc',
            function ($MSAC, $rootScope, $scope, $debugMode, PlacesSvc, DebugSvc, SinistriSvc) {

                var $ctrl = this;
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.map = {
                    center: {},
                    zoom: 13,
                    options: {
                        styles: $MSAC.MAPS_THEME
                    },
                    events: {
                        zoom_changed: function (maps, eventName, args) {
                            $ctrl.checkInBound(maps, eventName, args);
                        },
                        dragend: function (maps, eventName, args) {
                            $ctrl.checkInBound(maps, eventName, args);
                        },
                        bounds_changed: function (maps, eventName, args) {
                            $ctrl.checkInBound(maps, eventName, args);
                        }
                    }
                };

                $ctrl.markers = [];
                $ctrl.visibleMarkers = [];
                $ctrl.indirizzo = undefined;

                $ctrl.cercaCarrozzerie = function () {
                    $ctrl.cerca($ctrl.indirizzo);
                };

                $ctrl.cerca = function (indirizzo) {
                    PlacesSvc.getGeoconding(indirizzo).then(function (response) {
                        DebugSvc.log("Geocoding", response);
                        try {
                            var lat = response.data.results["0"].geometry.location.lat;
                            var lng = response.data.results["0"].geometry.location.lng;
                            $scope.map.center.latitude = lat;
                            $scope.map.center.longitude = lng;
                            return SinistriSvc.getCarrozzerie(response.data.results["0"].formatted_address);
                        } catch (error) {
                            DebugSvc.log("Error", error);
                            return UtilSvc.createErrorStatePromise();
                        }
                    }).then(function (response) {
                        DebugSvc.log("getCarrozzerie", response);
                        if (response.data.status === 200) {
                            $ctrl.makeMarkers(response.data.result);
                        } else {
                            //TODO errore
                        }
                    });
                };

                $ctrl.makeMarkers = function (carrozzerie) {
                    var temp = [];
                    carrozzerie.forEach(function (carrozzeria, index) {
                        var marker = {};
                        marker.id = carrozzeria.id;
                        marker.latitude = carrozzeria.latitudine;
                        marker.longitude = carrozzeria.longitudine;
                        marker.nome = carrozzeria.denominazione;
                        temp.push(marker);
                    });
                    $ctrl.markers = temp;
                };

                $ctrl.markerClick = function (instance, event, marker) {
                    DebugSvc.log("markerClick", instance);

                    try {
                        $ctrl.infowindow.close();
                    } catch (err) {
                    }

                    /* DOCS: https://developers.google.com/maps/documentation/javascript/examples/infowindow-simple */
                    $ctrl.infowindow = new google.maps.InfoWindow({
                        content: '<h1>' + marker.nome + '</h1>'
                    });
                    $ctrl.infowindow.open(instance.map, instance);

                };

                $scope.markerClick = function (instance, event, marker) {
                    $ctrl.markerClick(instance, event, marker);
                };

                $ctrl.checkInBound = function (map, event, argz) {
                    var bounds = map.getBounds();
                    var visibleMarkers = [];

                    $ctrl.markers.forEach(function (element, index) {
                        var latLng = new google.maps.LatLng(element.latitude, element.longitude);
                        if(bounds.contains(latLng)) {
                            visibleMarkers.push(element);
                        }
                    });

                    $ctrl.visibleMarkers = visibleMarkers;
                    DebugSvc.log("VisibileMarkers", $ctrl.visibleMarkers);
                };

                $ctrl.bindCarrozzeriaConvenzionata = function () {
                    if ($ctrl.sinistroProvvisorio.contraente !== undefined && $ctrl.sinistroProvvisorio.contraente !== null &&
                        $ctrl.sinistroProvvisorio.contraente.tracking !== undefined && $ctrl.sinistroProvvisorio.contraente.tracking !== null) {

                        var promise = undefined;

                        if ($ctrl.sinistroProvvisorio.contraente.tracking.comune !== undefined && $ctrl.sinistroProvvisorio.contraente.tracking.comune !== null) {
                            promise = PlacesSvc.getComuneById($ctrl.sinistroProvvisorio.contraente.tracking.comune);
                        } else {
                            promise = PlacesSvc.getNazioneById($ctrl.sinistroProvvisorio.contraente.tracking.nazione);
                        }
                        promise.then(function (response) {
                            $ctrl.indirizzo = $ctrl.sinistroProvvisorio.contraente.tracking.indirizzo + ", " + response.data.result;
                            $ctrl.cerca($ctrl.indirizzo);
                        });

                    }
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {
                            sinistroProvvisorio: $ctrl.sinistroProvvisorio
                        };
                    },
                    function handleChanges(newValues, oldValues) {

                        if (newValues.sinistroProvvisorio !== undefined) {
                            $ctrl.bindCarrozzeriaConvenzionata();
                        }

                    }, true
                );


            }])
    });

}());