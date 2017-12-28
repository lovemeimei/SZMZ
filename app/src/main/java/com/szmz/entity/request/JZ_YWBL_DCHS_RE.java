package com.szmz.entity.request;

import com.szmz.utils.Md5Util;

/**
 * Created by bz on 2017/11/27.
 */

public class JZ_YWBL_DCHS_RE extends BaseListRequest {

    public String id;//家庭ID
    public String userId;//用户编号
    public String streetCheckTime;//调查核实日期
    public String streetCheckInfo;//调查核实情况
    public String streetCheckResult;//调查核实结果
    public String streetCheckUser;//调查核实人员
    public String streetCheckChargeUser;//调查核实负责人
    public String coordinate;//地址


    public JZ_YWBL_DCHS_RE(String id, String userID, String time, String info, String result, String checkUser, String chargeUser, String address) {
        this.id = id;
        this.userId = userID;
        this.streetCheckInfo = info;
        this.streetCheckResult = result;
        this.streetCheckChargeUser = chargeUser;
        this.streetCheckTime = time;
        this.streetCheckUser = checkUser;
        this.coordinate = address;
        setMd5Key(Md5Util.getMd5(userID + id + streetCheckTime + streetCheckInfo + streetCheckResult + streetCheckUser + streetCheckChargeUser + coordinate));
    }
}