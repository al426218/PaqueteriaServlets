<%@ page import="org.json.simple.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 07/12/2024
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Enviar Paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div>

  <%
    String codcli = (String) session.getAttribute("codcli");
    JSONObject paquete = (JSONObject) request.getAttribute("paquete");
  %>
  <img src="img.png" alt="PaqueteriaServlets" >
  <h2>Enhorabuena, <%= codcli%>, has enviado el paquete con código: <%= paquete.get("codPaquete")%></h2>
  <h3><a href="menuClientes.html">Menú</a></h3>

</div>
</body>
</html>

