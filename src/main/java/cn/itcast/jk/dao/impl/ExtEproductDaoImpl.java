package cn.itcast.jk.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExtEproductDao;
import cn.itcast.jk.domain.ExtEproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ExtEproductDaoImpl extends BaseDaoImpl<ExtEproduct> implements ExtEproductDao{
	public ExtEproductDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ExtEproductMapper.");			//设置命名空间
	}
}
