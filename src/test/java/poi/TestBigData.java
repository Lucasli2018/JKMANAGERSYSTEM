package poi;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-23
 */
public class TestBigData {

	@Test
	public void testHSSF() throws IOException{
		Workbook wb = new HSSFWorkbook();					//excel 2003
		Sheet sheet = wb.createSheet();
		int rowNo = 0;										//行号
		Row nRow = null;
		Cell nCell = null;

		for(int i=0;i<1000000;i++){
			nRow = sheet.createRow(rowNo++);
			for(int j=0;j<10;j++){
				nCell = nRow.createCell(j);
				nCell.setCellValue("传智播客!");
			}
		}
		
		OutputStream os = new FileOutputStream("c:\\test.xls");			//输出流
		wb.write(os);							//写入到文件中
		os.flush();								//清空缓存
		os.close();								//关闭		
	}
	
	@Test
	public void testXSSF() throws IOException{
		Workbook wb = new XSSFWorkbook();					//excel 2007
		Sheet sheet = wb.createSheet();
		int rowNo = 0;										//行号
		Row nRow = null;
		Cell nCell = null;
		
		for(int i=0;i<1000000;i++){
			nRow = sheet.createRow(rowNo++);
			for(int j=0;j<10;j++){
				nCell = nRow.createCell(j);
				nCell.setCellValue("传智播客!");
			}
			
			if(rowNo%100==0){
				System.out.println(rowNo);
			}
		}
		
		OutputStream os = new FileOutputStream("c:\\test.xlsx");			//输出流
		wb.write(os);							//写入到文件中
		os.flush();								//清空缓存
		os.close();								//关闭		
	}
}
