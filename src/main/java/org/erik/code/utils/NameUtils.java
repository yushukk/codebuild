package org.erik.code.utils;

/**
 * Created by wandong.cwd on 2014/11/4.
 */
public class NameUtils {

    public static String getFirstLowerName(String shotClassName) {
        if (Character.isLowerCase(shotClassName.charAt(0)))
            return shotClassName;
        else
            return (new StringBuilder()).append(Character.toLowerCase(shotClassName.charAt(0))).append(shotClassName.substring(1)).toString();
    }

    public static String getFirstUpperName(String shotClassName) {
        if (Character.isLowerCase(shotClassName.charAt(0)))
            return shotClassName;
        else
            return (new StringBuilder()).append(Character.toUpperCase(shotClassName.charAt(0))).append(shotClassName.substring(1)).toString();
    }


    /**
     * 根据"_"拆分成单词，每个首字母大写
     *
     * @param name
     * @return
     */
    public static String getNameWordFirstUpper(String name) {

        String tmp = name.toLowerCase();

        //根据下划线将列名拆分
        String[] names = tmp.split("_");

        //返回的属性名
        StringBuilder propertyName = new StringBuilder();

        //有多个，将后面的首字母大写
        for (int i = 0; i < names.length; i++) {

            propertyName.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));

        }

        return propertyName.toString();

    }
    /**
     * 根据"_"拆分成单词，每个首字母小写
     *
     * @param name
     * @return
     */
    public static String getNameWordFirstLower(String name) {

        String tmp = name.toLowerCase();

        //根据下划线将列名拆分
        String[] names = tmp.split("_");

        //返回的属性名
        StringBuilder propertyName = new StringBuilder();

        //有多个，将后面的首字母大写
        for (int i = 0; i < names.length; i++) {

            if(i == 0){
                propertyName.append(names[i].substring(0, 1).toLowerCase() + names[i].substring(1));
            }else{
                propertyName.append(names[i].substring(0, 1).toUpperCase() + names[i].substring(1));
            }

        }

        return propertyName.toString();

    }

}
