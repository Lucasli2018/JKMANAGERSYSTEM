package cn.itcast.jk.controller.cargo.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.service.ContractProductService;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.util.Arith;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-16
 */
@Controller
public class ContractProductController extends BaseController{
	@Autowired
	ContractProductService contractProductService;
	
	@Autowired
	FactoryService factoryService;
	
	//转向新增页面
	@RequestMapping("/cargo/contractproduct/tocreate.action")
	public String tocreate(ContractProduct contractProduct, Model model){
		//传递主表的ID，同时作为查询条件
		model.addAttribute("contractId", contractProduct.getContractId());
		
		//准备厂家下拉框
		model.addAttribute("factoryList", factoryService.combo());
		
		//准备列表数据，某个合同下的货物
		List<ContractProduct> dataList = contractProductService.find(contractProduct);
		model.addAttribute("dataList", dataList);
		
		return "cargo/contractproduct/jContractProductCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/contractproduct/insert.action")
	public String insert(ContractProduct contractProduct){
		contractProduct.setId(UUID.randomUUID().toString());			//程序产生UUID
		
		if(contractProduct.getCnumber()!=null && contractProduct.getPrice()!=null){
			Arith arith = new Arith();										//java精度 工具类
			contractProduct.setAmount(arith.mul(contractProduct.getCnumber(), contractProduct.getPrice()));			//总金额
		}
		
		//合同总金额=当前合同下的总额+上面的总金额；注意，第一次判断是否为NULL
		
		contractProductService.insert(contractProduct);
		return "redirect:/cargo/contractproduct/tocreate.action?contractId=" + contractProduct.getContractId();		// 批量新增，同时携带主表ID
	}
	
	//转向修改页面
	@RequestMapping("/cargo/contractproduct/toupdate.action")
	public String toupdate(String id,Model model){
		//准备厂家下拉框
		model.addAttribute("factoryList", factoryService.combo());
		
		ContractProduct obj = contractProductService.get(id);
		model.addAttribute("obj", obj);
		
		return "cargo/contractproduct/jContractProductUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/contractproduct/update.action")
	public String update(ContractProduct contractProduct){
		if(contractProduct.getCnumber()!=null && contractProduct.getPrice()!=null){
			Arith arith = new Arith();										//java精度 工具类
			contractProduct.setAmount(arith.mul(contractProduct.getCnumber(), contractProduct.getPrice()));			//总金额
		}		
		contractProductService.update(contractProduct);
		
		return "redirect:/cargo/contractproduct/tocreate.action?contractId="+contractProduct.getContractId();			//修改保存后返回列表（新增）页面
	}
	
	//删除
	@RequestMapping("/cargo/contractproduct/delete.action")
	public String delete(String id, String contractId){
		contractProductService.delete(id);
		
		return "redirect:/cargo/contractproduct/tocreate.action?contractId="+contractId;
	}
}
