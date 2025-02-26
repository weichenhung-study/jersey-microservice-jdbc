package com.ntou.db.api.findcusbill;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FindCusBillRC {
    SUCCESS("FindCusBill00" , "成功")
    , VALIDATION_ERROR(" FindCusBill01" , "驗證有誤")
    , NODATA("FindCusBill02" , "查無資料")
    ;
    private final String code;
    @Getter
    private final String content;

    @JsonValue
    public String getCode() {return code;}
}
