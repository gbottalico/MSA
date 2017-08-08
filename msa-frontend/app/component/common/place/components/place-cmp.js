(function () {
    "use strict";

    app.component('msaPlace', {
        templateUrl: '../../app/component/common/place/components/templates/place-tpl.html',
        bindings: {
            result: "="
        },
        controller: ("msaPlaceController", ['$scope', 'PlacesSvc', function ($scope, PlacesSvc, result) {

            var ctrl = this;

            ctrl.nazioneSelezionata = undefined;
            ctrl.provinciaSelezionata = undefined;
            ctrl.comuneSelezionato = undefined;
            ctrl.capSelezionato = undefined;
            ctrl.caps = [];

            if (ctrl.result === undefined) {
                ctrl.result = {};
            }

            ctrl.getNazioni = function (nomeNazione) {
                return PlacesSvc.getNazioni(nomeNazione).then(function (response) {
                    return response.data.result;
                });
            };

            ctrl.getProvince = function (nomeProvincia) {
                if (ctrl.hasId(ctrl.nazioneSelezionata)) {
                    return PlacesSvc.getProvince(ctrl.nazioneSelezionata.id, nomeProvincia).then(function (response) {
                        return response.data.result;
                    });
                } else {
                    return [{
                        id: null,
                        descProvincia: "Nazione non valida."
                    }];
                }
            };

            ctrl.getComuni = function (nomeComune) {
                if (ctrl.hasId(ctrl.nazioneSelezionata) && ctrl.hasId(ctrl.provinciaSelezionata)) {
                    return PlacesSvc.getComuni(ctrl.nazioneSelezionata.id, ctrl.provinciaSelezionata.codProvincia, nomeComune).then(function (response) {
                        return response.data.result;
                    });
                } else {
                    return [{
                        id: null,
                        descrizione: "Provincia non valida."
                    }];
                }
            };

            $scope.$watch(
                function watchPlaces(scope) {
                    return {
                        nazsel: ctrl.nazioneSelezionata,
                        provsel: ctrl.provinciaSelezionata,
                        comsel: ctrl.comuneSelezionato,
                        capsel: ctrl.capSelezionato
                    };
                },
                function handlePlacesChange(newValue, oldValue) {

                    // Sblanco i campi più specifici, se i più generici non sono più validi.

                    if (newValue.nazsel !== oldValue.nazsel) {
                        if (!(newValue.nazsel instanceof Object)) {
                            ctrl.result.nazione = undefined;

                            ctrl.provinciaSelezionata = undefined;
                            ctrl.result.provincia = undefined;

                            ctrl.comuneSelezionato = undefined;
                            ctrl.result.comune = undefined;

                            ctrl.caps = [];
                        } else {
                            ctrl.result.nazione = newValue.nazsel;
                        }
                    }

                    if (newValue.provsel !== oldValue.provsel) {
                        if (!(newValue.provsel instanceof Object)) {
                            ctrl.result.provincia = undefined;

                            ctrl.comuneSelezionato = undefined;
                            ctrl.result.comune = undefined;

                            ctrl.caps = [];
                        } else {
                            ctrl.result.provincia = newValue.provsel;
                        }
                    }

                    if (newValue.comsel !== oldValue.comsel) {
                        if (!(newValue.comsel instanceof Object)) {
                            ctrl.result.comune = undefined;

                            ctrl.caps = [];
                        } else {
                            ctrl.caps = newValue.comsel.cap;
                            if(newValue.comsel.cap.length === 1) {
                                ctrl.capSelezionato = newValue.comsel.cap[0];
                            }
                            ctrl.result.comune = newValue.comsel;
                        }
                    }

                    ctrl.result.cap = newValue.capsel;

                    ctrl.result.$valid = PlacesSvc.isValidPlace(ctrl.result.nazione, ctrl.result.provincia, ctrl.result.comune);

                }, true
            );


            ctrl.hasId = function (obj) {
                return (
                    obj !== undefined &&
                    obj !== null &&
                    obj.id !== undefined &&
                    obj.id !== null);
            };

        }])
    });

}());
