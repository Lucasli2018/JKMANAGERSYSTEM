package cn.itcast.jk.dao;

import java.io.Serializable;

import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExtCproductDao extends BaseDao<ExtCproduct> {
	public void deleteByContractId(Serializable contractId);
}
