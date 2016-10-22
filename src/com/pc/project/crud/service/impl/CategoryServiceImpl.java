package com.pc.project.crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.pc.project.crud.dao.CategoryDao;
import com.pc.project.crud.domain.Category;
import com.pc.project.crud.service.CategoryServiceInter;

/**
 * 目录服务实现类
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class CategoryServiceImpl implements CategoryServiceInter {
	CategoryDao categoryDao = new CategoryDao();
	@Override
	public List<Category> getAllCategory() throws SQLException {
		return categoryDao.getAllCategory();
	}

}
