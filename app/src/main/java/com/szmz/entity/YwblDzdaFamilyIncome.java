package com.szmz.entity;

/**
 * 家庭收入列表
 * Created by bz on 2017/10/23.
 */

public class YwblDzdaFamilyIncome implements IEntity {
    private String incomeType;//	收入类型
    private String incomeproject;//	收入项目
    private String incomeamount;//收入金额
    private String id;//家庭id
    private String monthincome;//月收入
    private String yearincome;//年收入
    private String name;//户主姓名

    public String getMonthincome() {
        return monthincome;
    }

    public void setMonthincome(String monthincome) {
        this.monthincome = monthincome;
    }

    public String getYearincome() {
        return yearincome;
    }

    public void setYearincome(String yearincome) {
        this.yearincome = yearincome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public String getIncomeproject() {
        return incomeproject;
    }

    public void setIncomeproject(String incomeproject) {
        this.incomeproject = incomeproject;
    }

    public String getIncomeamount() {
        return incomeamount;
    }

    public void setIncomeamount(String incomeamount) {
        this.incomeamount = incomeamount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
