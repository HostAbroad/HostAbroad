package com.business.enums;

public enum CountriesEnum {

	Albania("Albania"),
	Andorra("Andorra"),
	Armenia("Armenia"),
	Austria("Austria"),
	Azerbaijan("Azerbaijan"),
	Belarus("Belarus"),
	Belgium("Belgium"),
	BosniaAndHerzegovina("Bosnia and Herzegovina"),
	Bulgaria("Bulgaria"),
	Croatia("Croatia"),
	Cyprus("Cyprus"),
	Czechia("Czechia"),
	Denmark("Denmark"),
	Estonia("Estonia"),
	Finland("Finland"),
	France("France"),
	Georgia("Georgia"),
	Germany("Germany"),
	Greece("Greece"),
	Hungary("Hungary"),
	Iceland("Iceland"),
	Ireland("Ireland"),
	Italy("Italy"),
	Kazakhstan("Kazakhstan"),
	Kosovo("Kosovo"),
	Latvia("Latvia"),
	Liechtenstein("Liechtenstein"),
	Lithuania("Lithuania"),
	Luxembourg("Luxembourg"),
	Malta("Malta"),
	Moldova("Moldova"),
	Monaco("Monaco"),
	Montenegro("Montenegro"),
	Netherlands("Netherlands"),
	Norway("Norway"),
	Poland("Poland"),
	Portugal("Portugal"),
	Macedonia("Republic of North Macedonia"),
	Romania("Romania"),
	Russia("Russia"),
	SanMarino("San Marino"),
	Serbia("Serbia"),
	Slovakia("Slovakia"),
	Slovenia("Slovenia"),
	Spain("Spain"),
	Sweden("Sweden"),
	Switzerland("Switzerland"),
	Turkey("Turkey"),
	Ukraine("Ukraine"),
	UnitedKingdom("United Kingdom"),
	VaticanCity("Vatican City");
	
	private String name;
	
	private CountriesEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
}