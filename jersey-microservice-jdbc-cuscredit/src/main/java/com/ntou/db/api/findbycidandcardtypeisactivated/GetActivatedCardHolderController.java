package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("GetActivatedCardHolder")
public class GetActivatedCardHolderController {
    private final GetActivatedCardHolder getActivatedCardHolder;

    public GetActivatedCardHolderController() {
        this.getActivatedCardHolder = new GetActivatedCardHolder(
                new CuscreditImpl(new CuscreditDAO()));
    }
    public GetActivatedCardHolderController(GetActivatedCardHolder activation) {
        this.getActivatedCardHolder = activation;
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(
            @QueryParam("cid") String cid,
            @QueryParam("cardType") String cardType,
            @QueryParam("cardNum") String cardNum,
            @QueryParam("securityCode") String securityCode) throws Exception {
        GetActivatedCardHolderReq req = new GetActivatedCardHolderReq();
        req.setCid(cid);
        req.setCardType(cardType);
        req.setCardNum(cardNum);
        req.setSecurityCode(securityCode);
        return getActivatedCardHolder.doAPI(req);
    }
}