package cn.itcast.jk.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.itcast.jk.dao.ExportDao;
import cn.itcast.jk.domain.Export;
import cn.itcast.jk.vo.OutProduct;

/**
 * @Description:
 * @Author:	nutony
 * @Company:	http://java.itcast.cn
 * @CreateDate:	2014-3-12
 */
@Repository
public class ExportDaoImpl extends BaseDaoImpl<Export> implements ExportDao{
	public ExportDaoImpl() {
		this.setNs("cn.itcast.jk.mapper.ExportMapper.");			//设置命名空间
	}
}
