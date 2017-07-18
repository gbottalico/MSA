/**
 * Created by Marco on 19/10/2016.
 */
var express = require('express'),
    cors = require('cors'),
    bodyParser = require('body-parser'),
    fs = require('fs');

var app = express();

app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({extended: true})); // support encoded bodies

var allowCrossDomain = function (req, res, next) {

    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, nfcsessioncookie, AMAuthCookie, amlbcookie");
    next();
  /*  res.header('Access-Control-Allow-Credentials', true)
   // res.header('Access-Control-Allow-Origin', 'http://localhost.gruppoitas.it:8887');
     res.header('Access-Control-Allow-Origin', '*');

    if ('OPTIONS' == req.method) {
        res.header('Access-Control-Allow-Methods', 'GET, PUT, POST, DELETE, PATCH, OPTIONS');
        res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With');
        res.send(200);
    } else {
       // res.header('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
        res.header("Access-Control-Allow-Headers","*")
        next();
    }  */
};

app.use(allowCrossDomain);

function readFsAndSend(pathRel, res) {
    console.log("___________________________INIT " + pathRel + "___________________________");

    res.setHeader('Content-Type', 'application/json');

    var obj = JSON.parse(fs.readFileSync('./resources/' + pathRel, 'utf8'));
    console.log(obj);
    console.log("___________________________END " + pathRel + "___________________________");

    return res.send(JSON.stringify(obj));
}

function readFsAndSendEmissione(pathRel, res) {
    console.log("___________________________INIT " + pathRel + "___________________________");

    res.setHeader('Content-Type', 'application/json');

    var obj = JSON.parse(fs.readFileSync('./resources/emissione/' + pathRel, 'utf8'));
    console.log(obj);
    console.log("___________________________END " + pathRel + "___________________________");

    return res.send(JSON.stringify(obj));
}

function readFsAndSenScontistiche(pathRel, res) {
    console.log("___________________________INIT " + pathRel + "___________________________");

    res.setHeader('Content-Type', 'application/json');

    var obj = JSON.parse(fs.readFileSync('./resources/scontistica/' + pathRel, 'utf8'));
    console.log(obj);
    console.log("___________________________END " + pathRel + "___________________________");

    return res.send(JSON.stringify(obj));
}

function readFsAndSendRiepilogo(pathRel, res) {
    console.log("___________________________INIT " + pathRel + "___________________________");

    res.setHeader('Content-Type', 'application/json');

    var obj = JSON.parse(fs.readFileSync('./resources/riepilogo/' + pathRel, 'utf8'));
    console.log(obj);
    console.log("___________________________END " + pathRel + "___________________________");

    return res.send(JSON.stringify(obj));
}

function readFsAndSendPdf(pathRel, filename, res) {
    var path = './resources/' + pathRel + '/' + filename;
    console.log("___________________________INIT " + path + "___________________________");

    fs.readFile(path, function (err, data) {
        res.contentType("application/pdf");
        res.send(data);

        console.log(data);
        console.log("___________________________END " + path + "___________________________");
    });

}


function sendVar(res, obj, what) {
    console.log("___________________________INIT " + what + "___________________________");

    res.setHeader('Content-Type', 'application/json');
    res.send(JSON.stringify(obj));
    console.log(what + ":= " + JSON.stringify(obj, undefined, 4));

    console.log("___________________________END " + what + "___________________________");
}


/***
 * /api/soluzioni/getModelContrattoIniziale
 * input
 * INPUT:
 {
    "proclie":"E1678465",
    "dataEffetto":"26/01/2017",
    "idSoluzione":1
 }
 */

app.get('/api/menuContratto/getScontoAutonomia',function(req,res){
    readFsAndSenScontistiche('getScontoAutonomia.json',res);
});
app.get('/api/soluzioni/goToRiepilogo',function(req,res){
    readFsAndSendRiepilogo('goToRiepilogo.json',res);
});

app.post('/api/firma/getInfoFirma',function(req,res){
    readFsAndSendRiepilogo('getInfoFirma.json',res);
});

app.post('/api/firma/getUrlOTP',function(req,res){
    readFsAndSendRiepilogo('getUrlOTP.json',res);
});

app.get('/api/riepilogo/getCompagnieCoassicurazione',function(req,res){
    readFsAndSendRiepilogo('GetCompagnieCoassicurazione.json',res);
});

app.get('/api/soluzioni/getGaranzie',function (req, res) {
    readFsAndSendEmissione('response-export_15022017.json', res);
});

app.post('/api/soluzioni/getModelContrattoIniziale', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('servizio_getModelContrattoIniziale.json', res);
});

/****
 *
 * /api/soluzioni/getConfigurazioneContrattoIniziale

 INPUT:
 /api/soluzioni/getConfigurazioneContrattoIniziale?numContratto=TPO02829718
 *
 */
app.get('/api/soluzioni/getConfigurazioneContrattoIniziale', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_getConfigurazioneContrattoIniziale.json', res);
});


/****
 * /api/soluzioni/insertEntitaTransito

 INPUT:
 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "tipoTarga": "N",
    "targa": "DX645YA",
    "intestPRACoincidente": true,
    "polizza":
       {
              "numOrdi":659839297373,
              "numPoli":"TPO02829718",
              "codProd":1001,
              "codComp":"022",
              "sysdate":"05/03/2007 10:49:13",
              "desProd":"DIMENSIONE AUTO",
              "codRamo":"50",
              "desRamo":"DIMENSIONE AUTO",
              "ibdv":"S",
              "flagResi":false,
              "flagMonoentita": true,
               "entita" :
               {
                  "prgEntita":null,
                  "tagEntita":null,
                  "numOggr":null,
                  "codTogg":"13",
                  "prgTogg":1,
                  "desEntita":"SETTORE 1 - AUTOVETTURE",
                  "categoriaTariff":{
                      "codSttr":"1",
                      "codClss":"01",
                      "codCate":"05",
                      "codSpcz":"0",
                      "desCate":"AUTOVETTURA NOLEGGIO CON CONDUCENTE"
                    }
               }
       }
 }

 *
 *
 */

app.post('/api/soluzioni/insertEntitaTransito', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_insertEntitatransito.json', res);
});


/****
 * /api/soluzioni/getConfigurazioneAttrEntita

 INPUT:

 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "polizza":
       {
              "numOrdi":659839297373,
              "numPoli":"TPO02829718",
              "codProd":1001,
              "codComp":"022",
              "sysdate":"05/03/2007 10:49:13",
              "desProd":"DIMENSIONE AUTO",
              "codRamo":"50",
              "desRamo":"DIMENSIONE AUTO",
              "ibdv":"S",
              "flagResi":false,
              "flagMonoentita": true,
              "entita" :
               {
                 "numOggr": 1,
                 "prgEntita": 1,
                 "tagEntita":"1 - Veicolo",
                 "codTogg":"13",
                 "prgTogg":1,
                 "desEntita":"SETTORE 1 - AUTOVETTURE",
                 "categoriaTariff":{
                      "codSttr":"1",
                      "codClss":"01",
                      "codCate":"05",
                      "codSpcz":"0",
                      "desCate":"AUTOVETTURA NOLEGGIO CON CONDUCENTE"
                 }
               }
       }
 }
 *
 *
 *
 */
app.post('/api/soluzioni/getConfigurazioneAttrEntita', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_getConfigurazioneAttrEntita.json', res);
});

/*****
 *
 * /api/soluzioni/getDominiAttrEntita  - MARCA

 INPUT:
 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "polizza":
       {
          "numOrdi":659839297373,
          "numPoli":"TPO02829718",
          "codProd":1001,
          "codComp":"022",
          "sysdate":"05/03/2007 10:49:13",
          "desProd":"DIMENSIONE AUTO",
          "codRamo":"50",
          "desRamo":"DIMENSIONE AUTO",
          "ibdv":"S",
          "flagResi":false,
          "flagMonoentita": true,
           "entita" :
           {
             "numOggr": 1,
             "prgEntita": 1,
             "tagEntita":"1 - Veicolo",
             "codTogg":"13",
             "prgTogg":1,
             "desEntita":"SETTORE 1 - AUTOVETTURE",
             "attributoSelected":
               {
                  "prgDizi":"4817",
                  "desCamp":"CODICE MARCA",
                  "flagObbl":true,
                  "flagModi":true,
                  "riferimento":true,
                  "tipoDato":"A",
                  "numCara":"6",
                  "numDeci":"0",
                  "valCara":null,
                  "desCara":null,
                  "flagCarica": true,
                  "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4821"  --MODELLO
                      },
                      {
                         "camDipe":"4824" --ALLESTIMENTO
                      }
                   ],
                  "listaCampiDipendentiPadri": [],
                  "nomTabe": null,
                  "nomColo": "COD_MARC"
              },
            "listaAttributiDipendentiPadri":null
           }
       }
 }
 *
 *
 */

app.post('/api/soluzioni/getDominiAttrEntita', function (req, res) {

    console.log("getDominiAttrEntita");
    console.log(req.body.key);
    switch (req.body.key) {
        case 0:
            readFsAndSendEmissione('Servizio_getDominiAttrEntita_MARCA.json', res);
            break;
        case 3:
            readFsAndSendEmissione('Servizio_getDominiAttrEntita_TIPOALIMENTAZIONE.json', res);
            break;
        case 4:
        readFsAndSendEmissione('Servizio_getDominiiAttrEntita_ALLESTIMENTI.json', res);
            break;
        case 5:
            readFsAndSendEmissione('Servizio_getDominiAttrEntita_MARCA.json', res);
            break;
        case 6:
            readFsAndSendEmissione('Servizio_getDominiAttrEntita_MODELLO.json', res);
            break;
        default :
            readFsAndSendEmissione('Servizio_getDominiAttrEntita_MARCA.json', res);
            break;

    }

   // console.log(req.query);
  //  readFsAndSendEmissione('Servizio_getDominiAttrEntita_MARCA.json', res);
});

/*******
 * api/soluzioni/getDominiAttrEntita  - TIPO ALIMENTAZIONE

 INPUT:
 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "polizza":
       {
          "numOrdi":659839297373,
          "numPoli":"TPO02829718",
          "codProd":1001,
          "codComp":"022",
          "sysdate":"05/03/2007 10:49:13",
          "desProd":"DIMENSIONE AUTO",
          "codRamo":"50",
          "desRamo":"DIMENSIONE AUTO",
          "ibdv":"S",
          "flagResi":false,
          "flagMonoentita": true,
           "entita" :
           {
             "numOggr": 1,
             "prgEntita": 1,
             "tagEntita":"1 - Veicolo",
             "codTogg":"13",
             "prgTogg":1,
             "desEntita":"SETTORE 1 - AUTOVETTURE",
             "attributoSelected":
               {
                  "prgDizi":"4815",
                   "desCamp":"TIPO ALIMENTAZIONE",
                   "flagObbl":true,
                   "flagModi":true,
                   "riferimento":true,
                   "tipoDato":"A",
                   "numCara":"10",
                   "numDeci":"0",
                   "valCara":null,
                   "desCara":null,
                   "flagCarica": true,
                   "listaCampiDipendentiFigli": null,
                   "listaCampiDipendentiPadri": null,
                  "nomTabe": null,
                  "nomColo": "TIP_ALIM"
              },
            "listaAttributiDipendentiPadri":null
           }
       }
 }

 *
 *
 *
 */
app.post('/api/soluzioni/getDominiAttrEntitaTipoA', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_getDominiAttrEntita_TIPOALIMENTAZIONE.json', res);
});


/***
 *
 * /api/soluzioni/getDominiAttrEntita  - MODELLI PER FIAT - 000008

 INPUT:
 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "polizza":
       {
          "numOrdi":659839297373,
          "numPoli":"TPO02829718",
          "codProd":1001,
          "codComp":"022",
          "sysdate":"05/03/2007 10:49:13",
          "desProd":"DIMENSIONE AUTO",
          "codRamo":"50",
          "desRamo":"DIMENSIONE AUTO",
          "ibdv":"S",
          "flagResi":false,
          "flagMonoentita": true,
           "entita" :
           {
             "numOggr": 1,
             "prgEntita": 1,
             "tagEntita":"1 - Veicolo",
             "codTogg":"13",
             "prgTogg":1,
             "desEntita":"SETTORE 1 - AUTOVETTURE",
             "attributoSelected":
               {
                   "prgDizi":"4821",
                   "desCamp":"CODICE MODELLO",
                   "flagObbl":false,
                   "flagModi":true,
                   "riferimento":true,
                   "tipoDato":"A",
                   "numCara":"6",
                   "numDeci":"0",
                   "valCara":null,
                   "desCara":null,
                   "flagCarica": false,
                   "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4824" --ALLESTIMENTO
                      }
                    ],
                    "listaCampiDipendentiPadri": [
                       {
                         "camDipe":"4817"  --MARCA
                       }
                     ],
                   "nomTabe": null,
                   "nomColo": "COD_MODE"
              },
            "listaAttributiDipendentiPadri":[
                {
                  "prgDizi":"4817",
                  "desCamp":"CODICE MARCA",
                  "flagObbl":true,
                  "flagModi":true,
                  "riferimento":true,
                  "tipoDato":"A",
                  "numCara":"6",
                  "numDeci":"0",
                  "valCara":"000008",
                  "desCara":"FIAT - 000008",
                  "flagCarica": true,
                  "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4821"  --MODELLO
                      },
                      {
                         "camDipe":"4824" --ALLESTIMENTO
                      }
                   ],
                  "listaCampiDipendentiPadri": [],
                  "nomTabe": null,
                  "nomColo": "COD_MARC"
                }
            ]
           }
       }
 }
 *
 *
 *
 */

app.post('/api/soluzioni/getDominiAttrEntitaTipoMDF', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_getDominiAttrEntita_MODELLO.json', res);
});


/******
 * /api/soluzioni/getDominiAttrEntita  - ALLESTIMENTO PER MARCA (FIAT - 000008) --> MODELLO (500L - 004142)


 INPUT:
 {
    "numContratto":"TPO02829718",
    "solTrad" : true,
    "polizza":
       {
          "numOrdi":659839297373,
          "numPoli":"TPO02829718",
          "codProd":1001,
          "codComp":"022",
          "sysdate":"05/03/2007 10:49:13",
          "desProd":"DIMENSIONE AUTO",
          "codRamo":"50",
          "desRamo":"DIMENSIONE AUTO",
          "ibdv":"S",
          "flagResi":false,
          "flagMonoentita": true,
           "entita" :
           {
             "numOggr": 1,
             "prgEntita": 1,
             "tagEntita":"1 - Veicolo",
             "codTogg":"13",
             "prgTogg":1,
             "desEntita":"SETTORE 1 - AUTOVETTURE",
             "attributoSelected":
               {
                   "prgDizi":"4824",
                   "desCamp":"CODICE ALLESTIMENTO",
                   "flagObbl":false,
                   "flagModi":true,
                   "riferimento":true,
                   "tipoDato":"A",
                   "numCara":"10",
                   "numDeci":"0",
                   "valCara":null,
                   "desCara":null,
                   "flagCarica": false,
                   "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4815"  -- TIPO ALIMENTAZIONE
                      },
                      {
                         "camDipe":"4812"  -- CILINDRATA
                      }
                   ],
                   "listaCampiDipendentiPadri": [
                       {
                         "camDipe":"4817"  --MARCA
                       },
                       {
                         "camDipe":"4821"  --MODELLO
                       }
                   ],
                  "nomTabe": null,
                  "nomColo": "COD_ALLE"
              },
            "listaAttributiDipendentiPadri":[
                {
                  "prgDizi":"4817",
                  "desCamp":"CODICE MARCA",
                  "flagObbl":true,
                  "flagModi":true,
                  "riferimento":true,
                  "tipoDato":"A",
                  "numCara":"6",
                  "numDeci":"0",
                  "valCara":"000008",
                  "desCara":"FIAT - 000008",
                  "flagCarica": true,
                  "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4821"  --MODELLO
                      },
                      {
                         "camDipe":"4824" --ALLESTIMENTO
                      }
                   ],
                  "listaCampiDipendentiPadri": [],
                  "nomTabe": null,
                  "nomColo": "COD_MARC"
                },
                {
                   "prgDizi":"4821",
                   "desCamp":"CODICE MODELLO",
                   "flagObbl":false,
                   "flagModi":true,
                   "riferimento":true,
                   "tipoDato":"A",
                   "numCara":"6",
                   "numDeci":"0",
                   "valCara":"004142",
                   "desCara":"500L - 004142",
                   "flagCarica": false,
                   "listaCampiDipendentiFigli": [
                      {
                         "camDipe":"4824" --ALLESTIMENTO
                      }
                    ],
                    "listaCampiDipendentiPadri": [
                       {
                         "camDipe":"4817"  --MARCA
                       }
                     ],
                   "nomTabe": null,
                   "nomColo": "COD_MODE"
                }
            ]
           }
       }
 }
 */
app.post('/api/soluzioni/getDominiAttrEntitaTipoAMarca', function (req, res) {
    console.log(req.query);
    readFsAndSendEmissione('Servizio_getDominiiAttrEntita_ALLESTIMENTI.json', res);
});

app.get('/', function (req, res) {
    res.send('Hello World!');
});

app.get('/api/account/user', function (req, res) {
    console.log(req.query);
    readFsAndSend('user_01.json', res);
});


app.get('/api/utils/logout', function (req, res) {
    sendVar(res, logout, 'logout');
});

var logout = {
    Link: 'http://...'
}

app.post('/api/ricerca/getRisultatiRicerca', function (req, res) {
    console.log(req.body);
    readFsAndSend('ricerca_01.json', res);
});

app.get('/api/socio/getDettaglioSocio', function (req, res) {
    readFsAndSend('dettaglio_socio_01.json', res);
});

app.get('/api/socio/verificaCf', function (req, res) {
    readFsAndSend('verifica_cf_piva_01.json', res);
});

app.get('/api/dominio/getTipoInfo', function (req, res) {
    sendVar(res, tipoInfo, 'tipoInfo');
});

app.get('api/dominio/getTipiRecapito', function (req, res) {
    sendVar(res, tiporecapito, 'tiporecapito');
})

app.get('/api/account/getAgenzie', function (req, res) {
    readFsAndSend('agenzie_01.json', res);
});

app.get('/api/account/getProduttori', function (req, res) {
    readFsAndSend('produttori_01.json', res);
});

app.get('/api/account/getCompagnie', function (req, res) {
    readFsAndSend('compagnie_01.json', res);
});


var tiporecapito = [{
    "codReca": 1,
    "numCart": 1,
    desReca: "RESIDENZA"
}, {
    "codReca": 2,
    "numCart": 1,
    desReca: "SEDE SOCIETARIA"
}, {
    "codReca": 3,
    "numCart": 20,
    desReca: "DOMICILIO"
}, {
    "codReca": 4,
    "numCart": 20,
    desReca: "UFFICIO"
}, {
    "codReca": 5,
    "numCart": 1,
    desReca: "RECAPITO PRINCIPALE"
}, {
    "codReca": 6,
    "numCart": 1,
    desReca: "RECAPITO LEGATO IN POLIZZA"
}, {
    "codReca": 7,
    "numCart": 20,
    desReca: "INDIRIZZO DI CORRISPONDENZA"
}, {
    "codReca": 20,
    "numCart": 99,
    desReca: "CENTRO AZIENDALE"
}, {
    "codReca": 21,
    "numCart": 20,
    desReca: "ALTRO RECAPITO"
}];

var tipoInfo = [{
    codInfo: "001",
    desTipoInfo: "tipo info 001"
}, {
    codInfo: "002",
    desTipoInfo: "tipo info 002"
}];

app.get('/api/dominio/getTipoLingua', function (req, res) {
    sendVar(res, tipoLingua, 'tipoLingua');
});

var tipoLingua = [{
    codLingua: "DEU",
    desLingua: "TEDESCO"
}, {
    codLingua: "ITA",
    desLingua: "ITALIANO"
}];

app.get('/api/dominio/getLvlIstruzione', function (req, res) {
    sendVar(res, livelloIstruzione, 'livelloIstruzione');
});

var livelloIstruzione = [{
    "codIstr": 10,
    desIstr: "LICENZA ELEMENTARE"
}, {
    "codIstr": 20,
    desIstr: "LICENZA MEDIA"
}, {
    "codIstr": 30,
    desIstr: "FORMAZIONE PROFESSIONALE"
}, {
    "codIstr": 40,
    desIstr: "DIPLOMA SCUOLA MEDIA SUPERIORE"
}, {
    "codIstr": 60,
    desIstr: "LAUREA BREVE"
}, {
    "codIstr": 70,
    desIstr: "LAUREA PRIMO LIVELLO"
}, {
    "codIstr": 71,
    desIstr: "LAUREA SECONDO LIVELLO"
}, {
    "codIstr": 72,
    desIstr: "DOTTORATO"
}, {
    "codIstr": 80,
    desIstr: "DIPLOMA DI LAUREA"
}, {
    "codIstr": 99,
    desIstr: "NON CODIFICATO"
}]

app.get('/api/dominio/getTipoSocieta', function (req, res) {
    console.log(req.query);
    readFsAndSend('tipi_societa.json', res);
});


app.get('/api/bisogni/getBisogni', function (req, res) {
    console.log(req.query);
    readFsAndSend('bisogni_01.json', res);
});


app.post('/api/account/salvaNodoDefault', function (req, res) {
    console.log(req.query);
    readFsAndSend('nodo_01.json', res);
});


app.get('/api/dominio/getTipoPersona', function (req, res) {
    console.log(req.query);
    readFsAndSend('tipologie_person.json', res);
});


app.get('/api/anagrafica/getAnagraficaCliente', function (req, res) {
    console.log(req.query);

    switch (req.query.proclie) {
        case '1':
        case 'f1':
            readFsAndSend('anagrafica_fisica_01.json', res);
            break;
        case '2':
        case 'f2':
            readFsAndSend('anagrafica_fisica_02.json', res);
            break;
        case '3':
        case 'g1':
            readFsAndSend('anagrafica_giuridica_01.json', res);
            break;
        case '4':
        case 'i1':
            readFsAndSend('anagrafica_individ_01.json', res);
            break;
        default:
            readFsAndSend('anagrafica_fisica_01.json', res);
    }
})

app.post('/api/soluzioni/getConfigurazioneSoluzione', function (req, res) {
    console.log(req.query);
    switch (req.query.proclie) {
        case '1':
            readFsAndSend('soluzioni_01.json', res);
            break;
        case '2':
            readFsAndSend('soluzioni_02.json', res);
            break;
        default:
            readFsAndSend('soluzioni_01.json', res);
    }
})


/*GIACOMO*/

app.get('/api/situazione/getSituazione', function (req, res) {
    console.log(req.query);
    readFsAndSend('situazione_01.json', res);
});

app.get('/api/ricerca/ricercaSocio', function (req, res) {
    console.log(req.query);
    readFsAndSend('ricercaSocio_01.json', res);
});

/*END GIACOMO*/


app.get('/fake/anag', function (req, res) {
    console.log(req.query);

    // readFsAndSend('situazione_01.json', res);
});


app.get('/api/anagrafica/getPDFAppendice', function (req, res) {
    console.log(req.query);
    readFsAndSendPdf('pdf/', req.query.prgRepertorio + '.pdf', res);
});


app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});