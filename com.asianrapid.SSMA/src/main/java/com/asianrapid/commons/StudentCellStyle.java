package com.asianrapid.commons;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import com.asianrapid.pojo.Student;


@Component
public class StudentCellStyle {
	
	public static void exportExcel(FileOutputStream fos, List<Student> list){
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
		HSSFSheet sheet = hssfWorkbook.createSheet("测试");
		
		HSSFCellStyle cellStyle = initColmnStyle(hssfWorkbook);
//		 设置列的宽度
		sheet.setColumnWidth(1, 4000);
		try {
			HSSFRow row = null;
			HSSFCell cell = null;

			for (int i = 0; i < 3; i++) {
				row = sheet.createRow(i);
				for (int j = 0; j < 15; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(cellStyle);
				}
			}
			
			cell = sheet.getRow(0).createCell(0);
			cell.setCellValue(new HSSFRichTextString("id"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 0, 0));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(1);
			cell.setCellValue(new HSSFRichTextString("学生姓名"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 1, 1));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(2);
			cell.setCellValue(new HSSFRichTextString("年龄"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 2, 2));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(3);
			cell.setCellValue(new HSSFRichTextString("毕业院校"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 3, 3));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(4);
			cell.setCellValue(new HSSFRichTextString("成绩"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 4, 4));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(5);
			cell.setCellValue(new HSSFRichTextString("备注"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 5, 8));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(9);
			cell.setCellValue(new HSSFRichTextString("创建时间"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 9, 11));
			cell.setCellStyle(cellStyle);
			
			cell = sheet.getRow(0).createCell(12);
			cell.setCellValue(new HSSFRichTextString("更新时间"));
			sheet.addMergedRegion(new CellRangeAddress(0, 2, 12, 14));
			cell.setCellStyle(cellStyle);
			
			setValue(sheet, list, hssfWorkbook);
			
			hssfWorkbook.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static HSSFCellStyle initColmnStyle(HSSFWorkbook wb){
		HSSFCellStyle cellStyle = wb.createCellStyle();
		//字体样式
        HSSFFont columnHeadFont = wb.createFont();  
        columnHeadFont.setFontName("宋体");  
        columnHeadFont.setFontHeightInPoints((short) 9);  
        cellStyle.setFont(columnHeadFont);  
        
		cellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFPalette palette = wb.getCustomPalette();
		palette.setColorAtIndex(IndexedColors.GREEN.getIndex(), (byte)0, (byte)255, (byte)0);
		return cellStyle;
	}
	
	private static HSSFCellStyle initValueColmnStyle(HSSFWorkbook wb){
		HSSFCellStyle cellStyle = wb.createCellStyle();
		//字体样式
        HSSFFont columnHeadFont = wb.createFont();  
        columnHeadFont.setFontName("宋体");  
        columnHeadFont.setFontHeightInPoints((short) 9);  
        cellStyle.setFont(columnHeadFont);  
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		return cellStyle;
	}
	
	private static void setValue(HSSFSheet sheet, List<Student> list, HSSFWorkbook wb){
		for (int i = 0; i < list.size(); i++) {
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(i+3, i+3, 5, 8));
			sheet.addMergedRegion(new CellRangeAddress(i+3, i+3, 9, 11));
			sheet.addMergedRegion(new CellRangeAddress(i+3, i+3, 12, 14));
			
			HSSFRow row = sheet.createRow(i+3);
			HSSFCell cell = null;
			
			for (int j = 0; j < 15; j++) {
				cell = row.createCell(j);
				cell.setCellStyle(initValueColmnStyle(wb));
			}
			
			row.getCell(0).setCellValue(list.get(i).getStuId());
			row.getCell(1).setCellValue(list.get(i).getName());
			row.getCell(2).setCellValue(list.get(i).getAge());
			row.getCell(3).setCellValue(list.get(i).getSchool());
			row.getCell(4).setCellValue(list.get(i).getScore());
			row.getCell(5).setCellValue(list.get(i).getComment());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			row.getCell(9).setCellValue(sdf.format(list.get(i).getCreated()));
			row.getCell(12).setCellValue(sdf.format(list.get(i).getUpdated()));
		}
	}

}
