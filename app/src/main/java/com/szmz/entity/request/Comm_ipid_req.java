package com.szmz.entity.request;

/**
 * 中移全通集成公司 版本所有
 * 创建人： 郄益轩
 * 创建时间：2017/11/22 0022下午 3:01
 */

public class Comm_ipid_req extends BaseRequest{

    private String ip_ports;

    public Comm_ipid_req(String ip_ports) {
        this.ip_ports = ip_ports;
    }
}
