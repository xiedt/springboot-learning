package com.laoxie.utils;

import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Vector;

/**
 * 执行shell命令
 */
@Slf4j
public class ExecuteShellUtil {

    private Vector<String> stdout;
    // 会话session
    Session session;
    //输入IP、端口、用户名和密码，连接远程服务器
    public ExecuteShellUtil(final String ipAddress, final String username, final String password, int port) {
        try {
            JSch jsch = new JSch();
            session = jsch.getSession(username, ipAddress, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect(100000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int execute(final String command) {
        int returnCode = 0;
        ChannelShell channel = null;
        PrintWriter printWriter = null;
        BufferedReader input = null;
        stdout = new Vector<String>();
        try {
            channel = (ChannelShell) session.openChannel("shell");
            channel.connect();
            input = new BufferedReader(new InputStreamReader(channel.getInputStream()));
            printWriter = new PrintWriter(channel.getOutputStream());
            printWriter.println(command);
            printWriter.println("exit");
            printWriter.flush();
            String line;
            while ((line = input.readLine()) != null) {
                stdout.add(line);
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
        return returnCode;
    }

    // 断开连接
    public void close(){
        if (session != null) {
            session.disconnect();
        }
    }
    // 执行命令获取执行结果
    public String executeForResult(String command) {
        execute(command);
        StringBuilder sb = new StringBuilder();
        for (String str : stdout) {
            sb.append(str);
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        ExecuteShellUtil executeShellUtil = new ExecuteShellUtil("XXX","abc","XXX",22);
//        // 执行 ls /opt/命令
//        String result = executeShellUtil.executeForResult("ls /opt/");
//        System.out.println(result);
//        executeShellUtil.close();
//    }
}