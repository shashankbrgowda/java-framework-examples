package com.shashank.resources.model;

public class Employee {
    private Integer empId;
    private String name;
    private String city;

    public Employee(Integer empId, String name, String city) {
        this.empId = empId;
        this.name = name;
        this.city = city;
    }

    public Employee() {
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId=" + empId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
