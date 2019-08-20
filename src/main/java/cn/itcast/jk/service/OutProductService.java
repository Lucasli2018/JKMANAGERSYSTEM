package cn.itcast.jk.service;

import java.io.Serializable;
import java.util.List;

import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
public interface OutProductService {
	public List<OutProduct> findOutProduct(Serializable inputDate);
	public List<String> getExtName(String contractProductId);
}

