package com.business.enums;

import com.business.businessObjects.Language;

public enum LanguagesEnum{

	Albanian("Albanian"),
	Arabic("Arabic"),
	Basque("Basque"),
	Bosnian("Bosnian"),
	Bulgarian("Bulgarian"),
	Catalan("Catalan"),
	Chinese("Chinese"),
	Croatian("Croatian"),
	Czech("Czech"),
	Danish("Danish"),
	Dutch("Dutch"),
	English("English"),
	Estonian("Estonian"),
	Faroese("Faroese"),
	Finnish("Finnish"),
	French("French"),
	Frisian("Frisian"),
	Galician("Galician"),
	German("German"),
	Greek("Greek"),
	GreenlandicInuktitut("Greenlandic Inuktitut"),
	Hindi("Hindi"),
	Hungarian("Hungarian"),
	Icelandic("Icelandic"),
	Irish("Irish"),
	Italian("Italian"),
	Japanese("Japanese"),
	Latin("Latin"),
	Latvian("Latvian"),
	Lithuanian("Lithuanian"),
	Luxembourgish("Luxembourgish"),
	Macedonian("Macedonian"),
	Maltese("Maltese"),
	Moldovan("Moldovan"),
	Norwegian("Norwegian"),
	Polish("Polish"),
	Portuguese("Portuguese"),
	Romanian("Romanian"),
	Romansch("Romansch"),
	Russian("Russian"),
	Serbian("Serbian"),
	SerboCroatian("Serbo-Croatian"),
	Slovak("Slovak"),
	Slovene("Slovene"),
	Slovenian("Slovenian"),
	Spanish("Spanish"),
	Swedish("Swedish"),
	Turkish("Turkish"),
	Ukrainian("Ukrainian");
	
	private String name;
	
	private LanguagesEnum(String name) {
		this.name = name;
	}
	
	public String getString() {
		return this.name;
	}
	
	public LanguagesEnum setToEnum(String name) {
		for(LanguagesEnum language : LanguagesEnum.values()) {
			if(language.getString().equals(name))
				return language;
		}
		return null;
	}
}
