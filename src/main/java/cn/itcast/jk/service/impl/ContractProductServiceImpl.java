package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ContractProductDao;
import cn.itcast.jk.domain.ContractProduct;
import cn.itcast.jk.service.ContractProductService;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class ContractProductServiceImpl implements ContractProductService {
	@Autowired
	ContractProductDao contractProductDao;
	
	public List<ContractProduct> find(ContractProduct contractProduct) {
		return contractProductDao.find(contractProduct);
	}

	public ContractProduct get(Serializable id) {
		return contractProductDao.get(id);
	}

	public void insert(ContractProduct contractProduct) {
		contractProductDao.insert(contractProduct);
	}

	public void update(ContractProduct contractProduct) {
		contractProductDao.update(contractProduct);
	}

	public void delete(Serializable id) {
		contractProductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		contractProductDao.delete(ids);
	}

	public List<ContractProduct> findForExport(Serializable contractId) {
		return contractProductDao.findForExport(contractId);
	}
}
