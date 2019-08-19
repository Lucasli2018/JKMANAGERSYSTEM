package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ContractService {
	public List<Contract> find(Contract contract);
	public Contract get(Serializable id);
	public cn.itcast.jk.vo.Contract view(Serializable id);
	public void insert(Contract contract);
	public void update(Contract contract);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public void changeState(Map<String,Object> map);
	public List<OutProduct> findOutProduct(Serializable inputDate);
	public List<String> getExtName(String contractProductId);
}

