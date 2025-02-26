package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

/** 查詢有效卡片持有者資料 */
@Log4j2
public class GetActivatedCardHolder {
    private final CuscreditSvc cuscreditSvc;
    public GetActivatedCardHolder(CuscreditSvc cuscreditSvc) {
        this.cuscreditSvc = cuscreditSvc;
    }
    public Response doAPI(GetActivatedCardHolderReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        GetActivatedCardHolderRes res = new GetActivatedCardHolderRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, GetActivatedCardHolderRC.VALIDATION_ERROR.getCode(), GetActivatedCardHolderRC.VALIDATION_ERROR.getContent(), req.getErrMsg());

        CuscreditVO voCuscredit = cuscreditSvc.selectCardHolderActivated(
                req.getCid(), req.getCardType(), req.getCardNum(), req.getSecurityCode());
        if(voCuscredit == null)
            ResTool.commonThrow(res, GetActivatedCardHolderRC.NODATA.getCode(), GetActivatedCardHolderRC.NODATA.getContent());

        res.setResult(voCuscredit);

        ResTool.setRes(res, GetActivatedCardHolderRC.SUCCESS.getCode(), GetActivatedCardHolderRC.SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
}
