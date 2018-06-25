package cn.linguolai.dorm.service;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.exception.DormitoryException;
import cn.linguolai.dorm.exception.StudentException;

public interface DormitoryService {

    /**
     * 获取宿舍分页信息
     * @param currentPageNum
     * @param limlit
     * @return
     */
    PageBean<Dormitory> getPageBean(Long currentPageNum, Integer limlit) throws DormitoryException;

    /**
     * 根据条件模糊查询分页数据
     * @param criteria
     * @param currentPageNum
     * @param limit
     * @return
     * @throws DormitoryException
     */
    PageBean<Dormitory> getPageBeanByPrerequisite(Dormitory criteria, Long currentPageNum, Integer limit) throws DormitoryException;

    /**
     * 根据id获取宿舍信息
     * @param id
     * @return
     */
    Dormitory getDormitoryById(Long id) throws DormitoryException;

    /**
     * 添加一个宿舍信息
     * @param dormitory
     */
    void addDormitory(Dormitory dormitory);


    /**
     * 修改宿舍评分信息
     * @param score
     * @param id
     */
    void updateDormitoryScore(Double score, Long id) throws DormitoryException;



}
