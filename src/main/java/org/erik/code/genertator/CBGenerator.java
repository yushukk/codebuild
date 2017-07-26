package org.erik.code.genertator;

import org.erik.code.context.EasyCodeContext;
import org.erik.code.model.Table;

/**
 * Created by 尘东 on 2016/7/13.
 */
public class CBGenerator {


    private CBGenerator(){
        super();
    }

    public static CBGenerator build(){
        return new CBGenerator();
    }

    public CBGenerator setConfigPath(String configPath){
        EasyCodeContext.getAllConstant().put("configPath",configPath);
        return this;
    }

    public CBGenerator setCreator(String creator){
        EasyCodeContext.getAllConstant().put("creator",creator);
        return this;
    }

    public CBGenerator setProjectPath(String projectPath){
        EasyCodeContext.getAllConstant().put("targetDir",projectPath);
        return this;
    }

    public CBGenerator setTable(Table table){
        EasyCodeContext.addTable(table.getName(),table);
        return this;
    }

    public void run(){
        GenerationOrganizer generationOrganizer = new GenerationOrganizer("config.xml");
        generationOrganizer.codeGenerate();
    }
}
