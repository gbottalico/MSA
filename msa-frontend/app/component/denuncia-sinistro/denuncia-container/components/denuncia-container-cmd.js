(function () {
	"use strict";

	app.component('msaDenunciaContainer', {
	    templateUrl: '../../app/component/denuncia-sinistro/denuncia-container/components/templates/denuncia-container-tpl.html',
	    bindings: {
	    	valoriRicerca: '='
	    },
	    controller: ("denunciaContainerController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});
	
}());