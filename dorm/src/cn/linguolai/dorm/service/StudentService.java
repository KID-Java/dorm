package cn.linguolai.dorm.service;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.exception.StudentException;

public interface StudentService {

    /**
     * 获取分页数据
     * @param currentPageNum
     * @param limlit
     * @return
     */
    PageBean<Student> getPageBean(Long currentPageNum, Integer limlit) throws StudentException;


    /**
     * 根据条件模糊查询分页数据
     * @param criteria
     * @param currentPageNum
     * @param limlit
     * @return
     * @throws StudentException
     */
    PageBean<Student> getPageBeanByPrerequisite(Student criteria,Long currentPageNum, Integer limlit) throws StudentException;

    /**
     * 根据ID查找学生
     * @param id
     * @return
     */
    Student getStudentById(Long id);

    /**
     * 根据Id删除学生
     * @param id
     */
    void deleteStudentById(Long id);

    /**
     * 修改学生
     * @param student
     */
    void updateStudent(Student student) throws StudentException;
}
