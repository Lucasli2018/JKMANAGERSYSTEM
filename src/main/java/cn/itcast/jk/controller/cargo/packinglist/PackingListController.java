package cn.itcast.jk.controller.cargo.packinglist;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.service.PackingListService;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-22
 */
@Controller
public class PackingListController extends BaseController {
	@Autowired
	PackingListService packingListService;
	
	//列表
	@RequestMapping("/cargo/packinglist/list.action")
	public String list(PackingList packingList, Model model){
		List<PackingList> dataList = packingListService.find(packingList);
		model.addAttribute("dataList", dataList);
		
		return "cargo/packinglist/jPackingListList.jsp";
	}
	
	//转向新增页面
	@RequestMapping("/cargo/packinglist/tocreate.action")
	public String tocreate(String id, Model model){			//报运号
		String divData = packingListService.getDivData(id.split(","));
		model.addAttribute("divData", divData);
		
		return "cargo/packinglist/jPackingListCreate.jsp";
	}
	
	//新增保存
	@RequestMapping("/cargo/packinglist/insert.action")
	public String insert(PackingList packingList){
		packingList.setId(UUID.randomUUID().toString());
		packingListService.insert(packingList);
		
		return "../../cargo/packinglist/list.action";
	}
	
	//转向修改页面
	@RequestMapping("/cargo/packinglist/toupdate.action")
	public String toupdate(String id, Model model){
		PackingList obj = packingListService.get(id);
		model.addAttribute("obj", obj);
		
		String divData = packingListService.getDivData(obj.getExportIds().split(","));
		model.addAttribute("divData", divData);
		
		return "cargo/packinglist/jPackingListUpdate.jsp";
	}
	
	//修改保存
	@RequestMapping("/cargo/packinglist/update.action")
	public String update(PackingList packingList){
		packingListService.update(packingList);
		
		return "redirect:/cargo/packinglist/list.action";
	}
	
	//删除
	@RequestMapping("/cargo/packinglist/deleteBatch.action")
	public String deleteBatch(String id){
		packingListService.delete(id.split(","));
		return "redirect:/cargo/packinglist/list.action";
	}
	
	//转向查看页面
	@RequestMapping("/cargo/packinglist/toview.action")
	public String toview(String id, Model model){
		PackingList obj = packingListService.get(id);
		model.addAttribute("obj", obj);
		
		String divData = packingListService.getDivDataView(obj.getExportIds().split(","));
		model.addAttribute("divData", divData);		
		
		return "cargo/packinglist/jPackingListView.jsp";
	}
	
}
