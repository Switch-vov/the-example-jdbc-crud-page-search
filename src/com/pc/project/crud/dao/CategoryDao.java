package com.pc.project.crud.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.utils.C3P0Utils;

public class CategoryDao {

	public List<Category> getAllCategory() throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select * from category";
		return queryRunner.query(C3P0Utils.getConnection(), sql, new BeanListHandler<>(Category.class));
	}

}
