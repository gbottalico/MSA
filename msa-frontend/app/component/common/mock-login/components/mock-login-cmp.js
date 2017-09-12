(function () {
	"use strict";

	app.component('mockLogin', {
	    templateUrl: '../../app/component/common/mock-login/components/templates/mock-login-tpl.html',
	    bindings: {},
	    controller: ("mockLoginController", ['$rootScope', '$translate', '$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
	        function($rootScope, $translate, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});

}());
 