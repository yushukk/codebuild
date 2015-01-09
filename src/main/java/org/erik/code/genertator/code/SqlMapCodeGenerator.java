package org.erik.code.genertator.code;

import org.apache.velocity.VelocityContext;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.model.Column;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.utils.NameUtils;

import java.util.Map;

/**
 * Created by wandong.cwd on 2015/1/9.
 */
public class SqlMapCodeGenerator extends AbstractEasyCodeGenerator  {
    @Override
    public void generate(Table table, Task task, VelocityContext context, StringBuilder template) {

        //所有的任务配置信息
        Map<String, Task> tasks = EasyCodeContext.getAllTask();
        Task modelTask = tasks.get("model");
        //放入bean的全路径类名
        context.put("modelLongClass", modelTask.getGeneratedReferenceClassName(table.getName()));
        //放入bean的小写开头的类名
        context.put("modelShortClass",modelTask.getGeneratedShotLowerClassName(table.getName()));

        //放入转化好的表名
        context.put("lowerTable", NameUtils.getNameWordFirstLower(table.getName()));

        context.put("table", table);
        context.put("task", task);

        StringBuilder columns = new StringBuilder();

        StringBuilder properties = new StringBuilder();


        for(Column column:table.columns){
            columns.append(column.getName());
            columns.append(",");
            properties.append("#" + column.getCamelName() + "#");
            properties.append(",");
        }

        context.put("columns", columns.substring(0, columns.length() - 1));
        context.put("properties", properties.substring(0, properties.length() - 1));

        context.put("fileName",table.getName());

    }
}
