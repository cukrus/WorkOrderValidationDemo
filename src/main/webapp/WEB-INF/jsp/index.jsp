<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>WorkOrder validation</title>
    </head>
    <body>
        <a href="./history">View WorkOrder Validation History</a>
        <form:form method="post" modelAttribute="validationRequest">
            <form:label path="request">request JSON: </form:label> <form:textarea path="request" rows="10" cols="20" />
            <input type="submit" value="submit"/>
        </form:form>

        <c:if test="${requestProcessed}">
            <div>Validation response: <textarea readonly> ${responseStr} </textarea></div>
        </c:if>
    </body>
</html>