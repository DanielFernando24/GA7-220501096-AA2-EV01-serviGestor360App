<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- Configura JSP --%>

<%@ page import="com.servigestor360.model.Cliente" %> <%-- Importa la clase Cliente --%>

<%
    Cliente cliente = (Cliente) request.getAttribute("cliente"); // Obtiene cliente enviado desde Servlet

    boolean editar = cliente != null; // Determina si se está editando o creando
%>

<!DOCTYPE html> <!-- Define HTML5 -->

<html> <!-- Inicio HTML -->

<head> <!-- Inicio head -->

    <title>Formulario Cliente</title> <!-- Título de página -->

</head> <!-- Fin head -->

<body> <!-- Inicio body -->

<h1><%= editar ? "Editar Cliente" : "Registrar Cliente" %></h1> <!-- Muestra título dinámico -->

<form action="clientes" method="post"> <!-- Formulario que envía datos al Servlet por POST -->

    <% if (editar) { %> <!-- Si está editando, incluye id oculto -->

        <input type="hidden" name="idCliente" value="<%= cliente.getIdCliente() %>"> <!-- Campo oculto con id -->

    <% } %> <!-- Fin condición -->

    <label>Nombres:</label> <!-- Etiqueta nombres -->
    <input type="text" name="nombres" value="<%= editar ? cliente.getNombres() : "" %>" required> <!-- Campo nombres -->
    <br><br> <!-- Salto visual -->

    <label>Apellidos:</label> <!-- Etiqueta apellidos -->
    <input type="text" name="apellidos" value="<%= editar ? cliente.getApellidos() : "" %>" required> <!-- Campo apellidos -->
    <br><br> <!-- Salto visual -->

    <label>Tipo Documento:</label> <!-- Etiqueta tipo documento -->
    <input type="text" name="tipoDocumento" value="<%= editar ? cliente.getTipoDocumento() : "" %>" required> <!-- Campo tipo documento -->
    <br><br> <!-- Salto visual -->

    <label>Número Documento:</label> <!-- Etiqueta número documento -->
    <input type="text" name="numeroDocumento" value="<%= editar ? cliente.getNumeroDocumento() : "" %>" required> <!-- Campo número documento -->
    <br><br> <!-- Salto visual -->

    <label>Teléfono:</label> <!-- Etiqueta teléfono -->
    <input type="text" name="telefono" value="<%= editar ? cliente.getTelefono() : "" %>"> <!-- Campo teléfono -->
    <br><br> <!-- Salto visual -->

    <label>Correo:</label> <!-- Etiqueta correo -->
    <input type="email" name="correoElectronico" value="<%= editar ? cliente.getCorreoElectronico() : "" %>"> <!-- Campo correo -->
    <br><br> <!-- Salto visual -->

    <label>Dirección:</label> <!-- Etiqueta dirección -->
    <input type="text" name="direccion" value="<%= editar ? cliente.getDireccion() : "" %>"> <!-- Campo dirección -->
    <br><br> <!-- Salto visual -->

    <button type="submit">Guardar</button> <!-- Botón para enviar formulario -->

</form> <!-- Fin formulario -->

<br> <!-- Salto visual -->

<a href="clientes">Volver al listado</a> <!-- Enlace para volver -->

</body> <!-- Fin body -->

</html> <!-- Fin HTML -->
