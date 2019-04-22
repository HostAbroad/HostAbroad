package com.business.ASSearch;

import java.util.ArrayList;

import com.business.enums.SearchEnum;
import com.business.transfers.TUser;

public interface ASSearch {
	public abstract ArrayList<TUser> search(ArrayList<SearchEnum> searchEnums);
}
