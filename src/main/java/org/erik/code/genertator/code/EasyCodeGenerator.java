package org.erik.code.genertator.code;

import org.apache.velocity.VelocityContext;
import org.erik.code.model.Table;
import org.erik.code.model.Task;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public interface EasyCodeGenerator {
    /**
     * 代码生成方法
     * @param table the table
     * @param task the task
     * @param context the context
     */
    public void doGenerate(Table table, Task task, VelocityContext context);

}
