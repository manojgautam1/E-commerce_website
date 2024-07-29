<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Css/login.css" />
    <title>JHAMS | Login</title>

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

<div class=" main">
	<h2>Login</h2>
	<p style="color:red">${error}</p>
	<form action="<%=request.getContextPath() %>/login" method="post">
		<div>
			<label for ="email">Email:</label>
			<input type="email" id="email" name="email" required>
		</div>
		<div>
			<label for ="password">Password:</label>
			<input type="password" id="password" name="password" required>
		</div>
		<button type="submit" name="submit">Login</button>
		
	</form>
	<p>New to the platform?
	<a href="<%=request.getContextPath() %>/register">SignUp</a>
	</p>
</div>

</body>
</html>