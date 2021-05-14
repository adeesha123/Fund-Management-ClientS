<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "model.FundManagement" %>
      
      
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Fund Management</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/research.js"></script>
</head>

<body>
		<div class="container"><div class="row"><div class="col-6">
		<h1>Fund Management</h1>
		<form id="formItem" name="formItem">
			userName:
			<input id="name" name="name" type="text"class="form-control form-control-sm">
			<br> 
			email:
			<input id="email" name="email" type="text"class="form-control form-control-sm">
			<br> 
			address:
			<input id="address" name="address" type="text"class="form-control form-control-sm">
			<br> 
			fundAmount:
			<input id="fundAmount" name="fundAmount" type="text"class="form-control form-control-sm">
			<br>
			interestedCategory:
			<input id="interestedCategory" name="interestedCategory" type="text"class="form-control form-control-sm">
			<br>
			<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
			<input type="hidden" id="fid" name="fid" value="">
		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>
		<div id="divItemsGrid">
		<%
			FundManagement fundObj = new FundManagement();
			out.print(fundObj.readFundmanagementData());
		%>
		</div>
		</div> </div> </div>
</body>
</html>