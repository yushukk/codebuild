package org.erik.code.genertator.code;

import org.apache.velocity.VelocityContext;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.utils.NameUtils;

import java.util.Map;

/**
 * Created by wandong.cwd on 2014/12/25.
 */
public class DaoImplCodeGenerate extends AbstractEasyCodeGenerator   {
    @Override
    public void generate(Table table, Task task, VelocityContext context, StringBuilder template) {

        context.put("packageName", task.getPackageName());

        context.put("fileName", NameUtils.getNameWordFirstUpper(table.getName()));

        String generatedShotClassName = task.getGeneratedShotClassName(table.getName());
        context.put("generatedShotClassName",generatedShotClassName);

        context.put("entityName",NameUtils.getNameWordFirstUpper(table.getName()));
        context.put("firstLowerEntityName", NameUtils.getNameWordFirstLower(table.getName()));

        //所有的任务配置信息
        Map<String, Task> tasks = EasyCodeContext.getAllTask();
        Task modelTask = tasks.get("model");
        Task daoTask = tasks.get("dao");

        //放入bean的小写开头的类名
        context.put("modelShortLowerClass",modelTask.getGeneratedShotLowerClassName(table.getName()));
        //放入bean的大写开头的类名
        context.put("modelShortClass",modelTask.getGeneratedShotClassName(table.getName()));
        //放入bean的全路径类名
        context.put("modelLongClass", modelTask.getGeneratedReferenceClassName(table.getName()));


        context.put("daoLongClass",daoTask.getGeneratedReferenceClassName(table.getName()));
        context.put("daoShortClass",daoTask.getGeneratedShotClassName(table.getName()));


    }
}
