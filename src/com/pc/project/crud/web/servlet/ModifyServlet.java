package com.pc.project.crud.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.pc.project.crud.domain.Category;
import com.pc.project.crud.domain.Product;
import com.pc.project.crud.service.impl.ProductServiceImpl;
import com.pc.project.crud.utils.UUIDUtils;

/**
 * 修改产品信息Servlet
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class ModifyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			Product product = new Product();
			// 这里不做验证，一般来说，是要做表单验证的
			BeanUtils.populate(product, request.getParameterMap());
			Category category = new Category();
			category.setCid(request.getParameter("cid"));
			product.setCategory(category);
			new ProductServiceImpl().modify(product);

			response.sendRedirect(request.getContextPath() + "/FindAllProductServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
