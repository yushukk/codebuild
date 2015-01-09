package org.erik.code.model;

import org.erik.code.genertator.project.ProjectGenerator;

import java.util.Map;

/**
 * Created by wandong.cwd on 2014/11/4.
 */
public class Project {

    private String name;

    private String clazz;


    private Map<String, Property> properties;

    private ProjectGenerator projectGenerator;


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

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }

    public void setClassInstance(ProjectGenerator generator) {
        this.projectGenerator = generator;
    }

    public ProjectGenerator getClassInstance() {
        return this.projectGenerator;
    }

    public Map<String, Property> getProperties() {
        return properties;
    }

    public String getGroupId(){
        return properties.get("groupId").getValue();
    }

    public String getArtifactId(){
        return properties.get("artifactId").getValue();
    }
}
