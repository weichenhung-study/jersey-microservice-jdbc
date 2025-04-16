package com.ntou.db.api.insertcusdatebill;

import com.ntou.db.billrecord.BillrecordSvc;
import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;
import java.util.UUID;

import static com.ntou.db.api.insertcusdatebill.InsertCusDateBillRC.*;

/** 客戶使用信用卡消費計入消費紀錄 */
@Log4j2
public class InsertCusDateBill {
    private final BillrecordSvc billrecordSvc;
    public InsertCusDateBill(BillrecordSvc billrecordSvc) {
        this.billrecordSvc = billrecordSvc;
    }
    public Response doAPI(InsertCusDateBillReq req) throws Exception {
        ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        InsertCusDateBillRes res = new InsertCusDateBillRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        int insertResult = billrecordSvc.insertCusDateBill(voBillrecordInsert(req));
        ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());

		if(insertResult !=1)
            ResTool.commonThrow(res, FAIL.getCode(), FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return Response.status(Response.Status.OK).entity(res).build();
    }
    private BillrecordVO voBillrecordInsert(InsertCusDateBillReq req){
        BillrecordVO vo = new BillrecordVO();
        vo.setUuid				(UUID.randomUUID().toString());//交易編號UUID
        vo.setBuyChannel		(req.getBuyChannel		());//消費通路(00:實體, 01:線上)
        vo.setBuyDate			(req.getBuyDate			());//消費時間yyyy/MM/dd HH:MM:ss.SSS
        vo.setReqPaymentDate	(req.getReqPaymentDate	());//請款時間yyyy/MM/dd HH:MM:ss.SSS
        vo.setCardType			(req.getCardType		());//卡別
        vo.setShopId			(req.getShopId			());//消費店家(統編)
        vo.setCid				(req.getCid				());//消費者(身分證)
        vo.setBuyCurrency		(req.getBuyCurrency		());//消費幣別
        vo.setBuyAmount			(req.getBuyAmount		());//消費金額
        vo.setDisputedFlag		(req.getDisputedFlag	());//爭議款項註記(00:正常,01異常)
        vo.setStatus			(req.getStatus			());//狀態(00:正常,99:註銷)
        vo.setActuallyDate		(DateTool.getDateTime());//交易完成的時間yyyy/MM/dd HH:MM:ss.SSS
        vo.setRemark			(req.getRemark			());//備註
        vo.setIssuingBank		(req.getIssuingBank		());//發卡銀行(swiftCode)
        vo.setCardNum			(req.getCardNum			());//卡號
        vo.setSecurityCode		(req.getSecurityCode	());//安全碼
        return vo;
    }
}
