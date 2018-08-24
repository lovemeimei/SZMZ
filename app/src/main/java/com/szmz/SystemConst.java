package com.szmz;

/**
 * 常量类
 */
public class SystemConst {

    /**
     *
     基础平台    http://125.66.246.2:9093/SalvationPlatform/
     社会救助    http://125.66.246.2:9094/SocietySalvation/
     核对系统APP接口   http://125.66.246.2:9004/appCheck
     社会救助APP接口   http://125.66.246.2:9005/SSP_PORT/api
     医疗救助APP接口   http://125.66.246.2:9006/ActionControler
     */

    public static final String DEFAULT_SERVER_COM = "http://125.66.246.2:9093/SalvationPlatform/";//平台
    public static final String DEFAULT_SERVER_COM_SQR = "http://222.222.49.34:8081/";//作废
    public static final String IP_JZ="125.66.246.2:9005";
    public static final String IP_SM="125.66.246.2:9094";
    public static final String IP_YZS="125.66.246.2:9006";
    public static final String IP_HD="125.66.246.2:9004";

    public static final String DEFAULT_SERVER_JZ= "http://"+IP_JZ+"/SSP_PORT/api/";//救助
    public static final String DEFAULT_SERVER_YZS= "http://"+IP_YZS+"/ActionControler/";
    public static final String DEFAULT_SERVER_SM= "http://"+IP_SM+"/";
    public static final String DEFAULT_SERVER = "http://"+IP_HD+"/appCheck/";//核对
    public static final String PROJECT_DIR = "SZMZ";
    public static final int MaxPhotoNumber = 9;

    public static  String SystemID_JZ="";
    public static  String SystemID_YZS="";
    public static  String SystemID_SH="";
    public static  String SystemID_SM="";

    public static  int systemID=1;

}
