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
                        return response.data.result;
                    });
                } else {
                    return [{
                        id: null,
                        descrizione: "Provincia non valida."
                    }];
                }
            };

            $ctrl.isFormEmpty = function () {
                return (!$ctrl.nazioneSelezionata || $ctrl.nazioneSelezionata.length === 0) &&
                    (!$ctrl.provinciaSelezionata || $ctrl.provinciaSelezionata.length === 0) &&
                    (!$ctrl.comuneSelezionato || $ctrl.comuneSelezionato.length === 0);

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
                            $ctrl.caps = [];
                        } else {

                            if(_.isObject(newValue.comuneSelezionato.cap)) {
                                $ctrl.caps = newValue.comuneSelezionato.cap;
                            }
                            if (newValue.comuneSelezionato.cap !== undefined &&
                                newValue.comuneSelezionato.cap.length === 1) {
                                $ctrl.capSelezionato = newValue.comuneSelezionato.cap[0];
                            }
                            $ctrl.result.comune = newValue.comuneSelezionato;
                        }
                    }

                    $ctrl.result.cap = newValue.capSelezionato;

                    if (!$ctrl.isInputConsumed) {
                        if (_.isObject(newValue.input)) {
                            var nazione = {
                                codNazione: newValue.input.codNazione,
                                descrizione: newValue.input.descrizioneNazione
                            };
                            var provincia = {
                                codProvincia: newValue.input.codProvincia,
                                descProvincia: newValue.input.descrizioneProvincia
                            };
                            var comune = {
                                codComune: newValue.input.codComune,
                                descrizione: newValue.input.descrizioneComune
                            };

                            var q = newValue.input.cap ? PlacesSvc.getCapsByIdComune(newValue.input.codComune) : UtilSvc.createPromise([]);
                            q.then(function (response) {

                                $ctrl.nazioneSelezionata = nazione;
                                $ctrl.provinciaSelezionata = provincia;
                                $ctrl.comuneSelezionato = comune;

                                $ctrl.caps = response.data.result;
                                $ctrl.capSelezionato = newValue.input.cap;

                            });
                        }

                        $ctrl.isInputConsumed = true;
                    }

                    $ctrl.$valid = false;
                    if ($ctrl.required) {
                        $ctrl.$valid = PlacesSvc.isValidPlace($ctrl.result.nazione, $ctrl.result.provincia, $ctrl.result.comune);
                    } else {
                        $ctrl.$valid = PlacesSvc.isValidPlace($ctrl.result.nazione, $ctrl.result.provincia, $ctrl.result.comune) || $ctrl.isFormEmpty();
                    }
                    $scope[$scope.name].$setValidity("place", $ctrl.$valid, $ctrl);

                }, true
            );


        }])
    });

}());
