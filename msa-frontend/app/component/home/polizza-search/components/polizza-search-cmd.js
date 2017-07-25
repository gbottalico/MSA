(function () {
	"use strict";

	app.component('msaPolizzaSearch', {
	    templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
	    bindings: {},
	    controller: ("polizzaSearchController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});
	
}());