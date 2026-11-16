<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- Configura la página JSP --%>

<%@ page import="java.util.List" %> <%-- Importa List --%>

<%@ page import="com.servigestor360.model.Cliente" %> <%-- Importa Cliente --%>

<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes"); // Obtiene lista enviada desde Servlet
%>

<!DOCTYPE html> <!-- Define HTML5 -->

<html> <!-- Inicio HTML -->

<head> <!-- Inicio head -->

    <title>Listado de Clientes</title> <!-- Título de página -->

</head> <!-- Fin head -->

<body> <!-- Inicio body -->

<h1>Listado de Clientes</h1> <!-- Título principal -->

<% if ("eliminar".equals(request.getParameter("error"))) { %> <!-- Si falló la eliminación, muestra el aviso -->

    <p style="color:red;">No se pudo eliminar el cliente porque tiene solicitudes asociadas.</p> <!-- Mensaje de error -->

<% } %> <!-- Fin condición -->

<% if ("guardar".equals(request.getParameter("error"))) { %> <!-- Si falló el guardado, muestra el aviso -->

    <p style="color:red;">No se pudo guardar el cliente. Verifique que el número de documento no esté repetido.</p> <!-- Mensaje de error -->

<% } %> <!-- Fin condición -->

<a href="clientes?accion=nuevo">Registrar nuevo cliente</a> <!-- Enlace para crear nuevo cliente -->

<br><br> <!-- Espacio visual -->

<table border="1"> <!-- Crea tabla con borde -->

    <tr> <!-- Fila de encabezado -->

        <th>ID</th> <!-- Columna ID -->
        <th>Nombres</th> <!-- Columna nombres -->
        <th>Apellidos</th> <!-- Columna apellidos -->
        <th>Documento</th> <!-- Columna documento -->
        <th>Teléfono</th> <!-- Columna teléfono -->
        <th>Correo</th> <!-- Columna correo -->
        <th>Dirección</th> <!-- Columna dirección -->
        <th>Acciones</th> <!-- Columna acciones -->

    </tr> <!-- Fin encabezado -->

    <% if (clientes != null) { %> <!-- Valida si la lista no es nula -->

        <% for (Cliente cliente : clientes) { %> <!-- Recorre la lista de clientes -->

            <tr> <!-- Fila por cada cliente -->

                <td><%= cliente.getIdCliente() %></td> <!-- Muestra id -->
                <td><%= cliente.getNombres() %></td> <!-- Muestra nombres -->
                <td><%= cliente.getApellidos() %></td> <!-- Muestra apellidos -->
                <td><%= cliente.getNumeroDocumento() %></td> <!-- Muestra documento -->
                <td><%= cliente.getTelefono() %></td> <!-- Muestra teléfono -->
                <td><%= cliente.getCorreoElectronico() %></td> <!-- Muestra correo -->
                <td><%= cliente.getDireccion() %></td> <!-- Muestra dirección -->

                <td> <!-- Columna acciones -->

                    <a href="clientes?accion=editar&idCliente=<%= cliente.getIdCliente() %>">Editar</a> <!-- Link editar -->

                    |

                    <a href="clientes?accion=eliminar&idCliente=<%= cliente.getIdCliente() %>"
                       onclick="return confirm('¿Desea eliminar este cliente?')">Eliminar</a> <!-- Link eliminar -->

                </td> <!-- Fin acciones -->

            </tr> <!-- Fin fila -->

        <% } %> <!-- Fin ciclo -->

    <% } %> <!-- Fin validación -->

</table> <!-- Fin tabla -->

<br> <!-- Salto visual -->

<a href="index.jsp">Volver al inicio</a> <!-- Enlace al inicio -->

</body> <!-- Fin body -->

</html> <!-- Fin HTML -->