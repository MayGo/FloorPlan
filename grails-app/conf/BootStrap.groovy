import floorplan.*
class BootStrap {
	def fixtureLoader

	def init = { servletContext ->

		//fixtureLoader.load("data")

		List pNames=[
			"AMEX",
			"EGO järelmaks",
			"ISIC",
			"Liisi.ee järelmaks",
			"MasterCard",
			"Mobiilimakse",
			"Sularaha",
			"VISA"
		]
		List cNames=[
			"Ajalehed & ajakirjad",
			"Apteek",
			"Arvutikaubad",
			"Fototeenindus",
			"Ilusalong",
			"Iluteenused",
			"Ilutooted",
			"Jalatsid",
			"Kasiino",
			"Keemiline puhastus",
			"Kingaparandus",
			"Kingitused",
			"Kinkide pakkimine",
			"Kodukaubad",
			"Kontoritarbed",
			"Kotid",
			"Lastekaubad",
			"Lemmikloomatarbed",
			"Lilled",
			"Meesterõivad",
			"Mobiilside",
			"Muusikapood",
			"Naisterõivad",
			"Noorterõivad",
			"Pangateenused",
			"Piletite müük",
			"Postiteenused",
			"Prillipood",
			"Raamatud",
			"Reisibüroo",
			"Spordikaubad",
			"Tankla",
			"Toidukaubad",
			"Toitlustus",
			"Valuutavahetus",
			"Võtmete tegemine",
			"Videolaenutus"
		]

		List tNames=[
			"Tefal",
			"Pintinox",
			"Lagostina",
			"Saeco",
			"Moulinex",
			"Beka",
			"Churchill",
			"Luigis Bormioli",
			"RCR",
			"Galic",
			"Bormioli",
			"Spidem",
			"Krups",
			"Rowenta",
			"Clarks",
			"Billi Bi",
			"Esprit",
			"Lacoste",
			"Ecco",
			"Rylko",
			"Hispanitas",
			"El Naturalista",
			"Ten Points",
			"Gabor",
			"The Art",
			"Rieker",
			"Paula Mendez",
			"Tamaris",
			"You Know",
			"Caprice",
			"Unisa",
			"Högl",
			"Janita",
			"Jana",
			"Marco Tozzi",
			"Gino Ventori",
			"Vagabond",
			"Fly London",
			"Bullboxer",
			"Bronx",
			"Fornarina",
			"Pepe Castell",
			"Aaltonen",
			"Blink",
			"Barbara Bucci",
			"Puma",
			"Vision",
			"K-Swiss",
			"Fred Perry",
			"Replay",
			"Vans",
			"Tommy Hilfiger"
		]
		println "Generating test data.."
		pNames.each{
			Paytype.build(name:it)
		}
		cNames.each{
			Category.build(name:it)
		}
		tNames.each{
			Trademark.build(name:it)
		}



		def floor1=Floor.build(nr:1)
		14.times {
			def s=Shop.build()
			Room.build(nr:100+it,floor:floor1, shop:s)
			int pTimes=new Random().nextInt(pNames.size())+1
			int cTimes=new Random().nextInt(cNames.size())+1
			int tTimes=new Random().nextInt(cNames.size())+1
			Collections.shuffle(pNames)
			Collections.shuffle(cNames)
			Collections.shuffle(tNames)

			pTimes.times{
		
				s.addToPaytypes(Paytype.findByName(pNames.get(it).toLowerCase()))
			}
			cTimes.times{
				s.addToCategories(Category.findByName(cNames.get(it).toLowerCase()))
			}
			tTimes.times{
				s.addToTrademarks(Trademark.findByName(tNames.get(it).toLowerCase()))
			}
			s.save(flush:true)
		}
		println "...Generating test data complete"
	}
	def destroy = {
	}
}
