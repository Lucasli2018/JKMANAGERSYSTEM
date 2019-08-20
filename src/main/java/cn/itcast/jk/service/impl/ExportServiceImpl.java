package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.service.ExportService;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class ExportServiceImpl implements ExportService {
	@Autowired
	ExportDao exportDao;
	
	public List<Export> find(Export export) {
		return exportDao.find(export);
	}

	public Export get(Serializable id) {
		return exportDao.get(id);
	}

	public void insert(Export export) {
		exportDao.insert(export);
	}

	public void update(Export export) {
		exportDao.update(export);
	}

	public void delete(Serializable id) {
//		extCproductDao.deleteByExportId(id);				//删除当前合同下的货物下的附件
//		contractProductDao.deleteByExportId(id);			//删除当前合同下的货物
		exportDao.delete(id);								//删除合同
	}

	public void delete(Serializable[] ids) {
//		for(Serializable id: ids){
//			extCproductDao.deleteByExportId(id);			//删除当前合同下的货物下的附件
//		}
//		for(Serializable id: ids){
//			contractProductDao.deleteByExportId(id);		//删除当前合同下的货物		
//		}
		exportDao.delete(ids);							//删除合同
	}
}
