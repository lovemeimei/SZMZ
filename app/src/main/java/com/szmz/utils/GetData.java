package com.szmz.utils;

import com.szmz.entity.HdxtGrcxInfo;
import com.szmz.entity.HdxtZlglInfo;
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
            item.setSrbz("收入备注" + i);
            item.setSrje("收入金额1000" + i);
            item.setSrlx("收入类型" + i);
            item.setSrxm("收入项目" + i);
            item.setSlhmj("数量或面积" + i);
            item.setJldw("计量单位" + i);
            item.setJtrk(i + "");
            item.setYhzgx("与户主关系" + i);
            item.setBsyrs(i + "");
            item.setSyfzc("222" + i);
            item.setSybz("赡养备注");
            item.setZllb("档案类" + i);
            item.setWj("家庭成员低保类型.xml");
            item.setScjd("上传节点" + i);
            item.setCd("彩电" + i);
            item.setXyj("洗衣机" + i);
            item.setKt("空调" + i);
            item.setDn("电脑" + i);
            item.setBx("冰箱" + i);
            item.setQt("其他" + i);
            item.setZfxz("住房性质" + i);
            item.setZfmj("住房面积" + i);

            item.setJtcyList(listJtcy);
            list.add(item);
        }

        return list;
    }

    public static List<HdxtGrcxInfo> getDataList() {
        List<HdxtGrcxInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HdxtGrcxInfo item = new HdxtGrcxInfo();
            item.setBlsj("2017-10-01");
            item.setCjsj("2017-09-09");
            item.setHzsj("2017-09-30");
            item.setPcmc("攀枝花2017090900" + i);
            item.setThyy("信息错误" + i);
            item.setWtsj("2017-09-22");
            item.setYclts("2000000");
            item.setYwlx("城乡低保" + i);
            item.setZts("3000000");
            list.add(item);

        }
        return list;

    }

    public static List<HdxtZlglInfo> getHdxtZlglInfoList() {
        List<HdxtZlglInfo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            HdxtZlglInfo item = new HdxtZlglInfo();
            item.setId("" + i);
            item.setDetail("");
            item.setName("攀枝花20170806");
            item.setType("待审核");
            list.add(item);

        }
        return list;

    }
}
