<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 07/12/2024
  Time: 13:47
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Listar paquetes enviados</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div>
  <img src="img.png" alt="PaqueteriaServlets">
  <h2>Lista de paquetes enviados</h2>
  <ul>

    <%

      JSONArray paquetes = (JSONArray) request.getAttribute("paquetes");
      if (paquetes == null || paquetes.isEmpty()) {
    %>
    <p>No hay paquetes en ese código postal.</p>
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
  <p><a href="menuMensajeros.html">Menú</a></p>
</div>
</body>
</html>
