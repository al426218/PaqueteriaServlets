<%@ page import="org.json.simple.JSONArray" %><%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 07/12/2024
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listar paquetes enviados</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<div>

    <img src="img.png" alt="PaqueteriaServlets" >
    <h2>Lista de paquetes enviados</h2>
    <ul>
        <%
            JSONArray paquetes = (JSONArray) request.getAttribute("paquetes");
            if (paquetes == null || paquetes.isEmpty()) {
        %>
        <li>No hay paquetes</li>
        <%
        } else {
            for (Object paquete : paquetes) {
        %>
        <li><%= paquete %></li>
        <%
                }
            }
        %>
    </ul>
    <h3><a href="menuClientes.html">Menú</a></h3>
</div>
</body>
</html>
