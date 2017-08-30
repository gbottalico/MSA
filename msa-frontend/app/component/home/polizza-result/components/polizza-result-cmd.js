(function () {
	"use strict";

	app.component('msaPolizzaResult', {
	    templateUrl: '../../app/component/home/polizza-result/components/templates/polizza-result-tpl.html',
	    bindings: {
	    	valoriRicerca: '='
	    },
	    controller: ("polizzaResultController", ['$rootScope', '$translate', '$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
	        function($rootScope, $translate, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        ctrl.back = function() {
	        	ctrl.valoriRicerca = undefined;
	        };

	    }])
	});
	
}());