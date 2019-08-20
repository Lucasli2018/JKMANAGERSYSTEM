package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.OutProductDao;
import cn.itcast.jk.service.OutProductService;
import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class OutProductServiceImpl implements OutProductService {
	@Autowired
	OutProductDao outProductDao;
	

	public List<OutProduct> findOutProduct(Serializable inputDate) {
		return outProductDao.outProduct(inputDate);
	}

	public List<String> getExtName(String contractProductId) {
		return outProductDao.getExtName(contractProductId);
	}	
}
