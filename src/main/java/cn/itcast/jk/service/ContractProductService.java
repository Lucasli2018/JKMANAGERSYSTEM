package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.ContractProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ContractProductService {
	public List<ContractProduct> find(ContractProduct contractProduct);
	public ContractProduct get(Serializable id);
	public void insert(ContractProduct contractProduct);
	public void update(ContractProduct contractProduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

