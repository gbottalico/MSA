(function () {
	"use strict";

	app.component('msaPolizzaSearch', {
	    templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
	    bindings: {},
	    controller: ("polizzaSearchController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        ctrl.listacompagnie = [
	        	'Compagnia con il nome molto lungo',
				'Come corto',
				'Ciao',
				'Gianluca spa',
				'Ammaccabanana',
				'Prova per filtro',
				'Termostato',
				'Elenco',
				'Funziona'
	        ];

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
	        	compagnie: {elenco: ctrl.listacompagnie},
	        	compagniaselected: undefined
	        };

	        ctrl.compagniaSelected = function(compagnia) {
	        	ctrl.ricercapolizza.compagniaselected = compagnia;
	        	ctrl.open.compagnia = !ctrl.open.compagnia;
	        };

	        ctrl.valoriricerca = undefined;

	        ctrl.cerca = function() {
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
		    };

	    }])
	});
	
}());