package org.erik.code.genertator.code;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.erik.code.extra.EasyCodePlugin;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.model.Column;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.utils.LocalFileUtils;
import org.erik.code.utils.VelocityUtils;

import java.util.*;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public abstract class AbstractEasyCodeGenerator implements EasyCodeGenerator {
    /**
     * 代码生成方法
     *
     * @param table the table
     * @param task  the task
     * @param context the context
     */
    @Override
    public void doGenerate(Table table, Task task, VelocityContext context) {

        context.put("serialVersionUID", getSerialVersionUID() + "L");
        StringBuilder sbTemp = new StringBuilder(LocalFileUtils.getTemplate(task.getTemplate()));

        this.generate(table, task, context, sbTemp);

        String template = VelocityUtils.parseString(sbTemp.toString(), context);

        String targetDir = EasyCodeContext.getConstant("targetDir");
        targetDir = StringUtils.isBlank(targetDir) ? "" : targetDir + "/";
        String moduleDir = task.getModuleDir();
        moduleDir = StringUtils.isBlank(moduleDir) ? "" : moduleDir + "/";
        String srcDir = task.getSrcDir();
        srcDir = StringUtils.isBlank(srcDir) ? "" : srcDir + "/";
        String fileName = (String) context.get("fileName");
        String packageFileDir = task.getGeneratedFileName(table);
        String filePath = targetDir + moduleDir + srcDir + packageFileDir;
        LocalFileUtils.writeFile(filePath, template);
    }

    /**
     * 运行插件
     *
     * @param table
     * @param task
     * @param context
     * @param sbTemp
     */
    private void executePlugin(Table table, Task task, VelocityContext context, StringBuilder sbTemp) {
        Map<String, EasyCodePlugin> pluginMap = task.getPluginMap();
        if (pluginMap == null || pluginMap.size() == 0) {
            return;
        }

        for (EasyCodePlugin easyCodePlugin : pluginMap.values()) {
            easyCodePlugin.execute(table, task, sbTemp, context);
        }
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

    /**
     * 代码生成方法
     *
     * @param table the table
     * @param task the task
     * @param context the context
     * @param template the template
     */
    public abstract void generate(Table table, Task task, VelocityContext context,
                                  StringBuilder template);

}
