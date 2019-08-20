package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.ExtCproduct;
import cn.itcast.jk.domain.ExtEproduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ExtEproductService {
	public List<ExtEproduct> find(ExtEproduct extEproduct);
	public ExtEproduct get(Serializable id);
	public void insert(ExtEproduct extEproduct);
	public void update(ExtEproduct extEproduct);
	public void delete(Serializable id);
	public void delete(Serializable[] ids);
}

