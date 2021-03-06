# Opiskelija-alennuslaskuri, Ohjelmistotekniikka -kurssi (Helsingin yliopisto)

Opiskelija-alennuslaskurin avulla käyttäjä voi seurata henkilökohtaista opiskelija-alennuksen kertymää. Opiskelija voi tarkastella saamiaan alennuksia ajallisesti, määrällisesti ja prosentuaalisesti. 

## Dokumentaatio

[vaatimusmäärittely](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/vaatimusm%C3%A4%C3%A4rittely.md)

[työaikakirjanpito](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/ty%C3%B6aikakirjanpito.md)

[arkkitehtuuri](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/arkkitehtuuri.md)

[käyttöohje](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/k%C3%A4ytt%C3%B6ohje.md)

[testausdokumentti](https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/Testausdokumentti.md)

## Releaset

[Viikko5](https://github.com/StrappedGlint13/ot-harjoitustyo/releases/viikko5)

[Viikko6](https://github.com/StrappedGlint13/ot-harjoitustyo/releases/tag/Viikko6)

[Loppupalautus](https://github.com/StrappedGlint13/ot-harjoitustyo/releases/tag/Loppupalautus)

## Komentorivitoiminnot 

### Ohjelman suorittaminen

Ohjelma voidaan suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=ui.CalculatorUi
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




