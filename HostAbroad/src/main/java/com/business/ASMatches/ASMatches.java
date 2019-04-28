package com.business.ASMatches;

import com.business.transfers.TMatches;

public interface ASMatches {

	abstract boolean acceptLike(TMatches tMatches);

	abstract boolean declineLike(TMatches transfer);

}
