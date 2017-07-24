(function () {
	"use strict";

	app.component('msaPolizza', {
	    templateUrl: '../../app/component/home/polizza/components/templates/polizza-tpl.html',
	    bindings: {},
	    controller: ("polizzaController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});
	
}());