<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JHAMS | Update Personal Details</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/Css/addproduct.css">
</head>
<body>
	<div class="container">
		<h2>Update Presonal Details</h2>
		<c:set var="customer" value="${customerlist }"></c:set>
		<form action="<%=request.getContextPath() %>/updatecustomer" method="post">
		<div class="row">
			<div class="col">
				<label for="firstname">First Name:</label>
				
				<input type="text" id="firstname"  value="${customer.firstname }" name="firstname" required>
			</div>
			<div class="col">	
				<label for="lastname">Last Name:</label>
				<input type="text" id="lastname" value="${customer.lastname }" name="lastname" required>
			</div>
		</div>
		
		<div class="row">
			<div class="col">
				<label for="gender">Gender :</label>
				
				<input type="text" id="gender"  value="${customer.gender }" name="gender" required>
			</div>
			<div class="col">	
				<label for="birthday">Birthday</label>
				<input type="date" id="birthday" value="${customer.dob }" name="birthday" required>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<label for="phonenumber">Phone Number:</label> <input type="tel"
					id="phonenumber" value="${customer.phonenumber }" name="phonenumber" required>
			</div>
				
			<div class="col">
				<label for="email">Email:</label> <input type="email" id="email" value="${customer.email }"
					name="email" required>
			</div>
		</div>
			<div class="row">
				<label for="address">Address:</label><input type="text"
					id="address" value="${customer.address }" name="address" required>
			</div>
			
			<button type="submit">Update</button>
	
		</form>
	</div>

</body>