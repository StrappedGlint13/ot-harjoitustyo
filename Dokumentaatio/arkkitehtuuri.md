# Arkkitehtuurikuvaus

## Rakenne

Ohjelma rakentuu kolmelle tasolle. Koodin pakkausrakennetta kuvaa seuraava pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Pakkauskaavio.png" widht="250">

Pakkaus _ui_ toteuttaa ohjelman käyttöliittymän javaFX:n avulla. Sovelluslogiikasta vastaa pakkaus _domain_. Pysyväistallennuksesta vastaa _database_ pakkaus, jota käytetään sqlite:llä paikalliseen tietokannan hallintaan. 

## Päätoiminnallisuudet

Sovelluksen muutamia tärkeitä toiminnallisuuksia kuvattuna sekvenssikaavioina.

### Käyttäjän kirjautuminen 

Ohjelman käynnistyessä käyttäjän eteen ilmestyy sisäänkirjautumisnäkymä. Käyttäjän syöttää käyttäjätunnuksensa ja salasanansa niille tarkoitettuihin kenttiin, jonka jälkeen hän painaa _loginButton_ -nappia, jonka jälkeen sovellus etenee seuraavasti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio%20 kirjautuminen.png" widht="250">

Tapahtumankäsittelijä kutsuu sovelluslogiikasta vastaavaa domainService-oliota, joka tarkistaa tietokannasta täsmääkö käyttäjänimi ja salasana luotuja tunnuksia. Tämän domainService-olio tekee kutsumalla oliometodilla _findTheUser_ tietokannasta vastaavaa DBcoordinator-oliota. Parametreina ovat käyttäjän syöttämät käyttäjätunnus ja salasana. Jos käyttäjä löytyy, palauttaa DBcoordinator haetun käyttäjän DomainService-oliolle käsiteltäväksi. 

Sovelluslogiikan puolella DomainService vielä tarkistaa, että käyttäjätunnukset vastaavat kirjautuvan käyttäjän tunnuksia metodeilla getUserName() ja getPassword(). Tämän jälkeen kontrolli palaa takaisin käyttöliittymälle, jossa Stage-olio _window_ asettaa Opiskelija-alennuslaskurin näkymän, ja palauttaa käyttäjän tiedot tallennetuista alennustuotteista. 
