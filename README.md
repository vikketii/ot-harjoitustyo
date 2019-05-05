# Ohjelmistotekniikka-kurssin projekti
## Kuviogeneraattori
Projektin aiheena on kertotauluun perustuva kuviogeneraattori.

[Lopullinen julkaisu.](https://github.com/vikketii/ot-harjoitustyo/releases/tag/lopullinen)

[Toinen julkaisu.](https://github.com/vikketii/ot-harjoitustyo/releases/tag/viikko6)

[Ensimmäinen julkaisu.](https://github.com/vikketii/ot-harjoitustyo/releases/tag/viikko5)

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/testausdokumentti.md)

[Tuntikirjanpito](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot
Jar-tiedoston voi suorittaa komennolla
```
java -jar timestable.jar
```

Lähdekoodista ohjelman voi suorittaa pääkansiossa komennolla
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

JavaDocista voi luoda HTML-version komennolla
```
mvn javadoc:javadoc
```
Sitä voi tarkastella avaamalla selaimella kansion _target/site/apidocs/_

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

Checkstyle tyyliraportin voi luoda komennolla
```
mvn jxr:jxr checkstyle:checkstyle
```
Raportin löytää tiestosta _target/site/checkstyle.html_
