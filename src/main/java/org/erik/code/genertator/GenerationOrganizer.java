package org.erik.code.genertator;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.erik.code.context.EasyCodeContext;
import org.erik.code.genertator.code.CommonCodeGenerator;
import org.erik.code.genertator.code.EasyCodeGenerator;
import org.erik.code.genertator.project.ProjectGenerator;
import org.erik.code.model.Column;
import org.erik.code.model.Project;
import org.erik.code.model.Table;
import org.erik.code.model.Task;
import org.erik.code.parser.XmlParser;
import org.erik.code.provider.DatabaseProvider;
import org.erik.code.provider.DatabaseProviderFactory;
import org.erik.code.utils.DBUtils;
import org.erik.code.utils.VelocityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class GenerationOrganizer {

    /** 日志对象 */
    private static final Logger LOG = LoggerFactory.getLogger(GenerationOrganizer.class);

    public GenerationOrganizer(String config) {
        XmlParser xmlParser = new XmlParser();
        xmlParser.parseConfigXml(config);
    }

    /**
     * 代码生成
     */
    public void codeGenerate() {

        //所有表配置信息
        Map<String, Table> tableMap = EasyCodeContext.getAllTable();

        //所有的任务配置信息
        Map<String, Task> tasks = EasyCodeContext.getAllTask();

        DBUtils.createConnection();
        for (Map.Entry<String, Table> entry : tableMap.entrySet()) {

            Table table = entry.getValue();
            if (CollectionUtils.isEmpty(table.getColumns())) {
                DatabaseProvider provider = DatabaseProviderFactory.buildProvider();
                List<Column> columnList = provider.getTableMetaData(table.getName());
                table.setColumns(columnList);
                if(StringUtils.isBlank(table.getDesc())){
                    table.setDesc(provider.getTableDesc(table.getName()));
                }
            }

            //表的任务
            Queue<String> tableTasks = table.getTasks();

            String tableTask;
            while ((tableTask = tableTasks.poll()) != null) {
                Task task = tasks.get(tableTask);
                if (task == null) {
                    LOG.info("不存在配置任务{}", tableTask);
                    continue;
                }
                CommonCodeGenerator codeGenerator = new CommonCodeGenerator(table,task);
                codeGenerator.doGenerate();
            }
        }
        DBUtils.closeConnection();
    }
}
