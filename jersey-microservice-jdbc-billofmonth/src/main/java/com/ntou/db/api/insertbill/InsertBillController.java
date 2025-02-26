package com.ntou.db.api.insertbill;

import com.ntou.db.billofmonth.BillofmonthDAO;
import com.ntou.db.billofmonth.BillofmonthImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("InsertBill")
public class InsertBillController {
    private final InsertBill insertBill;

    public InsertBillController() {
        this.insertBill = new InsertBill(
                new BillofmonthImpl(new BillofmonthDAO()));
    }
    public InsertBillController(InsertBill activation) {
        this.insertBill = activation;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(InsertBillReq req) throws Exception {
        return insertBill.doAPI(req);
    }
}
