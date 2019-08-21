package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.domain.PackingList;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface PackingListService {
	public List<PackingList> find(PackingList packingList);
	public PackingList get(Serializable id);
	public void insert(PackingList packingList);
	public void update(PackingList packingList);
	public void delete(Serializable[] ids);
	
	public String getDivData(Serializable[] exportIds);
	public String getDivDataView(Serializable[] exportIds);
}

