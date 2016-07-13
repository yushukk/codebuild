package org.erik.code.provider;

import org.erik.code.model.Column;
import org.erik.code.utils.DBUtils;

import java.sql.Connection;
import java.util.List;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public abstract class AbstractDatabaseProvider implements DatabaseProvider {

    /**
     * 获取数据库表的信息
     *
     * @param tableName 表名
     * @return meta data
     */
    @Override
    public List<Column> getTableMetaData(String tableName) {

        Connection connection = DBUtils.getDefaultConnection();

        return getMetaData(tableName, connection);
    }

    @Override
    public String getTableDesc(String tableName) {
        Connection connection = DBUtils.getDefaultConnection();
        return getDesc(tableName,connection);
    }

    /**
     * 获取数据库表元信息
     *
     * @param tableName the table name
     * @param connection the connection
     * @return meta data
     */
    public abstract List<Column> getMetaData(String tableName, Connection connection);

    public abstract String getDesc(String tableName, Connection connection);




}
