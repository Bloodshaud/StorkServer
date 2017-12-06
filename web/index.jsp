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
<%--<%--%>
<%--StringBuilder sb = new StringBuilder();--%>
<%--List<User> users = EntityFactory.getModelObjects(User.class);--%>
<%--for (User user : users) {--%>
<%--sb.append("<tr><td>")--%>
<%--.append(user.getId())--%>
<%--.append("</td><td>")--%>
<%--.append(user.getName())--%>
<%--.append("</td><td>")--%>
<%--.append(user.getMail())--%>
<%--.append("</td><td>")--%>
<%--.append(user.getPassword())--%>
<%--.append("</td></tr>");--%>
<%--}--%>
<%--StringBuilder sb2 = new StringBuilder();--%>
<%--List<Group> groups = EntityFactory.getModelObjects(Group.class);--%>
<%--for (Group group : groups) {--%>
<%--sb2.append("<tr><td>")--%>
<%--.append(group.getId())--%>
<%--.append("</td><td>")--%>
<%--.append(group.toString().replace(":", "</td><td>"))--%>
<%--.append("</td></tr>");--%>
<%--}--%>
<%--String body = sb.toString();--%>
<%--String body2 = sb2.toString();--%>

<%--%>--%>
<html>
<body>
<% List<User> users = EntityFactory.getModelObjects(User.class);

    out.println("Users: " + EntityFactory.getModelObjects(User.class).size() + "<br/>");
    for (User user : users) {
        out.println("&emsp; <b>" + user.getName() + "</b><br/>");
        out.println("&emsp; - friendCount: " + user.getFriends().size() + "<br/>");
        out.println("&emsp; - groupsCount: " + user.getGroups().size() + "<br/>");
        out.println("&emsp; - activatedGroupsCount: " + user.getActiveGroups().size() + "<br/>");
    }
    List<Group> groups = EntityFactory.getModelObjects(Group.class);
    out.println("Groups: " + EntityFactory.getModelObjects(Group.class).size() + "<br/>");
    for (Group group : groups) {
        out.println("&emsp; <b>" + group.getName() + "</b><br/>");
        out.println("&emsp; - memberCount: " + group.getMembers().size() + "<br/>");
    }
%>

<%--<table>--%>
<%--<tr>--%>
<%--<th> Id</th>--%>
<%--<th> Name</th>--%>
<%--<th> E-mail</th>--%>
<%--<th> Password</th>--%>
<%--</tr>--%>
<%--<% out.println(body); %>--%>
<%--</table>--%>
<%--<table>--%>
<%--<tr>--%>
<%--<th> Id</th>--%>
<%--<th> Name</th>--%>
<%--<th> Members</th>--%>
<%--</tr>--%>
<%--<% out.println(body2); %>--%>
<%--</table>--%>
</body>
</html>
