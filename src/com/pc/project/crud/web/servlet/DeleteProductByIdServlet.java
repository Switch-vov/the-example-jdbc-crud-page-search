package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.project.crud.service.impl.ProductServiceImpl;

/**
 * 删除产品Servlet
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class DeleteProductByIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			String pid = request.getParameter("pid");
			if (pid != null) {
				new ProductServiceImpl().deleteById(pid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/FindAllProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}