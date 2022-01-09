package com.laoxie.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.laoxie.entity.UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
/**
 * 将内存中的数据写入到SpringBoot根目录下面
 *
 * @author 谦谦公子爱编程
 *
 */
public class WriteDBToResourceUtil {

    static byte b1[]={0x03};
    static byte b2[]={0x0a};
    static String separator=new String(b1);
    static String newlineCharacter=new String(b2);
    /**
     * 写到资源文件
     *
     * @param students
     * @throws IOException
     */
    public static void writeToResource(List<UserInfo> students) throws IOException {
        String basePath = "src/main/resources/static/";
        System.out.println(basePath);
        String studentResourcePath = new File(basePath, "users.dat").getAbsolutePath();
        // 保证目录一定存在
        ensureDirectory(studentResourcePath);
        System.out.println("studentResourcePath = " + studentResourcePath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(studentResourcePath)));
        for (UserInfo student : students) {
            StringBuffer buffer = new StringBuffer();
            buffer.append(student.getUserName());
            buffer.append(separator);
            buffer.append(student.getAge());
            buffer.append(newlineCharacter);
            //writer.write(buffer.toString());
            writer.write(buffer.toString());
        }
        writer.flush();
        writer.close();
    }

    public static String castToGbk(String ss){
        return null;
    }
    /**
     * 保证拷贝的文件的目录一定要存在
     *
     * @param filePath
     *            文件路径
     */
    public static void ensureDirectory(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            return;
        }
        filePath = replaceSeparator(filePath);
        if (filePath.indexOf("/") != -1) {
            filePath = filePath.substring(0, filePath.lastIndexOf("/"));
            File file = new File(filePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
    }
    /**
     * 将符号“\\”和“\”替换成“/”,有时候便于统一的处理路径的分隔符,避免同一个路径出现两个或三种不同的分隔符
     *
     * @param str
     * @return
     */
    public static String replaceSeparator(String str) {
        return str.replace("\\", "/").replace("\\\\", "/");
    }
    /**
     * 获取项目根路径
     *
     * @return
     */
    private static String getResourceBasePath() {
        // 获取跟目录
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }
        String pathStr = path.getAbsolutePath();
        // 如果是在eclipse中运行，则和target同级目录,如果是jar部署到服务器，则默认和jar包同级
        pathStr = pathStr.replace("\\target\\classes", "");
        return pathStr;
    }
}