package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.PackingListDao;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.PackingList;
import cn.itcast.jk.service.PackingListService;
import cn.itcast.util.UtilFuns;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class PackingListServiceImpl implements PackingListService {
	@Autowired
	PackingListDao packingListDao;
	@Autowired
	ExportDao exportDao;
	
	public List<PackingList> find(PackingList packingList) {
		return packingListDao.find(packingList);
	}

	public PackingList get(Serializable id) {
		return packingListDao.get(id);
	}

	public void insert(PackingList packingList) {
		String[] _tmp = this.splitExport(packingList.getExportIds().split(","));
		packingList.setExportIds(_tmp[0]);
		packingList.setExportNos(_tmp[1]);
		
		packingListDao.insert(packingList);
	}

	public void update(PackingList packingList) {
		String[] _tmp = this.splitExport(packingList.getExportIds().split(","));
		packingList.setExportIds(_tmp[0]);
		packingList.setExportNos(_tmp[1]);
		packingListDao.update(packingList);
	}

	public void delete(Serializable id) {
		packingListDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		packingListDao.delete(ids);
	}
	
	//新增和修改页面用
	public String getDivData(Serializable[] exportIds){
		//获取报运号，展现到新增页面上
		StringBuffer sBuf = new StringBuffer();
		for(int i=0;i<exportIds.length;i++){
			Export export = exportDao.get(exportIds[i]);
			sBuf.append("<input type=\"checkbox\" name=\"exportIds\" value=\"").append(export.getId()).append("|").append(export.getCustomerContract()).append("\" checked class=\"input\"/>");
			sBuf.append(export.getCustomerContract());
		}
		return sBuf.toString();
	}
	
	//查看页面用
	public String getDivDataView(Serializable[] exportIds){
		//获取报运号，展现到新增页面上
		StringBuffer sBuf = new StringBuffer();
		for(int i=0;i<exportIds.length;i++){
			Export export = exportDao.get(exportIds[i]);
			sBuf.append(export.getCustomerContract());
		}
		return sBuf.toString();
	}
	
	//手工拆分Id和No的组合
	private String[] splitExport(String[] rows){
		System.out.println(rows);
		String[] _tmp1 = new String[]{"",""};
		
		for(int i=0;i<rows.length;i++){
			String[] _tmp2 = rows[i].split("\\|");		//注意，竖杠分隔符是正则表达式的特殊字符，转义
			
			_tmp1[0] += _tmp2[0] + ",";
			_tmp1[1] += _tmp2[1] + "|";
		}
		_tmp1[0] = UtilFuns.delLastChar(_tmp1[0]);
		_tmp1[1] = UtilFuns.delLastChar(_tmp1[1]);
		
		return _tmp1;
	}
}
