package com.ntou.db.api.findbycidandcardtype;

import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCardHolderRes extends SvcRes {
    private CuscreditVO result;
}