package com.ntou.db.api.findbycidandcardtype;

import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

/** 查詢卡片持有者資料 */
@Log4j2
public class GetCardHolder {
    private final CuscreditSvc cuscreditSvc;
    public GetCardHolder(CuscreditSvc cuscreditSvc) {
        this.cuscreditSvc = cuscreditSvc;
    }
    public Response doAPI(GetCardHolderReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        GetCardHolderRes res = new GetCardHolderRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, GetCardHolderRC.VALIDATION_ERROR.getCode(), GetCardHolderRC.VALIDATION_ERROR.getContent(), req.getErrMsg());

        CuscreditVO voCuscredit = cuscreditSvc.selectKey(
                req.getCid(), req.getCardType());
        if(voCuscredit == null)
            ResTool.commonThrow(res, GetCardHolderRC.NODATA.getCode(), GetCardHolderRC.NODATA.getContent());

        res.setResult(voCuscredit);

        ResTool.setRes(res, GetCardHolderRC.SUCCESS.getCode(), GetCardHolderRC.SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
}
