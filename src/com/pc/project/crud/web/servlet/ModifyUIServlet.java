package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.service.impl.CategoryServiceImpl;
import com.pc.project.crud.service.impl.ProductServiceImpl;

/**
 * 去修改产品UI的Servlet
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class ModifyUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		try {
			String pid = request.getParameter("pid");
			if (pid != null) {
				Product product = new ProductServiceImpl().getProductById(pid);
				request.setAttribute("product", product);
			}
			List<Category> categories = new CategoryServiceImpl().getAllCategory();
			if (categories != null) {
				request.setAttribute("categories", categories);
			}
			// 防止非法访问
			RequestDispatcher dispatcher = null;
			if (pid == null || categories == null) {
				dispatcher = request.getRequestDispatcher("/FindAllProductServlet");
			} else {
				dispatcher = request.getRequestDispatcher("/WEB-INF/modify.jsp");
			}
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
