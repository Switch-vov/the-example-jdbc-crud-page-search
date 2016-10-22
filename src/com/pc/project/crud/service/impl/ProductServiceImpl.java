package com.pc.project.crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.pc.project.crud.dao.ProductDao;
import com.pc.project.crud.domain.PageBean;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.service.ProductServiceInter;
import com.pc.project.crud.utils.C3P0Utils;

/**
 * 产品服务实现类
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class ProductServiceImpl implements ProductServiceInter {
 	ProductDao productDao = new ProductDao();

	@Override
	public List<Product> getAllProduct() throws SQLException {
		return productDao.getAllProduct();
	}

	@Override
	public PageBean<Product> getAllProduct(Long pageNumber) throws SQLException {
		return this.getAllProduct(pageNumber, 5L);
	}

	@Override
	public PageBean<Product> getAllProduct(Long pageNumber, Long pageSize) throws SQLException {
		// 获取产品总个数
		Long totalRecordNumber = productDao.getAllProductCount();
		// 创建PageBean对象
		PageBean<Product> pageBean = new PageBean<>(pageNumber, pageSize, totalRecordNumber);
		// 处理页数越界情况
		pageNumber = handlePageNumber(pageBean.getPageNumber(), pageBean.getTotalPageNumber());
		pageBean = new PageBean<>(pageNumber, pageSize, totalRecordNumber);

		return productDao.getAllProduct(pageBean);
	}

	@Override
	public PageBean<Product> getAllProduct(Long pageNumber, Product product) throws SQLException {
		return this.getAllProduct(pageNumber, 5L, product);
	}

	@Override
	public PageBean<Product> getAllProduct(Long pageNumber, Long pageSize, Product product) throws SQLException {
		// 获取符合搜索的产品总个数
		Long totalRecordNumber = productDao.getAllProductCount(product);
		// 创建PageBean对象
		PageBean<Product> pageBean = new PageBean<>(pageNumber, pageSize, totalRecordNumber);
		// 处理页数越界情况
		pageNumber = handlePageNumber(pageBean.getPageNumber(), pageBean.getTotalPageNumber());
		pageBean = new PageBean<>(pageNumber, pageSize, totalRecordNumber);
		return productDao.getAllProduct(pageBean, product);
	}

	/**
	 * 处理页小于1大于总页数情况,默认当前页为第一页
	 * 
	 * @param pageNumber
	 *            页数
	 * @param totalPageNumber
	 *            总页数
	 * @return 数字
	 */
	private Long handlePageNumber(Long pageNumber, Long totalPageNumber) {
		if (totalPageNumber != null && (pageNumber < 1 || pageNumber > totalPageNumber)) {
			pageNumber = 1L;
		}
		return pageNumber;
	}

	@Override
	public int save(Product product) throws SQLException {
		return productDao.save(product);
	}

	@Override
	public Product getProductById(String pid) throws SQLException {
		return productDao.getProductById(pid);
	}

	@Override
	public int modify(Product product) throws SQLException {
		return productDao.modify(product);
	}

	@Override
	public int deleteById(String pid) throws SQLException {
		return productDao.deleteById(pid);
	}

	@Override
	public int deleteBySelectId(String[] pids) {
		int isSuccess = 0;
		try {
			C3P0Utils.beginTransaction();
			isSuccess = productDao.deleteBySelectId(pids);
			C3P0Utils.commitTransaction();
		} catch (SQLException e) {
			try {
				C3P0Utils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return isSuccess;
	}

}
