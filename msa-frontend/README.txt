Per approfondimenti fare riferimento alla wiki al link seguente:
http://itaswkf.gruppoitas.local/mediawiki/index.php?title=Frontend

/*************************************************/
/*************** SETUP REQUIREMENT ***************/
/*************************************************/


    # Installare Node ((v4.6.0)) + Npm ((3.10.8))

    # Creo e mi sposto su una cartella temporanea
    mkdir /tmp/nodeinst
    cd /tmp/nodeinst

    # scarico il pacchetto di Node
    wget https://nodejs.org/download/release/v4.6.0/node-v4.6.0-linux-x86.tar.gz

    # estraggo il pacchetto e lo copio nella dir di installazione
    tar -xzf ./node-v4.6.0-linux-x86.tar.gz
    sudo mkdir -p /opt/nodejs/
    sudo mv ./node-v4.6.0-linux-x86 /opt/nodejs/

    # aggiornare NPM alla versione più recente
    cd /tmp/nodeinst
    sudo /opt/nodejs/node-v4.6.0-linux-x86/bin/npm install npm -g

/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@  END Setup REQUIREMENT   @@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/



/*************************************************/
/************** Setup ENVIRONMENT ****************/
/*************************************************/

    @Linux && OS

    # Installare dipendenze npm
    sudo npm install

    # Installare Bower
    sudo npm install bower
    sudo npm install -g bower

    # Installare moduli Bower
    sudo bower install

    # Installare Gulp
    sudo npm install gulp
    sudo npm install -g gulp


    @Windows lanciare i comandi da cmd

    # Installare dipendenze npm
    npm install

    # Installare Bower
    npm install bower
    npm install -g bower

    # Installare moduli Bower
    bower install

    # Installare Gulp
    npm install gulp
    npm install -g gulp

/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@  END Setup ENVIRONMENT   @@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/



/*************************************************/
/************    START UP FrontEnd   *************/
/*************************************************/

 FOR developer:

    # from $ITAS_NFC_FE_HOME
    gulp
    N.B. Per gli utenti mac lanciare il comando gulp com privilegi di amministratore (sudo gulp)

    # FOR Test environment:
    gulp test

    # FOR Integration environment:

    # FOR Production environment:

/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@  END START UP FrontEnd   @@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/


/*************************************************/
/**************  Setup COMMITGIT  ****************/
/*************************************************/

    # Verificare nuove versioni

    git status

    # Assegnare modifiche

    git add -A

    # Aggiungere commento

    git commit -am "Commento custom"

    # Push

    git push

/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@   END setup COMMITGIT    @@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/

Nella cartella expressnode è  disposizione un applicativo per invocare localmente
i servizi remoti
install
cd expressnode
npm install
creazione dir node_modules
per avviere il servizio
scrivere
$ node index.js

comparira il seguente messaggio
Example app listening on port xxxx!
per poter usufruire del servizo
entrare nel file app.js nella cartella app
ed impostare la seguente variabile
app.constant('serverhost','http://localhost:xxxx/')
avviare il gulp in locale
dalla root principale come sopra detto




/*************************************************/
/**********************   FAQ   ******************/
/*************************************************/

# Conoscere i processi appesi del livereload:

sudo lsof -i :35729

/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@  END FAQ  @@@@@@@@@@@@@@@@@@@/
/@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/

