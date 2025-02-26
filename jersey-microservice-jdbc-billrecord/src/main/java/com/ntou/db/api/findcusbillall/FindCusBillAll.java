package com.ntou.db.api.findcusbillall;

import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.tool.Common;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static com.ntou.db.api.findcusbillall.FindCusBillAllRC.*;

/** 查詢該月帳單的"特定"客戶資料 */
@Log4j2
public class FindCusBillAll {
    private final BillrecordSvc billrecordSvc;
    public FindCusBillAll(BillrecordSvc billrecordSvc) {
        this.billrecordSvc = billrecordSvc;
    }
    public Response doAPI(FindCusBillAllReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        FindCusBillAllRes res = new FindCusBillAllRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        ArrayList<BillrecordVO> billList = billrecordSvc
                .selectCusBillAll(voBillrecordSelect(req), req.getStartDate(), req.getEndDate());
        if(billList.isEmpty())
            ResTool.commonThrow(res, NODATA.getCode(), NODATA.getContent());

        res.setResult(billList);

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillrecordVO voBillrecordSelect(FindCusBillAllReq req){
        BillrecordVO vo = new BillrecordVO();
        vo.setCid		(req.getCid());
        vo.setCardType	(req.getCardType());
        return vo;
    }
}
