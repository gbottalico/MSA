(function () {
    "use strict";

    app.component('msaPolizzeSinistri', {
        templateUrl: '../../app/component/home/polizze-sinistri-result/components/templates/polizze-sinistri-tpl.html',
        bindings: {
            valoriRicerca: '=',
            bannerSearch: '=',
            bannerDenuncia: '='
        },
        controller: ("polizzeSinistriController", ['$rootScope', '$translate', '$log', 'toastr', '$analytics', '$location', '$cookies', '$window', '$sessionStorage',
            function ($rootScope, $translate, $log, toastr, $analytics, location, $cookies, $window, $sessionStorage) {

                var $ctrl = this;

                $ctrl.denuncia = function () {
                    ctrl.bannerSearch = false;
                    ctrl.bannerDenuncia = true;
                };

                $ctrl.polizze = [
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
                ];

            }])
    });

}());