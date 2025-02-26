package com.ntou.db.api.updatepaydate;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UpdatePayDateRC {
    SUCCESS("UpdatePayDate00" , "成功")
    , VALIDATION_ERROR(" UpdatePayDate01" , "驗證有誤")
    , FAIL("UpdatePayDate02" , "更新失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
