<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>修改商品</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">
	</head>
	<body>
		<table border="1" width="40%" class="imagetable" align="center">
			<tr>
				<th>修改商品</th>
			</tr>
		</table>
		<hr/>
	<form action="${pageContext.request.contextPath}/ModifyServlet" method="post">
			<input type="hidden" name="pid" value="${product.pid }"/>
			<table border="1" width="100%" class="imagetable">
				<tr>
					<td>商品名称</td>
					<td><input type="text" name="pname" value="${product.pname }"/></td>
				</tr>
				<tr>
					<td>商品价格</td>
					<td><input type="text" name="shop_price" value="${product.shop_price }"/></td>
				</tr>
				<tr>
					<td>商品图片路径</td>
					<td><input type="text" name="pimage" value="${product.pimage }"/></td>
				</tr>
				<tr>
					<td>是否热门</td>
					<c:choose>
						<c:when test="${product.is_hot == 0 }">
							<td>
								<input type="radio" name="is_hot" value="0" checked="checked"/>不热门
								<input type="radio" name="is_hot" value="1"/>热门
							</td>
						</c:when>
						<c:when test="${product.is_hot == 1 }">
							<td>
								<input type="radio" name="is_hot" value="0" />不热门
								<input type="radio" name="is_hot" value="1" checked="checked"/>热门
							</td>
						</c:when>
						<c:otherwise>
							<td>
								<input type="radio" name="is_hot" value="0" checked="checked"/>不热门
								<input type="radio" name="is_hot" value="1"/>热门
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<td>商品描述</td>
					<td>
						<textarea name="pdesc">${product.pdesc }</textarea>
					</td>
				</tr>
				<tr>
					<td>商品分类</td>
					<td>
						<select name="cid">
							<c:if test="${!empty categories }">
								<c:forEach var="category" items="${categories }">
									<c:choose>
										<c:when test="${category.cid == product.cid }">
											<option value="${category.cid }" selected="selected">${category.cname }</option>
										</c:when>
										<c:otherwise>
											<option value="${category.cid }">${category.cname }</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</c:if>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="submit" value="修改商品"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>