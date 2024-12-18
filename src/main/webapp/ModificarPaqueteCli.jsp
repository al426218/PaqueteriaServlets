<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Formulario modificador paquetes</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<img src="img.png" alt="PaqueteriaServlets" >
    <%  String codcli = (String) session.getAttribute("codcli");
        if(codcli == null){%>
    <h2>Ha habido un error al cargar la pagina</h2>
    <li><a href="index.html">Volver al home</a></li>
    <% }else{%>
    <h1>Formulario modificar paquetes</h1>
    <form  action="ModificarPaqueteCli" method="GET">
        <table>
            <tr>
                <th colspan="2">Codigo del paquete:</th>
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
            <tr><td colspan="4"><input type="submit" value="Modificar Paquete"></td></tr>
        </table>
    </form>
    <%}%>
</body>
</html>