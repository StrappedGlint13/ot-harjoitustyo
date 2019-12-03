# Arkkitehtuurikuvaus

## Rakenne

Ohjelma rakentuu kolmelle tasolle. Koodin pakkausrakennetta kuvaa seuraava pakkauskaavio:

<img src="https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Pakkauskaavio.png" widht="250">

Pakkaus _ui_ toteuttaa ohjelman käyttöliittymän javaFX:n avulla. Sovelluslogiikasta vastaa pakkaus _domain_. Pysyväistallennuksesta vastaa _database_ pakkaus, jota käytetään sqlite:llä paikalliseen tietokannan hallintaan. 

Ohjelman pakkausrakenne näyttää tältä:

<img src=https://github.com/StrappedGlint13/ot-harjoitustyo/blob/master/Dokumentaatio/kuvat/Sekvenssikaavio_%20kirjautuminen.png" widht="250">
