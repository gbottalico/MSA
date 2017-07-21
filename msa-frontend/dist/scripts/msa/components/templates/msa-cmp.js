'use strict';

angular.module('msa').component('msa', {
    templateUrl: '../../app/component/msa/components/templates/msa-tpl.html',
    $routeConfig: [{ path: '/', name: 'Home', component: 'msaHome', useAsDefault: true }],
    controller: ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage', function ($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

        var ctrl = this;

        /**
         * Funzione di inizializzazione in cui viene effettuato il login
         */
        ctrl.$onInit = function () {
            alert("FUNZIONA");
        };
    }]
});