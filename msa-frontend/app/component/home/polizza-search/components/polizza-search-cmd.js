(function () {
    "use strict";

    app.component('msaPolizzaSearch', {
        templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
        bindings: {
            valoriRicerca: "="
        },
        controller: ("polizzaSearchController", ['_', '$MSAC', '$scope', '$rootScope', '$filter', 'DomainSvc', '$sessionStorage',
            function (_, $MSAC, $scope, $rootScope, $filter, DomainSvc, $sessionStorage) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $scope.$storage = $sessionStorage;
                $ctrl.ricerca = false;

                $scope.$MSAC = $MSAC;

                $ctrl.casaRegole = undefined;
                $ctrl.numSinistroProvv = undefined;

                
                $ctrl.ricercapolizza = {
	                compagniaSelezionata: '',
                    cognome: '',
                    nome: '',
                    tipoPersona: undefined,
                    numeroPolizza: '',
                    numeroSinistro: '',
                    dataEvento: '',
                    targa: '',
                    numeroProvvisorio: '',
                    numeroPreapertura: ''
                };

                $ctrl.$onInit = function () {
                    DomainSvc.getElencoRegole().then(function (data) {
                        $ctrl.casaRegole = data.result;
                    });

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
                    return $scope.polizzaSearchForm.$invalid || !_.isObject($ctrl.ricercapolizza.compagniaSelezionata);
                };

                $ctrl.checkCompagnia = function () {
                    return ($scope.polizzaSearchForm.compagnia.$invalid || !_.isObject($ctrl.ricercapolizza.compagniaSelezionata)) &&
                        !$scope.polizzaSearchForm.compagnia.$pristine;
                };

                $scope.$watch(
                    function watch(scope) {
                        return {
                            compagniaSelezionata: $ctrl.ricercapolizza.compagniaSelezionata
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

                $ctrl.cerca = function () {
                	$ctrl.ricerca = true;
                    $scope.$storage.lastSearch = $ctrl.ricercapolizza;
                };


            }])
    });

}());