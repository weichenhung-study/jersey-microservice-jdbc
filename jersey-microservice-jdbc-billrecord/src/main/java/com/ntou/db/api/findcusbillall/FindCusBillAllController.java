package com.ntou.db.api.findcusbillall;

import com.ntou.db.billrecord.BillrecordDAO;
import com.ntou.db.billrecord.BillrecordImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("FindCusBillAll")
public class FindCusBillAllController {
    private final FindCusBillAll findCusBill;

    public FindCusBillAllController() {
        this.findCusBill = new FindCusBillAll(
                new BillrecordImpl(new BillrecordDAO()));
    }
    public FindCusBillAllController(FindCusBillAll activation) {
        this.findCusBill = activation;
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(
            @QueryParam("cid") String cid,
            @QueryParam("cardType") String cardType,
            @QueryParam("startDate") String startDate,
            @QueryParam("endDate") String endDate
    ) throws Exception {
        FindCusBillAllReq req = new FindCusBillAllReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setStartDate(startDate);
        req.setEndDate(endDate);
        return findCusBill.doAPI(req);
    }
}
