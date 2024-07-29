<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.*, service.*,utils.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
    <%@ page import="java.util.UUID" %>
    <%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
 	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/index.css" />
    <title>JHAMS | Admin</title>

</head>
<body>

<nav class="navbar">
    <div class="logo">Brand</div>
    <ul>
        <li><a href="<%=request.getContextPath()%>/home" class="home">Home</a></li>
        <li><a  href="<%=request.getContextPath()%>/product">Products</a></li>
        <li><a  href="<%=request.getContextPath()%>/services">Services</a></li>
        <li><a  href="<%=request.getContextPath()%>/login">Log In</a></li>
        <li><a  href="<%=request.getContextPath()%>/register">Register</a></li>
    </ul>
</nav>

<div class="admin-title">
    <h2>Welcome to Admin Pannel Page</h2>
</div>


<div class="add-prod">
	<a href="<%=request.getContextPath()%>/addproduct">Add Product</a>
	
</div>
<section>
	<table border="1">
		<thead>
			<tr>
				<th>ProductName</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>CategoryID</th>
				<th>ImageName</th>
			</tr>
		</thead>
		
		<tbody>
		       <%
				ProductDAO productdao= new ProductDAO();
				List<ProductDetails> prod = productdao.getAllProduct();
				for(ProductDetails pro : prod) {
				%>
			<tr>
				<td><%= pro.getName() %></td>
				<td> <%= pro.getDescription() %></td>
				<td><%= pro.getPrice() %></td>
				<td><%= pro.getQuantity() %></td>
				<td><%= pro.getCategoryId() %></td>
				<td><%= pro.getImage_name() %></td>
				
				<td><form action="<%=request.getContextPath() %>/edit" method="post">
					<input type="hidden" name="id" value="<%=pro.getProudctID() %>">
					<button type="submit">Edit</button>
					</form>
				</td>
				
				<td>
					<form action="<%=request.getContextPath()%>/uedit" method="post">
						<input type="hidden" name="id" value="<%=pro.getProudctID() %>">
						<button type="submit">Delete</button>
					</form>
				</td>
			<%
			}
			%>
			</tr>
		</tbody>
		
	</table>
</section>
</body>
</html>
	
	
	