# Opiskelija-alennuslaskuri, Ohjelmistotekniikka -kurssi (Helsingin yliopisto)

Opiskelija-alennuslaskurin avulla käyttäjä voi seurata henkilökohtaista opiskelija-alennuksen kertymää. Opiskelija voi tarkastella saamiaan alennuksia ajallisesti, määrällisesti ja prosentuaalisesti. 

## Dokumentaatio

[vaatimusmäärittely](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)

[työaikakirjanpito](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/ty%C3%B6aikakirjanpito.md)

[arkkitehtuuri](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

## Komentorivitoiminnot 

### Ohjelman suorittaminen

Ohjelma voidaan suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=Ui.Main
```

### Testaus

Testit suoritetaan komennolla 

```
mvn test

```
Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Raportti löytyy tiedostosta _./target/site/jacoco/index.html_

Testikattavuuden voi myös tarkistaa selaimelta komennolla

```
chromium-browser target/site/jacoco/index.html 
```

### Suoritettavan jarin generointi

Komento

```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston OpiskelijaAlennuslaskuri-1.0-SNAPSHOT.jar

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

### Checkstyle 

Tiedostoon checkstyle.xml määrittelemät tarkistukset suoritetaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```
Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto target/site/checkstyle.html




