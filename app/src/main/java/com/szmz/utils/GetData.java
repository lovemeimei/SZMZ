package com.szmz.utils;

import com.szmz.entity.YwblJtcy;
import com.szmz.entity.YwblPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bz on 2017/9/21.
 */

public class GetData {
    public static List<YwblPerson> doGetPersonList() {
        List<YwblPerson> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {

            YwblPerson item = new YwblPerson();
            item.setId(i);
            item.setName("名称" + i);
            item.setCounty("村子名称" + i);
            item.setTime("2017-10-01");
            item.setTypeName("农村低保" + i);
            item.setXzqh("攀枝花" + i);
            item.setXian("xx县" + i);
            item.setXiang("xx乡" + i);
            item.setHzxm("户主姓名" + i);
            item.setJtfljzlb("家庭分类救助类别" + i);
            item.setSfzh("13010315747415475" + i);
            item.setZyzpyy("主要致贫原因" + i);
            List<YwblJtcy> listJtcy = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                YwblJtcy jtcy = new YwblJtcy();
                jtcy.setId(j);
                jtcy.setSfzh("1301039999999" + j);
                jtcy.setCsrq("1988-10-1" + j);
                jtcy.setHyzk("未婚" + j);
                jtcy.setMz("汉族" + j);
                jtcy.setName("姓名" + j);
                jtcy.setRyfljzlb("城市低保对象" + j);
                jtcy.setSfbzdx("是" + j);
                jtcy.setXb("男" + j);
                jtcy.setYhzgx("父子" + j);
                jtcy.setYsr("0.00");
                listJtcy.add(jtcy);

            }

            item.setJtcyList(listJtcy);
            list.add(item);
        }

        return list;
    }
}
