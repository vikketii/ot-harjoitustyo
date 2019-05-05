# Käyttöohje
Lataa tiedosto [timestable.jar](https://github.com/vikketii/ot-harjoitustyo/releases/tag/lopullinen)

## Suorittaminen
Ohjelma suoritetaan komennolla
```
java -jar timestable.jar
```

## Käyttäjän valinta
Sovellus käynnistyy käyttäjän valintaan. Voit joko tehdä uuden käyttäjän tai valita jo olemassaolevan alhalla olevasta valikosta. Mikäli luodaan käyttäjä, jolla on sama nimi kuin olemassa olevalla käyttäjällä, korvataan vanha uudella.

## Kuviogeneraattori
![Käyttöliittymä](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/kayttoliittyma.png)
Käyttäjän valinnan jälkeen ohjelma siirtyy kuviogeneraattorinäkymään.
- Ohjelman suorituksen voi lopettaa painamalla 'quit'-painiketta.
- 'Play'-valinta käynnistää ja lopettaa kuvion animaation.
- 'Progress' kertoo, missä kohtaa kuvion generointia mennään, ja tilanteen voi asettaa kirjoittamalla lukuarvon alapuolella olevaan kenttään.
- Animaation nopeutta pystyy muokkaamaan 'Change speed' tekstin alapuolella olevalla liukusäätimellä.
- Piirrettävien viivojen määrää voi muuttaa 'Lines' kohdan alapuolella olevalla kentällä.
- Kuvion väritystä voi vaihtaa 'Line color' ja 'Background color' säätimillä.
- Nykyiset asetukset voi tallentaa tietokantaan 'Save settings' painikkeella. Asetukset voi ladata ohjelman uudelleenkäynnistyksen yhtyedessä valitsemalla saman käyttäjätunnuksen.