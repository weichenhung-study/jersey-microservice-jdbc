package com.ntou.db.api.updatepaydate;

import com.ntou.db.billofmonth.BillofmonthSvc;
import com.ntou.db.billofmonth.BillofmonthVO;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

import static com.ntou.db.api.insertbill.InsertBillRC.*;


/** 繳交信用卡費 */
@Log4j2
public class UpdatePayDate {
    private final BillofmonthSvc billofmonthSvc;
    public UpdatePayDate(BillofmonthSvc billofmonthSvc) {
        this.billofmonthSvc = billofmonthSvc;
    }
    public Response doAPI(UpdatePayDateReq req) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        UpdatePayDateRes res = new UpdatePayDateRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());

        BillofmonthVO vo = setUpdatePayDate(req);
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        int updateCount = billofmonthSvc.updatePayDate(vo);
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        if(updateCount !=1)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillofmonthVO setUpdatePayDate(UpdatePayDateReq req){
        BillofmonthVO vo = new BillofmonthVO();
        vo.setCid(req.getCid());
        vo.setCardType(req.getCardType());
        vo.setPayDate(DateTool.getDateTime());
        vo.setPaidAmount(req.getPayAmt());
        vo.setBillMonth(req.getPayDate());
        return vo;
    }
}
