(function () {
    "use strict";

    app.component('msaCarrozzeriaConvenzionata', {
        templateUrl: '../../app/component/denuncia-sinistro/carrozzeria-convenzionata/components/templates/carrozzeria-convenzionata-tpl.html',
        bindings: {
            numeroSinistroProvvisorio: "=",
            sinistroProvvisorio: "=",
            tempSegnalazione: "="
        },
        controller: ("carrozzeriaConvenzionataController", ['$rootScope', '$scope', '$debugMode', 'PlacesSvc', 'DebugSvc', 'SinistriSvc',
            function ($rootScope, $scope, $debugMode, PlacesSvc, DebugSvc, SinistriSvc) {

                var $ctrl = this;
                var parent = $scope.$parent;
                $scope.$debugMode = $debugMode;
                $scope.map = {
                    center: {},
                    zoom: 13,
                    options: {
                        styles: [
                            {
                                "stylers": [
                                    {
                                        "hue": "#0f8fd1"
                                    },
                                    {
                                        "saturation": 255
                                    }
                                ]
                            },
                            {
                                "featureType": "road",
                                "elementType": "geometry",
                                "stylers": [
                                    {
                                        "lightness": 50
                                    },
                                    {
                                        "visibility": "simplified"
                                    }
                                ]
                            },
                            {
                                "featureType": "road",
                                "elementType": "labels",
                                "stylers": [
                                    {
                                        "visibility": "simplified"
                                    }
                                ]
                            }
                        ]
                    },
                    // icon: {
                    //     path: fontawesome.markers.EXCLAMATION,
                    //     scale: 0.5,
                    //     strokeWeight: 0.2,
                    //     strokeColor: 'black',
                    //     strokeOpacity: 1,
                    //     fillColor: '#f8ae5f',
                    //     fillOpacity: 0.7
                    // }
                };


                $ctrl.markers = [];

                PlacesSvc.getGeoconding("Via Orfeo Mazzitelli, Bari").then(function (response) {
                    DebugSvc.log("Geocoding", response);
                    var lat = response.data.results["0"].geometry.location.lat;
                    var lng = response.data.results["0"].geometry.location.lng;

                    $scope.map.center.latitude = lat;
                    $scope.map.center.longitude = lng;
                    return SinistriSvc.getCarrozzerie(response.data.results["0"].formatted_address);
                }).then(function (response) {
                    DebugSvc.log("getCarrozzerie", response);
                    $ctrl.makeMarkers(response.data.result);
                });

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
                    } catch (err) {}

                    /* DOCS: https://developers.google.com/maps/documentation/javascript/examples/infowindow-simple */
                    $ctrl.infowindow = new google.maps.InfoWindow({
                        content: '<h1>' + marker.nome + '</h1>'
                    });

                    $ctrl.infowindow.open(instance.map, instance);

                };

                $scope.markerClick = function(instance, event, marker) {
                    $ctrl.markerClick(instance, event, marker);
                };

                $scope.$watch(
                    function watchScope(scope) {
                        return {};
                    },
                    function handleChanges(newValues, oldValues) {

                    }, true
                );


            }])
    });

}());