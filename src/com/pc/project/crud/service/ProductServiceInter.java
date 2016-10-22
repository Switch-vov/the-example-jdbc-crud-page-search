package com.pc.project.crud.service;

import java.sql.SQLException;
import java.util.List;

import com.pc.project.crud.domain.PageBean;
import com.pc.project.crud.domain.Product;

/**
 * 产品服务接口
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public interface ProductServiceInter {
	/**
	 * 获取所有产品信息
	 * 
	 * @return 所有产品列表
	 * @throws SQLException
	 */
	public List<Product> getAllProduct() throws SQLException;

	/**
	 * 获取所有产品信息,分页处理<br/>
	 * 使用getAllProduct(Long pageNumber, Long pageSize)<br/>
	 * pageSize默认为<b>5</b>
	 * 
	 * @param pageNumber
	 *            当前页
	 * @return 当前页的产品列表
	 * @throws SQLException
	 */
	PageBean<Product> getAllProduct(Long pageNumber) throws SQLException;

	/**
	 * 获取所有产品信息,分页处理
	 * 
	 * @param pageNumber
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @return 当前页的产品列表
	 * @throws SQLException
	 */
	PageBean<Product> getAllProduct(Long pageNumber, Long pageSize) throws SQLException;

	/**
	 * 获取搜索的产品信息,分页处理<br/>
	 * 使用getAllProduct(Long pageNumber, Long pageSize, Product product)<br/>
	 * pageSize默认为<b>5</b>
	 * 
	 * @param pageNumber
	 *            当前页
	 * @param product
	 *            产品
	 * @return 当前页的产品列表
	 * @throws SQLException
	 */
	PageBean<Product> getAllProduct(Long pageNumber, Product product) throws SQLException;

	/**
	 * 获取搜索的产品信息,分页处理
	 * 
	 * @param pageNumber
	 *            当前页
	 * @param pageSize
	 *            每页记录数
	 * @param product
	 *            产品
	 * @return 当前页的产品列表
	 * @throws SQLException 
	 */
	PageBean<Product> getAllProduct(Long pageNumber, Long pageSize, Product product) throws SQLException;

	/**
	 * 保存产品信息
	 * 
	 * @param product
	 *            产品
	 * @throws SQLException
	 */
	public int save(Product product) throws SQLException;

	/**
	 * 通过ID获取产品
	 * 
	 * @param pid
	 *            产品id
	 * @return 产品
	 * @throws SQLException
	 */
	public Product getProductById(String pid) throws SQLException;

	/**
	 * 修改产品信息
	 * 
	 * @param product
	 * @return
	 * @throws SQLException
	 */
	public int modify(Product product) throws SQLException;

	/**
	 * 通过ID删除产品
	 * 
	 * @param pid
	 *            产品id
	 * @return
	 * @throws SQLException
	 */
	public int deleteById(String pid) throws SQLException;

	/**
	 * 通过选中的ID删除产品
	 * 
	 * @param pids
	 *            选中的ID
	 * @return
	 */
	public int deleteBySelectId(String[] pids);

}
