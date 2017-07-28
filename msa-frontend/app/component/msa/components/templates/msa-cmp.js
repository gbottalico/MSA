angular
    .module('msa')
    .component('msa',
        {
            templateUrl: '../../app/component/msa/components/templates/msa-tpl.html',
            $routeConfig: [
                {path: '/', name: 'Home', component: 'msaHome', useAsDefault: true}
            ],
            controller: ['$rootScope', '$translate', '$log', 'AccountUserSvc', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
                function ($rootScope, $translate, $log, AccountUserSvc, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

                    var ctrl = this;

                    ctrl.bannerSearch = true;
                    ctrl.bannerDenuncia = false;

                    $rootScope.messages = [];

                    $rootScope.$watch('messages', function (newValue, oldValue) {
                        if (newValue !== null && newValue !== oldValue) {
                            angular.forEach(newValue, function (value) {

                                switch (value.tipo.toString()) {
                                    case 'ERROR':
                                        toastr.error(value.text);
                                        break;
                                    case 'INFO':
                                        toastr.info(value.text);
                                        break;
                                    default:
                                        toastr.warning(value.text);
                                        break;
                                }

                            });
                        }
                    }, true);

                    /**
                     * Funzione di inizializzazione in cui viene effettuato il login
                     */
                    /*ctrl.$onInit = function () {
                        alert("FUNZIONA");
                    };*/

                }]
        });
