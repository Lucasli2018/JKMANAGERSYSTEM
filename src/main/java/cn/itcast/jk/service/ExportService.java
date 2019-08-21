package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.Export;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExportService {
	public List<Export> find(Export export);
	public Export get(Serializable id);
	public void insert(Export export);
	public void update(Export export);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public String getHTMLString(String exportId);
}

