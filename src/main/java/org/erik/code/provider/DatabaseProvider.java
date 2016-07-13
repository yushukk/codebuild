package org.erik.code.provider;

import org.erik.code.model.Column;

import java.util.List;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public interface DatabaseProvider {
    /**
     * 获取数据库表的信息
     *
     * @param tableName 表名
     * @return meta data
     */
    public List<Column> getTableMetaData(String tableName);

    /**
     * 获取数据表的备注信息
     * @param tableName
     * @return
     */
    public String getTableDesc(String tableName);
}
