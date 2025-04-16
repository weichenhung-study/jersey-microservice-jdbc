package com.ntou.db.api.insertbill;

import com.ntou.db.billofmonth.BillofmonthSvc;
import com.ntou.db.billofmonth.BillofmonthVO;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static com.ntou.db.api.insertbill.InsertBillRC.*;


/** 產生各消費者的月結帳單 */
@Log4j2
public class InsertBill {
    private final BillofmonthSvc billofmonthSvc;
    public InsertBill(BillofmonthSvc billofmonthSvc) {
        this.billofmonthSvc = billofmonthSvc;
    }
    public Response doAPI(InsertBillReq req) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
		
		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        InsertBillRes res = new InsertBillRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        int bInsertCusDateBill = billofmonthSvc.insertBill(setBillofmonthVO(req));
        
		if(bInsertCusDateBill !=1)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());
        ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillofmonthVO setBillofmonthVO(InsertBillReq req){
        BillofmonthVO vo = new BillofmonthVO();
        vo.setUuid	            (UUID.randomUUID().toString());//VARCHAR(36)	交易編號UUID
        vo.setCid	            (req.getCid());//VARCHAR(10)	消費者(身分證)
        vo.setCardType	        (req.getCardType());//VARCHAR(5)	卡別
        vo.setWriteDate	        (DateTool.getDateTime		());//VARCHAR(23)	寫入時間yyyy/MM/dd HH:MM:ss.SSS
        vo.setBillData	        (req.getBillList());//VARCHAR(255)	當月所有帳單資訊
        vo.setBillMonth         (req.getYyyymm());
        vo.setAmt	            (req.getAmt());//VARCHAR(255)	帳單金額
        vo.setPaidAmount	    ("");//VARCHAR(255)	當月繳款金額
        vo.setNotPaidAmount	    ("");//VARCHAR(255)	剩餘未繳金額
        vo.setCycleRate	        ("");//VARCHAR(100)	循環利率
        vo.setCycleAmt	        ("");//VARCHAR(255)	循環金額
        vo.setSpaceCycleRate	("");//VARCHAR(100)	討論循環利率
        vo.setSpaceAmt	        ("");//VARCHAR(255)	討論金額
        vo.setPayDate("");
        return vo;
    }
}
