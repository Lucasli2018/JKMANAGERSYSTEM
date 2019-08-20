package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.ContractProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ContractProductDaoImpl extends BaseDaoImpl<ContractProduct> implements ContractProductDao{
	public ContractProductDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ContractProductMapper.");			//设置命名空间
	}
	
	public void deleteByContractId(Serializable contractId) {
		this.getSqlSession().delete(this.getNs() + "deleteByContractId", contractId);
	}

	public List<ContractProduct> findForExport(Serializable contractId) {
		return this.getSqlSession().selectList(this.getNs() + "findForExport", contractId);
	}
}
