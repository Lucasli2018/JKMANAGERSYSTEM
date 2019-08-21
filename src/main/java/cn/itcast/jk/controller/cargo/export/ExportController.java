package cn.itcast.jk.controller.cargo.export;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.service.ExportProductService;
import cn.itcast.jk.service.ExportService;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.service.ExtEproductService;
import cn.itcast.util.UtilFuns;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-20
 */
@Controller
public class ExportController extends BaseController {
	@Autowired
	ExportService exportService;
	@Autowired
	ContractService contractService;
	@Autowired
	ContractProductService contractProductService;
	@Autowired
	ExtCproductService extCproductService;
	@Autowired
	ExportProductService exportProductService;
	@Autowired
	ExtEproductService extEproductService;	
	
	//列表
	@RequestMapping("/cargo/export/list.action")
	public String list(Export export, Model model){
		List<Export> dataList = exportService.find(export);
		model.addAttribute("dataList", dataList);
		
		return "cargo/export/jExportList.jsp";
	}
	
	
	//购销合同查询
	@RequestMapping("/cargo/export/contractList.action")
	public String contractList(Contract contract, Model model){
		contract.setState(1);					//1已上报			查询条件
		List<Contract> dataList = contractService.find(contract);
		model.addAttribute("dataList", dataList);
		
		return "cargo/export/jContractList.jsp";
	}	
	
	//新增保存		
	@RequestMapping("/cargo/export/insert.action")
	public String insert(String id){
		/*
		 * 操作步骤：
		 * 1. 获取合同信息，从页面传递多个id
		 * 2. 创建报运对象
		 * 3. 将合同中的货物信息进行“搬家”
		 * 4. 将合同中的附件信息进行“搬家”
		 * 5. 保存报运
		 */
		String exportId = UUID.randomUUID().toString();
		Export export = new Export();
		export.setId(exportId);
		
		//1. 获取合同信息，从页面传递多个id
		export.setContractIds(id);						//合同ID集合
		String[] contractId = id.split(",");
		String _contractNos = "";
		for(int i=0;i<contractId.length;i++){
			Contract contract = contractService.get(contractId[i]);			//获得合同对象
			_contractNos += contract.getContractNo() + " ";
		}
 		export.setCustomerContract(_contractNos);
 		
 		export.setState(0);								//0-草稿 1-已上报 2-装箱 3-委托 4-发票 5-财务
 		exportService.insert(export);
 		
 		//3. 将合同中的货物信息进行“搬家”
 		ContractProduct findContractProduct = new ContractProduct();
 		for(int i=0;i<contractId.length;i++){
 			
 			findContractProduct.setContractId(contractId[i]);				//设置查询条件
	 		List<ContractProduct> cpList = contractProductService.find(findContractProduct);			//某个一个合同下的货物信息
	 		for(ContractProduct cp : cpList){
	 			String exportProductId = UUID.randomUUID().toString();					//货物ID
	 			ExportProduct ep = new ExportProduct();
	 			
	 			BeanUtils.copyProperties(cp, ep);					//利用工具类拷贝属性
	 			
	 			//设置不同的内容
	 			ep.setExportId(exportId);							//报运ID
	 			ep.setId(exportProductId);
	 			
	 			exportProductService.insert(ep);					//保存报运货物信息
	 			
	 			//4. 将合同中的附件信息进行“搬家”
	 			ExtCproduct findExtCproduct = new ExtCproduct();	//查询条件
	 			findExtCproduct.setContractProductId(cp.getId());	//货物id
	 			List<ExtCproduct> extcpList = extCproductService.find(findExtCproduct);
	 			for(ExtCproduct extcp : extcpList){
	 				ExtEproduct extep = new ExtEproduct();
	 				BeanUtils.copyProperties(cp, ep);					//利用工具类拷贝属性
	 				
	 				extep.setExportProductId(exportProductId);		//设置外键
	 				extep.setId(UUID.randomUUID().toString());
	 				
	 				extEproductService.insert(extep);
	 			}
	 		}
 		}
 		return "redirect:/cargo/export/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/cargo/export/toupdate.action")
	public String toupdate(String id, Model model){
		Export obj = exportService.get(id);
		model.addAttribute("obj", obj);

		String htmlString = exportService.getHTMLString(id);
		model.addAttribute("mRecordData", htmlString);
		
		return "cargo/export/jExportUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/export/update.action")
	public String update(Export export, HttpServletRequest request){
		
		//获取批量提交的数据
		String[] id = request.getParameterValues("mr_id");
		String[] changed = request.getParameterValues("mr_changed");			//监测单元格值是否发生变化
		String[] orderNo = request.getParameterValues("mr_orderNo");
		
		String[] cnumber = request.getParameterValues("mr_cnumber");
		String[] grossWeight = request.getParameterValues("mr_grossWeight");
		String[] netWeight = request.getParameterValues("mr_netWeight");
		String[] sizeLength = request.getParameterValues("mr_sizeLength");
		String[] sizeWidth = request.getParameterValues("mr_sizeWidth");
		String[] sizeHeight = request.getParameterValues("mr_sizeHeight");
		String[] exPrice = request.getParameterValues("mr_exPrice");
		String[] tax = request.getParameterValues("mr_tax");
		
		//批量提交
		ExportProduct ep;
		
		for(int i=0;i<orderNo.length;i++){
			if(UtilFuns.isEmpty(changed[i])){
				continue;									//跳过未修改的记录，优化
			}
			if(UtilFuns.isEmpty(id[i])){
				ep = new ExportProduct();
			}else{
				ep = exportProductService.get(id[i]);
			}
			if(UtilFuns.isNotEmpty(cnumber[i])){
				ep.setCnumber(Integer.parseInt(cnumber[i]));
			}
			if(UtilFuns.isNotEmpty(grossWeight[i])){
				ep.setGrossWeight(Double.parseDouble(grossWeight[i]));
			}
			if(UtilFuns.isNotEmpty(netWeight[i])){
				ep.setNetWeight(Double.parseDouble(netWeight[i]));
			}
			if(UtilFuns.isNotEmpty(sizeLength[i])){
				ep.setSizeLength(Double.parseDouble(sizeLength[i]));
			}
			if(UtilFuns.isNotEmpty(sizeWidth[i])){
				ep.setSizeWidth(Double.parseDouble(sizeWidth[i]));
			}
			if(UtilFuns.isNotEmpty(sizeHeight[i])){
				ep.setSizeHeight(Double.parseDouble(sizeHeight[i]));
			}
			if(UtilFuns.isNotEmpty(exPrice[i])){
				ep.setExPrice(Double.parseDouble(exPrice[i]));
			}
			if(UtilFuns.isNotEmpty(tax[i])){
				ep.setTax(Double.parseDouble(tax[i]));
			}
			exportProductService.update(ep);
		}
		
		
		exportService.update(export);
		return "redirect:/cargo/export/list.action";
	}
}
