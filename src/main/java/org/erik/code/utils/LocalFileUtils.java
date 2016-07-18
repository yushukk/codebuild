package org.erik.code.utils;

import org.erik.code.context.EasyCodeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wandong.cwd on 2014/11/3.
 */
public class LocalFileUtils {

    /**
     * 日志对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(LocalFileUtils.class);

    /** 存放模板文件，读取到的字符串的形式 */
    private static Map<String, String> fileMap = new HashMap<String, String>();

    /**
     * 判断文件是否存在
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean isFileExists(String filePath) {

        File file = new File(filePath);

        if (file.exists()) {
            return true;
        }

        return false;

    }

    public static synchronized String getContent(File file){
        if(file==null||!file.exists()||file.isDirectory()){
            return "";
        }

        try {
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bfReader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";

    }

    public static synchronized String getTemplate(String template) {
        String filePath = null;
        try {

            if (fileMap.get(template) != null) {
                return fileMap.get(template);
            }

            String encoding = "GBK";
            if (EasyCodeContext.getEncoding() != null && !"".equals(EasyCodeContext.getEncoding())) {
                encoding = EasyCodeContext.getEncoding();
            }

            BufferedReader bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(template),
                    encoding));

            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = bfReader.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            fileMap.put(template, new String(sb.toString().getBytes(encoding), encoding));

        } catch (IOException e) {
            LOG.error("读取模板文件失败[vm=" + filePath + "]", e);
        }

        return fileMap.get(template);
    }

    public static void writeFile(String filePath, String template){
        writeFile(filePath,template,false);
    }

    public static void writeFile(String filePath, String template,boolean isXml) {
        try {
            File targetFile = new File(filePath);
            File targetDir = new File(filePath.substring(0, filePath.lastIndexOf("/")));
            if (!targetDir.exists())
                targetDir.mkdirs();
            OutputStreamWriter targetFileWriter = new OutputStreamWriter(new FileOutputStream(targetFile),
                    isXml ? "UTF-8" : EasyCodeContext.getTargetEncoding());
            targetFileWriter.write(template);
            targetFileWriter.close();

            LOG.info("generate file:" + targetFile.getAbsoluteFile());
        } catch (IOException e) {
            LOG.error("生成代码文件失败", e);
        }

    }
}
