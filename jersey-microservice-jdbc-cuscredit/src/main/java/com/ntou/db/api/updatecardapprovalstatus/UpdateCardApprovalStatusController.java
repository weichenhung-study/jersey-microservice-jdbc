package com.ntou.db.api.updatecardapprovalstatus;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditImpl;
import com.ntou.tool.Common;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("UpdateCardApprovalStatus")
public class UpdateCardApprovalStatusController {
    private final UpdateCardApprovalStatus createCuscredit;

    public UpdateCardApprovalStatusController() {
        this.createCuscredit = new UpdateCardApprovalStatus(
                new CuscreditImpl(new CuscreditDAO()));
    }
    public UpdateCardApprovalStatusController(UpdateCardApprovalStatus activation) {
        this.createCuscredit = activation;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(UpdateCardApprovalStatusReq req) throws Exception {
        return createCuscredit.doAPI(req);
    }
}