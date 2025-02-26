package com.ntou.db.api.findcusbillall;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FindCusBillAllRC {
    SUCCESS("FindCusBillAll00" , "成功")
    , VALIDATION_ERROR(" FindCusBillAll01" , "驗證有誤")
    , NODATA("FindCusBillAll02" , "查無資料")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
