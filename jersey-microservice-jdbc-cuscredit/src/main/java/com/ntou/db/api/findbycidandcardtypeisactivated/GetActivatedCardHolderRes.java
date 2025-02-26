package com.ntou.db.api.findbycidandcardtypeisactivated;

import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetActivatedCardHolderRes extends SvcRes {
    private CuscreditVO result;
}