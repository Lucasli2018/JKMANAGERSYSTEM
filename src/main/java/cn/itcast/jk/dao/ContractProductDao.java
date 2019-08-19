package cn.itcast.jk.dao;

import java.io.Serializable;

import cn.itcast.jk.domain.ContractProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ContractProductDao extends BaseDao<ContractProduct> {
	public void deleteByContractId(Serializable contractId);
}
