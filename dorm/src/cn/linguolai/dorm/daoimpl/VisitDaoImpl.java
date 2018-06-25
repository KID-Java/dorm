package cn.linguolai.dorm.daoimpl;

import cn.linguolai.dorm.bean.VisitInfo;
import cn.linguolai.dorm.dao.VisitDao;
import cn.linguolai.dorm.tools.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitDaoImpl implements VisitDao {
    @Override
    public List<VisitInfo> getPartVisitInfo(Long offset, Integer limit) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        //创建来访信息集合
        List<VisitInfo> visitInfos = new ArrayList<VisitInfo>();

        //如果参数不正确，返回空集合
        if (offset == null || limit == null || offset < 0 || limit < 0) {
            return visitInfos;
        }

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM visit_info ORDER BY DATE DESC limit ? , ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, offset);
            statement.setInt(2, limit);

            //获取结果集
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                VisitInfo visitInfo = new VisitInfo();
                //填充来访记录信息
                visitInfo.setId(resultSet.getLong("id"));
                visitInfo.setName(resultSet.getString("name"));
                visitInfo.setStatus(resultSet.getInt("status"));
                visitInfo.setAddress(resultSet.getString("address"));
                visitInfo.setDate(resultSet.getTimestamp("date"));
                visitInfo.setContent(resultSet.getString("content"));
                //将来访记录添加到集合中
                visitInfos.add(visitInfo);
            }
            return visitInfos;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }
        return visitInfos;
    }

    @Override
    public VisitInfo getVisitInfoById(Long id) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "SELECT * FROM visit_info WHERE id = ?";

            statement = connection.prepareStatement(sql);

            //设置参数
            statement.setLong(1, id);

            //获取结果集
            resultSet = statement.executeQuery();


            VisitInfo visitInfo = new VisitInfo();
            if (resultSet.next()) {
                //填充来访记录信息
                visitInfo.setId(resultSet.getLong("id"));
                visitInfo.setName(resultSet.getString("name"));
                visitInfo.setStatus(resultSet.getInt("status"));
                visitInfo.setAddress(resultSet.getString("address"));
                visitInfo.setDate(resultSet.getTimestamp("date"));
                visitInfo.setContent(resultSet.getString("content"));
            }
            return visitInfo;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JdbcTools.close(connection, statement, resultSet);
        }
        return null;
    }

    @Override
    public void addVisitInfo(VisitInfo visitInfo) {
        //数据库连接
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            //获取数据库连接
            connection = JdbcTools.getCconnection();

            String sql = "INSERT INTO visit_info VALUES(NULL ,?,?,?,?,?)";

            statement = connection.prepareStatement(sql);

            //填充数据
            statement.setString(1, visitInfo.getName());
            statement.setInt(2, visitInfo.getStatus());
            statement.setString(3, visitInfo.getAddress());
            statement.setObject(4, visitInfo.getDate());
            statement.setString(5, visitInfo.getContent());

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

            String sql = "SELECT  COUNT(*) FROM visit_info";

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
}
