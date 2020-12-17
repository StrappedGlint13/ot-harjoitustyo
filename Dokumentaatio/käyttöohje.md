# Käyttöohje

## Sovelluksen käynnistäminen

Käynnistä ohjelma komennolla 

`java -jar OpiskelijaAlennuslaskuri.jar`

## Kirjautumisnäkymä

Sovellus käynnistyy kirjautumisnäkymään:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/login.png" width="600">

Kirjautuminen onnistuu, mikäli käyttäjätunnus ja salasana ovat oikein. Tämän jälkeen laskimeen kirjaudutaan painamalla [login](){: .btn } painiketta. 

## Uuden käyttäjän luominen

Kirjautumisnäkymästä pääsee luomaan uuden käyttäjän painamalla _new user?_ näppäintä. Tämän jälkeen käyttäjän tulee syöttää käyttäjänimi, sähköpostiosoite, opiskelijanumero ja salasana, niille annettujan ehtojen mukaisesti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Registeration.png" width ="600">

Jos käyttäjän luominen onnistuu, kirjautumisnäkymä aukeaa. Jos käyttäjän luominen epäonnistuu, järjestelmä antaa virheilmoituksen:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Errorscene.png" width="600">

## Laskinnäkymä

Käyttäjän onnistuttua kirjautumaan sisään, aukeaa laskinnäkymä:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Calculator.png" width="600">

Laskinnäkymässä näkyy käyttäjän jo aikaisemmin lisäämät opiskelija-alennetut tuotteet. Oikeassa alalaidassa on yhteissummat normaali- ja opiskelija-alennetuista tuotteista sekä keskiarvo prosentuaalisesta alennuksesta. Käyttäjä näkee myös käyttäjänimensä vasemmasta ylälaidasta, ja oikeassa ylälaidassa on _logout_ painike, josta käyttäjä voi kirjautua ulos sovelluksesta. _Add_ painikkeella sovellus tallentaa uuden lisätyn tuotteen seurantaan. 
