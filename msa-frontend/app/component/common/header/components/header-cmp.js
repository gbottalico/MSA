(function () {
	"use strict";

	app.component('msaHeader', {
	    templateUrl: '../../app/component/common/header/components/templates/header-tpl.html',
	    bindings: {},
	    controller: ("headerController", ['$MSAC', '$location',
	        function($MSAC, $location) {
	            
	        var $ctrl = this;

			$ctrl.reloadRoute = function() {
				var path = $MSAC.PATHS.HOME;
				$location.search({});
				$location.path(path);
			};

	    }])
	});

}());
 