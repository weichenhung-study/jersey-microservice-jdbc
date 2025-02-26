package com.ntou.db.api.insertbill;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InsertBillRC {
    SUCCESS("InsertBill00" , "成功")
    , VALIDATION_ERROR(" InsertBill01" , "驗證有誤")
    , FAIL("InsertBill02" , "新增失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
