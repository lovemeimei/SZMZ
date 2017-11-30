package com.szmz.entity;

/**
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaMemberIncomeInfo implements IEntity {
    private String incomeId;//成员收入Id
    private String incomeTypeName;//	成员收入类型
    private String monthIncome;//成员月收入金额

    public String getIncomeId() {
        return incomeId;
    }

    public void setIncomeId(String incomeId) {
        this.incomeId = incomeId;
    }

    public String getIncomeTypeName() {
        return incomeTypeName;
    }

    public void setIncomeTypeName(String incomeTypeName) {
        this.incomeTypeName = incomeTypeName;
    }

    public String getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(String monthIncome) {
        this.monthIncome = monthIncome;
    }
}
