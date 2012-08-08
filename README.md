# Opetusopas pokerille (TAO-kurssi)

## Riippuvuudet
* Java 1.6 tai uudempi
* Netbeans tai Apache Ant ohjelman kääntämiseen

## Alkuun pääseminen
Helpoin tapa hankkia ohjelma omalle koneelle onnistuu näin (vaatii `git`
ohjelman):

	git clone git://github.com/pokeriopas/PokerTutor.git

tai hakemalla [zip](https://github.com/pokeriopas/PokerTutor/zipball/master)
tiedoston viimeisimmästä versioista.

Siirry hakemistoon (esim. `cd PokerTutor`)

Ohjelman on kirjoitettu Javalla, joten se pitää kääntää aluksi. Kääntäminen
onnistuu joko avaamalla projekti Netbeansiin, tai kääntämällä ja ajamalle se
`ant` komennolla seuraavasti:

	ant run

