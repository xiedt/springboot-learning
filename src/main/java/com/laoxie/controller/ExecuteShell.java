package com.laoxie.controller;

import java.io.IOException;

public class ExecuteShell {
    public static void main(String[] args) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(new String[]{"sh","/test/aa.sh"});
        int i = exec.waitFor();
        if(i!=0){
            System.out.println("程序未正常执行");
        }
    }
}
