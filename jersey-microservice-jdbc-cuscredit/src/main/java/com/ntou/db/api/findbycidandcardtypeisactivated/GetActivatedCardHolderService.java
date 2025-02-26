package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditVO;

public class GetActivatedCardHolderService {
    private final CuscreditSvc cuscreditSvc;

    public GetActivatedCardHolderService(CuscreditSvc cuscreditSvc) {
        this.cuscreditSvc = cuscreditSvc;
    }

    public CuscreditVO getActivatedCardHolder(String cid, String cardType, String cardNum, String securityCode) throws Exception {
        return cuscreditSvc.selectCardHolderActivated(cid, cardType, cardNum, securityCode);
    }
}
