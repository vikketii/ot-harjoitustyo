# Testausdokumentti
Ohjelman testaus on suoritettu automatisoiduilla JUnit testeillä sekä manuaalisesti graafisen käyttöliittymän avulla.

## Yksikkö- ja integraatiotestaus
Sovellusta on testattu sekä logiikkaluokkia että DAO-luokkaa käsitelevillä automatisoiduilla testeillä. Tietokantatoimintojen testausta varten testit käyttävät test.db tiedostoa.

Testikattavuudessa on vielä parannettavaa sekä DAO- että logiikkaluokissa.
![Testikattavuus](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/jacoco.png))

## Sovellukseen jääneet laatuongelmat
Käyttäjä voi antaa generaattorin tekstikenttiin ei numeerillista syötettä, mikä aiheuttaa joissain tilanteissa ei-järkevän virheen tulostumisen. Tämä ei kuitenkaan lopeta ohjelman suoritusta.
Myös tietokannan luku/kirjoitusoikeuden puuttuminen aiheuttaisi virheen.