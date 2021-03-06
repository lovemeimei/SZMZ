package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/27.
 */

public class JZ_YWBL_DCHS_RE extends BaseListRequest {

    public String familyId;//家庭ID
    public String streetCheckTime;//调查核实日期
    public String streetCheckInfo;//调查核实情况
    public String streetCheckResult;//调查核实结果
    public String streetCheckUser;//调查核实人员
    public String streetCheckChargeUser;//调查核实负责人
    public String coordinate;//地址


    public JZ_YWBL_DCHS_RE(String id, String time, String info, String result, String checkUser, String chargeUser, String address) {
        this.familyId = id;
        this.streetCheckInfo = info;
        this.streetCheckResult = result;
        this.streetCheckChargeUser = chargeUser;
        this.streetCheckTime = time;
        this.streetCheckUser = checkUser;
        this.coordinate = address;
        setMd5Key(Md5Util.getMd5(id + streetCheckTime + streetCheckInfo + streetCheckResult + streetCheckUser + streetCheckChargeUser + coordinate));
    }
}