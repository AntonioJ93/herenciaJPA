<%-- 
    Document   : index
    Created on : 7 oct. 2021, 13:36:31
    Author     : pryet
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>Empleado ${empleado}</p>
        <p>Proyectos de ${empleado.nombre}:</p>
        ${empleado.proyectos.size()}
        ${empleado.proyectos}
        <p>Proyecto ${proyecto}</p>
        <p>Empleados del proyecto ${proyecto.nombre}: </p>
        ${proyecto.empleados.size()}
        ${proyecto.empleados}
    </body>
</html>
