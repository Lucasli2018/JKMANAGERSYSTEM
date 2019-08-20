package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.ExtEproduct;
import cn.itcast.jk.service.ExtEproductService;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Service
public class ExtEproductServiceImpl implements ExtEproductService {
	@Autowired
	ExtEproductDao extEproductDao;
	
	public List<ExtEproduct> find(ExtEproduct extEproduct) {
		return extEproductDao.find(extEproduct);
	}

	public ExtEproduct get(Serializable id) {
		return extEproductDao.get(id);
	}

	public void insert(ExtEproduct extEproduct) {
		extEproductDao.insert(extEproduct);
	}

	public void update(ExtEproduct extEproduct) {
		extEproductDao.update(extEproduct);
	}

	public void delete(Serializable id) {
		extEproductDao.delete(id);
	}

	public void delete(Serializable[] ids) {
		extEproductDao.delete(ids);
	}
}
