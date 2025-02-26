package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("CreateCuscredit")
public class CreateCuscreditController {
    private final CreateCuscredit createCuscredit;

    public CreateCuscreditController() {
        this.createCuscredit = new CreateCuscredit(
                new CuscreditImpl(new CuscreditDAO()));
    }
    public CreateCuscreditController(CreateCuscredit activation) {
        this.createCuscredit = activation;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(CreateCuscreditReq req) throws Exception {
        return createCuscredit.doAPI(req);
    }
}