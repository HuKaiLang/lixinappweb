<%--
  Created by IntelliJ IDEA.
  User: 11864
  Date: 2019/12/11
  Time: 14:06
  To change this template use File | Settings | File Templates.
  ?????????????????????????
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ page import="edu.lixin.servlet.UserLogin" %>
<%@ page import="edu.lixin.weixin.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
%>
<jsp:forward page="login.jsp"></jsp:forward>
<%
    }
%>