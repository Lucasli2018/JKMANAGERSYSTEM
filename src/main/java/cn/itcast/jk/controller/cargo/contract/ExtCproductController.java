package cn.itcast.jk.controller.cargo.contract;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.service.ExtCproductService;
import cn.itcast.jk.service.FactoryService;
import cn.itcast.util.Arith;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-16
 */
@Controller
public class ExtCproductController extends BaseController{
	@Autowired
	ExtCproductService extCproductService;
	
	@Autowired
	FactoryService factoryService;
	
	//转向新增页面
	@RequestMapping("/cargo/extcproduct/tocreate.action")
	public String tocreate(ExtCproduct extCproduct, Model model){
		//传递主表的ID，同时作为查询条件
		model.addAttribute("contractProductId", extCproduct.getContractProductId());
		
		//准备厂家下拉框
		model.addAttribute("factoryList", factoryService.combo());
		
		//准备列表数据，某个合同下的货物
		List<ExtCproduct> dataList = extCproductService.find(extCproduct);
		model.addAttribute("dataList", dataList);
		
		return "cargo/extcproduct/jExtCproductCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/extcproduct/insert.action")
	public String insert(ExtCproduct extCproduct){
		extCproduct.setId(UUID.randomUUID().toString());			//程序产生UUID
		
		if(extCproduct.getCnumber()!=null && extCproduct.getPrice()!=null){
			Arith arith = new Arith();										//java精度 工具类
			extCproduct.setAmount(arith.mul(extCproduct.getCnumber(), extCproduct.getPrice()));			//总金额
		}
		
		//合同总金额=当前合同下的总额+上面的总金额；注意，第一次判断是否为NULL
		
		extCproductService.insert(extCproduct);
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId=" + extCproduct.getContractProductId();		// 批量新增，同时携带主表ID
	}
	
	//转向修改页面
	@RequestMapping("/cargo/extcproduct/toupdate.action")
	public String toupdate(String id,Model model){
		//准备厂家下拉框
		model.addAttribute("factoryList", factoryService.combo());
		
		ExtCproduct obj = extCproductService.get(id);
		model.addAttribute("obj", obj);
		
		return "cargo/extcproduct/jExtCproductUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/extcproduct/update.action")
	public String update(ExtCproduct extCproduct){
		if(extCproduct.getCnumber()!=null && extCproduct.getPrice()!=null){
			Arith arith = new Arith();										//java精度 工具类
			extCproduct.setAmount(arith.mul(extCproduct.getCnumber(), extCproduct.getPrice()));			//总金额
		}		
		extCproductService.update(extCproduct);
		
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId="+extCproduct.getContractProductId();			//修改保存后返回列表（新增）页面
	}
	
	//删除
	@RequestMapping("/cargo/extcproduct/delete.action")
	public String delete(String id, String contractProductId){
		extCproductService.delete(id);
		
		return "redirect:/cargo/extcproduct/tocreate.action?contractProductId="+contractProductId;
	}
}
