package com.business.enums;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.fo0.advancedtokenfield.model.Token;

public class CountriesTokens {
	
List<Token> tokens;
	
	public CountriesTokens() {
		tokens = new ArrayList<>();
		tokens.add(new Token("Albania"));
		tokens.add(new Token("Andorra"));
		tokens.add(new Token("Armenia"));
		tokens.add(new Token("Austria"));
		tokens.add(new Token("Azerbaijan"));
		tokens.add(new Token("Belarus"));
		tokens.add(new Token("Belgium"));
		tokens.add(new Token("BosniaAndHerzegovina"));
		tokens.add(new Token("Bulgaria"));
		tokens.add(new Token("Croatia"));
		tokens.add(new Token("Cyprus"));
		tokens.add(new Token("Czechia"));
		tokens.add(new Token("Denmark"));
		tokens.add(new Token("Estonia"));
		tokens.add(new Token("Finland"));
		tokens.add(new Token("France"));
		tokens.add(new Token("Georgia"));
		tokens.add(new Token("Germany"));
		tokens.add(new Token("Greece"));
		tokens.add(new Token("Hungary"));
		tokens.add(new Token("Iceland"));
		tokens.add(new Token("Ireland"));
		tokens.add(new Token("Italy"));
		tokens.add(new Token("Kazakhstan"));
		tokens.add(new Token("Kosovo"));
		tokens.add(new Token("Lativa"));
		tokens.add(new Token("Lietchenstein"));
		tokens.add(new Token("Lithuania"));
		tokens.add(new Token("Luxembourg"));
		tokens.add(new Token("Macedonia"));
		tokens.add(new Token("Malta"));
		tokens.add(new Token("Moldova"));
		tokens.add(new Token("Monaco"));
		tokens.add(new Token("Montenegro"));
		tokens.add(new Token("Netherlands"));
		tokens.add(new Token("Norway"));
		tokens.add(new Token("Poland"));
		tokens.add(new Token("Portugal"));
		tokens.add(new Token("Romania"));
		tokens.add(new Token("Russia"));
		tokens.add(new Token("SanMarino"));
		tokens.add(new Token("Serbia"));
		tokens.add(new Token("Slovakia"));
		tokens.add(new Token("Slovenia"));
		tokens.add(new Token("Spain"));
		tokens.add(new Token("Sweeden"));
		tokens.add(new Token("Switzerland"));
		tokens.add(new Token("Turkey"));
		tokens.add(new Token("Ukraine"));
		tokens.add(new Token("UnitedKingdom"));
		tokens.add(new Token("VaticanCity"));
		
	}
	
	public List<Token> getTokens() {
		return tokens;
	}
	
}
