package com.ntou.db.api.insertcusdatebill;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum InsertCusDateBillRC {
    SUCCESS("InsertCusDateBill00" , "成功")
    , VALIDATION_ERROR(" InsertCusDateBill01" , "驗證有誤")
    , FAIL("InsertCusDateBill02" , "新增失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
