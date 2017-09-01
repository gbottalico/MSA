angular.module('msa').service('ConvertSvc',
    [
        'UtilSvc',
        function (UtilSvc) {

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
                dto.cf = anagrafica.cf;

                dto.luogoNascita = {};
                if (UtilSvc.isDefined(anagrafica.nascita.nazione)) {
                    dto.luogoNascita.codNazione = anagrafica.nascita.nazione.id;
                    dto.luogoNascita.descrizioneNazione = anagrafica.nascita.nazione.descrizione;
                }
                if (UtilSvc.isDefined(anagrafica.nascita.provincia)) {
                    dto.luogoNascita.codProvincia = anagrafica.nascita.provincia.codProvincia;
                    dto.luogoNascita.descrizioneProvincia = anagrafica.nascita.provincia.desProv;
                }
                if (UtilSvc.isDefined(anagrafica.nascita.comune)) {
                    dto.luogoNascita.codComune = anagrafica.nascita.comune.codComune;
                    dto.luogoNascita.descrizioneComune = anagrafica.nascita.comune.descrizione;
                    dto.luogoNascita.cap = anagrafica.nascita.comune.cap;
                }

                dto.dataNascita = anagrafica.nascita.data.date;

                dto.tracking = {};
                if (UtilSvc.isDefined(anagrafica.residenza.nazione)) {
                    dto.tracking.nazione = anagrafica.residenza.nazione.id;
                }
                if (UtilSvc.isDefined(anagrafica.residenza.provincia)) {
                    dto.tracking.provincia = anagrafica.residenza.provincia.codProvincia;
                }
                if (UtilSvc.isDefined(anagrafica.residenza.comune)) {
                    dto.tracking.comune = anagrafica.residenza.comune.codComune;
                    dto.tracking.cap = anagrafica.residenza.comune.cap;
                }

                dto.tracking.indirizzo = anagrafica.residenza.indirizzo;
                dto.tracking.telefono = anagrafica.telefono;
                dto.tracking.mail = anagrafica.mail;

                return dto;

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