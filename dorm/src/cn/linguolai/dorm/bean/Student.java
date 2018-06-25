package cn.linguolai.dorm.bean;

import java.io.Serializable;

public class Student implements Serializable{

    //学生id
    private Long id;
    //学生名字
    private String name;
    //学生年龄
    private Integer age;
    //学生性别
    private String sex;
    //学生电话
    private String telphone;
    //学生专业
    private String major;
    //学生院系
    private String department;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", telphone='" + telphone + '\'' +
                ", major='" + major + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
