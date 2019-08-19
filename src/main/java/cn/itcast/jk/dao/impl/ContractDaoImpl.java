package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{
	public ContractDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ContractMapper.");			//设置命名空间
	}

	public cn.itcast.jk.vo.Contract view(Serializable id) {
		return this.getSqlSession().selectOne(this.getNs() + "view", id);
	}
	
	public void changeState(Map<String, Object> map) {
		this.getSqlSession().update(this.getNs() + "changeState", map);
	}

	public List<OutProduct> outProduct(Serializable inputDate) {
		List<OutProduct> oList =  this.getSqlSession().selectList(this.getNs() + "findOutProduct", inputDate);
		return oList;
	}

	public List<String> getExtName(Serializable contractProductId) {
		return this.getSqlSession().selectList(this.getNs() + "getExtName", contractProductId);
	}	
}
