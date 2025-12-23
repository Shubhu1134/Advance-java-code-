<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
<%@include file="__bootstrap_lib.jsp" %>
<style>
  .form-container{
     width:30%;
     min-height:250px;
     box-shadow: 10px 10px 10px grey;
  }
</style>
</head>
<body>
   <div class="form-control d-flex justify-content-center align-items-center vh-100">
      <div class="form-container">
        <h1 class="bg-danger text-white text-center p-2">Sign here</h1>
        <form action="${pageContext.request.contextPath}/admin/sign-in" method="post">
          <div class="form-group">
            <input name="email" type="email" placeholder="Enter email id " class="form-control"/>
          </div>
          <div class="form-group">
            <input name="password" type="password" placeholder="Enter password" class="form-control"/>
          </div>
          <div class="form-group">
            <button type="submit" class="btn btn-secondary">Submit</button>
          </div>
          <div class="form-group">
            <%// http://localhost:8080/testmvc/create-new-account %>
             <a href="${pageContext.request.contextPath}/create-new-account">Create new account ?</a>
          </div>
        </form>
      </div>
   </div>
</body>
</html>









