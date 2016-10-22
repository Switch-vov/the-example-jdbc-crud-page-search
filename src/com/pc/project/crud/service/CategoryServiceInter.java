package com.pc.project.crud.service;

import java.sql.SQLException;
import java.util.List;

import com.pc.project.crud.domain.Category;

/**
 * 目录服务接口
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public interface CategoryServiceInter {
	/**
	 * 获取所有目录
	 * 
	 * @return 目录列表
	 * @throws SQLException 
	 */
	public List<Category> getAllCategory() throws SQLException;
}
