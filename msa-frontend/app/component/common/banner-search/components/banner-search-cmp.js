(function () {
	"use strict";

	app.component('msaBannerSearch', {
	    templateUrl: '../../app/component/common/banner-search/components/templates/banner-search-tpl.html',
	    bindings: {},
	    controller: ("bannerSearchController", ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', 
	        function($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {
	            
	        var ctrl = this;

	    }])
	});

}());

