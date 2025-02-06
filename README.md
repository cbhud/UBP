# UBP
 SQL DATABASE PROJECT
 - Design database
 - Implement database
 - Implement java app for CRUD operations

Documentation:

## UNIVERZITET „MEDITERAN“ PODGORICA

## FAKULTET ZA INFORMACIONE TEHNOLOGIJE, PODGORICA

# UVOD U BAZE PODATAKA

# Završni ispit - Željeznica

## Sadržaj:

- 1. Uvod
- 2. Scenario
- 3. ER model
- 4. Relacioni model
- 5. Grafikon
- 6. Opis aplikacije
- 7. Zaključak


## 1. Uvod

U okviru ovog projekta razvijen je sistem za upravljanje bazom podataka željeznice, koji
omogućava efikasno vođenje evidencije ključnih segmenata poslovanja. Projekat uključuje
ER model i Relacionu bazu podataka kao i Java aplikaciju, koja služi za upravljanje podacima
i praćenje statistike poslovanja u realnom vremenu, koje će omoguciti upravi da planira
buduće poslovanje. Dokumentacija pruža detaljan pregled ER modela, relacionih modela,
grafikona, implementacije u MySQL-u, kao i dodatnih funkcionalnosti. Aplikacija će
omogućiti osnovne operacije nad bazom, kao što su dodavanje novih podataka, prikaz
postojećih, ažuriranje i brisanje informacija,


## 2. Scenario

Scenario glasi:

„Angažovani ste od strane uprave željeznice da napravite bazu njihovog poslovanja. Njihov
zahtjev je da se u ovoj bazi vodi evidencija o vozovima, redovima vožnje, zaposlenima i
prodatim kartama. Svaki zaposleni se jedinstveno identifikuje preko svog matičnog broja, a
osim toga treba čuvati informaciju o njegovom imenu, prezimenu, datumu zapošljavanja i
ukupnom radnom stažu. Za vozače je poznato koliko sati vožnje su imali do sada. Vozači
imaju godišnji medicinski pregled. Datume i nalaz (tekst do 250 znakova) tih pregleda treba
čuvati u bazi. Svaki voz ima svoju jedinstvenu oznaku, dozvoljen broj vagona, godište
proizvodnje. Postoji više tipova vozila, npr. teretni vozovi, putnički itd. Svaki voz pripada
tačno jednom tipu. Za svakog vozača se vodi evidencija o tipovima vozila koje smije voziti.
Na osnovu reda vožnje se vrši prodaja karata. Red vožnje se kreira povremeno i potrebno je
čuvati informaciju o njegovom datumu kreiranja, kao i zaposlenom koji ga je kreirao. Red
vožnje sadrži informacije o datumu i vremenu polaska voza, vozaču koji vozi odgovarajući
voz, kao i informaciju o broju običnih I VIP putničkih mjesta na toj vožnji, cijeni karte i cijeni
karte za VIP mjesta. Putnici na osnovu reda vožnje kupuju karte. Prilikom prodaje karata se
bilježi informacija, o datumu prodaje karte, broju karte, imenu i prezimenu osobe na koju
glasi karta, kao i cijeni, u zavisnosti da li je putnik uzeo VIP ili obično mjesto.“


## 3. ER model

```
Slika 1. ER dijagram
```
Kao prvi korak, na osnovu zadatog scenariija, napravljen je ER dijagram koji se vidi na slici

1. Osim informacija iz scenarija, entitetu red voznje dodat je atribut destinacija smatrajući
da je ta informacija relevantna.


## 4. Relacioni model

Dalje, na osnovu ER dijagrama, izrađen je relacioni model pri cemu je zbog normalizacije
dodata tabela cjenovnik kako se cijena ne bi ponavljala.

zaposleni(id, jmbg, ime, prezime, radni_staz, datum_zaposlenja)

vozac(id, sati_voznje, zaposleni_id) //zaposleni_id NOT NULL

medic_pregled(id, datum_pregleda, nalaz, vozac_id) // vozac_id NOT NULL,

tip_voza(id, naziv_tipa)

vozac_tip_voza(vozac_id, tip_voza_id) // tip_voza_id, vozac_id NOT NULL

voz(id, oznaka, broj_vagona, god_proizvodnje, tip_voza_id) // tip_voza_id NOT NULL

red_voznje(id,datum_kreiranja,destinacija,datum_vrijeme_polaska,br_mjesta,
br_vip_mjesta, zaposleni_id, vozac_id, voz_id) //zaposleni_id, vozac_id, voz_id NOT NULL

cjenovnik(id, cijena, vip_cijena, red_voznje_id) //red_voznje_id NOT NULL

karta(id, br_karte, datum_prodaje, ime, prezime, tip_mjesta, red_voznje_id) //red_voznje_id
NOT NULL

NAPOMENA tip_mjesta moguce vrijednosti su VIP i Obicno (na osnovu tipa mjesta cemo
pronalaziti cijenu)


## 5. Grafikon

Na osnovu do sada odrađenog dobili smo grafikon iz MySQL Workbench-a

```
Slika 2. Grafikon
```

## 6. Opis aplikacije

Zadatak vezan za Java aplikaciju odnosio se na kreiranje aplikacije koja će omogućiti
pristupanje, modifikovanje i brisanje podataka. Za ove potrebe, korišćen je JDBC paket za
konektovanje kreirane baze podataka iz MySQL-a sa Java aplikacijom. Ovo se postiže
kreiranjem CRUD funkcija.
Java aplikacijom omoguceno je:

- Upravljanje radnicima
- Upravljanje vozovima
- Upravljanje redom voznje
- Upravljanje kartama
- Prikaz statistika poslovanja(zarada, broj prodatih karata svakog mjeseca itd...)


Pod upravljanjem se misli na CRUD funkcije

Klasa DbConnector sadrži konstruktor za konekciju, kao i metode open() i close() koje, po
potrebi, otvaraju i zatvaraju konekciju sa bazom podataka.

Klase Menus sadrže korisniku prilagođen prikaz pozivanja osnovnih CRUD metoda za
svaku od navedenih, omogućavajući jednostavnu interakciju preko tastature.

Za početak korisnik će izabrati sa kojom od tabela želi da radi. Meni je prikazan na slici.
Biranjem tabele sa kojom se radi otvara naredni meni koji nudi sve opcije za rad sa tom
tabelom.


## 7. Zaključak

Projekat demonstrira razumijevanje scenarija, modelovanje ER i relacionog dijagrama, te
povezivanje baze sa Java aplikacijom koja omogućava jednostavno upravljanje podacima i
praćenje statistike poslovanja koja će omogućiti poslodavcu dalje planiranje i razvoj
poslovanja.


