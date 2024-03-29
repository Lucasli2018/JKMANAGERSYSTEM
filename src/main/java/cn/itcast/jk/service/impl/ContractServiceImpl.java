package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.common.springdao.SqlDao;
import cn.itcast.jk.dao.ContractDao;
import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.dao.ExtCproductDao;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	ExtCproductDao extCproductDao;
	@Autowired
	ContractProductDao contractProductDao;
	@Autowired
	ContractDao contractDao;
	
	@Autowired
	SqlDao sqlDao;	
	
	public List<Contract> find(Contract contract) {
		return contractDao.find(contract);
	}

	public Contract get(Serializable id) {
		return contractDao.get(id);
	}
	
	public cn.itcast.jk.vo.Contract view(Serializable id) {
		return contractDao.view(id);
	}

	public void insert(Contract contract) {
		contractDao.insert(contract);
	}

	public void update(Contract contract) {
		contractDao.update(contract);
	}

	public void delete(Serializable id) {
		extCproductDao.deleteByContractId(id);				//删除当前合同下的货物下的附件
		contractProductDao.deleteByContractId(id);			//删除当前合同下的货物
		contractDao.delete(id);								//删除合同
	}

	public void delete(Serializable[] ids) {
		for(Serializable id: ids){
			extCproductDao.deleteByContractId(id);			//删除当前合同下的货物下的附件
		}
		for(Serializable id: ids){
			contractProductDao.deleteByContractId(id);		//删除当前合同下的货物		
		}
		contractDao.delete(ids);							//删除合同
	}
	

	public void changeState(Map<String, Object> map) {
		contractDao.changeState(map);
	}

	public void pigeonhole(String[] contractIds) {
		StringBuffer sBuf = new StringBuffer();
		for(String id : contractIds){
			sBuf.append("insert into contract_his_c select * from contract_c where contract_id='" + id + "';");
			sBuf.append("insert into contract_product_his_c select * from contract_product_c where contract_id='" + id + "';");
			sBuf.append("insert into ext_cproduct_his_c select * from ext_cproduct_c where contract_product_id in (select contract_product_id from contract_product_c where contract_id='" + id + "');");
			
			sBuf.append("delete from ext_cproduct_c where contract_product_id in (select contract_product_id from contract_product_c where contract_id='" + id + "');");
			sBuf.append("delete from contract_product_c where contract_id='" + id + "';");
			sBuf.append("delete from contract_c where contract_id='" + id + "';");
			
		}
		
		sqlDao.batchSQL(sBuf.toString().split(";"));
	}
}
