package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.domain.ExportProduct;
import cn.itcast.jk.service.ExportService;
import cn.itcast.util.UtilFuns;

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
	@Autowired
	ExportProductDao exportProductDao;
	
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

	//拼接HTML判断，addTRRecord("mRecordTable", id, productNo, cnumber, grossWeight, netWeight, sizeLength, sizeWidth, sizeHeight, exPrice, tax);
	public String getHTMLString(String exportId) {
		
		List<ExportProduct> epList = exportProductDao.findByExportId(exportId);
		StringBuffer sBuf = new StringBuffer();
		for(ExportProduct ep : epList){
			sBuf.append("addTRRecord(\"mRecordTable\", \"").append(ep.getId()).append("\", \"").append(ep.getProductNo()).append("\", \"").append(UtilFuns.convertNull(ep.getCnumber())).append("\", \"").append(UtilFuns.convertNull(ep.getGrossWeight())).append("\", \"").append(UtilFuns.convertNull(ep.getNetWeight())).append("\", \"").append(UtilFuns.convertNull(ep.getSizeLength())).append("\", \"").append(UtilFuns.convertNull(ep.getSizeWidth())).append("\", \"").append(UtilFuns.convertNull(ep.getSizeHeight())).append("\", \"").append(UtilFuns.convertNull(ep.getExPrice())).append("\", \"").append(UtilFuns.convertNull(ep.getTax())).append("\");");
		}
		
		return sBuf.toString();
	}
}
