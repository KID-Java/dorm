package cn.linguolai.dorm.daoimpl;

import cn.linguolai.dorm.bean.Dormitory;
import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.dao.DormitoryDao;
import cn.linguolai.dorm.dao.StudentDao;
import cn.linguolai.dorm.exception.DormitoryException;
import cn.linguolai.dorm.factory.StudentFactory;
import cn.linguolai.dorm.tools.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DormitoryDaoImpl implements DormitoryDao {

    private StudentDao studentDao = StudentFactory.getStudentDao();

    @Override
    public List<Dormitory> getPartDormitory(Long offset, Integer limit) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet stuResultSet = null;

        //创建宿舍集合
        List<Dormitory> dormitories = new ArrayList<Dormitory>();

        //如果参数不正确，返回空集合
        if (offset == null || limit == null || offset < 0 || limit < 0) {
            return dormitories;
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM dormitory limit ? , ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, offset);
            statement.setInt(2, limit);

            //获取结果集
            resultSet = statement.executeQuery();

            String stuSql = "SELECT  COUNT(*) FROM stu_dorm WHERE dormId = ?";

            statement = connection.prepareStatement(stuSql);

            while (resultSet.next()) {
                //填充宿舍信息
                Dormitory dormitory = setDormitory(resultSet);

                //获取学生结果集
                statement.setLong(1,dormitory.getId());
                stuResultSet = statement.executeQuery();
                int personNum = 0;
                if (stuResultSet.next()) {
                    //如果存在，则返回总记录数
                    personNum = stuResultSet.getInt(1);
                }
                //设置学生数量
                dormitory.setPersonNum(personNum);

                //将学生添加到集合中
                dormitories.add(dormitory);
            }
            return dormitories;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stuResultSet != null) {
                try {
                    stuResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }

        return dormitories;


    }

    @Override
    public Dormitory getDormitoryById(Long id) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet stuResultSet = null;


        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM dormitory WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, id);

            //获取结果集
            resultSet = statement.executeQuery();

            String stuSql = "SELECT stuId FROM stu_dorm WHERE dormId = ?";

            statement = connection.prepareStatement(stuSql);

            Dormitory dormitory;
            if (resultSet.next()) {
                //填充宿舍信息
                dormitory = setDormitory(resultSet);

                statement.setLong(1, dormitory.getId());
                //获取学生结果集
                stuResultSet = statement.executeQuery();
                List<Student> students = new ArrayList<Student>();
                int personNum = 0;
                for (;stuResultSet.next();personNum++){
                    Student student = studentDao.getStudentById(stuResultSet.getLong("stuId"));
                    //把当前学生添加入
                    students.add(student);
                }
                //设置宿舍人数
                dormitory.setPersonNum(personNum);
                //将学生添加到宿舍集合中
                dormitory.setStudents(students);
                return dormitory;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stuResultSet != null) {
                try {
                    stuResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }


        return null;
    }

    @Override
    public void updateDormitoryScore(Double score, Long id) throws DormitoryException {

        if (score == null || id == null) {
            throw new DormitoryException("评分信息参数异常！请重新填写！");
        }
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "UPDATE dormitory SET score = ? WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //填充数据
            statement.setDouble(1,score);
            statement.setLong(2,id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement);
        }


    }

    @Override
    public long getTotalRecordNumber() {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT  COUNT(*) FROM dormitory";

            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                //如果存在，则返回总记录数
                return resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭数据库资源
            JdbcTools.close(connection, statement, resultSet);
        }
        return 0;
    }

    @Override
    public void addDormitory(Dormitory dormitory) {

        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "INSERT INTO dormitory VALUES(NULL ,?,?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);

            //填充数据
            statement.setString(1,dormitory.getApartment());
            statement.setString(2,dormitory.getPhone());
            statement.setString(3,dormitory.getEquipment());
            statement.setString(4,dormitory.getEnvironment());
            statement.setString(5,dormitory.getFrame());
            statement.setLong(6,dormitory.getHeadmaster().getId());
            statement.setDouble(7,dormitory.getScore());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement);
        }

    }

    @Override
    public List<Dormitory> getDormitoryByPrerequisite(Dormitory criteria, Long offset, Integer limit) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        ResultSet stuResultSet = null;

        //创建宿舍集合
        List<Dormitory> dormitories = new ArrayList<Dormitory>();

        //如果参数不正确，返回空集合
        if (offset == null || limit == null || offset < 0 || limit < 0) {
            return dormitories;
        }

        //条件等于空时，相当于无条件
        if (criteria == null) {
            criteria = new Dormitory();
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            //创建动态sql语句，用于根据条件进行拼接
            StringBuffer sql = new StringBuffer("SELECT * FROM dormitory WHERE 1 = 1 ");
            String stuSql = "SELECT  COUNT(*) FROM stu_dorm WHERE dormId = ?";
            //创建参数集合，用于拼接sql语句条件
            List params = new ArrayList();
            if (criteria.getId() != null) {
                sql.append(" and id like ? ");
                params.add("%" + criteria.getId() + "%");
            }
            if (criteria.getApartment() != null) {
                sql.append(" and apartment like ? ");
                params.add("%" +criteria.getApartment()+ "%");
            }
            if (criteria.getPhone() != null) {
                sql.append(" and phone like ? ");
                params.add("%" +criteria.getPhone()+ "%");
            }
            if (criteria.getEquipment() != null) {
                sql.append(" and equipment like ? ");
                params.add("%" +criteria.getEquipment()+ "%");
            }
            if (criteria.getEnvironment() != null) {
                sql.append(" and environment like ? ");
                params.add("%" +criteria.getEnvironment()+ "%");
            }
            if (criteria.getFrame() != null) {
                sql.append(" and frame like ? ");
                params.add("%" +criteria.getFrame()+ "%");
            }
            if (criteria.getHeadmaster() != null && criteria.getHeadmaster().getId() != null) {
                sql.append(" and headmasterId like ? ");
                params.add("%" +criteria.getHeadmaster().getId()+ "%");
            }

            //最后拼接limit
            sql.append(" limit ? , ?");
            params.add(offset);
            params.add(limit);

            statement = connection.prepareStatement(sql.toString());

            //设置参数
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            //获取结果集
            resultSet = statement.executeQuery();

            //用于查找学生数量
            statement = connection.prepareStatement(stuSql);

            while (resultSet.next()) {
                //填充学生信息
                Dormitory dorm = setDormitory(resultSet);

                //获取学生结果集
                statement.setLong(1,dorm.getId());
                stuResultSet = statement.executeQuery();
                //默认宿舍人数为0
                int personNum = 0;
                if (stuResultSet.next()) {
                    //如果存在，则返回总记录数
                    personNum = stuResultSet.getInt(1);
                }
                //设置学生数量
                dorm.setPersonNum(personNum);

                //将宿舍添加到集合中
                dormitories.add(dorm);

            }
            return dormitories;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stuResultSet != null) {
                try {
                    stuResultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }
        return dormitories;
    }

    @Override
    public Long getTotalRecordNumberByPrerequisite(Dormitory criteria) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        //如果参数不正确，返回空集合
        if (criteria == null ) {
            criteria = new Dormitory();
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            //创建动态sql语句，用于根据条件进行拼接
            StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM dormitory WHERE 1 = 1 ");
            //创建参数集合，用于拼接sql语句条件
            List params = new ArrayList();
            if (criteria.getId() != null) {
                sql.append(" and id like ? ");
                params.add("%" + criteria.getId() + "%");
            }
            if (criteria.getApartment() != null) {
                sql.append(" and apartment like ? ");
                params.add("%" +criteria.getApartment()+ "%");
            }
            if (criteria.getPhone() != null) {
                sql.append(" and phone like ? ");
                params.add("%" +criteria.getPhone()+ "%");
            }
            if (criteria.getEquipment() != null) {
                sql.append(" and equipment like ? ");
                params.add("%" +criteria.getEquipment()+ "%");
            }
            if (criteria.getEnvironment() != null) {
                sql.append(" and environment like ? ");
                params.add("%" +criteria.getEnvironment()+ "%");
            }
            if (criteria.getFrame() != null) {
                sql.append(" and frame like ? ");
                params.add("%" +criteria.getFrame()+ "%");
            }
            if (criteria.getHeadmaster() != null && criteria.getHeadmaster().getId() != null) {
                sql.append(" and headmasterId like ? ");
                params.add("%" +criteria.getHeadmaster().getId()+ "%");
            }

            statement = connection.prepareStatement(sql.toString());

            //设置参数
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            //获取结果集
            resultSet = statement.executeQuery();

            //返回结果
            if (resultSet.next()) {
                return resultSet.getLong(1);
            }
            return 0L;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }

        return 0L;
    }


    /**
     * 填充宿舍信息
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Dormitory setDormitory(ResultSet resultSet) throws SQLException {

        Dormitory dormitory = new Dormitory();
        dormitory.setId(resultSet.getLong("id"));
        dormitory.setApartment(resultSet.getString("apartment"));
        dormitory.setPhone(resultSet.getString("phone"));
        dormitory.setEquipment(resultSet.getString("equipment"));
        dormitory.setEnvironment(resultSet.getString("environment"));
        dormitory.setFrame(resultSet.getString("frame"));
        dormitory.setScore(resultSet.getDouble("score"));
        //获取舍长id
        Long stuId = resultSet.getLong("headmasterId");
        dormitory.setHeadmaster(studentDao.getStudentById(stuId));
        return dormitory;
    }
}
