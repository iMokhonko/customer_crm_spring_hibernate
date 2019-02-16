<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>customers list</title>
</head>
<body>

    <a href="add">add customer</a>

    <c:forEach var="customer" items="${customersList}">
        <hr>
        ${customer.firstName}
        | ${customer.lastName}
        | ${customer.email}
        | <a href="edit?customerId=${customer.customerId}">edit</a>
        | <a href="delete?customerId=${customer.customerId}">delete</a>
    </c:forEach>

</body>
</html>