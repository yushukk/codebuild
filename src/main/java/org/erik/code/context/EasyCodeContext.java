package org.erik.code.context;

import org.apache.commons.lang.StringUtils;
import org.erik.code.model.ConvertType;
import org.erik.code.model.Project;
import org.erik.code.model.Table;
import org.erik.code.model.Task;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class EasyCodeContext {
    /** jdbc标识key */
    public static final String              JDBC_KEY       = "jdbc_";

    /** 常量标识key */
    public static final String              CONSTANT_KEY   = "";

    /** 常量map */
    private static Map<String, String>      constantMap    = new HashMap<String, String>();

    /** 表配置map */
    private static Map<String, Table>       tableMap       = new HashMap<String, Table>();

    /** 任务配置 */
    private static Map<String, Task>        taskMap        = new HashMap<String, Task>();

    /** 数据转换map */
    private static Map<String, ConvertType> dataConvertMap = new HashMap<String, ConvertType>();

    private static Map<String,Project> projectMap = new HashMap<String, Project>();


    public static void addProject(Project project){
        projectMap.put(project.getName(),project);
    }

    public static Map<String, Project> getAllProject() {
        return projectMap;
    }

    /**
     * 添加数据转换配置
     *
     * @param dbType the db type
     * @param convertType the convert type
     */
    public static void addDataConvertType(String dbType, ConvertType convertType) {
        dataConvertMap.put(StringUtils.upperCase(dbType), convertType);
    }

    /**
     * 获取数据转换配置
     *
     * @param dbType
     * @return
     */
    public static ConvertType getDataConvertType(String dbType) {
        if (StringUtils.isBlank(dbType)) {
            return null;
        }
        return dataConvertMap.get(StringUtils.upperCase(dbType));
    }

    /**
     * 添加任务
     *
     * @param map the task map
     */
    public static void addTask(Map<String, Task> map) {
        taskMap.putAll(map);
    }

    /**
     * 获取所有任务
     *
     * @return
     */
    public static Map<String, Task> getAllTask() {
        return taskMap;
    }

    /**
     * 获取所有常量
     *
     * @return
     */
    public static Map<String, String> getAllConstant() {
        return constantMap;
    }

    /**
     * 获取表配置
     *
     * @return
     */
    public static Map<String, Table> getAllTable() {
        return tableMap;
    }

    /**
     * 添加表配置
     *
     * @param tableName
     * @param table
     */
    public static void addTable(String tableName, Table table) {
        tableMap.put(tableName, table);
    }

    /**
     * 添加jdbc配置信息
     *
     * @param name
     * @param value
     */
    public static void addJdbcConfig(String name, String value) {

        constantMap.put(JDBC_KEY + name, value);
    }
    /**
     * 获取jdbc配置信息
     *
     * @param name
     * @return
     */
    public static String getJdbcConfig(String name) {
        return constantMap.get(JDBC_KEY + name);
    }

    /**
     * 添加常量
     *
     * @param map the map
     */
    public static void addConstant(Map<String, String> map) {

        for (Map.Entry<String, String> entry : map.entrySet()) {
            constantMap.put(CONSTANT_KEY + entry.getKey(), entry.getValue());
        }
    }

    /**
     * 获取常量
     *
     * @param name
     * @return
     */
    public static String getConstant(String name) {
        return constantMap.get(CONSTANT_KEY + name);
    }

    public static String getEncoding(){
        return getConstant("encoding");
    }

    public static String getTargetEncoding(){
        return getConstant("targetEncoding");
    }
}
