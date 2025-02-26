package com.ntou.db.api.updatecardapprovalstatus;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum UpdateCardApprovalStatusRC {
    SUCCESS("UpdateCardApprovalStatus00" , "成功")
    , VALIDATION_ERROR("UpdateCardApprovalStatus01" , "驗證有誤")
    , UPDATE_FAIL("UpdateCardApprovalStatus02" , "更新失敗")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
