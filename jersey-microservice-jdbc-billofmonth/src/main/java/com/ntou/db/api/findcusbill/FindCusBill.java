package com.ntou.db.api.findcusbill;

import com.ntou.db.billofmonth.BillofmonthSvc;
import com.ntou.db.billofmonth.BillofmonthVO;
import static com.ntou.db.api.findcusbill.FindCusBillRC.*;

import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;


import javax.ws.rs.core.Response;
import java.util.ArrayList;


/** 本期信用卡帳單查詢 */
@Log4j2
public class FindCusBill {
    private final BillofmonthSvc billofmonthSvc;
    public FindCusBill(BillofmonthSvc billofmonthSvc) {
        this.billofmonthSvc = billofmonthSvc;
    }
    public Response doAPI(FindCusBillReq req) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        FindCusBillRes res = new FindCusBillRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        BillofmonthVO vo = setUpdatePayDate(req);
        ArrayList<BillofmonthVO> listBillofmonth = billofmonthSvc.findBills(vo);
        if(listBillofmonth.isEmpty())
            ResTool.commonThrow(res, NODATA.getCode(), NODATA.getContent());
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        res.setResult(listBillofmonth);

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillofmonthVO setUpdatePayDate(FindCusBillReq req){
        BillofmonthVO vo = new BillofmonthVO();
        vo.setCid(req.getCid());
        vo.setCardType(req.getCardType());
        vo.setPayDate(DateTool.getDateTime());
        vo.setBillMonth(req.getPayDate());
        return vo;
    }
}
