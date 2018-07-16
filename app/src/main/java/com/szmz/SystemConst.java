package com.szmz;

/**
 * 常量类
 */
public class SystemConst {

    //新疆
    public static final String DEFAULT_SERVER_COM = "http://222.82.228.126:7780/";//平台
    public static final String DEFAULT_SERVER_COM_SQR = "http://222.82.228.126:80/";//新疆平台
    public static final String IP_JZ="222.82.228.126:3001";
    public static final String IP_SM="222.82.231.35:3003";
    public static final String IP_YZS="222.82.231.35:3003";
    public static final String IP_HD="222.82.228.126:3002";


//    public static final String DEFAULT_SERVER_COM = "http://222.222.49.34:8057/";//平台
//    //    public static final String DEFAULT_SERVER_COM_SQR = "http://222.222.49.34:8081/";//平台
//    public static final String DEFAULT_SERVER_COM_SQR = "http://222.222.49.34:8050/";//新疆平台
//
//    public static final String IP_JZ="222.222.49.34:8050";
//    public static final String IP_SM="222.222.49.34:9095";
//    //    public static final String IP_YZS="222.222.49.34:8189";
//    public static final String IP_YZS="222.222.49.34:18099";
//    //    public static final String IP_YZS="222.222.49.34:9095";//甘肃演示一站式
//    public static final String IP_HD="124.239.180.136:8088";
////public static final String IP_HD="222.222.49.34:8099";

    public static final String DEFAULT_SERVER_JZ= "http://"+IP_JZ+"/";//救助
    public static final String DEFAULT_SERVER_YZS= "http://"+IP_YZS+"/";
    public static final String DEFAULT_SERVER_SM= "http://"+IP_SM+"/";
    public static final String DEFAULT_SERVER = "http://"+IP_HD+"/";//核对
    public static final String PROJECT_DIR = "SZMZ";
    public static final int MaxPhotoNumber = 9;

    //8a8a800b5e029817015e02c96bba0012：社会救助信息管理系统
//8a8a800b5e029817015e02c84ae2000e：居民家庭经济收入核查系统
    //8a8a800b5e029817015e02c9106d0010:攀枝花医疗补助一站式信息管理系统

    public static  String SystemID_JZ="";
    public static  String SystemID_YZS="";
    public static  String SystemID_SH="";
    public static  String SystemID_SM="";

    //1是新疆2是攀枝花3甘肃
    public static  int systemID=1;

}
