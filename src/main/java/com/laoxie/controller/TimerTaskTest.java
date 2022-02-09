package com.laoxie.controller;


import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class TimerTaskTest {
    private String path = "/test/test01";
    //3.添加定时任务
    //@Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：10秒
    @Scheduled(fixedRate=1000*60)
    private void configureTasks() throws Exception {
        //写个定时任务删除路径下指定名字的文件
        //获取七天前的日期
        String tmp = getPastDate(7,new Date());
        //获取路径下的所有文件名
        File fileDir = new File(path);
        if(!fileDir.exists()&&!fileDir.isDirectory()){
            return;
        }
        //如果路径存在
            File[] array = fileDir.listFiles();
            if(array.length!=0)
                for(int i=0;i<array.length;i++){
                    //删除七天前的文件
                    deleteFile(tmp);
                }
    }
    public static void deleteFile(String fileName)throws Exception{
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(new String[]{"rm -rf","/test/test01/"+"aa_"+fileName+".txt"});
        exec.waitFor();
    }

    //获取七天前的日期
    public static String getPastDate(int past,Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - past);
        Date today = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String result = sdf.format(today);
        return result;
    }
}
