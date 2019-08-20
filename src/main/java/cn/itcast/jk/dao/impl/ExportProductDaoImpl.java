package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportProductDao;
import cn.itcast.jk.domain.ExportProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ExportProductDaoImpl extends BaseDaoImpl<ExportProduct> implements ExportProductDao{
	public ExportProductDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ExportProductMapper.");			//设置命名空间
	}
}
