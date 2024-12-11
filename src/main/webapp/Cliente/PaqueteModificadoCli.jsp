<%@ page import="org.json.simple.JSONObject" %><%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 11/12/2024
  Time: 13:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modificar Paquetes</title>
    <link rel="stylesheet" href="../styles.css">
</head>
<body>
<%
    String codcli = (String) request.getAttribute("codcli");
    JSONObject paquete = (JSONObject) request.getAttribute("paquete");
%>
<img src="../img.png" alt="PaqueteriaServlets" >
<h2>Enhorabuena, <%= codcli%>, has modificado el paquete con código: <%= paquete.get("codPaquete")%></h2>
<a href="../menuClientes.html">Menú</a>
</body>
</html>
