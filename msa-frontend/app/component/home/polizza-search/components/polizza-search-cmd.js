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

	        ctrl.valoriRicerca = undefined;

	        ctrl.cerca = function() {
		        ctrl.inputRicerca = {
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

		        ctrl.valoriRicerca = {
		        	user: {
			        	cognome: 'Piras',
			        	nome: 'Dario',
			        	cf: 'PRSDRA87E28B157S',
			        	luogonascita: 'Brescia',
			        	provincianascita: 'BS',
			        	datanascita: '15/01/1960',
			        	residenza: 'Brescia',
			        	provinciaresidenza: 'BS',
			        	indirizzo: 'Via Raffaello Sanzio, 11',
			        	telefono: '0331123456',
			        	cellulare: '3332363880',
			        	email: 'info@email.it'
			        },
			        polizze: [
			        	{
			        		numpoli: '100012058380',
			        		attivazione: '22/02/2016',
			        		scadenza: '22/02/2017',
			        		prodotto: 'Prodotto viaggia con me (clear box)',
			        		compagnia: 'Aviva',
			        		targabene: 'df832xb',
			        		contraente: 'Piras Dario',
			        		stato: 'Attivo',
			        		variazione: '18/02/2016',
			        		sinistri: [
			        			{
			        				nome: 'Sinistro',
			        				numsin: '100012058380',
			        				data: '23/03/2016',
			        				tipo: 'A',
			        				evento: 'Amet',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Sinistro provvisorio',
			        				numsin: '100017432380',
			        				data: '20/05/2016',
			        				tipo: 'B',
			        				evento: 'Consectetur',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Pre-codifica',
			        				numsin: '103212058363',
			        				data: '03/01/2017',
			        				tipo: 'C',
			        				evento: 'Super alta',
			        				denunciatoda: 'Anna Rossi',
			        				stato: 'Attivo'
			        			}
			        		]
			        	},
			        	{
			        		numpoli: '100012058380',
			        		attivazione: '22/02/2016',
			        		scadenza: '22/02/2017',
			        		prodotto: 'Prodotto viaggia con me (clear box)',
			        		compagnia: 'Aviva',
			        		targabene: 'df832xb',
			        		contraente: 'Piras Dario',
			        		stato: 'Attivo',
			        		variazione: '18/02/2016',
			        		sinistri: [
			        			{
			        				nome: 'Sinistro',
			        				numsin: '100012058380',
			        				data: '23/03/2016',
			        				tipo: 'A',
			        				evento: 'Amet',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Sinistro provvisorio',
			        				numsin: '100017432380',
			        				data: '20/05/2016',
			        				tipo: 'B',
			        				evento: 'Consectetur',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Pre-codifica',
			        				numsin: '103212058363',
			        				data: '03/01/2017',
			        				tipo: 'C',
			        				evento: 'Super alta',
			        				denunciatoda: 'Anna Rossi',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Pre-codifica',
			        				numsin: '103212058363',
			        				data: '03/01/2017',
			        				tipo: 'C',
			        				evento: 'Super alta',
			        				denunciatoda: 'Anna Rossi',
			        				stato: 'Attivo'
			        			}
			        		]
			        	},
			        	{
			        		numpoli: '100012058380',
			        		attivazione: '22/02/2016',
			        		scadenza: '22/02/2017',
			        		prodotto: 'Prodotto viaggia con me (clear box)',
			        		compagnia: 'Aviva',
			        		targabene: 'df832xb',
			        		contraente: 'Piras Dario',
			        		stato: 'Attivo',
			        		variazione: '18/02/2016',
			        		sinistri: [
			        			{
			        				nome: 'Sinistro',
			        				numsin: '100012058380',
			        				data: '23/03/2016',
			        				tipo: 'A',
			        				evento: 'Amet',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Sinistro provvisorio',
			        				numsin: '100017432380',
			        				data: '20/05/2016',
			        				tipo: 'B',
			        				evento: 'Consectetur',
			        				denunciatoda: 'Piras Dario',
			        				stato: 'Attivo'
			        			},
			        			{
			        				nome: 'Pre-codifica',
			        				numsin: '103212058363',
			        				data: '03/01/2017',
			        				tipo: 'C',
			        				evento: 'Super alta',
			        				denunciatoda: 'Anna Rossi',
			        				stato: 'Attivo'
			        			}
			        		]
			        	}
			        ]
		        };
		    };

	    }])
	});
	
}());