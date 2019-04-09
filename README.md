# Ohjelmistotekniikka-kurssin projekti
## Kuviogeneraattori
Projektin aiheena on kertotauluun perustuva kuviogeneraattori.

## Dokumentaatio

[Alustava vaatimusmäärittely](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuuri](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Tuntikirjanpito](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


## Komentorivitoiminnot

Ohjelman voi suorittaa pääkansiossa komennolla
```
mvn compile exec:java -Dexec.mainClass=timestable.ui.TimesTableUi
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

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_
