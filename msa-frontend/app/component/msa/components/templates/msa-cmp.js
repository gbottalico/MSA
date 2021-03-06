angular
    .module('msa')
    .component('msa',
        {
            templateUrl: '../../app/component/msa/components/templates/msa-tpl.html',
            $routeConfig: [
                {path: '/', name: 'Home', component: 'msaHome', useAsDefault: true}
            ],
            controller: ['$rootScope', '$translate', '$log', 'toastr',
                function ($rootScope, $translate, $log, toastr) {

                    var $ctrl = this;

                    $rootScope.debugMode = true;
                    $rootScope.messages = [];

                    $rootScope.domain = {};

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

                }]
        });
