package com.test;

import java.io.*;

import oracle.jdbc.pool.OracleDataSource;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.util.*;
import java.sql.*; 
public class Assesment {  
	public static void main(String[] args) throws Exception{                
		Connection con =ConProvider.getConnection();
        PreparedStatement pstm = null;
        String sql = "INSERT INTO Assesments"
                        + "(Ass_Id, questionNumber, question,answerOne,answerTwo,answerThree,answerFour,correctAnswer) VALUES"
                        + "(?,?,?,?,?,?,?,?)";
        int count = 0;
        FileInputStream input = new FileInputStream  
         ("C:\\Users\\IBM_ADMIN\\Desktop\\xls_to_oracle1.xls");
        POIFSFileSystem fs = new POIFSFileSystem (input);
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        Iterator<?> rows = sheet.rowIterator(); 
        for(int i=1; i<=sheet.getLastRowNum(); i++){
            HSSFRow row = sheet.getRow(i);
           // HSSFRow row=null;
            count = count+1;
            String ques =  row.getCell(0).getStringCellValue();
            String optOne = row.getCell(1).getStringCellValue();
            String optTwo =  row.getCell(2).getStringCellValue();
            String optThree = row.getCell(3).getStringCellValue();
            String optFour =  row.getCell(4).getStringCellValue();
            int corrans = (int) row.getCell(5).getNumericCellValue();
            
            pstm = (PreparedStatement)con.prepareStatement(sql);
            pstm.setInt(1, 111);
            pstm.setInt(2, count);
            pstm.setString(3,ques);
            pstm.setString(4, optOne);
            pstm.setString(5, optTwo);
            pstm.setString(6,optThree);
            pstm.setString(7, optFour);
            pstm.setInt(8, corrans);
            
            pstm.executeUpdate(); 
            System.out.println("Import rows "+i);
        }
        input.close();
        System.out.println("Success import excel to mysql table");
		ConProvider.cleanUp(pstm, con);
		
	}
}
