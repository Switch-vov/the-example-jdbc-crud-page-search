package com.pc.project.crud.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.pc.project.crud.domain.PageBean;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.utils.C3P0Utils;

/**
 * 产品Dao
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class ProductDao {

	/**
	 * 获取所有产品
	 * 
	 * @return 返回产品列表
	 * @throws SQLException
	 */
	public List<Product> getAllProduct() throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select * from product";
		return queryRunner.query(C3P0Utils.getConnection(), sql, new BeanListHandler<>(Product.class));
	}

	/**
	 * 获取所有产品(分页)
	 * 
	 * @param pageBean
	 *            分页Bean
	 * @return 返回产品列表，分页后
	 * @throws SQLException
	 */
	public PageBean<Product> getAllProduct(PageBean<Product> pageBean) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select * from product limit ?,?";
		Object[] params = { pageBean.getStartIndex(), pageBean.getPageSize() };
		List<Product> list = queryRunner.query(C3P0Utils.getConnection(), sql, new BeanListHandler<>(Product.class),
				params);
		pageBean.setPageContent(list);
		return pageBean;
	}

	/**
	 * 获取所有产品(分页+搜索)
	 * 
	 * @param pageBean
	 *            分页Bean
	 * @param product
	 *            搜索信息
	 * @return 返回产品列表，分页+搜索后
	 * @throws SQLException
	 */
	public PageBean<Product> getAllProduct(PageBean<Product> pageBean, Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();

		// 拼接SQL
		StringBuilder sBuilder = new StringBuilder("select * from product where 1 = 1");
		// 临时存储参数
		LinkedList<Object> list = new LinkedList<>();
		jointSql(sBuilder, list, product.getCategory().getCid(), " and cid = ?");
		jointSql(sBuilder, list, "%" + product.getPname() + "%", " and pname like ?");
		jointSql(sBuilder, list, pageBean.getStartIndex(), " limit ?");
		jointSql(sBuilder, list, pageBean.getPageSize(), " ,?");
		String sql = sBuilder.toString();
		Object[] params = list.toArray();

		List<Product> products = queryRunner.query(C3P0Utils.getConnection(), sql, new BeanListHandler<>(Product.class),
				params);
		pageBean.setPageContent(products);
		return pageBean;
	}

	/**
	 * 获取产品总个数
	 * 
	 * @return 产品总个数
	 * @throws SQLException
	 */
	public Long getAllProductCount() throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select count(pid) from product";
		return (Long) queryRunner.query(C3P0Utils.getConnection(), sql, new ScalarHandler());
	}

	/**
	 * 获取搜索后的产品总个数
	 * 
	 * @param product
	 *            搜索参数
	 * @return 搜索后的产品总个数
	 * @throws SQLException
	 */
	public Long getAllProductCount(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();

		// 拼接SQL
		StringBuilder sBuilder = new StringBuilder("select count(pid) from product where 1 = 1");
		// 临时存储参数
		LinkedList<Object> list = new LinkedList<>();
		jointSql(sBuilder, list, product.getCategory().getCid(), " and cid = ?");
		jointSql(sBuilder, list, "%" + product.getPname() + "%", " and pname like ?");
		String sql = sBuilder.toString();
		Object[] params = list.toArray();

		return (Long) queryRunner.query(C3P0Utils.getConnection(), sql, new ScalarHandler(), params);
	}

	/**
	 * 拼接SQL
	 * 
	 * @param sBuilder
	 *            临时字符
	 * @param list
	 *            临时存储参数
	 * @param param
	 *            ?所对应的字符
	 * @param chip
	 *            一段SQL
	 * @return sBuilder
	 */
	public StringBuilder jointSql(StringBuilder sBuilder, LinkedList<Object> list, String param, String chip) {
		if (param != null && !"".equals(param) && !param.contains("%null") && !param.contains("null%")) {
			sBuilder.append(chip);
			list.add(param);
		}
		return sBuilder;
	}

	/**
	 * 拼接SQL
	 * 
	 * @param sBuilder
	 *            临时字符
	 * @param list
	 *            临时存储参数
	 * @param param
	 *            ?所对应的字符
	 * @param chip
	 *            一段SQL
	 * @return sBuilder
	 */
	public StringBuilder jointSql(StringBuilder sBuilder, LinkedList<Object> list, Long param, String chip) {
		sBuilder.append(chip);
		list.add(param);
		return sBuilder;
	}

	/**
	 * 保存产品信息
	 * 
	 * @param product
	 *            产品
	 * @return 1成功，0失败
	 * @throws SQLException
	 */
	public int save(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { product.getPid(), product.getPname(), product.getMarket_price(), product.getShop_price(),
				product.getPimage(), product.getPdate(), product.getIs_hot(), product.getPdesc(), product.getPflag(),
				product.getCategory().getCid() };
		return queryRunner.update(C3P0Utils.getConnection(), sql, params);
	}

	/**
	 * 通过ID获取产品
	 * 
	 * @param pid
	 *            产品ID
	 * @return
	 * @throws SQLException
	 */
	public Product getProductById(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "select * from product where pid=?";
		Object[] params = { pid };
		return queryRunner.query(C3P0Utils.getConnection(), sql, new BeanHandler<>(Product.class), params);
	}

	/**
	 * 修改产品
	 * 
	 * @param product
	 *            需要修改的产品
	 * @return 1成功，0失败
	 * @throws SQLException
	 */
	public int modify(Product product) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "update product set pname=?,shop_price=?,pimage=?,is_hot=?,pdesc=?,cid=? where pid=?";
		Object[] params = { product.getPname(), product.getShop_price(), product.getPimage(), product.getIs_hot(),
				product.getPdesc(), product.getCid(), product.getPid() };
		return queryRunner.update(C3P0Utils.getConnection(), sql, params);
	}

	/**
	 * 通过ID删除产品
	 * 
	 * @param pid
	 *            产品ID
	 * @return 1成功，0失败
	 * @throws SQLException
	 */
	public int deleteById(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "delete from product where pid=?";
		Object[] params = { pid };
		return queryRunner.update(C3P0Utils.getConnection(), sql, params);
	}

	/**
	 * 通过选中ID删除产品
	 * 
	 * @param pids
	 *            选中ID
	 * @return 1成功，0失败
	 * @throws SQLException
	 */
	public int deleteBySelectId(String[] pids) throws SQLException {
		QueryRunner queryRunner = new QueryRunner();
		String sql = "delete from product where pid=?";
		String[][] params = new String[pids.length][1];
		for (int i = 0; i < pids.length; i++) {
			params[i][0] = new String(pids[i]);
		}
		int[] batch = queryRunner.batch(C3P0Utils.getConnection(), sql, params);
		int isSuccess = 1;
		for (int i = 0; i < batch.length; i++) {
			if (batch[i] == 0) {
				isSuccess = 0;
			}
		}
		return isSuccess;
	}

}
