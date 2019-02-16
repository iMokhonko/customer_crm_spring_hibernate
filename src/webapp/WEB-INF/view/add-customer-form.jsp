<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>add customer</title>
</head>
<body>

    <form:form method="post" modelAttribute="customer" action="">

        First name:<br>
        <form:input path="firstName" type="text"/>
        <form:errors path="firstName"/>
        <hr>

        Last name:<br>
        <form:input path="lastName" type="text"/>
        <form:errors path="lastName"/>
        <hr>

        Email:<br>
        <form:input path="email" type="text"/>
        <form:errors path="email"/>
        <hr>

        <form:button type="submit">add customer</form:button>

    </form:form>

</body>
</html>
