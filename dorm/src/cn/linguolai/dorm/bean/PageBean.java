package cn.linguolai.dorm.bean;

import java.io.Serializable;
import java.util.List;

public class PageBean<T> implements Serializable {

    //当前页码
    private long currentPage;
    //每页记录数
    private int pageNumber;
    //总页数
    private long toatlPageNumber;
    //总记录数
    private Long totalRecordNumber;
    //每页数据集合
    private List<T> list;

    //url
    private String url;


    @Override
    public String toString() {
        return "PageBean{" +
                "currentPage=" + currentPage +
                ", pageNumber=" + pageNumber +
                ", toatlPageNumber=" + toatlPageNumber +
                ", totalRecordNumber=" + totalRecordNumber +
                ", list=" + list +
                ", url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getToatlPageNumber() {
        return toatlPageNumber;
    }

    public void setToatlPageNumber(long toatlPageNumber) {
        this.toatlPageNumber = toatlPageNumber;
    }

    public Long getTotalRecordNumber() {
        return totalRecordNumber;
    }

    public void setTotalRecordNumber(Long totalRecordNumber) {
        this.totalRecordNumber = totalRecordNumber;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
