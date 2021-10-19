<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>history of WorkOrder validations</title>
    </head>
    <body>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>requestDate</th>
                    <th>workOrderType</th>
                    <th>department</th>
                    <th>valid</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${validationsEmpty}">
                    <tr><td colspan="5">No Validation History To Show</td></tr>
                </c:if>
                <c:forEach items="${validations}" var="validation">
                    <tr>
                        <td>${validation.id}</td>
                        <td>${validation.requestDate}</td>
                        <td>${validation.workOrderType}</td>
                        <td>${validation.department}</td>
                        <td>${validation.valid}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>