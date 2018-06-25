package cn.linguolai.dorm.serviceimpl;

import cn.linguolai.dorm.bean.PageBean;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.dao.StudentDao;
import cn.linguolai.dorm.exception.StudentException;
import cn.linguolai.dorm.factory.StudentFactory;
import cn.linguolai.dorm.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    //获取StudentDao实例
    private StudentDao studentDao = StudentFactory.getStudentDao();

    @Override
    public PageBean<Student> getPageBean(Long currentPageNum, Integer limlit) throws StudentException {

        //验证分页参数，当参数不正确时，抛出异常
        if (currentPageNum == null || limlit == null) {
            throw new StudentException("分页参数不能为空！");
        } else if (currentPageNum <= 0 || limlit <= 0) {
            throw new StudentException("分页参数不能为负数！");
        }


        //创建空的分页数据
        PageBean<Student> studentPageBean = new PageBean<Student>();

        //根据分页参数获取部分学生集合
        List<Student> students = studentDao.getPartStudent((currentPageNum - 1) * limlit, limlit);
        //获取总学生数
        Long totalStudentNum = studentDao.getTotalRecordNumber();
        //计算总页数
        Long totalPageNum = (totalStudentNum - 1) / limlit + 1;


        //填充分页数据
        studentPageBean.setCurrentPage(currentPageNum);
        studentPageBean.setPageNumber(limlit);
        studentPageBean.setTotalRecordNumber(totalStudentNum);
        studentPageBean.setToatlPageNumber(totalPageNum);
        studentPageBean.setList(students);

        return studentPageBean;
    }

    @Override
    public PageBean<Student> getPageBeanByPrerequisite(Student criteria, Long currentPageNum, Integer limlit) throws StudentException {
        //验证分页参数，当参数不正确时，抛出异常
        if (currentPageNum == null || limlit == null) {
            throw new StudentException("分页参数不能为空！");
        } else if (currentPageNum <= 0 || limlit <= 0) {
            throw new StudentException("分页参数不能为负数！");
        }


        if (criteria == null) {
            criteria = new Student();
        }

        //创建空的分页数据
        PageBean<Student> studentPageBean = new PageBean<Student>();
        //根据分页参数获取部分学生集合
        List<Student> students = studentDao.getStudentByPrerequisite(criteria,(currentPageNum - 1) * limlit, limlit);
        //获取总学生数
        Long totalStudentNum = studentDao.getTotalRecordNumberByPrerequisite(criteria);
        //计算总页数
        Long totalPageNum = (totalStudentNum - 1) / limlit + 1;


        //填充分页数据
        studentPageBean.setCurrentPage(currentPageNum);
        studentPageBean.setPageNumber(limlit);
        studentPageBean.setTotalRecordNumber(totalStudentNum);
        studentPageBean.setToatlPageNumber(totalPageNum);
        studentPageBean.setList(students);

        return studentPageBean;
    }

    @Override
    public Student getStudentById(Long id) {
        if (id == null) {
            return null;
        }
        return studentDao.getStudentById(id);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (id == null) {
            return;
        }
        studentDao.deleteStudentById(id);
    }

    @Override
    public void updateStudent(Student student) throws StudentException {
        if (student == null) {
            throw new StudentException("当前学生对象异常！");
        } else {
            studentDao.updateStudent(student);
        }
    }
}