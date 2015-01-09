package org.erik.code.model;

import java.io.Serializable;

/**
* 用户
*
* User: wandong.cwd
* Date: Tue Nov 04 17:43:50 GMT+08:00 2014
*/
public class Person  implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 8941553968173528252L;

        /**  */
    private Long id;

        /** 姓名 */
    private String name;

        /** 年龄 */
    private Integer age;


    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Integer getage() {
        return age;
    }

    public void setage(Integer age) {
        this.age = age;
    }

}
