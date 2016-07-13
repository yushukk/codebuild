package org.erik.code.model;

import org.apache.commons.lang.StringUtils;
import org.erik.code.utils.NameUtils;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class Table {
    public String name;
    public String desc;

    public String className;

    public Queue<String> tasks = new ArrayDeque<String>();

    public List<Column> columns;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void addTask(String taskName) {
        tasks.add(taskName);
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public Queue<String> getTasks() {
        return tasks;
    }

    public String getClassName() {
        if(StringUtils.isNotBlank(className)){
            return NameUtils.getNameWordFirstUpper(name);
        }
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
