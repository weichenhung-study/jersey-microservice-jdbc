package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.CuscreditSvc;
import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.tool.Common;
import com.ntou.tool.ExecutionTimer;
import com.ntou.tool.DateTool;
import com.ntou.tool.ResTool;
import lombok.extern.log4j.Log4j2;

import javax.ws.rs.core.Response;

import static com.ntou.db.api.savecuscredit.CreateCuscreditRC.*;

/** 新增客戶信用卡資訊 */
@Log4j2
public class CreateCuscredit {
    private final CuscreditSvc cuscreditSvc;
    public CreateCuscredit(CuscreditSvc cuscreditSvc) {
        this.cuscreditSvc = cuscreditSvc;
    }
    public Response doAPI(CreateCuscreditReq req) throws Exception {
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());

		log.info(Common.API_DIVIDER + Common.START_B + Common.API_DIVIDER);
        log.info(Common.REQ + req);
        CreateCuscreditRes res = new CreateCuscreditRes();

        if(!req.checkReq())
            ResTool.regularThrow(res, VALIDATION_ERROR.getCode(), CreateCuscreditRC.VALIDATION_ERROR.getContent(), req.getErrMsg());
		
		ExecutionTimer.startStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
        CuscreditVO cusDateBill = cuscreditSvc.selectKey(
            req.getCid(), req.getCardType());
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.DATABASE.getValue());
			
        if(cusDateBill!=null)
            ResTool.commonThrow(res, DUPLICATE_APPLICATION.getCode(), DUPLICATE_APPLICATION.getContent());

        int bInsertCusDateBill = cuscreditSvc.insert(voCuscreditInsert(req));
        if(bInsertCusDateBill !=1)
            ResTool.commonThrow(res, INSERT_FAIL.getCode(), INSERT_FAIL.getContent());

        ResTool.setRes(res, SUCCESS.getCode(), SUCCESS.getContent());

        log.info(Common.RES + res);
        log.info(Common.API_DIVIDER + Common.END_B + Common.API_DIVIDER);
        
		ExecutionTimer.endStage(ExecutionTimer.ExecutionModule.APPLICATION.getValue());
        ExecutionTimer.exportTimings(this.getClass().getSimpleName() + "_" + DateTool.getYYYYmmDDhhMMss() + ".txt");
		return Response.status(Response.Status.OK).entity(res).build();
    }
    private CuscreditVO voCuscreditInsert(CreateCuscreditReq req){
        CuscreditVO vo = new CuscreditVO();
        vo.setChName               (req.getChName               ());
        vo.setEnName               (req.getEnName               ());
        vo.setCid  			       (req.getCid  			    ());
        vo.setCidReissueDate       (req.getCidReissueDate       ());
        vo.setCidReissueLocation   (req.getCidReissueLocation   ());
        vo.setCidReissueStatus     (req.getCidReissueStatus     ());
        vo.setBirthDate            (req.getBirthDate            ());
        vo.setMaritalStatus        (req.getMaritalStatus        ());
        vo.setEducation            (req.getEducation            ());
        vo.setCurrentResidentialAdd(req.getCurrentResidentialAdd());
        vo.setResidentialAdd       (req.getResidentialAdd       ());
        vo.setCellphone            (req.getCellphone            ());
        vo.setEmail                (req.getEmail                ());
        vo.setCompanyName          (req.getCompanyName          ());
        vo.setCompanyIndustry      (req.getCompanyIndustry      ());
        vo.setOccupation           (req.getOccupation           ());
        vo.setDepartment           (req.getDepartment           ());
        vo.setJobTitle             (req.getJobTitle             ());
        vo.setDateOfEmployment     (req.getDateOfEmployment     ());
        vo.setCompanyAddress       (req.getCompanyAddress       ());
        vo.setCompanyPhoneNum      (req.getCompanyPhoneNum      ());
        vo.setAnnualIncome         (req.getAnnualIncome         ());
        vo.setCardApprovalStatus   (CuscreditVO.CardApprovalStatus.PROCESS.getValue());
        vo.setActivationRecord     (CuscreditVO.ActivationRecord.NOTOPEN.getValue());
        vo.setEventCode            (req.getEventCode            ());
        vo.setRegidate 		       (DateTool.getDateTime());
        vo.setIssuing_bank 	       ("803");
        vo.setCardType             (req.getCardType             ());
        vo.setRemark			   (req.getRemark			    ());
        return vo;
    }
}
