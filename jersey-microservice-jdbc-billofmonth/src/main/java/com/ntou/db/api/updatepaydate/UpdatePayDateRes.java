package com.ntou.db.api.updatepaydate;

import com.ntou.db.billofmonth.BillofmonthVO;
import com.ntou.spec.SvcRes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class UpdatePayDateRes extends SvcRes {
    private ArrayList<BillofmonthVO> result;
}
