package com.ntou.db.api.updatedisputedflag;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UpdateDisputedFlagRC {
    SUCCESS("UpdateDisputedFlag00" , "成功")
    , VALIDATION_ERROR(" UpdateDisputedFlag01" , "驗證有誤")
    , FAIL("UpdateDisputedFlag02" , "更新失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
