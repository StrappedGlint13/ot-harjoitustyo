  GNU nano 2.9.3                                                                                   Vaatimusmäärittely                                                                                              

## Vaatimusmäärittely

### Sovelluksen tarkoitus

Sovelluksen avulla käyttäjien on mahdollista seurata henkilökohtaista opiskelija-alennus kertymäänsä. Useampi rekisteröitynyt käyttäjä voi käyttää sovellusta yhtäaikaa. Sovellus on silti tarkoitettu henkilökohtaiseen opiskelija-alennuksen seurantaan. 

### Käyttäjät

Aluksi sovellusta on tarkoitus käyttää pelkästään normaalin käyttäjän tapaan. Myöhempää mahdollista kulutuskäyttäytymistutkimusta tai suuremman tiedon keruun johdosta, sovellukselle voidaan määritellä admin-käyttäjiä. 

### Käyttöliittymäluonnos

Sovelluksessa tulee olemaan 3 päänäkymää ja kaksi virheilmoitusnäkymää. Aluksi sovelluksessa avautuu kirjautumisnäkymä, josta voi siirtyä uuden käyttäjän luomisnäkymään tai, jos käyttäjä on jo olemassa, hän voi kirjautua suoraan opiskelija-alennussovelluslaskimeen. Virheilmoitusnäkymät ilmestyvät, jos kirjautuminen tai uuden käyttäjän rekisteröinti epäonnistuu. Tätä seuraa myös opastus, miksi käyttäjä ei onnistunut kirjautumaan tai luomaan uutta käyttäjää. 

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Vaatimusm%C3%A4%C3%A4rittely.png" width="400">

**Perusversion tarjoama toiminnallisuus**

### Kirjautumisnäkymä

- Käyttäjä voi kirjautua laskimeen 
  - Käyttäjä pääsee sisään laskimeen, mikäli käyttäjätunnus ja salasana löytyvät tietokannasta 
- Käyttäjä voi luoda uuden käyttäjätunnuksen 
- Käyttäjälle tulee ilmoitus, mikäli käyttäjätunnus tai salasana on väärin 

### Rekisteröintinäkymä
-Käyttäjä luo uuden ja uniikin käyttäjätunnuksen
  - salasana ei saa sisältää ä, ö tai å:ta
  - opiskelijanumero tulee olla uniikki, ja 9 numeroa pitkä. 
  - käyttäjätunnus on vähintään 2 merkkiä pitkä 
  - mitään kenttää ei saa jättää tyhjäksi  
  - jos uuden käyttäjätunnus ei saavuta vähimmäisehtoja, ilmoittaa järjestelmä tästä 
  - käyttäjä voi palata takaisin kirjautumisnäkymään 

###  Laskinnäkymä
  - käyttäjä voi lisätä tuotteita laskimeen
  - käyttäjä voi tallentaa tuotteita tietokantaan 
  - kirjautuessa laskimeen, käyttäjälle tulee aikaisemmin lisätyt tuotteet näkyviin
  - käyttäjä näkee paljon hän on saanut opiskelija-alennusta rahamäärällisesti, ja prosentuaalisesti
  - käyttäjä näkee vain oman opiskelija-alennusseurantansa
  - käyttäjä pystyy kirjautumaan ulos laskimesta 
  
###  Jatkokehitysidoeita
  
**Perusversion jälkeen mahdollisia toteutettavia toiminnallisuuksia**
  - hakukentän luominen tuotteiden lisäämisen helpottamista varten 
  - erilaisten filttereiden luominen
  - mahdollisuus kategorisoida ostettuja tuotteita
  - mahdollisuus yhdistää muiden käyttäjien kanssa 
  - tuotteiden tietojen lisäys ja mahdollisuus tarkastella niitä

  




