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
        table.setDesc("");//�ɲ���
        table.setClassName("");//�ɲ���
        table.addTask("model");
        table.addTask("sqlmap");
        table.addTask("dao");
        CBGenerator.build().setCreator("����").setConfigPath("D:\\buildConfigNew\\")
                .setProjectPath("D:\\code\\git\\godeyes")
                .setTable(table)
                .run();
    }
}
