package com.laoxie.controller;

import com.laoxie.entity.StudentInfo;
import com.laoxie.entity.UserInfo;
import com.laoxie.service.StudentService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
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

        return studentService.getStudentInfo();
    }

    /**
     * 第一种方式，消耗时间18秒
     * @param response
     * @throws IOException
     */
    @GetMapping("getExcelByType1")
    private void getExcel1(HttpServletResponse response)throws IOException {

        long start = System.currentTimeMillis();

        String fileName="学生信息表.xls";
        response.setContentType("application/excel");
        response.setHeader("Content-disposition","attachment;filename=" +fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));

        List<StudentInfo> list=studentService.getStudentInfo();
        //声明一个工作簿
        HSSFWorkbook workbook=new HSSFWorkbook();
        //声明一个表格
        HSSFSheet sheet=workbook.createSheet("学生信息表");

        int rowNum=0;
        //添加标题
        String[] headers={"ID","姓名","年龄"};
        HSSFRow row=sheet.createRow(rowNum);
        for (int i =0; i <headers.length;i++){
            HSSFCell cell=row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        int index = 0;
        for(int i=0;i<list.size();i++){
            if((i+1)%65535==0){
                sheet = workbook.createSheet("学生信息表"+index);
                row = sheet.createRow(0);
                row.createCell(0).setCellValue("ID");
                row.createCell(1).setCellValue("姓名");
                row.createCell(2).setCellValue("年龄");
                index++;
            }
            row = sheet.createRow((i+1)-(index*65535));
            row.createCell(0).setCellValue(list.get(i).getStuId());
            row.createCell(1).setCellValue(list.get(i).getStuNm());
            row.createCell(2).setCellValue(list.get(i).getStuAge());
        }

        workbook.write(response.getOutputStream());

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start)/1000 + "秒");
    }

    /**
     * 150万条数据，花费10秒
     * @param response
     * @throws IOException
     */
    @GetMapping("getExcelByType2")
    private String getExcel2(HttpServletResponse response)throws IOException {
        long start = System.currentTimeMillis();

        String fileName="学生信息表.xlsx";
        response.setContentType("application/excel");
        response.setHeader("Content-disposition","attachment;filename=" +fileName +";filename*=utf-8''"+ URLEncoder.encode(fileName,"UTF-8"));

        List<StudentInfo> list=studentService.getStudentInfo();
        //声明一个工作簿
        SXSSFWorkbook workbook=new SXSSFWorkbook();
        //声明一个表格
        SXSSFSheet sheet= (SXSSFSheet) workbook.createSheet("学生信息表");

        int rowNum=0;
        //添加标题
        String[] headers={"ID","姓名","年龄"};
        SXSSFRow row= (SXSSFRow) sheet.createRow(rowNum);
        for (int i =0; i <headers.length;i++){
            SXSSFCell cell= (SXSSFCell) row.createCell(i);
            HSSFRichTextString text=new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        int index = 0;
        for(int i=0;i<list.size();i++){
            if((i+1)%1048575==0){
                sheet = (SXSSFSheet) workbook.createSheet("学生信息表"+index);
                row = (SXSSFRow) sheet.createRow(0);
                row.createCell(0).setCellValue("ID");
                row.createCell(1).setCellValue("姓名");
                row.createCell(2).setCellValue("年龄");
                index++;
            }
            row = (SXSSFRow) sheet.createRow((i+1)-(index*1048575));
            row.createCell(0).setCellValue(list.get(i).getStuId());
            row.createCell(1).setCellValue(list.get(i).getStuNm());
            row.createCell(2).setCellValue(list.get(i).getStuAge());
        }

        workbook.write(response.getOutputStream());

        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start)/1000 + "秒");

        return "111";
    }





}
