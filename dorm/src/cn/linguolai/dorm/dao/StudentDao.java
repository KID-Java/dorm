package cn.linguolai.dorm.dao;

import cn.linguolai.dorm.bean.Student;

import java.util.List;

public interface StudentDao {

    /**
     * 获取部分学生
     * @param offset
     * @param limlit
     * @return
     */
    List<Student> getPartStudent(Long offset, Integer limlit);

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
    void updateStudent(Student student);

    /**
     * 获取总学生数
     * @return
     */
    Long getTotalRecordNumber();

    /**
     * 添加一个学生
     * @param student
     */
    void addStudent(Student student);


    /**
     * 根据条件模糊查询学生
     * @param criteria
     * @return
     */
    List<Student> getStudentByPrerequisite(Student criteria,Long offset, Integer limit);

    /**
     * 根据条件查询总记录数
     * @return
     */
    Long getTotalRecordNumberByPrerequisite(Student criteria);

}
