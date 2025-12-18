<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <title>Admin Dashboard</title>
    </head>

    <body>
        <div align="center">
            <h2>Admin Dashboard</h2>
            <h3>Welcome, ${admin.username}</h3>

            <p><a href="category/list">Manage Categories</a></p>
            <p><a href="logout">Logout</a></p>
        </div>
    </body>

    </html>