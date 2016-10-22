package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.domain.PageBean;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.service.impl.CategoryServiceImpl;
import com.pc.project.crud.service.impl.ProductServiceImpl;

/**
 * 显示商品，添加分页，搜索功能
 * 
 * @author Switch
 * @data 2016年10月20日
 * @version V1.1
 */
public class FindAllProductServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		// 获取当前页
		String pageNumberStr = request.getParameter("pageNumber");
		// 默认当前页为第一页
		Long pageNumber = handlePageNumber(pageNumberStr);
		try {
			// 获取当前页的内容
			PageBean<Product> pageBean = new ProductServiceImpl().getAllProduct(pageNumber);

			List<Product> products = pageBean.getPageContent();
			// 处理页数页数字串
			if (products != null) {
				// 添加到request域里去
				request.setAttribute("pageBean", pageBean);
				request.setAttribute("products", products);
			}
			List<Category> categories = new CategoryServiceImpl().getAllCategory();
			if (categories != null) {
				// 添加到request域里去
				request.setAttribute("categories", categories);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/plist.jsp").forward(request, response);
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