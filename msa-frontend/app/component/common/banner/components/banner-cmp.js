(function () {
	"use strict";

	app.component('msaBanner', {
	    templateUrl: '../../app/component/common/banner/components/templates/banner-tpl.html',
	    bindings: {},
	    controller: ("bannerController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});

}());

