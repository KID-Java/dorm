package cn.linguolai.dorm.bean;

import java.io.Serializable;
import java.util.List;

public class Dormitory implements Serializable {

    //宿舍编号ID
    private Long id;
    //宿舍所属公寓
    private String apartment;
    //宿舍电话
    private String phone;
    //宿舍人数
    private Integer personNum;
    //宿舍成员
    private List<Student> students;
    //宿舍设备
    private String equipment;
    //宿舍环境
    private String environment;
    //宿舍框架
    private String frame;
    //宿舍舍长
    private Student headmaster;
    //宿舍评分
    private Double score ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public Student getHeadmaster() {
        return headmaster;
    }

    public void setHeadmaster(Student headmaster) {
        this.headmaster = headmaster;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "id=" + id +
                ", apartment='" + apartment + '\'' +
                ", phone='" + phone + '\'' +
                ", personNum=" + personNum +
                ", students=" + students +
                ", equipment='" + equipment + '\'' +
                ", environment='" + environment + '\'' +
                ", frame='" + frame + '\'' +
                ", headmaster=" + headmaster +
                ", score=" + score +
                '}';
    }
}
