<%--
  Created by IntelliJ IDEA.
  User: Manu
  Date: 07/12/2024
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <img src="img.png" alt="PaqueteriaServlets" >
    <%
        String codcli = (String) request.getAttribute("codcli");
    %>
<h2>Hasta luego <%= codcli%></h2>
<h3>Te estaremos esperando</h3>
</body>
</html>
