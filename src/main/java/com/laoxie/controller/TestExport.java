package com.laoxie.controller;

import com.laoxie.entity.StudentInfo;
import com.laoxie.entity.UserInfo;
import com.laoxie.service.StudentService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 测试两种导出方式消耗时间，导出数据量为50万条
 */
@RestController
public class TestExport {

    @Resource
    StudentService studentService;

    @GetMapping("getStudents")
    private List<StudentInfo> getStudents(){

        //return studentService.list(null);
        return studentService.getStudentInfo();
    }

    @GetMapping("getExcelByType1")
    private void getExcel1(HttpServletResponse response)throws IOException {

        long start = System.currentTimeMillis();
        String fileName="业绩分配信息.xls";
        response.setContentType("application/excel");
        response.setHeader("Content-disposition","attachment;filename=" +fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));
        List<StudentInfo> list=studentService.getStudentInfo();
        HSSFWorkbook workbook=new HSSFWorkbook();
        HSSFSheet sheet=workbook.createSheet();

        int rowNum=0;
        //添加标题
        String[] headers={"ID","姓名","年龄"};
        HSSFRow row=sheet.createRow(rowNum);
        for (int i =0; i <headers.length;i++){
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        //添加数据
        for (StudentInfo topic:list) {
            rowNum++;
            HSSFRow row1=sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(topic.getStuId());
            row1.createCell(1).setCellValue(topic.getStuNm());
            row1.createCell(2).setCellValue(topic.getStuAge());
        }
        workbook.write(response.getOutputStream());

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start)/1000 + "秒");
    }

}
