(function () {
	"use strict";

	app.component('msaHome', {
	    templateUrl: '../../app/component/home/components/templates/home-tpl.html',
	    bindings: {

	    },
	    controller: ("homeController", ['$rootScope', '$translate', '$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
	        function($rootScope, $translate, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});

}());
 