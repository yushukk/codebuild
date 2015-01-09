package org.erik.code.genertator.project;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.erik.code.model.Project;
import org.erik.code.utils.LocalFileUtils;
import org.erik.code.utils.VelocityUtils;

import java.io.File;

/**
 * Created by wandong.cwd on 2014/11/4.
 */
public class DefaultProjectGenerator implements  ProjectGenerator{



    @Override
    public void doGenerate(Project project, VelocityContext context) {

        //遍历文件夹和文件
        String classPath = DefaultProjectGenerator.class.getResource("/").getPath();
        String projectPath = classPath + project.getProperties().get("templetaDir").getValue();
        String targetDir = project.getProperties().get("targetDir").getValue();
        File targetFileDir = new File(targetDir+project.getName());
        try {
            if (targetFileDir.exists()) {
                FileUtils.deleteDirectory(targetFileDir);
            }
            targetFileDir.mkdirs();
            File file = new File(projectPath);
            doFile(file,project);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doFile(File file,Project project){


        String targetPath = project.getProperties().get("targetDir").getValue();
        String tempPath = project.getProperties().get("templetaDir").getValue();
        if(file.isDirectory()){

            String relativePath = getRelativePath(file.getPath(),project.getName(),tempPath);

            if(!StringUtils.isBlank(relativePath)){
                File newFile = new File(targetPath+project.getName()+"/"+relativePath);
                newFile.mkdirs();
            }

            File[] files = file.listFiles();
            for(File f:files){
                doFile(f,project);
            }
        }else if(file.isFile()){

            //获取包含了所有常量的velocityContext
            VelocityContext context = VelocityUtils.getVelocityContext();
            context.put("project",project);


            String template = VelocityUtils.parseString(LocalFileUtils.getContent(file), context);

            LocalFileUtils.writeFile(targetPath+project.getName()+"/"+getRelativePath(file.getPath(),project.getName(),tempPath),template);
        }
    }


    private String getRelativePath(String path,String projectName,String tempPath){
        if(tempPath.endsWith("/")){
            tempPath = tempPath.substring(0,tempPath.length()-1);
        }
        try {
            return path.substring(path.lastIndexOf(tempPath)+tempPath.length()+1).
                    replaceAll("\\$\\{projectName\\}", projectName);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return "";
    }
}
