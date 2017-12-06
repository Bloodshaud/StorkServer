<%@ page import="dk.stork.entities.EntityFactory" %>
<%@ page import="dk.stork.entities.Group" %>
<%@ page import="dk.stork.entities.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Arrays" %>
<%--
  Created by IntelliJ IDEA.
  User: Johannes
  Date: 22-11-2017
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<body>
<% List<User> users = EntityFactory.getModelObjects(User.class);
    out.println("Users: " + EntityFactory.getModelObjects(User.class).size() + "<br/>");
    for (User user : users) {
        out.println("&emsp; <b>" + user.getName() + "</b><br/>");
        out.println("&emsp; - friendCount: " + (user.getFriends() != null ? user.getFriends().size() : "null") + "<br/>");
        out.println("&emsp; - groupsCount: " + (user.getGroups() != null ? user.getGroups().size(): "null") + "<br/>");
        out.println("&emsp; - activatedGroupsCount: " + user.getActiveGroups().size() + "<br/>");
    }
    List<Group> groups = EntityFactory.getModelObjects(Group.class);
    out.println("Groups: " + EntityFactory.getModelObjects(Group.class).size() + "<br/>");
    for (Group group : groups) {
        out.println("&emsp; <b>" + group.getName() + "</b><br/>");
        out.println("&emsp; - owner: " + (group.getOwner() != null ? group.getOwner().getName(): "null") + "<br/>");
        out.println("&emsp; - memberCount: " + group.getMembers().size() + "<br/>");
    }
%>
</body>
</html>
