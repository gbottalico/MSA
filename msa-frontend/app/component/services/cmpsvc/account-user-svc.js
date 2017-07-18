angular.module('msa').service(
		'AccountUserSvc',
		[
				'$http',
				'msaServicesApiUrls',
				'$sessionStorage',
				'$cookieStore',
				'$rootScope',
				'$q',
				'$timeout',
				'$log',
				function($http, msaServicesApiUrls, $sessionStorage,
						$cookieStore, $rootScope, $q, $timeout, $log) {

					var $svc = this;

				} ]

);