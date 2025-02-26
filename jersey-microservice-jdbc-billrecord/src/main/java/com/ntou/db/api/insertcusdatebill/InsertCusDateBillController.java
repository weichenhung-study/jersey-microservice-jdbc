package com.ntou.db.api.insertcusdatebill;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.billrecord.BillrecordImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("InsertCusDateBill")
public class InsertCusDateBillController {
    private final InsertCusDateBill insertCusDateBill;

    public InsertCusDateBillController() {
        this.insertCusDateBill = new InsertCusDateBill(
                new BillrecordImpl(new BillrecordDAO()));
    }
    public InsertCusDateBillController(InsertCusDateBill activation) {
        this.insertCusDateBill = activation;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(InsertCusDateBillReq req) throws Exception {
        return insertCusDateBill.doAPI(req);
    }
}
