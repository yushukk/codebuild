package org.erik.code.utils;

import org.erik.code.genertator.code.EasyCodeGenerator;
import org.erik.code.extra.EasyCodePlugin;
import org.erik.code.genertator.project.ProjectGenerator;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class ClassUtils {
    public static EasyCodeGenerator getGeneratorInstance(String clazz) {
        try {
            EasyCodeGenerator o = (EasyCodeGenerator) Class.forName(clazz).newInstance();
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static ProjectGenerator getProjectGeneratorInstance(String clazz) {
        try {
            ProjectGenerator o = (ProjectGenerator) Class.forName(clazz).newInstance();
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static EasyCodePlugin getPluginInstance(String clazz) {
        try {
            EasyCodePlugin o = (EasyCodePlugin) Class.forName(clazz).newInstance();
            return o;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
