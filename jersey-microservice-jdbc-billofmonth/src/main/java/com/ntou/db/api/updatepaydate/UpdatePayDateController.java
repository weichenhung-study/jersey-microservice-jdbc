package com.ntou.db.api.updatepaydate;

import com.ntou.db.billofmonth.BillofmonthDAO;
import com.ntou.db.billofmonth.BillofmonthImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("UpdatePayDate")
public class UpdatePayDateController {
    private final UpdatePayDate insertBill;

    public UpdatePayDateController() {
        this.insertBill = new UpdatePayDate(
                new BillofmonthImpl(new BillofmonthDAO()));
    }
    public UpdatePayDateController(UpdatePayDate activation) {
        this.insertBill = activation;
    }
    @PUT
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(UpdatePayDateReq req) throws Exception {
        return insertBill.doAPI(req);
    }
}
