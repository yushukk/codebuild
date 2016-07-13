package org.erik.code.utils;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class VelocityUtils {
    static {
        Velocity.init();
    }

    private static VelocityContext velocityContext =  new VelocityContext();



    public static String parseTemplate(String file, Map<String, String> context) {

        //初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine ve = new VelocityEngine(properties);
        ve.init();
        Template t = ve.getTemplate(file,"UTF-8");

        StringWriter w = new StringWriter();

        for(String key:context.keySet()){
            velocityContext.put(key,context.get(key));
        }
        t.merge(velocityContext,w);

        return w.toString();
    }
    public static String parseTemplate(String file, VelocityContext context) {

        //初始化参数
        Properties properties=new Properties();
        //设置velocity资源加载方式为class
        properties.setProperty("resource.loader", "class");
        //设置velocity资源加载方式为file时的处理类
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine ve = new VelocityEngine(properties);
        ve.init();
        Template t = ve.getTemplate(file,"UTF-8");

        StringWriter w = new StringWriter();


        t.merge(context,w);

        return w.toString();
    }

    public static VelocityContext getVelocityContext() {
        return velocityContext = new VelocityContext();
    }

    public static String parseString(String orgi, VelocityContext context) {
        StringWriter w = new StringWriter();
        Velocity.evaluate(context,w,"AAA",orgi);
        return w.toString();
    }
}
