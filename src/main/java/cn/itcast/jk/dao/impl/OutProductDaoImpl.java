package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class OutProductDaoImpl extends BaseDaoImpl<OutProduct> implements OutProductDao{
	public OutProductDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.OutProductMapper.");			//设置命名空间
	}

	public List<OutProduct> outProduct(Serializable inputDate) {
		List<OutProduct> oList =  this.getSqlSession().selectList(this.getNs() + "findOutProduct", inputDate);
		return oList;
	}

	public List<String> getExtName(Serializable contractProductId) {
		return this.getSqlSession().selectList(this.getNs() + "getExtName", contractProductId);
	}	
}
