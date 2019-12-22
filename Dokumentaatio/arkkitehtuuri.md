# Arkkitehtuurikuvaus

## Rakenne

Ohjelma rakentuu kolmelle tasolle. Koodin pakkausrakennetta kuvaa seuraava pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/pakkaus.png" widht="250">

Pakkaus _ui_ toteuttaa ohjelman käyttöliittymän javaFX:n avulla. Sovelluslogiikasta vastaa pakkaus _domain_. Pysyväistallennuksesta vastaa _database_ pakkaus, jota käytetään sqlite:llä paikalliseen tietokannan hallintaan. 

## Käyttöliittymä

Käyttöliittymästä vastaava luokka on [CalculatorUi](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/ui/CalculatorUi.java). Käyttöliittymä koostuu [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olioina toteutetuista kolmestä päänäkymästä ja kahdesta virheilmoitus-näkymästä:


- kirjautumisnäkymä

- rekisteröintinäkymä

- laskinnäkymä

- epäonnistunut kirjautumisnäkymä

- epäonnistunut rekisteröintinäkmyä


Jokainen näkymä on vuorollaan sijoitettuna sovelluksen [stage](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html)-olioon näkyväksi.

Käyttöliittymä on eriytetty sovelluslogiikasta, josta se hyödyntää _DomainServicen_ tarjoamia apumetodeja. 

## Sovelluslogiikka

Sovelluksen loogisen datamallin muodostavat luokat User ja Product, jotka kuvaavat käyttäjiä ja heidän ostamiaan opiskelija-alennettuja tuotteita.

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/looginen%20datamalli.png" widht="250">

[DomainService](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/domain/DomainService.java)-olio vastaa toiminnallisuuksista kokonaisuuksista, ja tarjoaa käyttöliittymälle mm. seuraavia metodeja:

- checkIfuserExist(String userName, String passWord)
- logIn (User user)
- getUser(String userName, String passWord)
- addProductDB(Product product) 

_DomainService_ käsittelee myös käyttäjiä ja tuotteita pakkauksessa _database_ luokan [ProductDao](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/database/ProductDao.java) ja [UserDao](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/database/UserDao.java) avulla.

Sovelluslogiikkaa ja muita ohjelman osia kuvaava luokka/pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Pakkauskaavio_02.png" widht="250">

## Tietojen pysyväistallennus

Pakkaukessa _database_ olevat luokat _UserDao_ ja _ProductDao_ huolehtivat tietojen tallennuksesta tietokantaan. Luokan _DataBaseSetterin_ vastuulla on tietokannan alustaminen. 

Luokat on luotu [Data Access Object](https://en.wikipedia.org/wiki/Data_access_object) mallilla. _DataBaseSetter_ luokan konstruktorille on annettu tietokannan nimi "db", joka tallentuu projektin juurikansioon. 

### Tietokantataulut

Käyttäjät ja tuotteet tallennetaan kahteen erilliseen tietokantatauluun:

|Users||||
|:---|:---|:---|:---|
|(pk) studentNumber:integer|userName:Varchar(200)|passWord:Varchar(200)|email:Varchar(200)|

|Products|||||
|:---|:---|:---|:---|:---|
|(pk) id:Integer|(fk)studentNumber_id:Integer|name:Varchar(200)|normalPrice:Double|studentPrice:Double|discountPercentage:Double|

Tietokanta sisältää käyttäjän tiedot, joita tarvitaan tuotteiden hallintaan. 

## Päätoiminnallisuudet

Sovelluksen muutamia tärkeitä toiminnallisuuksia kuvattuna sekvenssikaavioina.

### Käyttäjän kirjautuminen 

Ohjelman käynnistyessä käyttäjän eteen ilmestyy sisäänkirjautumisnäkymä. Käyttäjän syöttää käyttäjätunnuksensa ja salasanansa niille tarkoitettuihin kenttiin. Tämän jälkeen hän painaa _loginButton_ -nappia, jonka jälkeen sovellus etenee seuraavasti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio_%20kirjautuminen_02.jpg" widht="250">

[Tapahtumankäsittelijä]( kutsuu sovelluslogiikasta vastaavaa _domainService_-oliota, joka tarkistaa tietokannasta täsmääkö käyttäjänimi ja salasana luotuja tunnuksia. Tämän _domainService_-olio tekee kutsumalla oliometodilla _findTheUser_ käyttäjien tietokantataulusta vastaavaa UserDao-oliota. Parametreina ovat käyttäjän syöttämät käyttäjätunnus ja salasana. Jos käyttäjä löytyy, palauttaa _UserDao_ haetun käyttäjän _DomainService_-oliolle käsiteltäväksi. 

Sovelluslogiikan puolella _DomainService_ vielä tarkistaa, että käyttäjätunnukset vastaavat kirjautuvan käyttäjän tunnuksia metodeilla _getUserName()_ ja _getPassword()_. Tämän jälkeen kontrolli palaa takaisin käyttöliittymälle, jossa _Stage_-olio _window_ asettaa Opiskelija-alennuslaskurin näkymän, ja palauttaa käyttäjän tiedot tallennetuista alennustuotteista. 

### Uuden käyttäjän lisääminen 

Sisäänkirjautumisnäkymästä voi siirtyä luomaan uuden käyttäjän painamalla painiketta _new user?_. Tämän jälkeen kontrolli etenee seuraavasti:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio:%20uusi%20k%C3%A4ytt%C3%A4j%C3%A4.png" width="600">

[Tapahtumankäsittelijä](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/ui/CalculatorUi.java#L313) käsittelee käyttäjän syöttämiä tietoja ensin käyttöliittymän sisäisten ehtolauseiden kautta. Sovelluslogiikka hyödyntää metodia [iSunique](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/ui/CalculatorUi.java#L330) uniikin opiskelijanumeron tarkistuksessa. _UserDao_ tarkistaa tietokantataulusta tämän ja palauttaa totuusarvon sovelluslogiikalle. Mikäli uusi käyttäjätunnus täyttää kaikki ehdot, sovelluslogiikka luo uuden käyttäjän tietokantaan kutsumalla _userDaon_ metodia [create](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/main/java/domain/DomainService.java#L82). Tämän jälkeen tapahtumankäsittelijä vaihtaa näkymäksi takaisin kirjautumisnäkymän. 

## Ohjelman rakenteeseen jääneet heikkoudet

### Käyttöliittymä

Graafisessa käyttöliittymän rakenne on määritelty metodin _start_ alle. Esimerkiksi näkymät oltaisiin voitu eritellä omiksi olioikseen, niin koodista olisi tullut selkeämpää. 

### Database

Tietokannan käsittelystä vastaavassa paketissa _database_ on suhttellisen paljon toisteista koodia. Dao-rajapintojen käyttö jäi myös turhan vähäiselle tasolle. 
