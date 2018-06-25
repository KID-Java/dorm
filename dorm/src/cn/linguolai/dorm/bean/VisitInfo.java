package cn.linguolai.dorm.bean;

import java.io.Serializable;
import java.util.Date;

public class VisitInfo implements Serializable {

    //来访记录Id
    private Long id;

    //来访者姓名
    private String name;

    //来访者身份 1:本校工作人员，2：非本校工作人员
    private Integer status;

    //来访者住址
    private String address;

    //来访记录时间
    private Date date;

    //来访记录内容
    private String content;


    @Override
    public String toString() {
        return "VisitInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
