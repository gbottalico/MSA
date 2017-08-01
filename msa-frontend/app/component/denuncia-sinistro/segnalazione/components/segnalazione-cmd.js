(function () {
	"use strict";

	app.component('msaSegnalazione', {
	    templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
	    bindings: {
	    	valoriRicerca: '='
	    },
	    controller: ("segnalazioneController", ['$scope', '$rootScope', '$translate', '$log', 'AccountUserSvc', 'MezziComunicazioneSvc', 'PlacesSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
	        function($scope, $rootScope, $translate, $log, AccountUserSvc, MezziComunicazioneSvc, PlacesSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        ctrl.mezzicomunicazione = undefined;

	        MezziComunicazioneSvc.getMezziComunicazione().then(function (response) {
                ctrl.mezzicomunicazione = response.data.result;
            });

	        /* Gestione calendari */

	        ctrl.today = function() {
                ctrl.dataDenuncia = new Date();
                ctrl.dataSinistro = new Date();
            };

	        ctrl.today();

	        ctrl.dateOptions = {
                formatYear: 'yy',
                startingDay: 1
            };

	        ctrl.openDataDenuncia = function() {
                ctrl.popup1.opened = true;
            };

	        ctrl.openDataSinistro = function() {
                ctrl.popup2.opened = true;
            };

            ctrl.setDataDenuncia = function(year, month, day) {
                ctrl.dataDenuncia = new Date(year, month, day);
            };

            ctrl.setDataSinistro = function(year, month, day) {
                ctrl.dataSinistro = new Date(year, month, day);
            };

            ctrl.format = "dd-MM-yyyy";

            ctrl.popup1 = {
                opened: false
            };

            ctrl.popup2 = {
                opened: false
            };

            /* Gestione Location */

            ctrl.nazioneSelezionata = undefined;
            ctrl.provinciaSelezionata = undefined;
            ctrl.comuneSelezionato = undefined;
            ctrl.caps = [];

            ctrl.getNazioni = function(nomeNazione) {
                return PlacesSvc.getNazioni(nomeNazione).then(function (response) {
                    return response.data.result;
                });
            };

            ctrl.getProvince = function(nomeProvincia) {
              if(ctrl.hasId(ctrl.nazioneSelezionata)) {
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

            ctrl.getComuni = function(nomeComune) {
              if(ctrl.hasId(ctrl.nazioneSelezionata) && ctrl.hasId(ctrl.provinciaSelezionata)) {
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
                    return{
                        nazsel: ctrl.nazioneSelezionata,
                        provsel: ctrl.provinciaSelezionata,
                        comsel: ctrl.comuneSelezionato
                    };
                },
                function handlePlacesChange(newValue, oldValue) {

                    // Sblanco i campi più specifici, se i più generici non sono più validi.

                    if(newValue.nazsel !== oldValue.nazsel)  {
                        if(!(newValue.nazsel instanceof Object)) {
                            ctrl.provinciaSelezionata = undefined;
                            ctrl.comuneSelezionato = undefined;
                            ctrl.caps = [];
                        }
                    }

                    if(newValue.provsel !== oldValue.provsel) {
                        if(!(newValue.provsel instanceof Object)) {
                            ctrl.comuneSelezionato = undefined;
                            ctrl.caps = [];
                        }
                    }

                    if(newValue.comsel !== oldValue.comsel) {
                        if(!(newValue.comsel instanceof Object)) {
                            ctrl.caps = [];
                        } else {
                            ctrl.caps = newValue.comsel.cap;
                            console.log(ctrl.caps);
                        }
                    }


                }, true
            );

            ctrl.tipiStrada = ['Via', 'Viale', 'Piazza'];

            /* Utilities */

            ctrl.hasId = function (obj) {
                return(
                    obj !== undefined &&
                    obj !== null &&
                    obj.id !== undefined &&
                    obj.id !== null);
            };

	        ctrl.back = function() {
	        	ctrl.valoriRicerca = undefined;
	        };

	    }])
	});
	
}());