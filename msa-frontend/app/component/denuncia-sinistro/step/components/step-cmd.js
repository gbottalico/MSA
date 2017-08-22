(function () {
	"use strict";

	app.component('msaStep', {
	    templateUrl: '../../app/component/denuncia-sinistro/step/components/templates/step-tpl.html',
	    bindings: {
	    	valoriRicerca: '=',
            datiContraente: '='
	    },
	    controller: ("stepController", ['$rootScope', '$scope',
	        function($rootScope, $scope) {
	            
	        var ctrl = this;
	        ctrl.step = 1;

            ctrl.user = ctrl.datiContraente;

            console.log(ctrl.user);

	        // ctrl.user = {
	        // 	nome: "Piras Dario",
	        // 	cf: "PRSDRA87E28B157S",
	        // 	nascita: "Brescia (BS) 15/01/1960",
	        // 	residenza: "Via Raffaello Sanzio, 11 Brescia (BS)",
	        // 	recapiti: "3332363880 info@email.it"
	        // };

	    }])
	});
	
}());