package org.erik.code.utils;

/**
 * Created by wandong.cwd on 2014/11/3.
 */

import org.erik.code.context.EasyCodeContext;
import org.erik.code.exceptions.EasyCodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据库操作辅助类
 *
 * User: liyd
 * Date: 13-12-6
 * Time: 上午10:34
 */
public class DBUtils {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(DBUtils.class);

    /** 数据库连接对象 */
    private static Connection connection;

    /**
     * 创建数据库连接
     */
    public synchronized static void createConnection() {

        try {
            if (connection != null && !connection.isClosed()) {
                return;
            }
            if (EasyCodeContext.getJdbcConfig("driverClassName") == null) {
                return;
            }
            String driverClassName = EasyCodeContext.getJdbcConfig("driverClassName");
            String url = EasyCodeContext.getJdbcConfig("url");
            String username = EasyCodeContext.getJdbcConfig("username");
            String password = EasyCodeContext.getJdbcConfig("password");
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);

        } catch (ClassNotFoundException e) {
            LOG.error("没有找到数据库驱动类，是否添加了数据库驱动的jar包？", e);
            throw new EasyCodeException("没有找到数据库驱动类，是否添加了数据库驱动的jar包？");
        } catch (SQLException e) {
            LOG.error("创建数据库连接出现错误", e);
            throw new EasyCodeException("创建数据库连接出现错误");
        }
    }

    /**
     * 获取配置文件中配置的默认数据库连接
     *
     * @return default connection
     */
    public static Connection getDefaultConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                createConnection();
            }
            return connection;
        } catch (SQLException e) {
            LOG.error("获取数据库连接出现错误", e);
            throw new EasyCodeException("获取数据库连接出现错误");
        }
    }

    /**
     * 关闭数据库连接
     *
     */
    public static void closeConnection() {

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {

        }
    }
}
