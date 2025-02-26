package com.ntou.db.api.updateactivationrecord;

import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

import static com.ntou.db.api.updateactivationrecord.UpdateActivationRecordRC.*;

/** 信用卡開卡 更新信用卡狀態 */
@Log4j2
public class UpdateActivationRecord {
    private final CuscreditSvc cuscreditSvc;
    public UpdateActivationRecord(CuscreditSvc cuscreditSvc) {
        this.cuscreditSvc = cuscreditSvc;
    }
    public Response doAPI(UpdateActivationRecordReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdateActivationRecordRes res = new UpdateActivationRecordRes();

        if (!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), UpdateActivationRecordRC.VALIDATION_ERROR.getContent(), req.getErrMsg());

        int updateResult = cuscreditSvc.updateActivationRecord(voCuscreditUpdate(req));
        if (updateResult != 1)
            ResTool.setRes(res, UPDATE_FAIL.getCode(), UPDATE_FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
    private CuscreditVO voCuscreditUpdate(UpdateActivationRecordReq req){
        CuscreditVO vo = new CuscreditVO();
        vo.setCid  		(req.getCid  	 ());
        vo.setCardType  (req.getCardType ());
        vo.setActivationRecord(CuscreditVO.ActivationRecord.OPEN.getValue());
        return vo;
    }
}
