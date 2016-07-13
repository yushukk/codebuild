package org.erik.code.main;


import org.erik.code.genertator.CBGenerator;
import org.erik.code.model.Table;

/**
 * Created by wandong.cwd on 2014/10/31.
 */
public class Geneter {

    public static void main(String[] args) {
        Table table = new Table();
        table.setName("tpp_account");
        table.setDesc("");//可不填
        table.setClassName("");//可不填
        table.setName("tpp_account");
        table.addTask("model");
        table.addTask("sqlmap");
        table.addTask("dao");
        CBGenerator.build().setConfigPath("D:\\buildConfig\\")
                .setProjectPath("D:\\code\\branches\\20160602_692039_patch_1\\visa\\")
                .setTable(table)
                .run();
    }
}
