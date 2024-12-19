<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Formulario recogedor de paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >
<%
  String codcli = (String) session.getAttribute("codcli");
  if (codcli == null) {
    response.sendRedirect("index.html");
    return;
  }
%>
<h2>Formulario recoger paquetes</h2>
<form  action="RecogePaqueteMensajero" method="GET">
  <table>
    <tr>
      <th colspan="2">Codigo del paquete:</th>
      <td colspan="2"><input required type="text" name="codPaq"></td>
    </tr>

    <tr><td colspan="4"><input type="submit" value="Recoger Paquete"></td></tr>
  </table>
</form>
</body>
</html>