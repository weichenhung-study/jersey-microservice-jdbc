package com.ntou.db.api.findcusbill;

import com.ntou.db.billrecord.BillrecordVO;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class FindCusBillRes extends SvcRes {
    private ArrayList<BillrecordVO> result;
}
