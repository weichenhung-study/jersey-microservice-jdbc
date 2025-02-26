package com.ntou.db.api.updatedisputedflag;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.billrecord.BillrecordImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("UpdateDisputedFlag")
public class UpdateDisputedFlagController {
    private final UpdateDisputedFlag insertCusDateBill;

    public UpdateDisputedFlagController() {
        this.insertCusDateBill = new UpdateDisputedFlag(
                new BillrecordImpl(new BillrecordDAO()));
    }
    public UpdateDisputedFlagController(UpdateDisputedFlag activation) {
        this.insertCusDateBill = activation;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(UpdateDisputedFlagReq req) throws Exception {
        return insertCusDateBill.doAPI(req);
    }
}
