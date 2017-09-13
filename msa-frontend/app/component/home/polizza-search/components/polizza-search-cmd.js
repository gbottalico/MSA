(function () {
    "use strict";

    app.component('msaPolizzaSearch', {
        templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
        bindings: {
            valoriRicerca: "="
        },
        controller: ("polizzaSearchController", ["$scope", '$rootScope', '$translate', '$filter', 'DomainSvc', 'SinistriSvc', 'PlacesSvc', 'toastr', '$analytics', '$location', '$anchorScroll', '$uibModal', '$cookies', '$window', '$sessionStorage', 'DebugSvc',
            function ($scope, $rootScope, $translate, $filter, DomainSvc, SinistriSvc, PlacesSvc, toastr, $analytics, $location, $anchorScroll, $uibModal, $cookies, $window, $sessionStorage, DebugSvc) {

                var $ctrl = this;
                var $translate = $filter('translate');
                var parent = $scope.$parent;
                $ctrl.ricerca = false;

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

                    // DomainSvc.successCall().then(function (response) {
                    //    DebugSvc.log("successCall", response);
                    //    return DomainSvc.successCall();
                    // }).then(function (response) {
                    //     DebugSvc.log("successCall", response);
                    //     return DomainSvc.successCall();
                    // }).catch(function (error) {
                    //     DebugSvc.log("catch", error);
                    // });

                };

                $ctrl.campiObbligatori = {
                    cognome: false,
                    nome: false,
                    tipoPersona: false,
                    numeroPolizza: false,
                    numeroSinistro: false,
                    dataEvento: false,
                    targa: false,
                    numeroProvvisorio: false,
                    numeroPreapertura: false
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
                                        case 6:
                                            $ctrl.campiObbligatori.dataEvento = campiObbligatoriRicerca[i].required;
                                            break;
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
                	console.log($ctrl.ricercapolizza)
                	$ctrl.ricerca = true;
                	setTimeout(function(){ 
                		var id = $location.hash();
                		$location.hash('risultatiPolizze');
                		$anchorScroll();
                		$location.hash(id); 
                	}, 1000);
                	
                };


            }])
    });

}());