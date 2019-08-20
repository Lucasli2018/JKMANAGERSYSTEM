package cn.itcast.jk.controller.cargo.contract;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.service.OutProductService;
import cn.itcast.jk.vo.OutProduct;
import cn.itcast.util.DownloadUtil;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-17
 */
@Controller
public class OutProductController {
	@Autowired
	OutProductService outProductService;
	
	//转向出货表页面
	@RequestMapping("/cargo/outproduct/toedit.action")
	public String toedit(){
		return "cargo/outproduct/jOutProduct.jsp";
	}
	
	//打印
	@RequestMapping("/cargo/outproduct/outProductPrint.action")
	public void print(String inputDate, HttpServletResponse response) throws FileNotFoundException, IOException, ParseException{
		/*
		 * 操作步骤：
		 * 1、获取数据
		 * 2、POI写数据到文件
		 */
		List<OutProduct> oList = outProductService.findOutProduct(inputDate+"%");
		
		Workbook wb = new HSSFWorkbook(new FileInputStream(new File("c:\\tFACTORY.xls")));				//打开模板文件
		Sheet sheet = wb.getSheetAt(0);						//打开第一个工作表
		Row nRow = null;
		Cell nCell = null;
		int rowNo = 2;										//行号
		int colNo = 1;										//列号
		
		//处理标题
		nRow = sheet.getRow(0);								//获得行对象
		nCell = nRow.getCell(1);							//获得单元格对象
		nCell.setCellValue(inputDate.replaceFirst("-0", "-").replaceFirst("-", "年")+"月份出货表");			//yyyy-MM 2010-08
		
		//获取模板文件中的样式
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(1);
		CellStyle customNameStyle = nCell.getCellStyle();			//获取客户名称样式
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(2);
		CellStyle contractNoStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(3);
		CellStyle productNoStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(4);
		CellStyle cnumberStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(5);
		CellStyle factoryStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(6);
		CellStyle extStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(7);
		CellStyle dateStyle = nCell.getCellStyle();
		
		nRow = sheet.getRow(2);
		nCell = nRow.getCell(9);
		CellStyle tradeTermsStyle = nCell.getCellStyle();
		
		for(int i=0;i<oList.size();i++){
			colNo = 1;
			OutProduct op = oList.get(i); 					//获取每个出货表对象
			
			nRow = sheet.createRow(rowNo++);				//创建行
			nRow.setHeightInPoints(24);						//行高
			
			nCell = nRow.createCell(colNo++);				//创建单元格
			nCell.setCellValue(op.getCustomName());
			nCell.setCellStyle(customNameStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getContractNo());
			nCell.setCellStyle(contractNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getProductNo());
			nCell.setCellStyle(productNoStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getCnumber());
			nCell.setCellStyle(cnumberStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getFactoryName());
			nCell.setCellStyle(factoryStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue("附件");

			List<String> extNameList = outProductService.getExtName(op.getContractProductId());
			String _extName = "";
			if(extNameList!=null&&extNameList.size()>0){
				for(String extName : extNameList){
					_extName += extName + "\n";				//换行符
				}
				_extName = _extName.substring(0,_extName.length()-1);		//去掉最后一个字符
			}else{
				_extName = "无";
			}
			nCell.setCellValue(_extName);
			nCell.setCellStyle(extStyle);
			
			nCell = nRow.createCell(colNo++);
			//nCell.setCellValue(UtilFuns.dateTimeFormat(op.getDeliveryPeriod()));		//利用工具类转类型，同时进行格式化
			nCell.setCellValue(op.getDeliveryPeriod());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			//nCell.setCellValue(UtilFuns.dateTimeFormat(op.getShipTime()));
			nCell.setCellValue(op.getShipTime());
			nCell.setCellStyle(dateStyle);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(op.getTradeTerms());
			nCell.setCellStyle(tradeTermsStyle);
		}
		
		
		DownloadUtil du = new DownloadUtil();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			//生成流对象
		wb.write(byteArrayOutputStream);
		du.download(byteArrayOutputStream, response, "出货表.xls");		//弹出下载框，用户就可以直接下载
	}
	
}
