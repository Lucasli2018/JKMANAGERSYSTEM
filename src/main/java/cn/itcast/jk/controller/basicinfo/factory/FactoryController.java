package cn.itcast.jk.controller.basicinfo.factory;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Factory;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.util.DownloadUtil;

/**
 * @Description: 生产厂家业务实现，CRUD、打印操作
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Controller
public class FactoryController extends BaseController {
	@Autowired
	FactoryService factoryService;
	
	//列表
	@RequestMapping("/basicinfo/factory/list.action")
	public String list(Factory factory, Model model){
		List<Factory> dataList = factoryService.find(factory);
		model.addAttribute("dataList", dataList);			//传递结果集到页面
		
		return "/basicinfo/factory/jFactoryList.jsp";			//逻辑名
	}
	
	//转向新增页面
	@RequestMapping("/basicinfo/factory/tocreate.action")
	public String tocreate(){
		return "/basicinfo/factory/jFactoryCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/basicinfo/factory/insert.action")
	public String insert(Factory factory){
		factoryService.insert(factory);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/basicinfo/factory/toupdate.action")
	public String toupdate(String id, Model model){
		//准备修改的对象
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);						//将对象传递个页面
		
		return "/basicinfo/factory/jFactoryUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/basicinfo/factory/update.action")
	public String update(Factory factory){
		factoryService.update(factory);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//删除一条
	@RequestMapping("/basicinfo/factory/delete.action")
	public String delete(String id){
		factoryService.delete(id);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//批量删除
	@RequestMapping("/basicinfo/factory/deletebatch.action")
	public String deletebatch(String[] id){
		factoryService.delete(id);
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//转向查看页面
	@RequestMapping("/basicinfo/factory/toview.action")
	public String toview(String id, Model model){
		Factory obj = factoryService.get(id);
		model.addAttribute("obj", obj);
		
		return "/basicinfo/factory/jFactoryView.jsp";
	}
	
	//批量进行启用
	@RequestMapping("/basicinfo/factory/start.action")
	public String start(String id){
		this.changeState(1, id.split(","));						//对多个ID进行解串
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//批量进行停用
	@RequestMapping("/basicinfo/factory/stop.action")
	public String stop(String id){
		this.changeState(0, id.split(","));						//对多个ID进行解串
		return "redirect:/basicinfo/factory/list.action";
	}
	
	//修改状态 	//0停用1启用 
	private void changeState(Integer stateValue, String[] ids){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("state", stateValue);					
		map.put("ids", ids);
		
		factoryService.changeState(map);
	}
	
	//打印
	@RequestMapping("/basicinfo/factory/print.action")
	public void print(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException{
		/*
		 * 操作步骤：
		 * 1、获取数据
		 * 2、将数据写入到excel文件中
		 */
		
		//设置查询条件
		Factory factory = new Factory();
		factory.setState(1);
		
		List<Factory> dataList = factoryService.find(factory);
		String[] title = new String[]{"厂家全称","缩写","联系人","电话","手机","传真","备注"};
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
		int rowNo = 0;										//行号
		int colNo = 0;										//列号
		Row nRow = null;
		Cell nCell = null;
		
		sheet.setColumnWidth(0, 30*256);					//设置列宽
		
		nRow = sheet.createRow(rowNo);
		nRow.setHeightInPoints(40);
		sheet.addMergedRegion(new CellRangeAddress(rowNo, rowNo, 0, 6));			//合并单元格，新对象，不会覆盖合并的那些单元格，只是遮住
		rowNo++;
		
		nCell = nRow.createCell(0);
		nCell.setCellValue("生产厂家通讯录");
		nCell.setCellStyle(this.bigTilteStyle(wb));
		
		
		//写标题
		nRow = sheet.createRow(rowNo++);
		nRow.setHeightInPoints(28);							//设置行高
		
				
		for(int i=0;i<title.length;i++){
			nCell = nRow.createCell(i);
			nCell.setCellValue(title[i]);
			nCell.setCellStyle(this.titleStyle(wb));		//绑定样式
		}
		
		//写数据
		for(int j=0;j<dataList.size();j++){
			colNo = 0;										//初始化
			Factory f = dataList.get(j);					//获取到每条厂家记录
			
			nRow = sheet.createRow(rowNo++);
			nRow.setHeightInPoints(21);
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFullName());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFactoryName());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getContractor());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getPhone());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getMobile());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getFax());
			nCell.setCellStyle(this.textStyle(wb));
			
			nCell = nRow.createCell(colNo++);
			nCell.setCellValue(f.getCnote());
			nCell.setCellStyle(this.textStyle(wb));
		}
		
//		String path = request.getSession().getServletContext().getRealPath("/");		//虚拟路径对应的真实物理路径
//		path += "/tmpfile";				//防止tomcat8不能直接获取.getRealPath("/tmpfile")会为null
//		File file = new File(path);
//		if(!file.exists()){
//			file.mkdirs();				//创建多级目录
//		}
		
//		FileUtil fu = new FileUtil();
//		String fileName = path + "/" + fu.newFile(path, "factory.xls");			//产生新的文件名，防止冲突 
		
//		OutputStream os = new FileOutputStream(fileName);			//输出流
//		wb.write(os);							//写入到文件中
//		os.flush();								//清空缓存
//		os.close();								//关闭
		
		DownloadUtil du = new DownloadUtil();
		//du.prototypeDownload(fileName, "生产厂家通讯录.xls", response, true);		//下载临时文件，下载后删除

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();			//生成流对象
		wb.write(byteArrayOutputStream);
		du.download(byteArrayOutputStream, response, "生产厂家通讯录.xls");		//弹出下载框，用户就可以直接下载
	}
	
	public void print2() throws FileNotFoundException, IOException{
		/*
		 * 操作步骤：
		 * 1、获取数据
		 * 2、将数据写入到excel文件中
		 */
		
		//创建工作簿
		Workbook wb = new HSSFWorkbook();
		//创建工作表sheet
		Sheet sheet = wb.createSheet();
		
		//创建行对象
		Row nRow = sheet.createRow(3);					//起始行为0，第四行
		//创建单元格对象
		Cell nCell = nRow.createCell(1);				//第2列
		nCell.setCellValue("传智播客!");					//设置单元格内容
		nCell.setCellStyle(this.titleStyle(wb));		//绑定样式
		
		nRow = sheet.createRow(4);
		nCell = nRow.createCell(2);
		nCell.setCellValue("java.itcast.cn");
		nCell.setCellStyle(this.textStyle(wb));
		
		
		FileOutputStream os = new FileOutputStream("c:\\factory.xls");			//输出流
		wb.write(os);							//写入到文件中
		os.flush();								//清空缓存
		os.close();								//关闭
	}
	
	//大标题样式
	private CellStyle bigTilteStyle(Workbook wb){
		//创建一个单元格样式对象
		CellStyle curStyle = wb.createCellStyle();
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);						//横向居中
		curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			//纵向居中
		
		Font curFont = wb.createFont();										//创建字体对象
		curFont.setFontName("华文隶书");										//设置字体
		curFont.setFontHeightInPoints((short)30);							//设置字体大小
		
		curStyle.setFont(curFont);											//将字体对象绑定到样式对象上
		
		return curStyle;
	}
	
	//标题样式
	private CellStyle titleStyle(Workbook wb){
		//创建一个单元格样式对象
		CellStyle curStyle = wb.createCellStyle();
		curStyle.setAlignment(CellStyle.ALIGN_CENTER);						//横向居中
		curStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			//纵向居中
		
		Font curFont = wb.createFont();										//创建字体对象
		curFont.setFontName("微软雅黑");										//设置字体
		curFont.setFontHeightInPoints((short)12);							//设置字体大小
		
		curStyle.setFont(curFont);											//将字体对象绑定到样式对象上
		
		//画线
		curStyle.setBorderTop(CellStyle.BORDER_THIN);						//细实线
		curStyle.setBorderBottom(CellStyle.BORDER_THIN);
		curStyle.setBorderLeft(CellStyle.BORDER_THIN);
		curStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		
		return curStyle;
	}
	//文本样式
	private CellStyle textStyle(Workbook wb){
		CellStyle xStyle = wb.createCellStyle();
		Font xFont = wb.createFont();
		xStyle.setFont(xFont);
		
		xStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);			//纵向居中
		
		//画线
		xStyle.setBorderTop(CellStyle.BORDER_THIN);						//细实线
		xStyle.setBorderBottom(CellStyle.BORDER_THIN);
		xStyle.setBorderLeft(CellStyle.BORDER_THIN);
		xStyle.setBorderRight(CellStyle.BORDER_THIN);
		
		return xStyle;
	}
	
	public void print1() throws FileNotFoundException, IOException{
		/*
		 * 操作步骤：
		 * 1、获取数据
		 * 2、将数据写入到excel文件中
		 */
		
		
		Workbook wb = new HSSFWorkbook();		//创建工作簿
		Sheet sheet = wb.createSheet();			//创建工作表sheet
		Row nRow = sheet.createRow(3);			//创建行对象，起始行为0，第四行
		Cell nCell = nRow.createCell(1);		//创建单元格对象，第2列
		nCell.setCellValue("传智播客!");			//设置单元格内容
		OutputStream os = new FileOutputStream("c:\\factory.xls");			//输出流
		wb.write(os);							//写入到文件中
		os.flush();								//清空缓存
		os.close();								//关闭
	}
	
	//JXL打印
	@RequestMapping("/basicinfo/factory/printJXL.action")
	public void printJXL(HttpServletResponse response) throws IOException, RowsExceededException, WriteException{
		OutputStream os = new FileOutputStream("c:\\testJXL.xls");
		WritableWorkbook workbook = jxl.Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("MySheet1", 0); // 可添加第一个工作
		
		WritableFont wf = new WritableFont(WritableFont.createFont("微软雅黑"), 30,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				jxl.format.Colour.CORAL); 						// 定义格式 字体 下划线 斜体 粗体 颜色
		
		WritableCellFormat wcf = new WritableCellFormat(wf); 	// 单元格定义
		wcf.setBackground(jxl.format.Colour.BLACK); 			// 设置单元格的背景颜色
		wcf.setAlignment(jxl.format.Alignment.CENTRE); 			// 设置对齐方式
		
		sheet.mergeCells(5, 3, 8, 3);			//合并单元格 startcol,startrow,stopcol,stoprow

		Label label = new jxl.write.Label(3, 5, "传智播客! 改变中国IT教育，我们在行动!", wcf); // Label(列号,行号 ,内容 )
		sheet.addCell(label);
		Label label2 = new jxl.write.Label(5, 6, "java.itcast.cn");			//无样式 
		sheet.addCell(label2);
		
		sheet.setColumnView(5, 30); 	// 设置列的宽度	29.29	和POI一样精度不够
		sheet.setRowView(3, 1000); 		// 设置行的高度	50

		
		workbook.write();		// 输出到文件
		workbook.close();		// 关闭文件
		
	}
	
}
