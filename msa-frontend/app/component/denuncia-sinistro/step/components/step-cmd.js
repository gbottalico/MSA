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

	        ctrl.back = function() {
	        	ctrl.valoriRicerca = undefined;
	        };

	    }])
	});
	
}());