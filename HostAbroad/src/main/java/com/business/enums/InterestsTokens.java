package com.business.enums;

import java.util.ArrayList;
import java.util.List;

import com.fo0.advancedtokenfield.model.Token;

public class InterestsTokens {

	List<Token> tokens;

	public InterestsTokens() {
		tokens = new ArrayList<>();
		tokens.add(new Token("Movies"));
		tokens.add(new Token("Reading"));
		tokens.add(new Token("Sports"));
		tokens.add(new Token("Traveling"));
		tokens.add(new Token("Gaming"));
		tokens.add(new Token("Fashion"));
		tokens.add(new Token("Makeup"));
		tokens.add(new Token("Photography"));
		tokens.add(new Token("Music"));
		tokens.add(new Token("Gastronomy"));
		tokens.add(new Token("Partying"));
		tokens.add(new Token("Animals"));
		tokens.add(new Token("Nature"));
		tokens.add(new Token("Arts"));
		tokens.add(new Token("Cars"));
	}

	public List<Token> getTokens() {
		return tokens;
	}

}
