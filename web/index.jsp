<%@ page import="dk.stork.entities.EntityFactory" %>
<%@ page import="dk.stork.entities.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Johannes
  Date: 22-11-2017
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    StringBuilder sb = new StringBuilder();
    sb.append("<tr> <td>1</td><td>2</td><td>3</td> </tr>");
    List<User> users = EntityFactory.getModelObjects(User.class);
    for (User user : users) {
        sb.append("<tr><td>")
                .append(user.getId())
                .append("</td><td>")
                .append(user.getName())
                .append("</td><td>")
                .append(user.getMail())
                .append("</td></tr>");
    }
    EntityFactory.destroy();
    String body = sb.toString();
%>
<html>
<head>
    <title>Users:</title>
</head>
<body>
<table>
    <tr>
        <th> Id</th>
        <th> Name</th>
        <th> E-mail</th>
    </tr>
    <% out.println(body); %>
</table>
</body>
</html>
