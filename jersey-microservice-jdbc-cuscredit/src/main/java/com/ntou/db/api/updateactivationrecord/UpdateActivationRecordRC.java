package com.ntou.db.api.updateactivationrecord;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UpdateActivationRecordRC {
    SUCCESS("UpdateActivationRecord00" , "成功")
    , VALIDATION_ERROR("UpdateActivationRecord01" , "驗證有誤")
    , UPDATE_FAIL("UpdateActivationRecord02" , "更新失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
