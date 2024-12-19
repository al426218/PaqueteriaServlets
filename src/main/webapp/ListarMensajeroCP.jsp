<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Formulario Mensajero listar paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >

<%

  response.setContentType("text/html");
  response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, proxy-revalidate, max-age=0");
  response.setHeader("Pragma", "no-cache");
  response.setDateHeader("Expires", 0);
  String codcli = (String) session.getAttribute("codcli");
  if (codcli == null) {
    response.sendRedirect("index.html");
    return;
  }
%>

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
</body>
</html>