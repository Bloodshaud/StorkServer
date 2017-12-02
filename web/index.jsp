<%@ page import="dk.stork.entities.EntityFactory" %>
<%@ page import="dk.stork.entities.Group" %>
<%@ page import="dk.stork.entities.User" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Johannes
  Date: 22-11-2017
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    StringBuilder sb = new StringBuilder();
    List<User> users = EntityFactory.getModelObjects(User.class);
    for (User user : users) {
        sb.append("<tr><td>")
                .append(user.getId())
                .append("</td><td>")
                .append(user.getName())
                .append("</td><td>")
                .append(user.getMail())
                .append("</td><td>")
                .append(user.getPassword())
                .append("</td></tr>");
    }
    StringBuilder sb2 = new StringBuilder();
    List<Group> groups = EntityFactory.getModelObjects(Group.class);
    for (Group group : groups) {
        sb2.append("<tr><td>")
                .append(group.getId())
                .append("</td><td>")
                .append(group.toString().replace(":", "</td><td>"))
                .append("</td></tr>");
    }
    EntityFactory.destroy();
    String body = sb.toString();
    String body2 = sb2.toString();

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
        <th> Password</th>
    </tr>
    <% out.println(body); %>
</table>
<table>
    <tr>
        <th> Id</th>
        <th> Name</th>
        <th> Members</th>
    </tr>
    <% out.println(body2); %>
</table>
</body>
</html>
