<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
</head>
<body>
    <div align="center">
        <h2>Admin Login</h2>
        <span style="color:red">${error}</span>
        <form action="login" method="post">
            <table border="0">
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" required="required"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" required="required"/></td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><input type="submit" value="Login"/></td>
                </tr>
            </table>
        </form>
        <p><a href="${pageContext.request.contextPath}/">Back to Home</a></p>
    </div>
</body>
</html>
