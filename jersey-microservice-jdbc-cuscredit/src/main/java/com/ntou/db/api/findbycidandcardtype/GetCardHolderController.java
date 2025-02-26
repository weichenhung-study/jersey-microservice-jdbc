package com.ntou.db.api.findbycidandcardtype;

import com.ntou.db.cuscredit.CuscreditDAO;
import com.ntou.db.cuscredit.CuscreditImpl;
import com.ntou.tool.Common;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("GetCardHolder")
public class GetCardHolderController {
    private final GetCardHolder getCardHolder;

    public GetCardHolderController() {
        this.getCardHolder = new GetCardHolder(
                new CuscreditImpl(new CuscreditDAO()));
    }
    public GetCardHolderController(GetCardHolder activation) {
        this.getCardHolder = activation;
    }
    @GET
    @Consumes(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    @Produces(MediaType.APPLICATION_JSON + Common.CHARSET_UTF8)
    public Response doController(
            @QueryParam("cid") String cid,
            @QueryParam("cardType") String cardType) throws Exception {
        GetCardHolderReq req = new GetCardHolderReq();
        req.setCid(cid);
        req.setCardType(cardType);
        return getCardHolder.doAPI(req);
    }
}