(function () {
    "use strict";

    app.component('msaPolizzaSearch', {
        templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
        bindings: {
            valoriRicerca: "="
        },
        controller: ("polizzaSearchController", ['_', '$MSAC', '$scope', '$rootScope', '$filter', 'DomainSvc', 'SinistriSvc', '$sessionStorage',
            function (_, $MSAC, $scope, $rootScope, $filter, DomainSvc, SinistriSvc, $sessionStorage) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$storage = $sessionStorage;
                $scope.$MSAC = $MSAC;
                $ctrl.ricerca = false;

                $scope.$MSAC = $MSAC;
                $ctrl.today = new Date();

                $ctrl.casaRegole = undefined;
                $ctrl.numSinistroProvv = undefined;


                $ctrl.ricercaPolizza = SinistriSvc.getOggettoRicerca();

                $ctrl.$onInit = function () {
                    DomainSvc.getElencoRegole().then(function (response) {
                        $ctrl.casaRegole = response.data.result;
                    });

                    //FIXME rimuovere
                    // if ($scope.$storage.lastSearch) {
                    //     $ctrl.ricercaPolizza = $scope.$storage.lastSearch;
                    // }
                };

                $ctrl.campiObbligatori = {
                    cognome: false,
                    nome: false,
                    tipoPersona: false,
                    numeroPolizza: false,
                    numeroSinistro: false,
                    dataEvento: true,
                    targa: false,
                    numeroProvvisorio: false,
                    numeroPreapertura: false
                };

                $ctrl.isSearchDisabled = function () {
                    return $scope.polizzaSearchForm.$invalid || !_.isObject($ctrl.ricercaPolizza.compagniaSelezionata);
                };

                $ctrl.checkCompagnia = function () {
                    return ($scope.polizzaSearchForm.compagnia.$invalid);
                };

                $ctrl.cerca = function () {
                    parent.cerca($ctrl.ricercaPolizza);
                };

                $scope.$watch(
                    function watch(scope) {
                        return {
                            compagniaSelezionata: $ctrl.ricercaPolizza.compagniaSelezionata
                        };
                    },
                    function handleChanges(newValue, oldValue) {

                        if (newValue.compagniaSelezionata !== oldValue.compagniaSelezionata) {
                            if (newValue.compagniaSelezionata instanceof Object &&
                                newValue.compagniaSelezionata !== null) {

                                // Eseguo il binding dei campi obbligatori.
                                var campiObbligatoriRicerca = newValue.compagniaSelezionata.campiObbligatoriRicerca;
                                for (var i = 0; i < campiObbligatoriRicerca.length; i++) {
                                    switch (campiObbligatoriRicerca[i].idFE) {
                                        case 1:
                                            $ctrl.campiObbligatori.cognome = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 2:
                                            $ctrl.campiObbligatori.nome = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 3:
                                            $ctrl.campiObbligatori.tipoPersona = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 4:
                                            $ctrl.campiObbligatori.numeroPolizza = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 5:
                                            $ctrl.campiObbligatori.numeroSinistro = campiObbligatoriRicerca[i].required;
                                            break;
                                        // case 6:
                                        //     $ctrl.campiObbligatori.dataEvento = campiObbligatoriRicerca[i].required;
                                        //     break;
                                        case 7:
                                            $ctrl.campiObbligatori.targa = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 8:
                                            $ctrl.campiObbligatori.numeroProvvisorio = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 9:
                                            $ctrl.campiObbligatori.numeroPreapertura = campiObbligatoriRicerca[i].required;
                                            break;

                                    }
                                }
                            }
                        }

                    }, true
                );

            }])
    });

}());