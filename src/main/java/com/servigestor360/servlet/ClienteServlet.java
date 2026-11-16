package com.servigestor360.servlet; // Define el paquete servlet

import com.servigestor360.dao.ClienteDao; // Importa DAO de cliente
import com.servigestor360.model.Cliente; // Importa modelo Cliente

import jakarta.servlet.ServletException; // Importa excepción de Servlet
import jakarta.servlet.annotation.WebServlet; // Permite mapear el Servlet con anotación
import jakarta.servlet.http.HttpServlet; // Clase base para crear Servlets HTTP
import jakarta.servlet.http.HttpServletRequest; // Representa la solicitud del cliente
import jakarta.servlet.http.HttpServletResponse; // Representa la respuesta al cliente

import java.io.IOException; // Maneja errores de entrada/salida
import java.util.List; // Permite trabajar con listas

@WebServlet("/clientes") // Define la URL que atenderá este Servlet
public class ClienteServlet extends HttpServlet { // Clase Servlet para gestionar clientes

    private ClienteDao clienteDao; // Variable para usar las operaciones de base de datos

    @Override // Sobrescribe método init
    public void init() { // Método que se ejecuta al iniciar el Servlet

        clienteDao = new ClienteDao(); // Inicializa el DAO de clientes
    }

    @Override // Sobrescribe doGet
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Método para atender peticiones GET

        String accion = request.getParameter("accion"); // Obtiene la acción enviada por URL

        if (accion == null) { // Valida si no llega acción

            accion = "listar"; // Define listar como acción por defecto
        }

        switch (accion) { // Evalúa la acción recibida

            case "nuevo": // Caso para mostrar formulario nuevo

                mostrarFormularioNuevo(request, response); // Llama método que muestra formulario

                break; // Finaliza caso

            case "editar": // Caso para editar cliente

                mostrarFormularioEditar(request, response); // Llama método de edición

                break; // Finaliza caso

            case "eliminar": // Caso para eliminar cliente

                eliminarCliente(request, response); // Llama método eliminar

                break; // Finaliza caso

            default: // Si no coincide ninguna acción

                listarClientes(request, response); // Lista clientes
        }
    }

    @Override // Sobrescribe doPost
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Método para atender peticiones POST

        String idClienteTexto = request.getParameter("idCliente"); // Obtiene id desde formulario

        String nombres = request.getParameter("nombres"); // Obtiene nombres
        String apellidos = request.getParameter("apellidos"); // Obtiene apellidos
        String tipoDocumento = request.getParameter("tipoDocumento"); // Obtiene tipo documento
        String numeroDocumento = request.getParameter("numeroDocumento"); // Obtiene número documento
        String telefono = request.getParameter("telefono"); // Obtiene teléfono
        String correoElectronico = request.getParameter("correoElectronico"); // Obtiene correo
        String direccion = request.getParameter("direccion"); // Obtiene dirección

        Cliente cliente = new Cliente(); // Crea objeto cliente

        cliente.setNombres(nombres); // Asigna nombres
        cliente.setApellidos(apellidos); // Asigna apellidos
        cliente.setTipoDocumento(tipoDocumento); // Asigna tipo documento
        cliente.setNumeroDocumento(numeroDocumento); // Asigna número documento
        cliente.setTelefono(telefono); // Asigna teléfono
        cliente.setCorreoElectronico(correoElectronico); // Asigna correo
        cliente.setDireccion(direccion); // Asigna dirección
        cliente.setActivo(true); // Define cliente activo

        boolean guardado; // Indica si la operación se realizó correctamente

        if (idClienteTexto == null || idClienteTexto.isEmpty()) { // Valida si es nuevo cliente

            guardado = clienteDao.insertarCliente(cliente); // Inserta cliente

        } else { // Si tiene id, es actualización

            cliente.setIdCliente(Integer.parseInt(idClienteTexto)); // Convierte id a entero

            guardado = clienteDao.actualizarCliente(cliente); // Actualiza cliente
        }

        if (guardado) { // Si se guardó correctamente

            response.sendRedirect("clientes"); // Redirige al listado

        } else { // Si no se pudo guardar, por ejemplo por documento repetido

            response.sendRedirect("clientes?error=guardar"); // Redirige al listado con aviso de error
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Método para listar clientes

        List<Cliente> clientes = clienteDao.listarClientes(); // Consulta clientes

        request.setAttribute("clientes", clientes); // Envía lista a la JSP

        request.getRequestDispatcher("cliente-lista.jsp").forward(request, response); // Redirecciona a JSP
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Método para formulario nuevo

        request.getRequestDispatcher("cliente-form.jsp").forward(request, response); // Muestra formulario
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { // Método para formulario editar

        int idCliente = Integer.parseInt(request.getParameter("idCliente")); // Obtiene id del cliente

        Cliente cliente = clienteDao.buscarClientePorId(idCliente); // Busca cliente

        request.setAttribute("cliente", cliente); // Envía cliente a JSP

        request.getRequestDispatcher("cliente-form.jsp").forward(request, response); // Muestra formulario
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws IOException { // Método para eliminar cliente

        int idCliente = Integer.parseInt(request.getParameter("idCliente")); // Obtiene id

        boolean eliminado = clienteDao.eliminarCliente(idCliente); // Elimina cliente

        if (eliminado) { // Si se eliminó correctamente

            response.sendRedirect("clientes"); // Redirige al listado

        } else { // Si no se pudo eliminar, por ejemplo porque tiene solicitudes asociadas

            response.sendRedirect("clientes?error=eliminar"); // Redirige al listado con aviso de error
        }
    }
}