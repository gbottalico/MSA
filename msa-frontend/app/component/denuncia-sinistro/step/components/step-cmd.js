(function () {
	"use strict";

	app.component('msaStep', {
	    templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
	    bindings: {
	    	valoriRicerca: '='
	    },
	    controller: ("stepController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;
	        ctrl.step = 1;

	        ctrl.user = {
	        	nome: "Piras Dario",
	        	cf: "PRSDRA87E28B157S",
	        	nascita: "Brescia (BS) 15/01/1960",
	        	residenza: "Via Raffaello Sanzio, 11 Brescia (BS)",
	        	recapiti: "3332363880 info@email.it"
	        }

	    }])
	});
	
}());