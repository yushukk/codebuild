package org.erik.code.genertator.code;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.velocity.VelocityContext;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.model.Column;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.utils.LocalFileUtils;
import org.erik.code.utils.NameUtils;
import org.erik.code.utils.VelocityUtils;

import java.io.File;
import java.util.*;

/**
 * Created by 尘东 on 2016/7/12.
 */
public class CommonCodeGenerator{

    private Task task;

    private Table table;

    public CommonCodeGenerator(Table table,Task task) {
        this.task = task;
        this.table = table;
    }

    public void doGenerate(){
        //获取包含了所有常量的velocityContext
        VelocityContext context = VelocityUtils.getVelocityContext();

        //放入变量，解析模版
        //全局变量
        context.put("serialVersionUID", getSerialVersionUID() + "L");
        context.put("date", new Date());
        context.put("dateUtil", new DateFormatUtils());
        context.put("creator", EasyCodeContext.getConstant("creator"));

        //表相关变量
        context.put("packageName", task.getPackageName());
        context.put("className", task.getGeneratedShotClassName(table.getClassName()));
        context.put("lowerClassName", task.getGeneratedShotLowerClassName(table.getClassName()));
//        context.put("table.name", table.getName());
//        context.put("table.desc", table.getDesc());

        //属性相关变量
        context.put("table", table); //从table对象取属性

        //依赖相关变量，
        Map<String, Task> allTask = EasyCodeContext.getAllTask();
        for(String taskName : allTask.keySet()){
            Map<String,Object> taskModel = new HashMap<String, Object>();
            taskModel.put("className",allTask.get(taskName).getGeneratedShotClassName(table.getClassName()));
            taskModel.put("modelName",table.getClassName());
            taskModel.put("lowerModelName", NameUtils.getFirstLowerName(table.getClassName()));
            taskModel.put("packageName",allTask.get(taskName).getPackageName());
            taskModel.put("lowerClassName",allTask.get(taskName).getGeneratedShotLowerClassName(table.getClassName()));
            context.put(taskName,taskModel);
        }


        StringBuilder sbTemp = new StringBuilder(LocalFileUtils.getContent(
                new File(EasyCodeContext.getConstant("configPath") + task.getTemplate())));
        //导入变量 import
        context.put("importSet",getImportSet(sbTemp));

        String template = VelocityUtils.parseString(sbTemp.toString(), context);

        String targetDir = EasyCodeContext.getConstant("targetDir");
        targetDir = StringUtils.isBlank(targetDir) ? "" : targetDir + "/";
        String moduleDir = task.getModuleDir();
        moduleDir = StringUtils.isBlank(moduleDir) ? "" : moduleDir + "/";
        String srcDir = task.getSrcDir();
        srcDir = StringUtils.isBlank(srcDir) ? "" : srcDir + "/";
        String packageFileDir = task.getGeneratedFileName(table.getClassName());
        String filePath = targetDir + moduleDir + srcDir + packageFileDir;
        LocalFileUtils.writeFile(filePath, template , ".xml".equals(task.getPropertyMap().get("suffix").getValue()));
    }

    /**
     * 添加字段类型需要导入的包
     *
     * @param columns the columns
     * @return the columns import class
     */
    protected Set<String> getColumnsImportClass(List<Column> columns) {

        Set<String> importSet = new HashSet<String>();
        for (Column column : columns) {
            if (EasyCodeContext.getDataConvertType(column.getDbType()) != null) {
                addImportClass(importSet, EasyCodeContext.getDataConvertType(column.getDbType())
                        .getJavaClass());
            } else {
                addImportClass(importSet, column.getJavaClass());
            }
        }
        return importSet;
    }

    /**
     * 生成serialVersionUID
     *
     * @return
     */
    protected String getSerialVersionUID() {
        return String.valueOf(Math.abs(UUID.randomUUID().getMostSignificantBits()));
    }

    /**
     * 添加引用的类
     *
     * @param importSet
     * @param className
     */
    private void addImportClass(Set<String> importSet, String className) {

        if (StringUtils.startsWith(className, "java.lang")) {
            return;
        }
        importSet.add(className);
    }

    private Set<String> getImportSet(StringBuilder template){
        Set<String> importSet = new HashSet<String>();

        String tmp = template.toString();
        if (StringUtils.indexOf(tmp, "List<") != -1
                && StringUtils.indexOf(tmp, "java.util.List") == -1) {
            importSet.add("java.util.List");
        }
        if (StringUtils.indexOf(tmp, "Map<") != -1
                && StringUtils.indexOf(tmp, "java.util.Map") == -1) {
            importSet.add("java.util.Map");
        }
        if (StringUtils.indexOf(tmp, "@Repository") != -1
                && StringUtils.indexOf(tmp, "org.springframework.stereotype.Repository") == -1) {
            importSet.add("org.springframework.stereotype.Repository");
        }
        if (StringUtils.indexOf(tmp, "@Autowired") != -1
                && StringUtils.indexOf(tmp, "org.springframework.beans.factory.annotation.Autowired") == -1) {
            importSet.add("org.springframework.beans.factory.annotation.Autowired");
        }
        if (StringUtils.indexOf(tmp, "@Component") != -1
                && StringUtils.indexOf(tmp, "org.springframework.stereotype.Component") == -1) {
            importSet.add("org.springframework.stereotype.Component");
        }

        if (StringUtils.indexOf(tmp, "table.columns") != -1) {
            importSet.addAll(getColumnsImportClass(table.getColumns()));
        }
        return importSet;
    }
}
