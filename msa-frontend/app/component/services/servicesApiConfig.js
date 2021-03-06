app.service('msaServicesApiUrls', function ($MSAC) {

    var apiPath                 = $MSAC.SERVER_HOSTS.API_COMMON + 'api';
    var localApiPath            = $MSAC.SERVER_HOSTS.API_ALT1 + 'api';

    /* DOMINIO */

    this.compagnia              = apiPath + "/dominio/compagnia/{0}"; // 0: descrizione compagnia
    this.nazione                = apiPath + "/dominio/nazione/";
    this.provincia              = apiPath + "/dominio/provincia/";
    this.comune                 = apiPath + "/dominio/comune/";
    this.luogoById              = apiPath + "/dominio/desLuogoById?id={0}&codLuogo={1}&param";  //0: idLuogo.
    this.autorita               = apiPath + "/dominio/autorita/";
    this.mezzicomunicazione     = apiPath + "/dominio/mezzicomunicazione/";
    this.causerotturacristalli  = apiPath + "/dominio/causerotturacristalli/";
    this.tipotarghe             = apiPath + "/dominio/tipotarghe/";
    this.tipoveicoli            = apiPath + "/dominio/tipoveicoli/";
    this.toponomastiche         = apiPath + "/dominio/particelleToponomastiche";
    this.casaregole             = apiPath + "/dominio/casaregole/";
    this.baremes                = apiPath + "/dominio/baremes/";
    this.ruoli                  = apiPath + "/dominio/ruoli/";

    /* UTILS */

    this.cf                     = apiPath + "/utils/calcolaCf";

    /* SINISTRI */

    this.aperturasinitro        = apiPath + "/sinistro/apertura/";
    this.ricercasinitro         = apiPath + "/sinistro/ricerca/";
    this.ricercaprovvisorio     = apiPath + "/sinistro/{0}/get";                    //0: numero sinistro provvisorio.
    this.aprisegnalazione       = apiPath + "/sinistro/{0}/segnalazione/";          //0: numero sinistro provvisorio.
    this.rca                    = apiPath + "/sinistro/{0}/RCA/";                   //0: numero sinistro provvisorio.
    this.cai                    = apiPath + "/sinistro/{0}/CAI/";                   //0: numero sinistro provvisorio.
    this.colpa                  = apiPath + "/sinistro/colpa/";                     //0: numero sinistro provvisorio.
    this.ca                     = apiPath + "/sinistro/{0}/CA/";                    //0: numero sinistro provvisorio.
    this.dannorcacliente        = apiPath + "/sinistro/{0}/dannoRCA/conducente";    //0: numero sinistro provvisorio.
    this.dannorcacontroparte    = apiPath + "/sinistro/{0}/dannoRCA/controparte";   //0: numero sinistro provvisorio.
    this.dannorcaterzeparti     = apiPath + "/sinistro/{0}/dannoRCA/terzeParti";    //0: numero sinistro provvisorio.
    this.legali                 = apiPath + "/sinistro/{0}/dannoRCA/legale";        //0: numero sinistro provvisorio.
    this.perito                 = apiPath + "/sinistro/perito/";
    this.salvaperito            = apiPath + "/sinistro/{0}/perito/";                //0: numero sinistro provvisorio.
    this.partitedanno           = apiPath + "/sinistro/{0}/PD";                     //0: numero sinistro provvisorio.
    this.anagraficheass         = apiPath + "/sinistro/{0}/anagraficheAssociabili"; //0: numero sinistro provvisorio.

    this.carrozzerie            = apiPath + "/sinistro/centri/{0}?param";           //0: indirizzo
    this.salvacarrozzeria       = apiPath + "/sinistro/{0}/centroConvenzionato/";   //0: indirizzo
    this.furtoincendio          = apiPath + "/sinistro/{0}/furtoIncendio";          //0: indirizzo
    this.cristalli              = apiPath + "/sinistro/{0}/cristalli";              //0: indirizzo
    this.kasko                  = apiPath + "/sinistro/{0}/kasko";                  //0: indirizzo
    this.infortuni              = apiPath + "/sinistro/{0}/infortuniConducente";    //0: indirizzo

    this.tipoSinistro           = apiPath + "/sinistro/{0}/getTipoSinistro";        //0: numero sinistro provvisorio

    /* DOCUMENTI */

    this.upload                 = apiPath + "/documenti/{0}/{1}/upload";            //0: numero sinistro provvisorio, 1: codTipoDocumento
    this.delete                 = apiPath + "/documenti/{0}/delete";                //0: idDocumento
    this.listadoc               = apiPath + "/documenti/{0}/lista";                 //0: numero sinistro provvisorio
    this.getdocumento           = apiPath + "/documenti/{0}/get";                 //0: numero sinistro provvisorio

    /* DISPATCHER */

    this.nextpath               = apiPath + "/dispatcher/nextPath";
    this.path                   = apiPath + "/dispatcher/getPath?numSinistro={0}&param";
    this.percentuale            = apiPath + "/dispatcher/getPercentuale";

    /* EXTERNAL */
    this.geocoding              = "https://maps.googleapis.com/maps/api/geocode/json?address={0}&key=" + $MSAC.API_KEYS.MAPS;



});
