package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.ExportProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExportProductService {
	public List<ExportProduct> find(ExportProduct exportProduct);
	public ExportProduct get(Serializable id);
	public void insert(ExportProduct exportProduct);
	public void update(ExportProduct exportProduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

