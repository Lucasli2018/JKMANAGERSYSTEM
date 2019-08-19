package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.ExtCproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExtCproductService {
	public List<ExtCproduct> find(ExtCproduct extCproduct);
	public ExtCproduct get(Serializable id);
	public void insert(ExtCproduct extCproduct);
	public void update(ExtCproduct extCproduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

