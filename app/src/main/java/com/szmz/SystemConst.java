package com.szmz;

/**
 * 常量类
 */
public class SystemConst {

    /**
     *
     *
     * 基础平台    http://125.66.246.2:9093/SalvationPlatform/
     社会救助    http://125.66.246.2:9094/SocietySalvation/扫码和图片上传
     核对系统APP接口   http://125.66.246.2:9004/appCheck
     社会救助APP接口   http://125.66.246.2:9005/SSP_PORT/api
     医疗救助APP接口   http://125.66.246.2:9006/ActionControler
     */

    public static final String DEFAULT_SERVER_COM = "http://125.66.246.2:9093/";//平台
//    public static final String DEFAULT_SERVER_COM_SQR = "http://222.222.49.34:8081/";//平台
    public static final String DEFAULT_SERVER_COM_SQR = "http://125.66.246.2:9005/";

    public static final String IP_JZ="125.66.246.2:9005";
    public static final String IP_SM="125.66.246.2:9094";
    public static final String IP_YZS="125.66.246.2:9006";
//    public static final String IP_YZS="222.222.49.34:9095";//甘肃演示一站式
//    public static final String IP_HD="124.239.180.136:8088";
public static final String IP_HD="125.66.246.2:9004";

    public static final String DEFAULT_SERVER_JZ= "http://"+IP_JZ+"/";//救助
    public static final String DEFAULT_SERVER_SM= "http://"+IP_JZ+"/";//SM
    public static final String DEFAULT_SERVER_YZS= "http://"+IP_YZS+"/";
    public static final String DEFAULT_SERVER = "http://"+IP_HD+"/";//核对
    public static final String PROJECT_DIR = "SZMZ";
    public static final int MaxPhotoNumber = 9;

    //8a8a800b5e029817015e02c96bba0012：社会救助信息管理系统
//8a8a800b5e029817015e02c84ae2000e：居民家庭经济收入核查系统
    //8a8a800b5e029817015e02c9106d0010:攀枝花医疗补助一站式信息管理系统

    public static  String SystemID_JZ="8a8a800b5e029817015e02c96bba0012";
    public static  String SystemID_YZS="8a8a800b5e029817015e02c84ae2000e";
    public static  String SystemID_SH="8a8a800b5e029817015e02c9106d0010";

    //1是新疆2是攀枝花
    public static  int systemID=1;
}
