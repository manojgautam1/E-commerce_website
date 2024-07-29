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

<div class="container">
	<h1>New User Registration</h1>
	<p style="color:red">${error1}</p> 
	<form action="<%=request.getContextPath() %>/register" method="post">
			<div class="row">
				<div class="col">
					<label for="firstname">First Name:</label> <input type="text"
						id="firstname" value="${firstname}" name="firstname" required>
				</div>
				<div class="col">
					<label for="lastname">Last Name:</label> <input type="text"
						id="lastname" name="lastname" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="gender">Gender:</label> <select id="gender"
						name="gender" required>
						<option value="male">Male</option>
						<option value="female">Female</option>
					</select>
				</div>
				<div class="col">
					<label for="birthday">Birthday:</label> <input type="date"
						id="birthday" name="birthday" required>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="phonenumber">Phone Number:</label> <input type="tel"
						id="phonenumber" name="phonenumber" required>
				</div>
				
				<div class="col">
					<label for="email">Email:</label> <input type="email" id="email"
						name="email" required>
				</div>
			</div>
				<div class="row">
					<label for="address">Address:</label><input type="text"
						id="address" name="address" required>
				</div>
			<div class="row">
				<div class="col">
					<label for="password">Password:</label> <input type="password"
						id="password" name="password" required>
				</div>
				<div class="col">
					<label for="retypePassword">Retype Password:</label> <input
						type="password" id="retypePassword" name="retypePassword" required>
						<%
						    if(request.getAttribute("error")!=null)
						    {
						    	%><p style="color:red" <%=request.getAttribute("error") %>></p>
						   
						   <%  }
						
						
						%>
				</div>
			</div>
			<button type="submit">Submit</button>
	</form>
</div>

</body>
</html>