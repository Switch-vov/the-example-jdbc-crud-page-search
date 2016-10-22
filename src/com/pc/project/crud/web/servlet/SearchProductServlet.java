package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.domain.PageBean;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.service.impl.CategoryServiceImpl;
import com.pc.project.crud.service.impl.ProductServiceImpl;

/**
 * 搜索Servlet
 * 
 * @author Switch
 * @data 2016年10月20日
 * @version V1.0
 */
public class SearchProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		// 获取当前页
		String pageNumberStr = request.getParameter("pageNumber");
		// 默认当前页为第一页
		Long pageNumber = handlePageNumber(pageNumberStr);

		// 获取搜索信息
		// 封装信息
		Product product = new Product();
		try {
			BeanUtils.populate(product, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String cid = request.getParameter("cid");
		Category category = new Category();
		category.setCid(cid);
		product.setCategory(category);
		
		try {
			// 获取当前页的内容
			PageBean<Product> pageBean = new ProductServiceImpl().getAllProduct(pageNumber, product);

			List<Product> products = pageBean.getPageContent();
			// 处理页数页数字串
			if (products != null) {
				// 添加到request域里去
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("products", products);
				request.setAttribute("pr", product);
			}
			List<Category> categories = new CategoryServiceImpl().getAllCategory();
			if (categories != null) {
				// 添加到request域里去
				request.setAttribute("categories", categories);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/plistsearch.jsp").forward(request, response);
	}

	/**
	 * 处理页数字串,默认当前页为第一页
	 * 
	 * @param pageNumberStr
	 *            字符串
	 * @return 数字
	 */
	private Long handlePageNumber(String pageNumberStr) {
		Long pageNumber = 1L;
		if (pageNumberStr != null) {
			// 处理转换异常
			try {
				pageNumber = Long.parseLong(pageNumberStr);
			} catch (NumberFormatException e) {
				// 不做任何处理
				e.printStackTrace();
			}
		}
		return pageNumber;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}