app.service('msaServicesApiUrls', function ($MSAC) {

    var apiPath                 = $MSAC.SERVER_HOSTS.API_COMMON + 'api';
    var localApiPath            = $MSAC.SERVER_HOSTS.API_ALT1 + 'api';

    /* DOMINIO */

    this.compagnia              = apiPath + "/dominio/compagnia/";

    this.nazione                = apiPath + "/dominio/nazione/";
    this.provincia              = apiPath + "/dominio/provincia/";
    this.comune                 = apiPath + "/dominio/comune/";

    this.autorita               = apiPath + "/dominio/autorita/";

    this.mezzicomunicazione     = apiPath + "/dominio/mezzicomunicazione/";

    this.causerotturacristalli  = apiPath + "/dominio/causerotturacristalli/";

    this.tipotarghe             = apiPath + "/dominio/tipotarghe/";
    this.tipoveicoli            = apiPath + "/dominio/tipoveicoli/";

    this.casaregole             = apiPath + "/dominio/casaregole/";

    this.ruoli                  = apiPath + "/dominio/ruoli/";

    /* SINISTRI */

    this.aperturasinitro        = apiPath + "/sinistro/apertura/";
    this.ricercasinitro         = apiPath + "/sinistro/ricerca/";
    this.aprisegnalazione       = apiPath + "/sinistro/{0}/segnalazione/"           //0: numero sinistro provvisorio.

});
