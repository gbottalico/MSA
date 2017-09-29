(function () {
    "use strict";

    app.component('msaPlace', {
        templateUrl: '../../app/component/common/place/components/templates/place-tpl.html',
        bindings: {
            result: "=",
            input: "<",
            name: "<",
            required: "<"
        },
        controller: ("msaPlaceController", ['_', '$scope', '$debugMode', 'PlacesSvc', 'UtilSvc', 'DebugSvc', function (_, $scope, $debugMode, PlacesSvc, UtilSvc, DebugSvc) {

            var $ctrl = this;
            $scope.$debugMode = $debugMode;
            $scope.name = $ctrl.name || "place" + Date.now();
            $ctrl.isInputConsumed = false;

            $ctrl.options = {
                ID_ITALIA: 1
            };

            $ctrl.searchComuneByDescrizione = false;
            $ctrl.comuniByDescrizione = false;
            $ctrl.nazioneSelezionata = undefined;
            $ctrl.provinciaSelezionata = undefined;
            $ctrl.comuneSelezionato = undefined;
            $ctrl.capSelezionato = undefined;
            $ctrl.caps = [];

            if ($ctrl.result === undefined) {
                $ctrl.result = {};
            }

            $ctrl.getNazioni = function (nomeNazione) {
                return PlacesSvc.getNazioni(nomeNazione).then(function (response) {
                    return response.data.result;
                });
            };

            $ctrl.getProvince = function (nomeProvincia) {
                if (_.isObject($ctrl.nazioneSelezionata) && $ctrl.nazioneSelezionata.codNazione) {
                    return PlacesSvc.getProvince($ctrl.nazioneSelezionata.codNazione, nomeProvincia).then(function (response) {
                        return response.data.result;
                    });
                } else {
                    return [{
                        id: null,
                        descProvincia: "Nazione non valida."
                    }];
                }
            };

            $ctrl.getComuni = function (nomeComune) {
                if (_.isObject($ctrl.nazioneSelezionata) && _.isObject($ctrl.provinciaSelezionata) && $ctrl.provinciaSelezionata.codProvincia) {
                    return PlacesSvc.getComuni($ctrl.nazioneSelezionata.codNazione, $ctrl.provinciaSelezionata.codProvincia, nomeComune).then(function (response) {
                        $ctrl.searchComuneByDescrizione = false;
                        return response.data.result;
                    });
                } else {
                    return PlacesSvc.getComuniByDescrizione(nomeComune).then(function (response) {
                        $ctrl.searchComuneByDescrizione = true;
                        $ctrl.comuniByDescrizione = response.data.result;
                        var comuni = [];
                        response.data.result.forEach(function (elem, index) {
                            var comune = {
                                codComune: elem.codComune,
                                descrizione: elem.descrizioneComune
                            };
                            comuni.push(comune);
                        });
                        return comuni;
                    });
                }
            };

            $ctrl.isFormEmpty = function () {
                return (!$ctrl.nazioneSelezionata || $ctrl.nazioneSelezionata.length === 0) &&
                    (!$ctrl.provinciaSelezionata || $ctrl.provinciaSelezionata.length === 0) &&
                    (!$ctrl.comuneSelezionato || $ctrl.comuneSelezionato.length === 0);

            };

            $ctrl.bindPlace = function (place) {
                if (_.isObject(place)) {
                    var nazione = {
                        codNazione: place.codNazione,
                        descrizione: place.descrizioneNazione
                    };
                    nazione = place.codNazione ? nazione : null;

                    var provincia = {
                        codProvincia: place.codProvincia,
                        descProvincia: place.descrizioneProvincia
                    };
                    provincia = place.codProvincia ? provincia : null;

                    var comune = {
                        codComune: place.codComune,
                        descrizione: place.descrizioneComune
                    };
                    comune = place.codComune ? comune : null;

                    var q = place.cap ? PlacesSvc.getCapsByIdComune(place.codComune) : UtilSvc.createPromise([]);
                    q.then(function (response) {

                        $ctrl.nazioneSelezionata = nazione;
                        $ctrl.provinciaSelezionata = provincia;
                        $ctrl.comuneSelezionato = comune;

                        // TODO RIVEDERE
                        $ctrl.caps = response.data.result || place.caps || [];
                        $ctrl.capSelezionato = place.cap;

                    });
                    $ctrl.isInputConsumed = true;
                }
            };
            
            $scope.$watch(
                function watchPlaces(scope) {
                    return {
                        nazioneSelezionata: $ctrl.nazioneSelezionata,
                        provinciaSelezionata: $ctrl.provinciaSelezionata,
                        comuneSelezionato: $ctrl.comuneSelezionato,
                        capSelezionato: $ctrl.capSelezionato,
                        input: $ctrl.input
                    };
                },
                function handlePlacesChange(newValue, oldValue) {

                    // Sblanco i campi più specifici, se i più generici non sono più validi.

                    if (newValue.nazioneSelezionata !== oldValue.nazioneSelezionata) {
                        if (!_.isObject(newValue.nazioneSelezionata)) {
                            $ctrl.result.nazione = undefined;

                            $ctrl.provinciaSelezionata = undefined;
                            $ctrl.result.provincia = undefined;

                            $ctrl.comuneSelezionato = undefined;
                            $ctrl.result.comune = undefined;

                            $ctrl.caps = [];
                        } else {
                            $ctrl.result.nazione = newValue.nazioneSelezionata;
                        }
                    }

                    if (newValue.provinciaSelezionata !== oldValue.provinciaSelezionata) {

                        if (!_.isObject(newValue.provinciaSelezionata)) {
                            $ctrl.result.provincia = undefined;

                            $ctrl.comuneSelezionato = undefined;
                            $ctrl.result.comune = undefined;

                            $ctrl.caps = [];
                        } else {
                            $ctrl.result.provincia = newValue.provinciaSelezionata;
                        }
                    }

                    if (newValue.comuneSelezionato !== oldValue.comuneSelezionato) {
                        if (!_.isObject(newValue.comuneSelezionato)) {
                            $ctrl.result.comune = undefined;
                            $ctrl.capSelezionato = undefined;
                            $ctrl.caps = [];
                        } else {

                            if($ctrl.searchComuneByDescrizione) {

                                var comune = undefined;
                                $ctrl.comuniByDescrizione.forEach(function (elem, index) {
                                    if(elem.codComune === newValue.comuneSelezionato.codComune)
                                        comune = elem;
                                });
                                $ctrl.bindPlace(comune);
                                $ctrl.comuniByDescrizione = undefined;
                                $ctrl.searchComuneByDescrizione = false;

                            } else{
                                if(_.isObject(newValue.comuneSelezionato.cap)) {
                                    $ctrl.caps = newValue.comuneSelezionato.cap;
                                }
                                if (newValue.comuneSelezionato.cap !== undefined &&
                                    newValue.comuneSelezionato.cap.length === 1) {
                                    $ctrl.capSelezionato = newValue.comuneSelezionato.cap[0];
                                }
                            }

                            $ctrl.result.comune = newValue.comuneSelezionato;

                        }
                    }

                    $ctrl.result.cap = newValue.capSelezionato;

                    if (!$ctrl.isInputConsumed) {
                        $ctrl.bindPlace(newValue.input);
                    }

                    $ctrl.$valid = false;
                    if ($ctrl.required) {
                        $ctrl.$valid = PlacesSvc.isValidPlace($ctrl.result.nazione, $ctrl.result.provincia, $ctrl.result.comune, $ctrl.result.cap);
                    } else {
                        $ctrl.$valid = PlacesSvc.isValidPlace($ctrl.result.nazione, $ctrl.result.provincia, $ctrl.result.comune, $ctrl.result.cap) || $ctrl.isFormEmpty();
                    }
                    $scope[$scope.name].$setValidity("place", $ctrl.$valid, $ctrl);

                }, true
            );


        }])
    });

}());
