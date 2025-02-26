package com.ntou.db.api.savecuscredit;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CreateCuscreditRC {
    SUCCESS("CreateCuscredit00" , "成功")
    , VALIDATION_ERROR("CreateCuscredit01" , "驗證有誤")
    , INSERT_FAIL("CreateCuscredit02" , "新增失敗")
    , DUPLICATE_APPLICATION("CreateCuscredit03" , "重複申請信用卡")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
