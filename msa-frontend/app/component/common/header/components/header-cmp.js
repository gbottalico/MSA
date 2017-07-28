(function () {
	"use strict";

	app.component('msaHeader', {
	    templateUrl: '../../app/component/common/header/components/templates/header-tpl.html',
	    bindings: {},
	    controller: ("headerController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

			ctrl.reloadRoute = function() {
				$window.location.reload();
			};

	    }])
	});

}());
 