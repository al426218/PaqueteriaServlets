<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario modificar paquetes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<%
    String codcli = (String) session.getAttribute("codcli");
    if (codcli == null) {
        response.sendRedirect("index.html");
        return;
    }
%>
<img src="img.png" alt="PaqueteriaServlets" >

<h1>Formulario modificar paquetes</h1>
<form action="ModificarPaqueteCli" method="GET">
    <table>
        <tr>
            <th colspan="2">Código del paquete:</th>
            <td colspan="2"><input required type="text" name="codPaq"></td>
        </tr>
        <tr>
            <th>Nuevo CP Origen:</th>
            <td><input required type="text" name="CPOrigen"></td>
            <th>Nuevo CP Destino:</th>
            <td><input required type="text" name="CPDestino"></td>
        </tr>
        <tr>
            <th>Nuevo Peso:</th>
            <td><input required type="text" name="peso"></td>
        </tr>
        <tr>
            <td colspan="4"><input type="submit" value="Modificar Paquete"></td>
        </tr>
    </table>
</form>

</body>
</html>
