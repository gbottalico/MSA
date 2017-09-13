angular.module('msa').service('ConvertSvc',
    ['_',
        'UtilSvc',
        function (_, UtilSvc) {

            var $svc = this;

            /**
             * Converte l'oggetto anagrafica prelevato dal modal o affini in un DTO.
             * @param anagrafica
             */

            $svc.anagraficaToDTO = function (anagrafica) {

                var dto = {};
                dto.tipoPersona = anagrafica.tipoPersona;
                dto.nome = anagrafica.nome;
                dto.cognome = anagrafica.cognome;
                dto.ragioneSociale = anagrafica.ragioneSociale;
                dto.sesso = anagrafica.sesso;
                dto.cf = anagrafica.cf;
                dto.codRuolo = anagrafica.ruolo;
                dto.lesioni = anagrafica.lesioni;
                dto.veicolo = anagrafica.veicolo;
                dto.targa = anagrafica.targa;
                dto.targaEstera = anagrafica.targaEstera;
                dto.targaSpeciale = anagrafica.targaSpeciale;
                

                if (_.isObject(anagrafica.compagnia)) {
                    dto.compagnia = anagrafica.compagnia.id;
                }

                dto.luogoNascita = {};
                if (anagrafica.nascita != undefined) {
                    if (_.isObject(anagrafica.nascita.nazione)) {
                        dto.luogoNascita.codNazione = anagrafica.nascita.nazione.id;
                        dto.luogoNascita.descrizioneNazione = anagrafica.nascita.nazione.descrizione;
                    }
                    if (_.isObject(anagrafica.nascita.provincia)) {
                        dto.luogoNascita.codProvincia = anagrafica.nascita.provincia.codProvincia;
                        dto.luogoNascita.descrizioneProvincia = anagrafica.nascita.provincia.desProv;
                    }
                    if (_.isObject(anagrafica.nascita.comune)) {
                        dto.luogoNascita.codComune = anagrafica.nascita.comune.codComune;
                        dto.luogoNascita.descrizioneComune = anagrafica.nascita.comune.descrizione;
                        dto.luogoNascita.cap = anagrafica.nascita.cap;
                    }

                    dto.dataNascita = anagrafica.nascita.data.date;
                }

                dto.tracking = {};
                if (anagrafica.residenza != undefined) {
                    if (_.isObject(anagrafica.residenza.nazione)) {
                        dto.tracking.nazione = anagrafica.residenza.nazione.id;
                        dto.tracking.descNazione = anagrafica.residenza.nazione.descrizione;
                    }
                    if (_.isObject(anagrafica.residenza.provincia)) {
                        dto.tracking.provincia = anagrafica.residenza.provincia.codProvincia;
                        dto.tracking.descProvincia = anagrafica.residenza.provincia.siglaProv;
                    }
                    if (_.isObject(anagrafica.residenza.comune)) {
                        dto.tracking.comune = anagrafica.residenza.comune.codComune;
                        dto.tracking.descComune = anagrafica.residenza.comune.descrizione;
                        dto.tracking.cap = anagrafica.residenza.cap;
                    }

                    dto.tracking.indirizzo = anagrafica.residenza.indirizzo;
                }
                dto.tracking.telefono = anagrafica.telefono;
                dto.tracking.mail = anagrafica.mail;

                return dto;

            };


            /**
             * Converte l'oggetto DTO del BE in un oggetto FE.
             * @param dto
             * @returns {{nascita: {nazione: {}, provincia: {}, comune: {}, data: {}}, residenza: {nazione: {}, provincia: {}, comune: {}}}}
             */
            $svc.dtoToAnagrafica = function (dto) {

                var anagrafica = {
                    nascita: {
                        nazione: {},
                        provincia: {},
                        comune: {},
                        data: {}
                    },
                    residenza: {
                        nazione: {},
                        provincia: {},
                        comune: {},
                    }
                };

                anagrafica.tipoPersona = dto.tipoPersona;
                anagrafica.nome = dto.nome;
                anagrafica.cognome = dto.cognome;
                anagrafica.ragioneSociale = dto.ragioneSociale;
                anagrafica.sesso = dto.sesso;
                anagrafica.cf = dto.cf;
                anagrafica.ruolo = dto.codRuolo;
                anagrafica.lesioni = dto.lesioni;
                anagrafica.compagnia = {
                    id: dto.compagnia
                };

                if (_.isObject(dto.luogoNascita)) {

                    anagrafica.nascita.nazione.id = dto.luogoNascita.codNazione;
                    anagrafica.nascita.provincia.codProvincia = dto.luogoNascita.codProvincia;
                    anagrafica.nascita.comune.codComune = dto.luogoNascita.codComune;
                    anagrafica.nascita.cap = dto.luogoNascita.cap;

                }

                anagrafica.nascita.data.date = dto.dataNascita ? new Date(dto.dataNascita) : undefined;

                if (_.isObject(dto.tracking)) {

                    anagrafica.residenza.nazione.id = dto.tracking.nazione;
                    anagrafica.residenza.provincia.codProvincia = dto.tracking.provincia;
                    anagrafica.residenza.comune.codComune = dto.tracking.comune;
                    anagrafica.residenza.cap = dto.tracking.cap;
                    anagrafica.residenza.indirizzo = dto.tracking.indirizzo;

                    anagrafica.telefono = dto.tracking.telefono;
                    anagrafica.mail = dto.tracking.mail;

                }

                return anagrafica;

            };


            /**
             * Converte l'oggetto danni auto in DTO.
             * @param danniAuto
             * @param descrizioneDanno
             * @returns {{a: (boolean|*), adx: (boolean|*), asx: (boolean|*), cdx: (boolean|*), csx: (boolean|*), d: (boolean|*), ddx: (boolean|*), descrizioneDanno: *, dsx: (boolean|*)}}
             */

            $svc.danniAutoToDTO = function (danniAuto, descrizioneDanno) {

                var dto = {
                    a: danniAuto.middleleft,
                    adx: danniAuto.topleft,
                    asx: danniAuto.bottomleft,
                    cdx: danniAuto.topcenter,
                    csx: danniAuto.bottomcenter,
                    d: danniAuto.middleright,
                    ddx: danniAuto.topright,
                    descrizioneDanno: descrizioneDanno,
                    dsx: danniAuto.bottomright
                };

                return dto;

            }

        }
    ]
);