<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Formulario Mensajero listar paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >
<%  String codcli = (String) session.getAttribute("codcli");
  if(codcli == null){%>
<h2>Ha habido un error al cargar la pagina</h2>
<li><a href="index.html">Volver al home</a></li>
<% }else{%>
<h1>Formulario listar paquetes</h1>
<form  action="ListaPaquetesMensajeros" method="GET">
  <table>
    <tr>
      <th>CP Destino de los paquetes:</th>
      <td><input required type="text" name="CPDestino"></td>
    </tr>
    <tr><td colspan="4"><input type="submit" value="Listar Paquetes"></td></tr>
  </table>
</form>
<%}%>
</body>
</html>