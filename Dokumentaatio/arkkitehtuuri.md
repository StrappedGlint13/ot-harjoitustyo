# Arkkitehtuurikuvaus

## Rakenne

Ohjelma rakentuu kolmelle tasolle. Koodin pakkausrakennetta kuvaa seuraava pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Pakkauskaavio.png" widht="250">

Pakkaus _ui_ toteuttaa ohjelman käyttöliittymän javaFX:n avulla. Sovelluslogiikasta vastaa pakkaus _domain_. Pysyväistallennuksesta vastaa _database_ pakkaus, jota käytetään sqlite:llä paikalliseen tietokannan hallintaan. 

## Päätoiminnallisuudet

Sovelluksen muutamia tärkeitä toiminnallisuuksia kuvattuna sekvenssikaavioina.

### Käyttäjän kirjautuminen 

Ohjelman käynnistyessä käyttäjän eteen ilmestyy sisäänkirjautumisnäkymä. Käyttäjän syöttää käyttäjätunnuksensa ja salasanansa niille tarkoitettuihin kenttiin, jonka jälkeen hän painaa _loginButton_ -nappia, jonka jälkeen sovellus etenee seuraavasti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio_%20kirjautuminen.png" widht="250">

Tapahtumankäsittelijä kutsuu sovelluslogiikasta vastaavaa domainService-oliota, joka tarkistaa tietokannasta täsmääkö käyttäjänimi ja salasana luotuja tunnuksia. Tämän domainService-olio tekee kutsumalla olio _findTheUser_ tietokannasta vastaavaa DBcoordinator-oliota. Jos käyttäjä löytyy, palauttaa DBcoordinator haetun käyttäjän DomainService-oliolle käsiteltäväksi.  
