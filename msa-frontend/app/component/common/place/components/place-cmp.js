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
            ctrl.caps = [];

            if(ctrl.result === undefined) {
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
                        id: "-1",
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
                        id: "-1",
                        descrizione: "Provincia non valida."
                    }];
                }
            };

            $scope.$watch(
                function watchPlaces(scope) {
                    return {
                        nazsel: ctrl.nazioneSelezionata,
                        provsel: ctrl.provinciaSelezionata,
                        comsel: ctrl.comuneSelezionato
                    };
                },
                function handlePlacesChange(newValue, oldValue) {

                    // Sblanco i campi più specifici, se i più generici non sono più validi.

                    if (newValue.nazsel !== oldValue.nazsel) {
                        if (!(newValue.nazsel instanceof Object)) {
                            ctrl.provinciaSelezionata = undefined;
                            ctrl.comuneSelezionato = undefined;
                            ctrl.caps = [];
                        } else {
                            ctrl.result.nazione = newValue.nazsel;
                        }
                    }

                    if (newValue.provsel !== oldValue.provsel) {
                        if (!(newValue.provsel instanceof Object)) {
                            ctrl.comuneSelezionato = undefined;
                            ctrl.caps = [];
                        } else {
                            ctrl.result.provincia = newValue.provsel;
                        }
                    }

                    if (newValue.comsel !== oldValue.comsel) {
                        if (!(newValue.comsel instanceof Object)) {
                            ctrl.caps = [];
                        } else {
                            ctrl.caps = newValue.comsel.cap;
                            ctrl.result.comune = newValue.comsel;
                        }
                    }


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