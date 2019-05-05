# Arkkitehtuurikuvaus
## Rakenne
Ohjelman rakenne pyrkii noudattamaan kerrosarkkitehtuuria. Pakkaus _timestable.ui_ sisältää sovelluksen käyttöliittymän. Käyttöliittymä kutsuu sovelluslogiikasta vastaavaa pakkausta _timestable.domain_ ja injektoi sille tietokantayhteydestä huolehtivan pakkauksen _timestable.dao_.

## Käyttöliittymä
Käyttöliittymä koostuu kahdesta JavaFX-näkymästä: käyttäjätilin valinnasta ja  kuviogeneraattorista. Näkymät ovat Scene-olioita ja käyttävät asettelussaan Grid-oliota helposti muokattavan käyttöliittymän toteuttamiseen.
Kuviogeneraattori päivittää kuviota aina kun käyttäjä muuttaa kuvion säätöjä tai kun animaatio on käynnissä.

## Sovelluslogiikka
![Luokkakaavio](https://github.com/vikketii/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/luokkakaavio.png)

TimeTableService-luokka vastaa sovelluslogiikan yhteenvedosta. Se saa käyttöliittymän säätimien arvoja sille injektoidun Settings-luokan avulla.
TimesTable-luokka toteuttaa kuvion matemaattisen laskennan, käyttäen hyväksi Vector-luokan vektoreita ja Settings-luokan arvoja.
TimesTableService tarjoaa käyttöliittymälle metodit kuvion tilanteen päivittämiseen sekä tietokannan kanssa keskusteluun.

## Tietojen pysyväistallennus
Tietokantaa hallinnoi FileUserDao, joka mahdollistaa käyttäjän hakemisen, tallentamisen ja muokkaamisen. Luokka noudattaa DAO-suunnittelumallia ja sovelluslogiikka käyttääkin luokkaa vain UserDao-rajapinnaan avulla. Tietokanta on toteutettu SQLite-relaatiotietokantajärjestelmän avulla. Tietokannan kanssa kommunikointiin on käytetty sqlite-jdbc kirjastoa.

Suoritettaessa ohjelma luo users.db tiedoston. Tietokantaan lisätään kolme oletuskäyttäjää, joiden asetukset vastaavat käyttäjien nimiä.
Tallennuksen testauksessa käytetään test.db tiedostoa.

## Ohjelman rakenteeseen jääneet heikkoudet
### Käyttöliittymä
Käyttöliittymän toteutus olisi syytä jakaa pienempiin osiin eli metodeihin ja luokkiin. Käyttöliittymän käyttäjystävällisyyttä sekä ulkonäköä voisi myös kohentaa huomattavasti.
Valittaessa jo olemassa oleva käyttäjä tulisi käyttöliittymän päivittää kuviosäätimet käyttäjän asetuksien mukaisesti. Nyt käyttöliittymä ei tätä tee vaikka ohjelma reagoi muuten odotetusti.

### Asetukset
Käyttäjältä pyydettäville asetuksille pitäisi tehdä tarkistukset, nyt ohjelma tulostaa joissain tapauksissa virheen konsoliin (mutta jatkaa toimintaansa).

### Pysyväistallennus
Koodin refaktoroinnilla voitaisiin vähentää sen toisteisuutta.

