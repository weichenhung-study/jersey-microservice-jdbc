package com.ntou.db.api.findcusbill;

import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.tool.Common;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static com.ntou.db.api.findcusbill.FindCusBillRC.*;

/** 查詢該月帳單的所有客戶資料 */
@Log4j2
public class FindCusBill {
    private final BillrecordSvc billrecordSvc;
    public FindCusBill(BillrecordSvc billrecordSvc) {
        this.billrecordSvc = billrecordSvc;
    }
    public Response doAPI(FindCusBillReq req) throws Exception {
        log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        FindCusBillRes res = new FindCusBillRes();

//        if(!req.checkReq())
//            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        ArrayList<BillrecordVO> billList = billrecordSvc
                .selectCusBill(voBillrecordSelect(), DateTool.getFirstDayOfMonth(), DateTool.getLastDayOfMonth());
        if(billList.isEmpty())
            ResTool.commonThrow(res, NODATA.getCode(), NODATA.getContent());

        res.setResult(billList);

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillrecordVO voBillrecordSelect(){
        BillrecordVO vo = new BillrecordVO();
        vo.setDisputedFlag      ("00");
        return vo;
    }
}
