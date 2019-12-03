# Arkkitehtuurikuvaus

## Rakenne

Ohjelma rakentuu kolmelle tasolle. Koodin pakkausrakennetta kuvaa seuraava pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Pakkauskaavio.png" widht="250">

Pakkaus _ui_ toteuttaa ohjelman käyttöliittymän javaFX:n avulla. Sovelluslogiikasta vastaa pakkaus _domain_. Pysyväistallennuksesta vastaa _database_ pakkaus, jota käytetään sqlite:llä paikalliseen tietokannan hallintaan. 

## Päätoiminnallisuudet

Sovelluksen muutamia tärkeitä toiminnallisuuksia kuvattuna sekvenssikaavioina.

### Käyttäjän kirjautuminen 

Ohjelman käynnistyessä käyttäjän eteen ilmestyy sisäänkirjautumisnäkymä. Käyttäjän syöttää käyttäjätunnuksensa ja salasanansa niille tarkoitettuihin kenttiin. Tämän jälkeen hän painaa _loginButton_ -nappia, jonka jälkeen sovellus etenee seuraavasti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio%20 kirjautuminen.png" widht="250">

Tapahtumankäsittelijä kutsuu sovelluslogiikasta vastaavaa _domainService_-oliota, joka tarkistaa tietokannasta täsmääkö käyttäjänimi ja salasana luotuja tunnuksia. Tämän _domainService_-olio tekee kutsumalla oliometodilla _findTheUser_ tietokannasta vastaavaa _DBcoordinator_-oliota. Parametreina ovat käyttäjän syöttämät käyttäjätunnus ja salasana. Jos käyttäjä löytyy, palauttaa _DBcoordinator_ haetun käyttäjän _DomainService_-oliolle käsiteltäväksi. 

Sovelluslogiikan puolella _DomainService_ vielä tarkistaa, että käyttäjätunnukset vastaavat kirjautuvan käyttäjän tunnuksia metodeilla _getUserName()_ ja _getPassword()_. Tämän jälkeen kontrolli palaa takaisin käyttöliittymälle, jossa _Stage_-olio _window_ asettaa Opiskelija-alennuslaskurin näkymän, ja palauttaa käyttäjän tiedot tallennetuista alennustuotteista. 
