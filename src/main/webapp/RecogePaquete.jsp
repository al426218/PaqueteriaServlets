<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Recoger Paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<div>
  <%
    String mensaje = (String) request.getAttribute("mensaje");
  %>
  <img src="img.png" alt="PaqueteriaServlets" >
  <h2><%=mensaje%></h2>
  <a href="menuMensajeros.html">Menú</a>
</div>
</body>
</html>

