package org.erik.code.genertator.code;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.utils.NameUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class ModelCodeGenerator extends AbstractEasyCodeGenerator  {
    /**
     * 代码生成方法
     *
     * @param table
     * @param task
     * @param context
     * @param template
     */
    @Override
    public void generate(Table table, Task task, VelocityContext context, StringBuilder template) {

        context.put("date", new Date());
        context.put("table", table);
        context.put("task", task);
        context.put("packageName", task.getPackageName());
        String longClassKey = task.getName() + "GeneratedLongClassName";
        String shortClassKey = task.getName() + "GeneratedShotClassName";
        String firstLowerClassKey = task.getName() + "FirstLowerGeneratedClassName";
        String generatedShotClassName = task.getGeneratedShotClassName(table.getName());
        context.put(longClassKey, task.getGeneratedReferenceClassName(table.getName()));
        context.put(shortClassKey, generatedShotClassName);
        context.put(firstLowerClassKey, NameUtils.getFirstLowerName(generatedShotClassName));

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
        if (StringUtils.indexOf(tmp, "${modelGeneratedShotClassName}") != -1
                && StringUtils.indexOf(tmp, "${modelGeneratedLongClassName}") == -1
                && !StringUtils.equals(task.getName(), "model")) {
            Object modelGeneratedLongClassName = context.get("modelGeneratedLongClassName");
            importSet.add(modelGeneratedLongClassName == null ? "" : modelGeneratedLongClassName
                    .toString());
        }

        if (StringUtils.equalsIgnoreCase("model", task.getName())
                || StringUtils.equalsIgnoreCase("modelVo", task.getName())) {
            importSet.addAll(super.getColumnsImportClass(table.getColumns()));
        }
        context.put("importList", importSet);
        context.put("fileName",NameUtils.getNameWordFirstUpper(table.getName()));
    }
}
