package com.pc.project.crud.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pc.project.crud.service.impl.ProductServiceImpl;

/**
 * 通过ID删除选中的商品
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class DeleteProductBySelectedIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String selectedPid = request.getParameter("selectedPid");
		if (selectedPid != null) {
			String[] pids = selectedPid.split(",");
			new ProductServiceImpl().deleteBySelectId(pids);
		}
		response.sendRedirect(request.getContextPath() + "/FindAllProductServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
