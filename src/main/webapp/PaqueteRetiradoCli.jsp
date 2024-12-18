<%@ page import="org.json.simple.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 11/12/2024
  Time: 13:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modificar Paquetes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >
<%
    JSONObject paquete = (JSONObject) request.getAttribute("paquete");
    if (paquete == null || paquete.isEmpty()) {
%>
    <h2>No hay paquetes</h2>
<%
    } else {

%>
<ul>
    <li>Paquete retirado: <%=paquete%></li>
</ul>
<%

    }
%>
<h3><a href="menuClientes.html">Menú</a></h3>
</body>
</html>
