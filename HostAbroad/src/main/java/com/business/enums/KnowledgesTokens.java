package com.business.enums;

import java.util.ArrayList;
import java.util.List;

import com.fo0.advancedtokenfield.model.Token;

public class KnowledgesTokens {

	List<Token> tokens;
	
	public KnowledgesTokens() {
		tokens = new ArrayList<>();
		tokens.add(new Token("Programming"));
		tokens.add(new Token("Physics"));
		tokens.add(new Token("History"));
		tokens.add(new Token("Arts"));
		tokens.add(new Token("Music"));
		tokens.add(new Token("Literature"));
		tokens.add(new Token("Biology"));
		tokens.add(new Token("Chemistry"));
		tokens.add(new Token("Cooking"));
		tokens.add(new Token("Sports"));
		tokens.add(new Token("Languages"));
	}
	
	public List<Token> getTokens() {
		return tokens;
	}

}
