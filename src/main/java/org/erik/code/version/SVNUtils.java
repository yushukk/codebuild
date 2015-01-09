package org.erik.code.version;

import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.*;

import java.io.File;

/**
 * Created by wandong.cwd on 2015/1/9.
 */
public class SVNUtils {

    //	声明SVN客户端管理类
    private static SVNClientManager ourClientManager;


    public static boolean commit() throws SVNException {
        //初始化支持svn://协议的库。 必须先执行此操作。
        SVNRepositoryFactoryImpl.setup();
        //相关变量赋值
        SVNURL repositoryURL = null;
        try {
            repositoryURL = SVNURL.parseURIEncoded("http://svn.develop.taobao.net/repos/freemall/branches/20150106_163695_crm_1");
        } catch (SVNException e) {
            //
        }
        String name = "wandong.cwd";
        String password = "63580993xX";
        ISVNOptions options = SVNWCUtil.createDefaultOptions(true);
        //实例化客户端管理类
        ourClientManager = SVNClientManager.newInstance(
                (DefaultSVNOptions) options, name, password);
        //要提交的文件
        File commitFile=new File("D:\\code\\branches\\20150106_163695_crm_1\\freemall\\freemall-dao\\src\\main\\resources\\sql\\member_extend_sqlmap_mapping.xml");
        //获取此文件的状态（是文件做了修改还是新添加的文件？）
        SVNStatus status=ourClientManager.getStatusClient().doStatus(commitFile, true);
        //如果此文件是新增加的则先把此文件添加到版本库，然后提交。
        if(status.getContentsStatus()== SVNStatusType.STATUS_UNVERSIONED){
            //把此文件增加到版本库中
            ourClientManager.getWCClient().doAdd(commitFile, false, false, false, SVNDepth.INFINITY,false,false);
            //提交此文件
            ourClientManager.getCommitClient().doCommit(
                    new File[] { commitFile }, true, "",null,null,true, false, SVNDepth.INFINITY);
            System.out.println("add");
        }
        //如果此文件不是新增加的，直接提交。
        else{
//			ourClientManager.getCommitClient().doCommit(
//					new File[] { commitFile }, true, "", false, true);
            ourClientManager.getCommitClient().doCommit(
                    new File[] { commitFile }, true, "",null,null,true, false, SVNDepth.INFINITY);
            System.out.println("commit");
        }
        System.out.println(status.getContentsStatus());

        return false;
    }

    public static void main(String[] args) throws SVNException {
        commit();
    }
}
