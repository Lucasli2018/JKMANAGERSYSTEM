package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.itcast.jk.domain.Factory;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface FactoryService {
	public List<Factory> find(Factory factory);
	public Factory get(Serializable id);
	public void insert(Factory factory);
	public void update(Factory factory);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
	
	public void changeState(Map<String,Object> map);
	public List<Factory> combo();
}

