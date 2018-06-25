package cn.linguolai.dorm.test;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.dao.DormitoryDao;
import cn.linguolai.dorm.dao.StudentDao;
import cn.linguolai.dorm.daoimpl.DormitoryDaoImpl;
import cn.linguolai.dorm.daoimpl.StudentDaoImpl;
import cn.linguolai.dorm.tools.BeanTools;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws SQLException {



        Student criteria = new Student();
        criteria.setSex("男");

        System.out.println(BeanTools.endcoding(criteria));
//
//        StudentDao studentDao = new StudentDaoImpl();
//        System.out.println(studentDao.getStudentByPrerequisite(null, 0L, 10));
//        System.out.println(studentDao.getTotalRecordNumberByPrerequisite(criteria));
//        System.out.println(studentDao.getTotalRecordNumberByPrerequisite(null));

//
//        DormitoryDao dormitoryDao = new DormitoryDaoImpl();
//        Student student = new Student();
//
//        List<Student> students = new ArrayList<>();
//        for (int i = 1; i <= 5; i++) {
//
//            student.setName("学生" + i);
//            student.setAge(22);
//            if (i % 2 == 0) {
//                student.setSex("男");
//            } else {
//                student.setSex("女");
//            }
//            student.setTelphone("10001022" + i);
//            student.setMajor("网络工程");
//            student.setDepartment("电信学院");
//            students.add(student);
//        }
//
//        Dormitory dormitory = new Dormitory();
//
//        for (int i = 5; i < 57; i++) {
//            dormitory.setId(i);
//            dormitory.setApartment(i % 5 + "区");
//            dormitory.setPhone("3295" + i);
//            dormitory.setPersonNum(5 + (i % 5));
//            dormitory.setEquipment("有空调");
//            dormitory.setEnvironment("优");
//            dormitory.setFrame("结构框架");
//            dormitory.setHeadmaster(students.get(i % 5));
//            double score = nextDouble(7.0, 9.9);
//            String result = formateRate(score + "");
//            score = Double.parseDouble(result);
//            dormitory.setScore(score);
//            dormitoryDao.addDormitory(dormitory);
//        }




    }

    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }

    public static String formateRate(String rateStr) {
        if (rateStr.indexOf(".") != -1) {
            // 获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");

            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            if (rateStr == "1") {
                return "100";
            } else {
                return rateStr;
            }
        }
    }
}
