package cn.linguolai.dorm.dao;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.exception.DormitoryException;

import java.util.List;

public interface DormitoryDao {

    /**
     * 获取部分宿舍
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Dormitory> getPartDormitory(Long offset, Integer limit);

    /**
     * 根据Id 获取宿舍信息
     * @param id
     * @return
     */
    Dormitory getDormitoryById(Long id);


    /**
     * 修改宿舍评分信息
     * @param score
     */
    void updateDormitoryScore(Double score, Long id) throws DormitoryException;


    /**
     * 获取总宿舍数量
     * @return
     */
    long getTotalRecordNumber();


    /**
     * 添加一个宿舍
     * @param dormitory
     */
    void addDormitory(Dormitory dormitory);

    /**
     * 根据条件模糊查询宿舍
     * @param criteria
     * @return
     */
    List<Dormitory> getDormitoryByPrerequisite(Dormitory criteria, Long offset, Integer limit);

    /**
     * 根据条件查询总记录数
     * @return
     */
    Long getTotalRecordNumberByPrerequisite(Dormitory criteria);

}
