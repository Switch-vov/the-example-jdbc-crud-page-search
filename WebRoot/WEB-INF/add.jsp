<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>添加商品</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">
	</head>
	<body>
		<table border="1" width="40%" class="imagetable" align="center">
			<tr>
				<th>添加商品</th>
			</tr>
		</table>
		<hr/>
		<form action="${pageContext.request.contextPath}/AddProductServlet" method="post">
			<table border="1" width="100%" class="imagetable">
				<tr>
					<td>商品名称</td>
					<td><input type="text" name="pname"/></td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td><input type="text" name="shop_price"/></td>
				</tr>
				<tr>
					<td>商品图片路径</td>
					<td><input type="text" name="pimage"/></td>
				</tr>
				<tr>
					<td>是否热门</td>
					<td><input type="radio" name="is_hot" value="0" checked="checked"/>不热门
					<input type="radio" name="is_hot" value="1"/>热门</td>
				</tr>
				<tr>
					<td>商品描述</td>
					<td>
						<textarea name="pdesc"></textarea>
					</td>
				</tr>
				<tr>
					<td>商品分类</td>
					<td>
						<select name="cid">
							<c:if test="${!empty categories }">
								<c:forEach var="category" items="${categories }">
									<option value="${category.cid }">${category.cname }</option>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="添加商品"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>