package com.szmz;

/**
 * 常量类
 */
public class SystemConst {

//    //新疆
//    public static final String DEFAULT_SERVER_COM = "http://222.82.228.126:7780/";//平台
//    public static final String DEFAULT_SERVER_COM_SQR = "http://222.82.228.126:80/";//新疆平台
//    public static final String IP_JZ="222.82.228.126:3001";
//    public static final String IP_SM="222.82.231.35:3003";
//    public static final String IP_YZS="222.82.231.35:3003";
//    public static final String IP_HD="222.82.228.126:3002";


    public static final String DEFAULT_SERVER_COM = "http://222.222.49.34:8057/";//平台
    public static final String DEFAULT_SERVER_COM_SQR = "http://222.222.49.34:8081/";//平台
    public static final String IP_JZ="222.222.49.34:8050";
    public static final String IP_SM="222.222.49.34:9095";
    public static final String IP_YZS="222.222.49.34:8189";
    public static final String IP_HD="222.222.49.34:8099";

    public static final String DEFAULT_SERVER_JZ= "http://"+IP_JZ+"/";//救助
    public static final String DEFAULT_SERVER_YZS= "http://"+IP_YZS+"/";
    public static final String DEFAULT_SERVER_SM= "http://"+IP_SM+"/";
    public static final String DEFAULT_SERVER = "http://"+IP_HD+"/";//核对
    public static final String PROJECT_DIR = "SZMZ";
    public static final int MaxPhotoNumber = 9;


    public static  String SystemID_JZ="";
    public static  String SystemID_YZS="";
    public static  String SystemID_SH="";
    public static  String SystemID_SM="";

    //1是新疆2是攀枝花
    public static  int systemID=2;

}
