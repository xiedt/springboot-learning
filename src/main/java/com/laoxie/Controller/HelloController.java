package com.laoxie.Controller;

import com.laoxie.entity.UserInfo;
import com.laoxie.mapper.UserMapper;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class HelloController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping(value ="hello",method = RequestMethod.GET)
    public String hello(){
        return "11111";
    }

    @RequestMapping(value ="getUser",method = RequestMethod.GET)
    public List<UserInfo>getUser(){
        List<UserInfo>list = userMapper.selectList(null);
        for(UserInfo user:list){
            System.out.println(user);
        }
        return list;
    }

    @RequestMapping(value = "getUserByname",method = RequestMethod.GET)
    public UserInfo getUserByName(@RequestParam String name){
        return userMapper.getUserByName(name);
    }


    @GetMapping("getExcel")
    private void getExcel(HttpServletResponse response)throws IOException {

        String fileName="业绩分配信息.xls";
        response.setContentType("application/excel");
        response.setHeader("Content-disposition","attachment;filename=" +fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
        List<UserInfo> list=userMapper.selectList(null);
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet();

        int rowNum=0;
        //添加标题
        String[] headers={"姓名","年龄"};
        HSSFRow row=sheet.createRow(rowNum);
        for (int i =0; i <headers.length;i++){
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //添加数据
        for (UserInfo topic:list) {
            rowNum++;
            HSSFRow row1=sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(topic.getUserName());
            row1.createCell(1).setCellValue(topic.getAge());
        }
        workbook.write(response.getOutputStream());
    }

}
