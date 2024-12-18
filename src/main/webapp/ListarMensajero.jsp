<%@ page import="org.json.simple.JSONArray" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
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
