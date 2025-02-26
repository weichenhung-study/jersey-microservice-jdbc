package com.ntou.db.api.insertbill;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntou.tool.JsonTool;
import com.ntou.tool.RegexpTool;
import lombok.Data;

@Data
public class InsertBillReq {
    private String cid          = "";//VARCHAR(10)	消費者(身分證)
    private String cardType		= "";//VARCHAR(5)	卡別
    private String billList     = "";//VARCHAR(255)	當月所有帳單資訊
    private String amt          = "";//VARCHAR(255)	帳單金額
    private String yyyymm       = "";//帳單月份

    @JsonIgnore
    private String errMsg;
    public boolean checkReq(){
        if(!RegexpTool.CID.matcher(cid    ).matches()){this.errMsg = "消費者(身分證)"+ RegexpTool.C_INVALID_NUM_LEN;return false;}
        if(!RegexpTool.LENGTH_5.matcher(cardType).matches()){this.errMsg = "卡別"+ RegexpTool.C_INVALID_NUM_LEN;return false;}
        return true;
    }

    @Override
    public String toString() {return JsonTool.format2Json(this);}
}
