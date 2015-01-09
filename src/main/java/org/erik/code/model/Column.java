package org.erik.code.model;

import org.apache.commons.lang.StringUtils;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.utils.NameUtils;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class Column {
    /** 列名 */
    private String  name;

    /** 是否主键 */
    private boolean isPrimaryKey;

    /** 列备注 */
    private String  comment;

    /** 数据库类型 */
    private String  dbType;

    /** jdbc类型 */
    private String  jdbcType;

    /** java类型 例：String */
    private String  javaType;

    /** java类型class名称例：java.lang.String */
    private String  javaClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getJavaType() {
        return getClassName(EasyCodeContext.getDataConvertType(jdbcType).getJavaClass());
    }

    protected String getClassName(String qualifiedClassName) {

        if (StringUtils.isBlank(qualifiedClassName)) {
            return "";
        }

        return StringUtils.substring(qualifiedClassName,
                qualifiedClassName.lastIndexOf(".") + 1);
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }

    public void setIsPrimaryKey(boolean isPrimaryKey) {
        this.isPrimaryKey = isPrimaryKey;
    }

    public String getCamelName(){
        return NameUtils.getNameWordFirstLower(name);
    }

    public String getFirstUpperName(){
        return NameUtils.getNameWordFirstUpper(name);
    }
}
