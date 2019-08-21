package cn.itcast.jk.dao;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.ExportProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExportProductDao extends BaseDao<ExportProduct> {
	public List<ExportProduct> findByExportId(Serializable exportId);
}
