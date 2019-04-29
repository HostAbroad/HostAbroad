package com.business.transfers;
import java.util.TreeSet;

import com.business.enums.KnowledgesEnum;

public class THost {
	
	private TreeSet<KnowledgesEnum> knowledges;
	private String nickname;
	
	public THost() {}
	
	public THost(String nickname, TreeSet<KnowledgesEnum> knowledges) {
		this.nickname = nickname;
		this.knowledges = knowledges;
	}
	
	public void setListOfKnowledges(TreeSet<KnowledgesEnum> knowledges) {
		this.knowledges = knowledges;
	}
	
	public TreeSet<KnowledgesEnum> getListOfKnowledges() {
		return this.knowledges;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}