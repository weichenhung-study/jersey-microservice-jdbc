package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GetActivatedCardHolderRC {
    SUCCESS("GetActivatedCardHolder00" , "成功")
    , VALIDATION_ERROR("GetActivatedCardHolder01" , "驗證有誤")
    , NODATA("GetActivatedCardHolder02" , "查無資料")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
