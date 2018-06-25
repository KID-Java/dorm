package cn.linguolai.dorm.factory;

import cn.linguolai.dorm.dao.StudentDao;
import cn.linguolai.dorm.daoimpl.StudentDaoImpl;
import cn.linguolai.dorm.service.StudentService;
import cn.linguolai.dorm.serviceimpl.StudentServiceImpl;

public class StudentFactory {

    /**
     * 返回一个StudnetDao实例
     * @return
     */
    public static StudentDao getStudentDao() {
        return new StudentDaoImpl();
    }

    /**
     * 返回一个StudentService的实例
     * @return
     */
    public static StudentService getStudentService() {
        return new StudentServiceImpl();
    }


}
