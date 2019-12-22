# Testausdokumentti

Ohjelmaa on testattu  yksikkötestein JUnitilla sekä manuaalisesti tapahtunein järjestelmätason testein.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoitujen testeistä päävastuussa on pakkauksen _domain_ ja _database_ luokkia testaavat integraatiotesti [DomainServiceTest](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/test/java/domain/DomainServiceTest.java). Integraatiotesti _DomainServiceTest_ integroi testeihin  myös pakkauksessa _database_ olevia luokkia. Myös sovellusloogikan luokille [User](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/OpiskelijaAlennuslaskuri/OpiskelijaAlennuslaskuri/src/test/java/domain/UserTest.java) ja [Product](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/testaus.md) on luotu yksikkötestejä.   

### Testauskattavuus

Sovelluksen testauksen rivikattavuus on 87% ja haarautumakattavuus 76%. Käyttöliittymäkerros ei ole mukana testauksessa.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/kayttoohje.md) kuvaamalla tavalla sekä OSX- että Linux-ympäristöön siten, että sovelluksen käynnistyshakemistossa on ollut käyttöohjeen kuvauksen mukainen _config.properties_-tiedosto.

Sovellusta on testattu sekä tilanteissa, joissa käyttäjät ja työt tallettavat tiedostot ovat olleet olemassa ja joissa niitä ei ole ollut jolloin ohjelma on luonut ne itse.

### Toiminnallisuudet

Kaikki [määrittelydokumentin](https://github.com/mluukkai/OtmTodoApp/blob/master/dokumentaatio/vaatimusmaarittely.md#perusversion-tarjoama-toiminnallisuus) ja käyttöohjeen listaamat toiminnallisuudet on käyty läpi. Kaikkien toiminnallisuuksien yhteydessä on syötekentät yritetty täyttää myös virheellisillä arvoilla kuten tyhjillä.

## Sovellukseen jääneet laatuongelmat

Sovellus ei anna tällä hetkellä järkeviä virheilmoituksia, seuraavissa tilanteissa
- Kun käyttäjä luo uutta käyttäjää ja hän jättää opiskelijanumeron tyhjäksi tai syöttää opiskelijanumero kenttään kirjaimia. 

