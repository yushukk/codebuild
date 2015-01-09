package org.erik.code.provider.impl;

import org.apache.commons.lang.StringUtils;
import org.erik.code.exceptions.EasyCodeException;
import org.erik.code.model.Column;
import org.erik.code.provider.AbstractDatabaseProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class MysqlProvider extends AbstractDatabaseProvider {

    /**
     * 获取数据库表元信息
     *
     * @param tableName  the table name
     * @param connection the connection
     * @return meta data
     */
    @Override
    public List<Column> getMetaData(String tableName, Connection connection) {
        List<Column> columnList = new ArrayList<Column>();

        PreparedStatement pst = null;
        ResultSet rs = null;
        ResultSetMetaData rsd = null;
        try {
            //查询时没有数据，只返回表头信息
            pst = connection.prepareStatement("select * from " + tableName + " where 1=2");
            rsd = pst.executeQuery().getMetaData();

            //查询主键
            String primaryKey = null;
            pst = connection
                    .prepareStatement("SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.KEY_COLUMN_USAGE WHERE CONSTRAINT_NAME='PRIMARY' and TABLE_NAME = ?");
            pst.setString(1, tableName.toUpperCase());
            rs = pst.executeQuery();
            if (rs.next()) {
                primaryKey = rs.getString(1);
            }

            //查询列备注
            pst = connection
                    .prepareStatement("select column_name, column_comment from information_schema.columns where table_name = ?");
            pst.setString(1, tableName.toUpperCase());
            rs = pst.executeQuery();

            //先将注释放入到map再获取，防止有些列没有注释获取不对应的问题
            Map<String, String> commentMap = new HashMap<String, String>();
            while (rs.next()) {
                commentMap.put(rs.getString("COLUMN_NAME"), rs.getString("column_comment"));
            }

            for (int i = 1; i <= rsd.getColumnCount(); i++) {
                String name = rsd.getColumnName(i);
                String dbType = rsd.getColumnTypeName(i);
                String javaClass = rsd.getColumnClassName(i);
                String comment = commentMap.get(name);

                boolean b = StringUtils.equalsIgnoreCase(primaryKey, name) ? true : false;
                Column column = new Column();
                column.setName(name);
                column.setDbType(dbType);
                column.setJdbcType(dbType);
                column.setJavaClass(javaClass);
                column.setComment(comment);
                column.setIsPrimaryKey(b);

                columnList.add(column);
            }
        } catch (SQLException e) {
            throw new EasyCodeException(e);
        } finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                //ignore
            }
        }
        return columnList;
    }

}
