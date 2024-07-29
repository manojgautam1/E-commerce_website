<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Css/profile.css" />
<title>Profile</title>
</head>
<body>
<nav class="navbar">
    <div class="logo">Brand</div>
    <ul>
        <li><a href="<%=request.getContextPath()%>/home" class="home">Home</a></li>
        <li><a  href="<%=request.getContextPath()%>/product">Products</a></li>
        <li><a  href="<%=request.getContextPath()%>/services">Services</a></li>
        <li><a  href="<%=request.getContextPath()%>/logout">Log out</a></li>
    </ul>
</nav>
<div class="main">
<form action="<%=request.getContextPath() %>/edit" method="post">
<button type="submit"> Update Profile</button>
</form>

<form action="<%=request.getContextPath()%>/logout" method="post">
<button type="submit">Logout</button>
</form>
</div>
</body>
</html>
