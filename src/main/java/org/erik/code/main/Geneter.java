package org.erik.code.main;


import org.erik.code.genertator.CBGenerator;
import org.erik.code.model.Table;

/**
 * Created by wandong.cwd on 2014/10/31.
 */
public class Geneter {

    public static void main(String[] args) {
        Table table = new Table();
        table.setName("business");
        table.setDesc("");//可不填
        table.setClassName("");//可不填
        table.addTask("model");
        table.addTask("sqlmap");
        table.addTask("dao");
        CBGenerator.build().setCreator("尘东").setConfigPath("D:\\buildConfigNew\\")
                .setProjectPath("D:\\code\\git\\godeyes")
                .setTable(table)
                .run();
    }
}
