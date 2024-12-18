<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Formulario retirador de paquetes</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >
<%  String codcli = (String) session.getAttribute("codcli");
  if(codcli == null){%>
  <h2>Ha habido un error al cargar la pagina</h2>
  <li><a href="index.html">Volver al home</a></li>
<% }else{%>
  <h2>Formulario retirar paquetes</h2>
  <form  action="RetiraPaqueteCli" method="GET">
    <table>
      <tr>
        <th colspan="2">Codigo del paquete:</th>
        <td colspan="2"><input required type="text" name="codPaquete"></td>
      </tr>

      <tr><td colspan="4"><input type="submit" value="Retirar Paquete"></td></tr>
    </table>
  </form>
<%}%>
</body>
</html>