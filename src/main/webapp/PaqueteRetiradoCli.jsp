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
        // Obtener los valores directamente del paquete
        String codcli = (String) paquete.get("codcli");
        long codpaquete = (long) paquete.get("codPaquete") ;
        String codmensa = (String) paquete.get("codMensajero");
        String CPDestino = (String) paquete.get("CPDestino");
        String CPOrigen = (String) paquete.get("CPOrigen");
        String fechaenv = (String) paquete.get("fechaEnvio");
        String fecharecogida = (String) paquete.get("fechaRecogida");
        double peso = (double) paquete.get("peso");
%>
<ul>
    <li>Codcli: <%=codcli  %></li>
    <li>Código del paquete: <%=codpaquete  %></li>
    <li>CodMensajero: <%=codmensa  %></li>
    <li>CPDestino: <%=CPDestino  %></li>
    <li>CPOrigen: <%=CPOrigen  %></li>
    <li>Fecha envio: <%=fechaenv  %></li>
    <li>Fecha de recogida: <%=fecharecogida  %></li>
    <li>Peso: <%=peso  %></li>
</ul>
<%

    }
%>
<h3><a href="menuClientes.html">Menú</a></h3>
</body>
</html>
