package cn.itcast.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ExtCproductDaoImpl extends BaseDaoImpl<ExtCproduct> implements ExtCproductDao{
	public ExtCproductDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ExtCproductMapper.");			//设置命名空间
	}

	public void deleteByContractId(Serializable contractId) {
		this.getSqlSession().delete(this.getNs() + "deleteByContractId", contractId);
	}
}
