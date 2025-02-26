package com.ntou.db.api.updateactivationrecord;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("UpdateActivationRecord")
public class UpdateActivationRecordController {
    private final UpdateActivationRecord createCuscredit;

    public UpdateActivationRecordController() {
        this.createCuscredit = new UpdateActivationRecord(
                new CuscreditImpl(new CuscreditDAO()));
    }
    public UpdateActivationRecordController(UpdateActivationRecord activation) {
        this.createCuscredit = activation;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(UpdateActivationRecordReq req) throws Exception {
        return createCuscredit.doAPI(req);
    }
}