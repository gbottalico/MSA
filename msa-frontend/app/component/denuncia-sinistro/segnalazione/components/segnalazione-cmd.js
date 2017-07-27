(function () {
	"use strict";

	app.component('msaSegnalazione', {
	    templateUrl: '../../app/component/denuncia-sinistro/segnalazione/components/templates/segnalazione-tpl.html',
	    bindings: {
	    	valoriRicerca: '='
	    },
	    controller: ("segnalazioneController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        ctrl.back = function() {
	        	ctrl.valoriRicerca = undefined;
	        };

	    }])
	});
	
}());