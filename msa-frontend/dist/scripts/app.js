'use strict';

window.app = angular.module('msa', ['pascalprecht.translate', 'ngStorage', 'ngCookies', 'ngSanitize', 'ngResource', 'ui.bootstrap', 'ui.bootstrap.tpls', 'ngComponentRouter', 'tmh.dynamicLocale', 'ngAnimate', 'ui.select', 'ui.bootstrap.showErrors', 'ngFileUpload', 'ngStomp', 'cgBusy', 'angular-loading-bar', 'textAngular', 'rzModule', 'toastr', 'toggle-switch', 'angulartics', 'angulartics.google.analytics']);

function getMSAC() {
	var msac = {
		SERVER_HOSTS: {
			FE_HOST: 'http://localhost:8887/?refresh',
			API_COMMON: 'http://localhost:8080/msa/',
			API_ALT1: 'http://localhost.gruppoitas.it:3000/'
		},
		LOCALES: {
			preferredLocale: 'it'
		},
		PATTERN: {
			VALIDATION: {
				// https://regex101.com/
				ANY: "^[[:ascii:]]*$",
				CODICE_FISCALE: "^[A-Za-z]{6}[0-9]{2}[A-Za-z]{1}[0-9]{2}[A-Za-z]{1}[0-9]{3}[A-Za-z]{1}$",
				PARTITA_IVA: "^[0-9]{11}$",

				// max 5 caratteri alfanumerici: a-z, A-Z, 0-9
				CAP: "^[A-Za-z0-9]{5}$",

				// http://kourge.net/projects/regexp-unicode-block
				// 0080-00FF Latin-1 Supplement
				// 0100-017F Latin Extended-A
				// 0180-024F Latin Extended-B
				ANY_LETTER: '^[\\u0080-\\u00FF\\u0100-\\u017F\\u0180-\\u024Fa-zA-Z ]*$',
				ANY_LETTER_NUMBER: '^[\\u0080-\\u00FF\\u0100-\\u017F\\u0180-\\u024Fa-zA-Z0-9 ]*$',
				ANY_NUMBER: "^[0-9]+$",
				ANY_NUMBER_MAX_100: "^[0-9][0-9]?$|^100$",
				MONEY: "^[0-9,]+(\.)[0-9]{2}$",
				MONEY_ALTERNATIVE: "^[-+]?([0-9]*(\.[0-9][0-9]?)?)$",
				ANY_PAGELLINO: "^([0-9]+|[*]|(NA)|(ND))$",
				TEXT: "",
				PHONE: "^[+]{0,1}[0-9 -]{5,20}$",
				EMAIL: "^[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,3}$",
				URL: "^(http|https|ftp)?(:\/\/)?(www|ftp)?.?[a-z0-9-]+(.|:)([a-z0-9-]+)+([\/?].*)?$"
			}
		}
	};
	return msac;
}

app.constant('$MSAC', getMSAC());
// rendo lodash (underscore) disponibile per tutti
app.constant('_', window._);

app.config(function (toastrConfig) {
	angular.extend(toastrConfig, {
		allowHtml: false,
		closeButton: true,
		closeHtml: '<button>&times;</button>',
		extendedTimeOut: 1000,
		iconClasses: {
			error: 'toast-error',
			info: 'toast-info',
			success: 'toast-success',
			warning: 'toast-warning'
		},
		positionClass: 'toast-bottom-right',
		messageClass: 'toast-message',
		onHidden: null,
		onShown: null,
		onTap: null,
		progressBar: false,
		tapToDismiss: true,
		templates: {
			toast: 'directives/toast/toast.html',
			progressbar: 'directives/progressbar/progressbar.html'
		},
		timeOut: 10000,
		titleClass: 'toast-title',
		toastClass: 'toast'
	});
});

app.config(function ($translateProvider, $MSAC) {
	/*
  * if (DEBUG_MODE) {
  * $translateProvider.useMissingTranslationHandlerLog();//
  * warns about missing translates }
  */

	$translateProvider.useStaticFilesLoader({
		prefix: 'resources/',
		suffix: '.json'
	});

	$translateProvider.preferredLanguage($MSAC.LOCALES.preferredLocale);
	$translateProvider.useLocalStorage();

	$translateProvider.useSanitizeValueStrategy('escapeParameters');
})
// Angular Dynamic Locale
.config(function (tmhDynamicLocaleProvider) {
	tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
});

/**
 * Configure the top level routed App Component.
 */
app.value('$routerRootComponent', 'msa');