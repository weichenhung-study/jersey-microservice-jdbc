package com.ntou.db.api.updatedisputedflag;

import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

import static com.ntou.db.api.updatedisputedflag.UpdateDisputedFlagRC.*;

/** 爭議款項申請:上註記  */
@Log4j2
public class UpdateDisputedFlag {
    private final BillrecordSvc billrecordSvc;
    public UpdateDisputedFlag(BillrecordSvc billrecordSvc) {
        this.billrecordSvc = billrecordSvc;
    }
    public Response doAPI(UpdateDisputedFlagReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdateDisputedFlagRes res = new UpdateDisputedFlagRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        int updateResult = billrecordSvc
                .updateDisputedFlag(voBillrecordSelect(req));
        if(updateResult !=1)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillrecordVO voBillrecordSelect(UpdateDisputedFlagReq req){
        BillrecordVO vo = new BillrecordVO();
        vo.setUuid		(req.getUuid());
        return vo;
    }
}
