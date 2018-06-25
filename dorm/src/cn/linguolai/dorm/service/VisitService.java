package cn.linguolai.dorm.service;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.VisitInfo;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.exception.VisitException;

public interface VisitService {

    /**
     * 获取来访记录信息分页
     * @param currentPageNum
     * @param limlit
     * @return
     * @throws StudentException
     */
    PageBean<VisitInfo> getPageBean(Long currentPageNum, Integer limlit) throws VisitException;

    /**
     * 根据Id获取来访记录信息
     * @param id
     * @return
     */
    VisitInfo getVisitInfoById(Long id);

    /**
     * 添加一个来访记录信息
     * @param visitInfo
     */
    void addVisitInfo(VisitInfo visitInfo);

    /**
     * 获取最后一页的页码
     * @return
     */
    long getLastPageNum(Integer limit);

    /**
     * 获取最后一个访客记录
     * @return
     */
    VisitInfo getLastVisitInfo();
}
