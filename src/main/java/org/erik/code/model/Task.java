package org.erik.code.model;

import org.apache.commons.lang.StringUtils;
import org.erik.code.genertator.code.EasyCodeGenerator;
import org.erik.code.extra.EasyCodePlugin;
import org.erik.code.utils.NameUtils;

import java.util.Map;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class Task {
    public String name;
    public String clazz;


    public Map<String, Property> propertyMap;

    public Map<String, EasyCodePlugin> pluginMap;
    private EasyCodeGenerator classInstance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public EasyCodeGenerator getClassInstance() {
        return this.classInstance;
    }


    public Map<String, Property> getPropertyMap() {
        return propertyMap;
    }


    public Map<String, EasyCodePlugin> getPluginMap() {
        return pluginMap;
    }

    public void setPluginMap(Map<String, EasyCodePlugin> pluginMap) {
        this.pluginMap = pluginMap;
    }

    public void setProperties(Map<String, Property> properties) {
        this.propertyMap = properties;
    }

    public String getModuleDir() {
        return propertyMap.get("modelName").getValue();
    }

    public String getSrcDir() {
        return propertyMap.get("srcDir").getValue();
    }

    public boolean fileNameIsNull() {
        return propertyMap.get("fileName") == null;
    }

    public String getFileName() {
        return propertyMap.get("fileName").getValue();
    }

    public String fileNameReplaceBefore() {
        return propertyMap.get("fileNameReplace").getValue().split(",")[0];
    }

    public String fileNameReplaceWith() {
        return propertyMap.get("fileNameReplace").getValue().split(",")[1];
    }

    public boolean fileNameReplaceIsNull() {
        return propertyMap.get("fileNameReplace") == null
            || propertyMap.get("fileNameReplace").getValue().split(",").length < 2;
    }


    public String getGeneratedFileName(Table table) {
        String fileName = table.getClassName();
        if(!fileNameIsNull()){
            fileName = getFileName();
            String key = "\\$\\{classNameUnderScoreCase}";
            String key2 = "${classNameUnderScoreCase}";
            if(fileName.indexOf(key2) >= 0){
                fileName = fileName.replaceAll(key,NameUtils.toHump(table.getClassName()));
            }
        }
        if(!fileNameReplaceIsNull()){
            fileName = fileName.replaceAll(fileNameReplaceBefore(),fileNameReplaceWith());
        }
        return getPackageName().replace(".", "/") + "/" + getFullName(fileName) + propertyMap.get("suffix").getValue();
    }

    private String getFullName(String fileName) {
        return propertyMap.get("beginFix").getValue() + fileName + propertyMap.get("endFix").getValue();
    }

    public String getTemplate() {
        return propertyMap.get("template").getValue();
    }

    public String getPackageName() {
        return propertyMap.get("package").getValue();
    }

    public String getGeneratedShotClassName(String className) {
        return propertyMap.get("beginFix").getValue() + className + propertyMap.get("endFix").getValue();
    }

    public String getGeneratedShotLowerClassName(String className) {
        if (StringUtils.isEmpty(propertyMap.get("beginFix").getValue())) {
            return NameUtils.getFirstLowerName(className) + propertyMap.get("endFix").getValue();
        } else {
            return propertyMap.get("beginFix").getValue() + className + propertyMap.get("endFix").getValue();
        }
    }

    public String getGeneratedReferenceClassName(String name) {
        return getPackageName() + "." + getGeneratedShotClassName(name);
    }

    public void setClassInstance(EasyCodeGenerator classInstance) {
        this.classInstance = classInstance;
    }
}
