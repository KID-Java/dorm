package cn.linguolai.dorm.daoimpl;

import cn.linguolai.dorm.bean.Student;
import cn.linguolai.dorm.dao.StudentDao;
import cn.linguolai.dorm.tools.JdbcTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> getPartStudent(Long offset, Integer limit) {

        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //创建学生集合
        List<Student> students = new ArrayList<Student>();

        //如果参数不正确，返回空集合
        if (offset == null || limit == null || offset < 0 || limit < 0) {
            return students;
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM student limit ? , ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, offset);
            statement.setInt(2, limit);

            //获取结果集
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                //填充学生信息
                Student student = setStudent(resultSet);
                //将学生添加到集合中
                students.add(student);
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }

        return students;
    }

    @Override
    public Student getStudentById(Long id) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM student WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, id);

            //获取结果集
            resultSet = statement.executeQuery();


            Student student = new Student();
            if (resultSet.next()) {
                //填充学生信息
                student = setStudent(resultSet);
            }
            return student;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }


        return null;
    }

    @Override
    public void deleteStudentById(Long id) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;


        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "DELETE  FROM student WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement);
        }
    }

    @Override
    public void updateStudent(Student student) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "UPDATE student SET name = ?, age = ?, sex = ?, telphone = ?, major = ?, department = ? WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getSex());
            statement.setString(4, student.getTelphone());
            statement.setString(5, student.getMajor());
            statement.setString(6, student.getDepartment());
            statement.setLong(7, student.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement);
        }


    }

    @Override
    public Long getTotalRecordNumber() {

        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT  COUNT(*) FROM student";

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
            JdbcTools.close(connection,statement,resultSet);
        }

        return null;
    }

    @Override
    public void addStudent(Student student) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "INSERT INTO student VALUES(NULL ,?,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);

            //填充数据
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getSex());
            statement.setString(4, student.getTelphone());
            statement.setString(5, student.getMajor());
            statement.setString(6, student.getDepartment());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement);
        }
    }

    @Override
    public List<Student> getStudentByPrerequisite(Student criteria,Long offset, Integer limit) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //创建学生集合
        List<Student> students = new ArrayList<Student>();

        //如果参数不正确，返回空集合
        if (offset == null || limit == null || offset < 0 || limit < 0) {
            return students;
        }

        if (criteria == null) {
            criteria = new Student();
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            //创建动态sql语句，用于根据条件进行拼接
            StringBuffer sql = new StringBuffer("SELECT * FROM student WHERE 1 = 1 ");
            //创建参数集合，用于拼接sql语句条件
            List params = new ArrayList();
            if (criteria.getId() != null) {
                sql.append(" and id like ? ");
                params.add("%" + criteria.getId() + "%");
            }
            if (criteria.getName() != null) {
                sql.append(" and name like ? ");
                params.add("%" +criteria.getName()+ "%");
            }
            if (criteria.getAge() != null) {
                sql.append(" and age like ? ");
                params.add("%" +criteria.getAge()+ "%");
            }
            if (criteria.getSex() != null) {
                sql.append(" and sex like ? ");
                params.add("%" +criteria.getSex()+ "%");
            }
            if (criteria.getTelphone() != null) {
                sql.append(" and telphone like ? ");
                params.add("%" +criteria.getTelphone()+ "%");
            }
            if (criteria.getMajor() != null) {
                sql.append(" and major like ? ");
                params.add("%" +criteria.getMajor()+ "%");
            }
            if (criteria.getDepartment() != null) {
                sql.append(" and department like ? ");
                params.add("%" +criteria.getDepartment()+ "%");
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

            while (resultSet.next()) {
                //填充学生信息
                Student stu = setStudent(resultSet);
                //将学生添加到集合中
                students.add(stu);
            }
            return students;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }

        return students;
    }

    @Override
    public Long getTotalRecordNumberByPrerequisite(Student criteria) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;


        //如果参数不正确，返回空集合
        if (criteria == null ) {
            criteria = new Student();
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            //创建动态sql语句，用于根据条件进行拼接
            StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM student WHERE 1 = 1 ");
            //创建参数集合，用于拼接sql语句条件
            List params = new ArrayList();
            if (criteria.getId() != null) {
                sql.append(" and id like ? ");
                params.add("%" + criteria.getId() + "%");
            }
            if (criteria.getName() != null) {
                sql.append(" and name like ? ");
                params.add("%" +criteria.getName()+ "%");
            }
            if (criteria.getAge() != null) {
                sql.append(" and age like ? ");
                params.add("%" +criteria.getAge()+ "%");
            }
            if (criteria.getSex() != null) {
                sql.append(" and sex like ? ");
                params.add("%" +criteria.getSex()+ "%");
            }
            if (criteria.getTelphone() != null) {
                sql.append(" and telphone like ? ");
                params.add("%" +criteria.getTelphone()+ "%");
            }
            if (criteria.getMajor() != null) {
                sql.append(" and major like ? ");
                params.add("%" +criteria.getMajor()+ "%");
            }
            if (criteria.getDepartment() != null) {
                sql.append(" and department like ? ");
                params.add("%" +criteria.getDepartment()+ "%");
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
     * 填充学生信息
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Student setStudent(ResultSet resultSet) throws SQLException {
        Student stu = new Student();
        //填充学生信息
        stu.setId(resultSet.getLong("id"));
        stu.setName(resultSet.getString("name"));
        stu.setAge(resultSet.getInt("age"));
        stu.setSex(resultSet.getString("sex"));
        stu.setTelphone(resultSet.getString("telphone"));
        stu.setMajor(resultSet.getString("major"));
        stu.setDepartment(resultSet.getString("department"));
        return stu;
    }

}
