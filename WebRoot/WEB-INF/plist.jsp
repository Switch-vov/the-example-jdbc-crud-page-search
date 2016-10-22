<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>商品列表</title>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/imagetable.css">
	</head>
	<body>
		<table border="1" width="40%" class="imagetable" align="center">
			<tr>
				<th>商品列表</th>
			</tr>
		</table>
		<hr/>
		<table border="1" width="100%" class="imagetable">
			<tr>
				<th>
					全选<input type="checkbox" id="isSelectedAll" onclick="selectAll(this)"/>
				</th>
				<th colspan="4" align="right">
					<div style="display: inline-block;">
						<form action="${pageContext.request.contextPath }/SearchProductServlet" method="post">
							<select name="cid">
								<option value="">——请选择——</option>
								<c:if test="${not empty categories }">
									<c:forEach items="${categories}" var="category">
										<option value="${category.cid}">${category.cname }</option>
									</c:forEach>
								</c:if>
							</select>
							<input type="text" name="pname" value="${pp.pname }" />
							<input type="submit" value="搜索" />
						</form>
					</div> 
				</th>
				<th colspan="2" align="right">
					<input type="button" value="添加商品" onclick="addProduct()"/>
					<button onclick="deleteSelectedProduct()">删除选中</button>
				</th>
			</tr>
			<tr>
				<th style="width: 114px">是否选中</th>
				<th style="width: 114px">商品序号</th>
				<th style="width: 124px">商品名称</th>
				<th style="width: 123px">商品图片</th>
				<th style="width: 114px; text-align: center">商品价格</th>
				<th>商品描述</th>
				<th style="width: 114px">操作</th>
			</tr>
			<c:if test="${!empty products}">
				<c:forEach var="product" items="${products }">
					<tr>
						<td><input type="checkbox" name="pIdCheckbok" value="${product.pid }"/></td>
						<td>${product.pid }</td>
						<td>${product.pname }</td>
						<td><img width="100%" alt="商品图片" src="${pageContext.servletContext.contextPath}/${product.pimage }" /></td>
						<td>${product.shop_price }</td>
						<td>${product.pdesc }</td>
						<td>
							<button onclick="modifyProduct('${product.pid}')">修改</button>
							<button onclick="deleteProduct('${product.pid}')">删除</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<tr>
				<td colspan="7" align="center">
					<c:choose>
						<c:when test="${1 == pageBean.pageNumber}">
							[ 首页 ] 
							[ 上一页 ] 
						</c:when>
						<c:otherwise>
							[ <a href="${pageContext.request.contextPath }/FindAllProductServlet?pageNumber=1">首页</a> ]
							[ <a href="${pageContext.request.contextPath }/FindAllProductServlet?pageNumber=${pageBean.pageNumber - 1 }">上一页</a> ]
						</c:otherwise>
					</c:choose>
					<c:forEach begin="1" end="${pageBean.totalPageNumber }" var="i" step="1">
						<c:choose>
							<c:when test="${i == pageBean.pageNumber}">
								[ ${i } ] 
							</c:when>
							<%-- 实现只显示当前页及前后各两页，如果前后没有页，则不显示 --%>
							<c:when test="${i > pageBean.pageNumber - 3 && i < pageBean.pageNumber + 3}">
								[ <a href="${pageContext.request.contextPath }/FindAllProductServlet?pageNumber=${i }">${i }</a> ] 
							</c:when>
						</c:choose>
					</c:forEach>
					
					<c:choose>
						<c:when test="${pageBean.totalPageNumber == pageBean.pageNumber}">
							[ 下一页 ] 
							[ 尾页 ] 
						</c:when>
						<c:otherwise>
							[ <a href="${pageContext.request.contextPath }/FindAllProductServlet?pageNumber=${pageBean.pageNumber + 1 }">下一页</a> ]
							[ <a href="${pageContext.request.contextPath }/FindAllProductServlet?pageNumber=${pageBean.totalPageNumber }">尾页</a> ]
						</c:otherwise>
					</c:choose>
					[ 总计：${pageBean.totalRecordNumber }项 ]
				</td>
			</tr>
		</table>
		
		
		
		<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript">
			function addProduct() {
				// alert("a");
				location.href = "${pageContext.servletContext.contextPath}/AddProductUIServlet";
			}
			
			function modifyProduct(pid) {
				location.href = "${pageContext.servletContext.contextPath}/ModifyUIServlet?pid=" + pid;
			}
			
			function deleteProduct(pid) {
				if(confirm("确认删除？")) {
					location.href = "${pageContext.servletContext.contextPath}/DeleteProductByIdServlet?pid=" + pid;
				}
			}
			
			function deleteSelectedProduct() {
				// alert("123");
				var selectedPid = "";
				$("td > input:checked").each(function(i) {
					// alert($(this).val());
					if(i == 0) {
						selectedPid += $(this).val();					
					} else {
						selectedPid += "," + $(this).val();
					}
				});
				if(confirm("确认删除选中的商品？")) {
					location.href = "${pageContext.servletContext.contextPath}/DeleteProductBySelectedIdServlet?selectedPid=" + selectedPid;
				}
			}
			
			function selectAll(obj) {
				// alert($(obj).prop("checked"));
				// alert("asd");
				$("td > input[type='checkbox']").prop("checked", $(obj).prop("checked"));
			}
		</script>
	</body>
</html>