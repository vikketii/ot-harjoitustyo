# Vaatimusmäärittely
## Sovelluksen tarkoitus
Sovelluksen avulla voidaan tuottaa erilaisia ympyrämuotoisia kuvioita perustuen kertotauluun. Lisää tietoa kuvioiden toteutustavasta löytyy [Mathologerin videosta](https://www.youtube.com/watch?v=qhbuKbxJsk8).
Kuvioiden piirtotapaa ja animaatiota voidaan muokata sovelluksen käyttöliittymän avulla.

## Käyttöliittymä
Sovellus koostuu kahdesta näkymästä. Ensimmäisessä näkymässä käyttäjältä kysytään uuden käyttäjätilin luomista tai jo olemassaolevan tilin valitsemista. Jos luodaan uusi tili, se tallennetaan tietokantaan. Jos taas valitaan jo olemassaoleva tili, ladataan siihen liittyyvät asetukset seuraavaan näkymään.
Toinen käyttöliittymänäkymä on kuviogeneraattori. Generaattori koostuu otsikosta, kuvioiden piirtoruudusta ja erilaisista säätimistä. Säädinten avulla voidaan lopettaa ohjelma, muokata kuviogeneraattorin toimintaa ja tallentaa nykyiset asetukset.

## Perusversion tarjoama toiminnallisuus
### Ennen tilin valintaa
- käyttäjä voi ehdä uuden tilin
  - jos tilillä on sama nimi kuin jo luodulla tilillä, korvataan vanha uudella
- käyttäjä voi valita tilin jo tietokantaan luoduista tileistä (oletuksena kolme)

### Tilin valinnan jälkeen
- otsikko, joka kertoo nykyisen käyttäjän
- kuvioiden katselunäkymä
- kuvion muokkaus tapahtuu säätimillä, ja muokattavia parametrejä ovat
  - piirrettävien viivojen määrä
  - laskennassa käytettävän kertoimen arvon asettaminen
  - piirrettävien viivojen väri
  - taustan väri
- kuvion animointia voi myös säätää parametreillä
  - päälle/pois painike
  - nopeussäädin
- nykyisten asetusten tallennus tietokantaan
- ohjelman lopetuspainike

## Jatkokehitysideoita
Ohjelman perusidea on nykyisellään aika suppea, mutta erilaisia jatkokehitysideoita voisi olla esimerkiksi
- käyttäjän asetusten lataaminen myös käyttöliittymän säätimiin sovelluksen käynnistyksen yhteydessä
- erilaisten kuvioiden piirtäminen, esimerkiksi fraktaalien
- uusien parametrien lisääminen
  - viivojen piirtämisen käytettävän ympyrän muodon muokkaaminen. Tällöin voitaisiin esimerkiksi esittää planeettojen kiertoratojen aikaansaamia kuvioita, kuten tässä [Francesco Carpinterin julkaisemassa videossa](https://www.youtube.com/watch?v=r_DYZWpp95g)
  - viiivojen värityksen laajempi muokkaaminen esimerkiksi liukuväreillä
- käyttäjien kirjautuminen salasanalla