package org.erik.code.model;

import java.io.Serializable;
import java.util.Date;

/**
* 基础表
*
* User: wandong.cwd
* Date: Thu Dec 25 15:14:54 GMT+08:00 2014
*/
public class JobsBaseData  implements Serializable{

    /** serialVersionUID */
    private static final long serialVersionUID = 4159867262214123877L;

        /**  */
    private Long id;

        /**  */
    private Date gmt_create;

        /**  */
    private Date gmt_modified;

        /**  */
    private Long application_id;

        /** 基础数据名称(中文) */
    private String name;

        /** 基础数据文件名(英文) */
    private String file_name;

        /**  */
    private String ext_info;

        /**  */
    private String interface_name;

        /**  */
    private Long user_create;

        /**  */
    private Long user_modified;


    public Long getid() {
        return id;
    }

    public void setid(Long id) {
        this.id = id;
    }

    public Date getgmt_create() {
        return gmt_create;
    }

    public void setgmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public Date getgmt_modified() {
        return gmt_modified;
    }

    public void setgmt_modified(Date gmt_modified) {
        this.gmt_modified = gmt_modified;
    }

    public Long getapplication_id() {
        return application_id;
    }

    public void setapplication_id(Long application_id) {
        this.application_id = application_id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getfile_name() {
        return file_name;
    }

    public void setfile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getext_info() {
        return ext_info;
    }

    public void setext_info(String ext_info) {
        this.ext_info = ext_info;
    }

    public String getinterface_name() {
        return interface_name;
    }

    public void setinterface_name(String interface_name) {
        this.interface_name = interface_name;
    }

    public Long getuser_create() {
        return user_create;
    }

    public void setuser_create(Long user_create) {
        this.user_create = user_create;
    }

    public Long getuser_modified() {
        return user_modified;
    }

    public void setuser_modified(Long user_modified) {
        this.user_modified = user_modified;
    }

}
