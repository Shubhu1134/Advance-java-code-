<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Category List</title>
        </head>

        <body>
            <div align="center">
                <h2>Category List</h2>
                <p><a href="add">Add New Category</a> | <a href="../dashboard">Back to Dashboard</a></p>
                <table border="1" cellpadding="5">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="category" items="${categories}">
                        <tr>
                            <td>${category.id}</td>
                            <td>${category.name}</td>
                            <td>${category.description}</td>
                            <td>
                                <a href="edit/${category.id}">Edit</a>
                                <a href="delete/${category.id}" onclick="return confirm('Are you sure?')">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </body>

        </html>