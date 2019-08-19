package poi;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @Description:  poi实现输出信息到excel文件
 * @Author: nutony
 * @Date: 2013-12-14
 */
public class Test2XFFS {
	public static void main(String[] args) throws Exception {
		Test2XFFS tm = new Test2XFFS();
		tm.print();
	}
	
	
	/*
	 * dom4j-1.6.1.jar
	 * poi-3.9-20121203.jar
	 * poi-ooxml-3.9-20121203.jar
	 * poi-ooxml-schemas-3.9-20121203.jar
	 * stax-api-1.0.1.jar
	 * xmlbeans-2.3.0.jar
	 */
	public void print() throws Exception{
		String xlsFile = "c:/poiXFFS.xlsx";
		
		//STEP 1:打开excel文件
		XSSFWorkbook wb = new XSSFWorkbook();									//创建excel文件
		//XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsFile));		//打开已存在的excel文件

		//STEP 2:打开当前工作簿
		XSSFSheet sheet = wb.createSheet("我的第一个工作簿");		//建立新的sheet对象
		//XSSFSheet sheet = wb.getSheetAt(0);						//选择第一个工作簿
		//wb.setSheetName(0, "我的第一个工作簿");					//设置工作簿的名称

		XSSFRow nRow = null;
		XSSFCell nCell   = null;
		
		//STEP 3:创建行对象
		nRow = sheet.createRow((short)1);						//第2行

		//STEP 4:指定列 创建单元格对象
		nCell = nRow.createCell((short)(2));					//第3列
		
		//STEP 5:指定列 创建单元格对象
		nCell.setCellValue("我是单元格传智播客");
		
		//STEP 6:设置样式
		nCell.setCellStyle(leftStyle(wb));

		//STEP 7:关闭保存excel文件
		FileOutputStream fOut = new FileOutputStream(xlsFile);
		wb.write(fOut);
		fOut.flush();
		fOut.close();

	}	
	
	
	
	//设置单元格样式
	private XSSFCellStyle leftStyle(XSSFWorkbook wb){
		XSSFCellStyle curStyle = wb.createCellStyle();
		XSSFFont curFont = wb.createFont();								//设置字体
		//curFont.setFontName("Times New Roman");						//设置英文字体
		curFont.setFontName("微软雅黑");								//设置英文字体
		curFont.setCharSet(XSSFFont.DEFAULT_CHARSET);					//设置中文字体，那必须还要再对单元格进行编码设置
		curFont.setFontHeightInPoints((short)10);						//字体大小
		curStyle.setFont(curFont);
		
		curStyle.setBorderTop(XSSFCellStyle.BORDER_THICK);				//粗实线
		curStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);			//实线
		curStyle.setBorderLeft(XSSFCellStyle.BORDER_MEDIUM);			//比较粗实线
		curStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);				//实线
		
		curStyle.setWrapText(true);  									//换行   
		curStyle.setAlignment(XSSFCellStyle.ALIGN_RIGHT);				//横向具右对齐
		curStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);	//单元格垂直居中
		
		return curStyle;
	}
	
	
}
