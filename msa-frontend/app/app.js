var app = angular.module('msa', ['pascalprecht.translate', 'ngStorage',
    'ngCookies', 'ngSanitize', 'ngResource', 'ui.bootstrap',
    'ui.bootstrap.tpls', 'tmh.dynamicLocale',
    'ngAnimate', 'ui.select', 'ui.bootstrap.showErrors', 'ngFileUpload',
    'ngStomp', 'cgBusy', 'angular-loading-bar', 'textAngular', 'rzModule',
    'toastr', 'toggle-switch', 'angulartics',
    'angulartics.google.analytics', 'ngRoute', 'uiGmapgoogle-maps']);

/**
 * Parametri di configurazione dell'applicazione.
 *
 * @returns Configuration
 */

function getMSAC() {
    return {
        SERVER_HOSTS: {
            FE_HOST: 'http://localhost:8887/?refresh',
            API_COMMON: 'http://localhost:8080/msa/',
            API_ALT: 'http://172.25.14.169:8080/msa/',
            API_ALT1: 'http://localhost.gruppoitas.it:3000/',
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
                ANY_LETTER: "^[\\u0080-\\u00FF\\u0100-\\u017F\\u0180-\\u024Fa-zA-Z ]*$",
                ANY_LETTER_NUMBER: "^[\\u0080-\\u00FF\\u0100-\\u017F\\u0180-\\u024Fa-zA-Z0-9 ]*$",
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
        },
        PATHS: {
            HOME: "/",
            DENUNCIA: "/denuncia"
        },
        API_KEYS: {
            MAPS: "AIzaSyClOUgLxnK_B5-IdKK4EjavitUwoqxLr14"
        }
    };
}

/*
 * Definizione delle costanti
 */
app.constant('$MSAC', getMSAC());
app.constant('$debugMode', true);
app.constant('_', window._);

/*
 * Configurazione del routing.
 */
app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when(getMSAC().PATHS.HOME, {
            template: "<msa-home></msa-home>"
        })
        .when(getMSAC().PATHS.DENUNCIA, {
            template: "<msa-denuncia-container></msa-denuncia-container>"
        })
        .when(getMSAC().PATHS.DENUNCIA + "/:idSinistroProvvisorio", {
            template: "<msa-denuncia-container></msa-denuncia-container>"
        })
        .otherwise({
            redirectTo: getMSAC().PATHS.HOME
        });
}]);

/*
 * Configurazione di Toastr.
 */

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

/*
 * Configurazione della lingua.
 */

app.config(function ($translateProvider, $MSAC) {

    $translateProvider.useStaticFilesLoader({
        prefix: 'resources/',
        suffix: '.json'
    });

    $translateProvider.preferredLanguage($MSAC.LOCALES.preferredLocale);
    $translateProvider.useLocalStorage();
    $translateProvider.useSanitizeValueStrategy('escapeParameters');

}).config(function (tmhDynamicLocaleProvider) {
    tmhDynamicLocaleProvider.localeLocationPattern('bower_components/angular-i18n/angular-locale_{{locale}}.js');
});

/* Configurazione di Google Maps */
app.config(function(uiGmapGoogleMapApiProvider, $MSAC) {
    uiGmapGoogleMapApiProvider.configure({
        key: $MSAC.API_KEYS.MAPS,
        v: '3',
        libraries: 'weather,geometry,visualization'
    });
});

/*
 * Intercettazione globale dei messaggi nelle chiamate asincrone.
 */

app.factory('messageInterceptor', function ($rootScope) {

    return {
        'response': function (response) {
            if (hasMessages(response)) {
                $rootScope.messages = getMessages(response);
            }
            return response;
        },
        'responseError': function (response) {
            $rootScope.errors = response;
            console.log(response);
            return response;
        }
    };

    function hasMessages(response) {
        return (response.data !== undefined &&
            response.data !== null &&
            response.data.messaggi !== undefined &&
            response.data.messaggi !== null &&
            response.data.messaggi.length > 0);
    }

    function getMessages(response) {
        return response.data.messaggi;
    }
});


app.config(['$httpProvider', function ($httpProvider) {
    $httpProvider.interceptors.push("messageInterceptor");
}]);