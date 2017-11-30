package com.szmz.entity;

/**
 * Created by bz on 2017/11/23.
 */

public class YwblDzdaMemberSalaryInfo implements IEntity {
    private String salaryId;//成员待遇ID
    private String personName;//	成员名称
    private String salary;//成员待遇类型
    private String money;//	成员待遇金额

    public String getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(String salaryId) {
        this.salaryId = salaryId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
