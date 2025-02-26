package com.ntou.db.api.findcusbill;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.billrecord.BillrecordImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("FindCusBill")
public class FindCusBillController {
    private final FindCusBill findCusBill;

    public FindCusBillController() {
        this.findCusBill = new FindCusBill(
                new BillrecordImpl(new BillrecordDAO()));
    }
    public FindCusBillController(FindCusBill activation) {
        this.findCusBill = activation;
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(
//            @QueryParam("cid") String cid,
//            @QueryParam("cardType") String cardType,
//            @QueryParam("payDate") String payDate
    ) throws Exception {
        FindCusBillReq req = new FindCusBillReq();
//        req.setCid(cid);
//        req.setCardType(cardType);
//        req.setPayDate(payDate);
        return findCusBill.doAPI(req);
    }
}
