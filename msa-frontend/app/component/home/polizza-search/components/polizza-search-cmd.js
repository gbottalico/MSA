(function () {
    "use strict";

    app.component('msaPolizzaSearch', {
        templateUrl: '../../app/component/home/polizza-search/components/templates/polizza-search-tpl.html',
        bindings: {
            bannerSearch: '=',
            bannerDenuncia: '='
        },
        controller: ("polizzaSearchController", ["$scope", '$rootScope', '$translate', '$log', 'AccountUserSvc', "CompagnieSvc", 'CasaRegoleSvc', 'toastr', '$analytics', '$location', '$anchorScroll', '$uibModal', '$cookies', '$window', '$sessionStorage',
            function ($scope, $rootScope, $translate, $log, AccountUserSvc, CompagnieSvc, CasaRegoleSvc, toastr, $analytics, $location, $anchorScroll, $uibModal, $cookies, $window, $sessionStorage) {

                var ctrl = this;

                ctrl.casaRegole = undefined;
                ctrl.compagniaSelezionata = undefined;
                ctrl.valoriRicerca = undefined;

                ctrl.$onInit = function () {
                    CasaRegoleSvc.getElencoRegole().then(function (response) {
                        ctrl.casaRegole = response.data.result;
                    });

                };

                ctrl.campiObbligatori = {
                    cognome: false,
                    nome: false,
                    tipoPersona: false,
                    numeroPolizza: false,
                    numeroSinistro: false,
                    dataEvento: false,
                    targa: false,
                    numeroProvvisorio: false,
                    numeroPreapertura: false
                };

                $scope.$watch(
                    function watch(scope) {
                        return {
                            compagniaSelezionata: ctrl.compagniaSelezionata
                        };
                    },
                    function handleChanges(newValue, oldValue) {

                        if(newValue.compagniaSelezionata !== oldValue.compagniaSelezionata)  {
                            if(newValue.compagniaSelezionata instanceof Object &&
                                newValue.compagniaSelezionata !== null) {

                                // Eseguo il binding dei campi obbligatori.
                                var campiObbligatoriRicerca = newValue.compagniaSelezionata.campiObbligatoriRicerca;
                                for (var i = 0; i < campiObbligatoriRicerca.length; i++) {
                                    switch(campiObbligatoriRicerca[i].idFE) {
                                        case 1:
                                            ctrl.campiObbligatori.cognome = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 2:
                                            ctrl.campiObbligatori.nome = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 3:
                                            ctrl.campiObbligatori.tipoPersona = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 4:
                                            ctrl.campiObbligatori.numeroPolizza = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 5:
                                            ctrl.campiObbligatori.numeroSinistro = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 6:
                                            ctrl.campiObbligatori.dataEvento = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 7:
                                            ctrl.campiObbligatori.targa = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 8:
                                            ctrl.campiObbligatori.numeroProvvisorio = campiObbligatoriRicerca[i].required;
                                            break;
                                        case 9:
                                            ctrl.campiObbligatori.numeroPreapertura = campiObbligatoriRicerca[i].required;
                                            break;

                                    }
                                }
                            }
                        }

                    }, true
                );

                ctrl.ricercapolizza = {
                    cognome: '',
                    nome: '',
                    tipoPersona: undefined,
                    numeroPolizza: '',
                    numeroSinistro: '',
                    dataEvento: '',
                    targa: '',
                    numeroProvvisorio: '',
                    numeroPreapertura: '',
                };

                ctrl.denuncia = function () {
                    ctrl.bannerSearch = false;
                    ctrl.bannerDenuncia = true;
                };

                ctrl.open = function () {
                    $uibModal.open({
                        templateUrl: 'myModalContent.html', // loads the template
                        backdrop: 'static', // setting backdrop allows us to close the modal window on clicking outside the modal window
                        windowClass: 'yolo', // windowClass - additional CSS class(es) to be added to a modal window template
                        controller: function ($scope, $uibModalInstance , $log, user) {
                            $scope.user = user;
                            $scope.submit = function () {
                                $log.log('Submiting user info.'); // kinda console logs this statement
                                $log.log(user);
                                ctrl.denuncia();
                                $uibModalInstance.dismiss('cancel'); // dismiss(reason) - a method that can be used to dismiss a modal, passing a reason
                            };
                            $scope.cancel = function () {
                                $uibModalInstance.dismiss('cancel');
                            };
                        },
                        resolve: {
                            user: function () {
                                return $scope.user;
                            }
                        }
                    });//end of modal.open
                };

                ctrl.cerca = function () {

                    ctrl.valoriRicerca = {
                        bannersearch: ctrl.bannersearch,
                        bannerdenuncia: ctrl.bannerdenuncia,
                        user: {
                            cognome: ctrl.ricercapolizza.cognome,
                            nome: 'Dario',
                            cf: 'PRSDRA87E28B157S',
                            luogonascita: 'Brescia',
                            provincianascita: 'BS',
                            datanascita: '15/01/1960',
                            residenza: 'Brescia',
                            provinciaresidenza: 'BS',
                            indirizzo: 'Via Raffaello Sanzio, 11',
                            telefono: '0331123456',
                            cellulare: '3332363880',
                            email: 'info@email.it'
                        },
                        polizze: [
                            {
                                numpoli: '100012058380',
                                attivazione: '22/02/2016',
                                scadenza: '22/02/2017',
                                prodotto: 'Prodotto viaggia con me (clear box)',
                                compagnia: 'Aviva',
                                targabene: 'df832xb',
                                contraente: 'Piras Dario',
                                stato: 'Attivo',
                                variazione: '18/02/2016',
                                sinistri: [
                                    {
                                        nome: 'Sinistro',
                                        numsin: '100012058380',
                                        data: '23/03/2016',
                                        tipo: 'A',
                                        evento: 'Amet',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Sinistro provvisorio',
                                        numsin: '100017432380',
                                        data: '20/05/2016',
                                        tipo: 'B',
                                        evento: 'Consectetur',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Pre-codifica',
                                        numsin: '103212058363',
                                        data: '03/01/2017',
                                        tipo: 'C',
                                        evento: 'Super alta',
                                        denunciatoda: 'Anna Rossi',
                                        stato: 'Attivo'
                                    }
                                ]
                            },
                            {
                                numpoli: '100012058380',
                                attivazione: '22/02/2016',
                                scadenza: '22/02/2017',
                                prodotto: 'Prodotto viaggia con me (clear box)',
                                compagnia: 'Aviva',
                                targabene: 'df832xb',
                                contraente: 'Piras Dario',
                                stato: 'Attivo',
                                variazione: '18/02/2016',
                                sinistri: [
                                    {
                                        nome: 'Sinistro',
                                        numsin: '100012058380',
                                        data: '23/03/2016',
                                        tipo: 'A',
                                        evento: 'Amet',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Sinistro provvisorio',
                                        numsin: '100017432380',
                                        data: '20/05/2016',
                                        tipo: 'B',
                                        evento: 'Consectetur',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Pre-codifica',
                                        numsin: '103212058363',
                                        data: '03/01/2017',
                                        tipo: 'C',
                                        evento: 'Super alta',
                                        denunciatoda: 'Anna Rossi',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Pre-codifica',
                                        numsin: '103212058363',
                                        data: '03/01/2017',
                                        tipo: 'C',
                                        evento: 'Super alta',
                                        denunciatoda: 'Anna Rossi',
                                        stato: 'Attivo'
                                    }
                                ]
                            },
                            {
                                numpoli: '100012058380',
                                attivazione: '22/02/2016',
                                scadenza: '22/02/2017',
                                prodotto: 'Prodotto viaggia con me (clear box)',
                                compagnia: 'Aviva',
                                targabene: 'df832xb',
                                contraente: 'Piras Dario',
                                stato: 'Attivo',
                                variazione: '18/02/2016',
                                sinistri: [
                                    {
                                        nome: 'Sinistro',
                                        numsin: '100012058380',
                                        data: '23/03/2016',
                                        tipo: 'A',
                                        evento: 'Amet',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Sinistro provvisorio',
                                        numsin: '100017432380',
                                        data: '20/05/2016',
                                        tipo: 'B',
                                        evento: 'Consectetur',
                                        denunciatoda: 'Piras Dario',
                                        stato: 'Attivo'
                                    },
                                    {
                                        nome: 'Pre-codifica',
                                        numsin: '103212058363',
                                        data: '03/01/2017',
                                        tipo: 'C',
                                        evento: 'Super alta',
                                        denunciatoda: 'Anna Rossi',
                                        stato: 'Attivo'
                                    }
                                ]
                            }
                        ]
                    };
                    $location.hash('polizzaResult');
                    $anchorScroll();
                };

            }])
    });

}());