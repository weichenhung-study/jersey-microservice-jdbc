package com.ntou.db.api.savecuscredit;

import com.ntou.db.cuscredit.CuscreditVO;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCuscreditRes extends SvcRes {
    private CuscreditVO result;
}