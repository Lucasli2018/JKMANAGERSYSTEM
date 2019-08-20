package cn.itcast.jk.dao;

import java.io.Serializable;
import java.util.Map;

import cn.itcast.jk.domain.Contract;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface ContractDao extends BaseDao<Contract> {
	public cn.itcast.jk.vo.Contract view(Serializable id);
	public void changeState(Map<String,Object> map);
}
