(function () {
	"use strict";

	app.component('msaPolizzeSinistri', {
	    templateUrl: '../../app/component/home/polizze-sinistri-result/components/templates/polizze-sinistri-tpl.html',
	    bindings: {
	    	valoriRicerca: '=',
	    	bannerSearch: '=',
	    	bannerDenuncia: '='
	    },
	    controller: ("polizzeSinistriController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	        ctrl.denuncia = function() {
	        	ctrl.bannerSearch = false;
	        	ctrl.bannerDenuncia = true;
	        };

	    }])
	});
	
}());