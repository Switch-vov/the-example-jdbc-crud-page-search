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
 * 添加商品的Servlet
 * 
 * @author Switch
 * @data 2016年10月18日
 * @version V1.0
 */
public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		try {
			Product product = new Product();
			// 这里不做验证，一般来说，是要做表单验证的
			BeanUtils.populate(product, request.getParameterMap());
			product.setPid(UUIDUtils.getUUID());
			product.setPdate(new Date());

			Category category = new Category();
			category.setCid(request.getParameter("cid"));
			product.setCategory(category);
			int isSuccess = new ProductServiceImpl().save(product);

			if (isSuccess != 0) {
				response.sendRedirect(request.getContextPath() + "/FindAllProductServlet");
				return;
			}
			response.sendRedirect(request.getContextPath() + "/AddProductUIServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}