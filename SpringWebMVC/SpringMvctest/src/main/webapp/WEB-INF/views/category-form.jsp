<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Category Form</title>
        </head>

        <body>
            <div align="center">
                <h2>${category.id == null ? 'Add New' : 'Edit'} Category</h2>
                <form:form action="${pageContext.request.contextPath}/admin/category/save" method="post"
                    modelAttribute="category">
                    <form:hidden path="id" />
                    <table border="0">
                        <tr>
                            <td>Name:</td>
                            <td>
                                <form:input path="name" required="required" />
                            </td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td>
                                <form:input path="description" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center"><input type="submit" value="Save" /></td>
                        </tr>
                    </table>
                </form:form>
                <p><a href="${pageContext.request.contextPath}/admin/category/list">Back to List</a></p>
            </div>
        </body>

        </html>