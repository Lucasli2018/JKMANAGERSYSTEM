package cn.itcast.jk.controller.run.stat;

import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.common.springdao.SqlDao;
import cn.itcast.util.file.FileUtil;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-23
 */
@Controller
public class SysStatController {
	@Autowired
	SqlDao sqlDao;

	//生产厂家销售情况统计饼形图
	@RequestMapping("/run/stat/factorySale.action")
	public String factorySale(HttpServletRequest request) throws FileNotFoundException{
		/*
		 * 操作步骤：
		 * 1、获得数据，使用jdbcTemplate查出来
		 * 2、拼串形成xml文本文件
		 * 3、转向到指定的页面index.xml
		 */
		
		String sql = "SELECT f.factory_name,cp.num FROM ( SELECT factory_id,COUNT(*) AS num FROM contract_product_c GROUP BY factory_id) cp left JOIN ( SELECT factory_id,factory_name FROM factory_c ) f ON cp.factory_id=f.factory_id";
		this.makeXMLFile(request, "factorysale", this.getPieXML(this.getData(sql)));
		return "run/stat/jStat.jsp?forward=factorysale";			//通过统一的jsp跳转，转向指定目录下的index.html
	}	
	
	//产品销售排行柱状图
	@RequestMapping("/run/stat/productSale.action")
	public String productSale(HttpServletRequest request) throws FileNotFoundException{
		String sql = "SELECT product_no,SUM(amount) AS sumnum FROM contract_product_c GROUP BY product_no ORDER BY sumnum DESC LIMIT 20";
		this.makeXMLFile(request, "productsale",  this.getColumnLineXML(this.getData(sql)));
		
		return "run/stat/jStat.jsp?forward=productsale";
	}
	
	//系统访问压力曲线图
	@RequestMapping("/run/stat/onlineInfo.action")
	public String onlineInfo(HttpServletRequest request) throws FileNotFoundException{
		String sql = "SELECT t.a1,p.num FROM (SELECT a1 FROM on_line_t) t LEFT JOIN (SELECT SUBSTRING(login_time,12,2) AS a1,COUNT(*) AS num FROM login_log_p GROUP BY SUBSTRING(login_time,12,2) ) p ON t.a1=p.a1";
		this.makeXMLFile(request, "onlineinfo",  this.getColumnLineXML(this.getData(sql)));
		
		return "run/stat/jStat.jsp?forward=onlineinfo";
	}
	
	//获取数据
	private List<String> getData(String sql){
		return sqlDao.executeSQL(sql);		//构造结果，一维数组
	}
	
	//生成饼形图XML
	private String getPieXML(List<String> dList){
		StringBuffer sBuf = new StringBuffer();
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<pie>");
		for(int i=0;i<dList.size();){						//在循环内部，等量循环，循环体内，变量取和结果集列数相同
			sBuf.append("  <slice pull_out=\"true\" title=\"").append(dList.get(i++)).append("\">").append(dList.get(i++)).append("</slice>");
		}
		sBuf.append("<pie>");
		
		return sBuf.toString();
	}
	
	//生成柱状图XML
	private String getColumnLineXML(List<String> dList){
		int xidNo = 0;
		StringBuffer sBuf = new StringBuffer();
		
		sBuf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sBuf.append("<chart>");
		sBuf.append("	<series>");
		
		for(int i=0;i<dList.size();){
			sBuf.append("		<value xid=\"").append(xidNo++).append("\">").append(dList.get(i++)).append("</value>");
			i++;			//跳过第二个元素
		}
		
		sBuf.append("	</series>");
		sBuf.append("	<graphs>");
		sBuf.append("		<graph gid=\"1\">");
		
		xidNo = 0;			//初始化
		for(int i=0;i<dList.size();){
			i++;			//跳过第一个元素
			sBuf.append("			<value xid=\"").append(xidNo++).append("\" color=\"#318DBD\">").append(dList.get(i++)).append("</value>");
		}
		sBuf.append("		</graph>");
		sBuf.append("	</graphs>");
		sBuf.append("</chart>");
		
		return sBuf.toString();
	}
	
	//生成utf-8格式的xml
	private void makeXMLFile(HttpServletRequest request, String dir, String xmlData) throws FileNotFoundException{
		FileUtil fu = new FileUtil();
		String path = request.getSession().getServletContext().getRealPath("/");
		fu.newTxt(path+"/run/stat/"+dir+"/", "data.xml", xmlData, "utf-8");
	}
}
