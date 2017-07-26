(function () {
	"use strict";

	app.component('msaPolizzaSearch', {
	    templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
	    bindings: {},
	    controller: ("polizzaSearchController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        var listacompagnie = {
	        	compagnia1: 'Prova 1',
	        	compagnia2: 'Prova 2',
	        	compagnia3: 'Prova 3',
	        	compagnia4: 'Prova 4',
	        	compagnia5: 'Prova 5',
	        	compagnia6: 'Prova 6',
	        	compagnia7: 'Prova 7',
	        	compagnia8: 'Prova 8',
	        	compagnia9: 'Prova 9'
	        };

	        ctrl.ricercapolizza = {
	        	cognome: '',
	        	nome: '',
	        	tipopersona: undefined,
	        	numpoli: '',
	        	numsin: '',
	        	dataevento: '',
	        	targa: '',
	        	numprov: '',
	        	numpre: '',
	        	compagnie: listacompagnie,
	        	compagniaselected: undefined,
	        };

	        ctrl.compagniaSelected = function(compagnia) {
	        	ctrl.ricercapolizza.compagniaselected = compagnia;
	        	ctrl.open.compagnia = !ctrl.open.compagnia;
	        };

	        ctrl.valoriricerca = {
	        	cognome: ctrl.ricercapolizza.cognome,
	        	nome: ctrl.ricercapolizza.nome,
	        	tipopersona: ctrl.ricercapolizza.tipopersona,
	        	numpoli: ctrl.ricercapolizza.numpoli,
	        	numsin: ctrl.ricercapolizza.numsin,
	        	dataevento: ctrl.ricercapolizza.dataevento,
	        	targa: ctrl.ricercapolizza.targa,
	        	numprov: ctrl.ricercapolizza.numprov,
	        	numpre: ctrl.ricercapolizza.numpre,
	        	compagnia: ctrl.ricercapolizza.compagniaselected
	        };

	    }])
	});
	
}());