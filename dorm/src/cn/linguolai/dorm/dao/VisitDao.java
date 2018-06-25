package cn.linguolai.dorm.dao;

import cn.linguolai.dorm.bean.VisitInfo;

import java.util.List;

public interface VisitDao {


    /**
     * 获取部分来访记录信息
     * @param offset
     * @param limit
     * @return
     */
    List<VisitInfo> getPartVisitInfo(Long offset, Integer limit);


    /**
     * 根据Id获取来访记录信息
     * @param id
     * @return
     */
    VisitInfo getVisitInfoById(Long id);

    /**
     * 添加一个来访记录
     * @param visitInfo
     */
    void addVisitInfo(VisitInfo visitInfo);


    /**
     * 获取总来访记录数
     * @return
     */
    Long getTotalRecordNumber();
}

