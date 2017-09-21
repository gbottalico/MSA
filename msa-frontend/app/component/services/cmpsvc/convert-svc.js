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

                if(_.isObject(anagrafica.associato)) {
                    dto.associato = anagrafica.associato;
                }

                if (_.isObject(anagrafica.compagnia)) {
                    dto.compagnia = anagrafica.compagnia.id;
                }

                dto.luogoNascita = {};
                if (_.isObject(anagrafica.nascita)) {
                    dto.luogoNascita = $svc.luogoToDTO(anagrafica.nascita);
                    dto.dataNascita = anagrafica.nascita.data;
                }

                dto.tracking = {};
                if (_.isObject(anagrafica.residenza)) {
                    dto.tracking.residenza = $svc.luogoToDTO(anagrafica.residenza);
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
                    anagrafica.nascita = $svc.dtoToLuogo(dto.luogoNascita);
                }

                anagrafica.nascita.data = dto.dataNascita ? new Date(dto.dataNascita) : undefined;

                if (_.isObject(dto.tracking)) {
                    if (_.isObject(dto.tracking.residenza)) {
                        anagrafica.residenza = $svc.dtoToLuogo(dto.tracking.residenza);
                        anagrafica.residenza.indirizzo = dto.tracking.indirizzo;
                    }
                    anagrafica.telefono = dto.tracking.telefono;
                    anagrafica.mail = dto.tracking.mail;
                }

                if(_.isObject(dto.associato)) {
                    anagrafica.associato = dto.associato;
                }

                return anagrafica;
            };

            $svc.luogoToDTO = function (luogo) {
                var dto = {};
                if (_.isObject(luogo.nazione)) {
                    dto.codNazione = luogo.nazione.codNazione;
                    dto.descrizioneNazione = luogo.nazione.descrizione;
                }
                if (_.isObject(luogo.provincia)) {
                    dto.codProvincia = luogo.provincia.codProvincia;
                    dto.descrizioneProvincia = luogo.provincia.descProvincia;
                }
                if (_.isObject(luogo.comune)) {
                    dto.codComune = luogo.comune.codComune;
                    dto.descrizioneComune = luogo.comune.descrizione;
                    dto.cap = luogo.cap;
                }
                return dto;
            };

            $svc.dtoToLuogo = function (dto) {
                var luogo = {
                    nazione: {
                        codNazione: dto.codNazione,
                        descrizione: dto.descrizioneNazione
                    },
                    provincia: {
                        codProvincia: dto.codProvincia,
                        descProvincia: dto.descrizioneProvincia
                    },
                    comune: {
                        codComune: dto.codComune,
                        descrizione: dto.descrizioneComune
                    },
                    cap: dto.cap
                };
                return luogo;
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

            };

        }
    ]
);