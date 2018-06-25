package cn.linguolai.dorm.tools;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 实现一个数据库的工具
 * 用于获取数据库连接和关闭数据库操作，以及事务的相关操作。
 * 从而在MVC分层上彻底将每一层分开，降低每一层的耦合。
 * 其中数据库连接使用了ThreadLocal来保存，保证每一个线程只有一份连接。保证并发的安全！
 */
public class JdbcTools {

    //配置数据库连接池，c3p0数据库连接池
    private static DataSource dataSource = new ComboPooledDataSource();

    //使用ThreadLocal来存储连接，以解决线程并发的问题。确保每个线程就一个连接
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal();

    private JdbcTools() {
    }

    /**
     * 获取数据库连接
     * @return
     * @throws SQLException
     */
    public static Connection getCconnection() throws SQLException {
        Connection connection = threadLocal.get();
        return connection != null ? connection : dataSource.getConnection();
    }

    /**
     * 获取数据库连接池
     * @return
     */
    public static DataSource getDatasDataSource() {
        return dataSource;
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void beginTranscation() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection != null) {
            throw new SQLException("已经开启了事务!无需重复开启!");
        } else {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);//开启事务
            threadLocal.set(connection);
        }
    }

    /**
     * 提交事务
     * @throws SQLException
     */
    public static void commitTranscation() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new SQLException("还没开启事务!无需提交!");
        } else {
            connection.commit();//提交事务
            connection.close();
            threadLocal.remove();//删除当前的连接
        }
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollbackTranscation() throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            throw new SQLException("还没开启事务!无需回滚!");
        } else {
            connection.rollback();//回滚事务
            connection.close();
            threadLocal.remove();//删除当前的连接
        }
    }

    /**
     * 关闭数据库资源
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection con) throws SQLException {
        Connection connection = threadLocal.get();
        if (connection == null) {
            con.close();
        }
        if (connection != con) {
            con.close();
        }

    }


    /**
     * 关闭数据库连接资源
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            close(connection,statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭数据库连接资源
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, PreparedStatement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
