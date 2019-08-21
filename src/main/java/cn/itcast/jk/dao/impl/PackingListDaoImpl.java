package cn.itcast.jk.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.PackingListDao;
import cn.itcast.jk.domain.PackingList;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class PackingListDaoImpl extends BaseDaoImpl<PackingList> implements PackingListDao{
	public PackingListDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.PackingListMapper.");			//设置命名空间
	}
}
