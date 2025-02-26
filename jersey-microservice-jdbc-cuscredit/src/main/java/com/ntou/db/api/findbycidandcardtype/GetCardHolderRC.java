package com.ntou.db.api.findbycidandcardtype;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum GetCardHolderRC {
    SUCCESS("GetCardHolder00" , "成功")
    , VALIDATION_ERROR(" GetCardHolder01" , "驗證有誤")
    , NODATA("GetCardHolder02" , "查無資料")

    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
