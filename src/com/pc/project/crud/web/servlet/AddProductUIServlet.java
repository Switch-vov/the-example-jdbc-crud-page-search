package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.service.impl.CategoryServiceImpl;

/**
 * 去往添加商品UI
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class AddProductUIServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			List<Category> categories = new CategoryServiceImpl().getAllCategory();
			if(categories != null) {
				request.setAttribute("categories", categories);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("/WEB-INF/add.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}