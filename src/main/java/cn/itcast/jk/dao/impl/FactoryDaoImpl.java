package cn.itcast.jk.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.FactoryDao;
import cn.itcast.jk.domain.Factory;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class FactoryDaoImpl extends BaseDaoImpl<Factory> implements FactoryDao{
	public FactoryDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.FactoryMapper.");			//设置命名空间
	}
	
	public void changeState(Map<String, Object> map) {
		this.getSqlSession().update(this.getNs() + "changeState", map);
	}

	//下拉列表
	public List<Factory> combo() {
		return this.getSqlSession().selectList(this.getNs() + "combo");
	}
}
