# Ohjelmistotekniikka-kurssin projekti
## Kuviogeneraattori
Projektin aiheena on kertotauluun perustuva kuviogeneraattori.

[Ensimmäinen julkaisu.](https://github.com/vikketii/ot-harjoitustyo/releases/tag/viikko5)

## Dokumentaatio

[Alustava vaatimusmäärittely](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot

Ohjelman voi suorittaa pääkansiossa komennolla
```
mvn compile exec:java -Dexec.mainClass=timestable.ui.TimesTableUi
```

Suoritettavan jar-tiedoston voi generoida komennolla
```
mvn package
```

Checkstyleraportin saa luotua komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```

Sitä voi tarkastella avaamalla selaimella tiedoston _target/site/checkstyle.html_

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_
