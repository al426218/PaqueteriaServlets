<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enviar Paquetes</title>
  <link rel="stylesheet" href="../styles.css">
</head>
<body>
<div>
  <img src="../img.png" alt="PaqueteriaServlets" >
  <h2>Formulario de envío de paquetes</h2>
  <form  action="EnvioPaquetesCli" method="GET">
    <table>
      <tr>
        <th>CP Origen:</th>
        <td><input required type="text" name="CPOrigen"></td>
        <th>CP Destino:</th>
        <td><input required type="text" name="CPDestino"></td>
      </tr>
      <tr>
        <th>Peso:</th>
        <td><input required type="text" name="peso"></td>
      </tr>
      <tr><td colspan="4"><input type="submit" value="Enviar Paquete"></td></tr>
    </table>
  </form>
</div>
</body>
</html>
