package com.servigestor360; // Define el paquete principal de la aplicación

import com.servigestor360.dao.ClienteDao; // Importa DAO de cliente
import com.servigestor360.dao.SolicitudDao; // Importa DAO de solicitud
import com.servigestor360.model.Cliente; // Importa modelo Cliente
import com.servigestor360.model.Solicitud; // Importa modelo Solicitud

import java.time.LocalDateTime; // Permite obtener la fecha y hora actual
import java.util.List; // Permite trabajar con listas
import java.util.Scanner; // Permite leer datos por consola

public class Main { // Clase principal que muestra el menú del módulo de clientes

    private static final Scanner scanner = new Scanner(System.in); // Lector de datos por consola

    private static final ClienteDao clienteDao = new ClienteDao(); // Objeto para operar con la tabla cliente

    private static final SolicitudDao solicitudDao = new SolicitudDao(); // Objeto para operar con la tabla solicitud

    public static void main(String[] args) { // Método de inicio del programa

        int opcion; // Variable para guardar la opción elegida

        do { // Repite el menú hasta que el usuario elija salir

            mostrarMenu(); // Muestra las opciones

            opcion = leerEntero("Seleccione una opción: "); // Lee la opción

            switch (opcion) { // Evalúa la opción elegida
                case 1: // Registrar cliente
                    registrarCliente(); // Llama método de inserción
                    break; // Finaliza caso
                case 2: // Listar clientes
                    listarClientes(); // Llama método de consulta
                    break; // Finaliza caso
                case 3: // Actualizar cliente
                    actualizarCliente(); // Llama método de actualización
                    break; // Finaliza caso
                case 4: // Eliminar cliente
                    eliminarCliente(); // Llama método de eliminación
                    break; // Finaliza caso
                case 5: // Registrar solicitud
                    registrarSolicitud(); // Llama método de inserción
                    break; // Finaliza caso
                case 6: // Listar solicitudes
                    listarSolicitudes(); // Llama método de consulta
                    break; // Finaliza caso
                case 7: // Listar solicitudes con cliente
                    listarSolicitudesConCliente(); // Llama método de consulta con JOIN
                    break; // Finaliza caso
                case 8: // Actualizar solicitud
                    actualizarSolicitud(); // Llama método de actualización
                    break; // Finaliza caso
                case 9: // Eliminar solicitud
                    eliminarSolicitud(); // Llama método de eliminación
                    break; // Finaliza caso
                case 0: // Salir
                    System.out.println("Saliendo de ServiGestor 360..."); // Mensaje de despedida
                    break; // Finaliza caso
                default: // Opción no válida
                    System.out.println("Opción no válida."); // Mensaje de error
            }

        } while (opcion != 0); // Termina cuando la opción es 0
    }

    private static void mostrarMenu() { // Método que imprime el menú

        System.out.println("\n===== SERVIGESTOR 360 ====="); // Título del menú
        System.out.println("1. Registrar cliente"); // Opción insertar
        System.out.println("2. Listar clientes"); // Opción consultar
        System.out.println("3. Actualizar cliente"); // Opción actualizar
        System.out.println("4. Eliminar cliente"); // Opción eliminar
        System.out.println("5. Registrar solicitud"); // Opción insertar solicitud
        System.out.println("6. Listar solicitudes"); // Opción consultar solicitudes
        System.out.println("7. Listar solicitudes con cliente"); // Opción consultar con JOIN
        System.out.println("8. Actualizar solicitud"); // Opción actualizar solicitud
        System.out.println("9. Eliminar solicitud"); // Opción eliminar solicitud
        System.out.println("0. Salir"); // Opción salir
    }

    private static void registrarSolicitud() { // Método para registrar una solicitud

        int idCliente = leerEntero("ID del cliente: "); // Pide el id del cliente

        if (clienteDao.buscarClientePorId(idCliente) == null) { // Verifica que el cliente exista

            System.out.println("No existe un cliente con ese ID."); // Mensaje de error

            return; // Sale del método
        }

        Solicitud solicitud = leerDatosSolicitud(); // Pide los datos de la solicitud

        solicitud.setIdCliente(idCliente); // Asigna el cliente

        solicitud.setFechaCreacion(LocalDateTime.now()); // Asigna la fecha y hora actual

        if (solicitudDao.insertarSolicitud(solicitud)) { // Inserta en la base de datos

            System.out.println("Solicitud registrada correctamente."); // Mensaje informativo

        } else { // Si no se pudo guardar

            System.out.println("No se pudo registrar la solicitud."); // Mensaje de error
        }
    }

    private static void listarSolicitudes() { // Método para listar solicitudes

        List<Solicitud> solicitudes = solicitudDao.listarSolicitudes(); // Consulta solicitudes

        if (solicitudes.isEmpty()) { // Valida si no hay registros

            System.out.println("No hay solicitudes registradas."); // Mensaje informativo

            return; // Sale del método
        }

        for (Solicitud solicitud : solicitudes) { // Recorre la lista

            System.out.println(solicitud.getIdSolicitud() + " | Cliente " + solicitud.getIdCliente() + " | " // Muestra ids
                    + solicitud.getFechaCreacion() + " | " // Muestra fecha de creación
                    + solicitud.getDescripcionProblema() + " | " // Muestra descripción del problema
                    + solicitud.getDireccionServicio() + " | " // Muestra dirección del servicio
                    + solicitud.getPrioridad() + " | " // Muestra prioridad
                    + solicitud.getObservaciones()); // Muestra observaciones
        }
    }

    private static void listarSolicitudesConCliente() { // Método para listar solicitudes con su cliente

        List<Solicitud> solicitudes = solicitudDao.listarSolicitudesConCliente(); // Consulta con JOIN

        if (solicitudes.isEmpty()) { // Valida si no hay registros

            System.out.println("No hay solicitudes registradas."); // Mensaje informativo

            return; // Sale del método
        }

        for (Solicitud solicitud : solicitudes) { // Recorre la lista

            System.out.println(solicitud.getIdSolicitud() + " | " // Muestra id de la solicitud
                    + solicitud.getNombreCliente() + " | " // Muestra nombre del cliente
                    + solicitud.getFechaCreacion() + " | " // Muestra fecha de creación
                    + solicitud.getDescripcionProblema() + " | " // Muestra descripción del problema
                    + solicitud.getDireccionServicio() + " | " // Muestra dirección del servicio
                    + solicitud.getPrioridad() + " | " // Muestra prioridad
                    + solicitud.getObservaciones()); // Muestra observaciones
        }
    }

    private static void actualizarSolicitud() { // Método para actualizar una solicitud

        int idSolicitud = leerEntero("ID de la solicitud a actualizar: "); // Pide el id

        Solicitud existente = solicitudDao.buscarSolicitudPorId(idSolicitud); // Busca la solicitud

        if (existente == null) { // Verifica que exista

            System.out.println("No existe una solicitud con ese ID."); // Mensaje de error

            return; // Sale del método
        }

        Solicitud solicitud = leerDatosSolicitud(); // Pide los nuevos datos

        solicitud.setIdSolicitud(idSolicitud); // Asigna el id a actualizar

        solicitud.setIdCliente(existente.getIdCliente()); // Mantiene el cliente

        if (solicitudDao.actualizarSolicitud(solicitud)) { // Actualiza en la base de datos

            System.out.println("Solicitud actualizada correctamente."); // Mensaje informativo

        } else { // Si no se pudo actualizar

            System.out.println("No se pudo actualizar la solicitud."); // Mensaje de error
        }
    }

    private static void eliminarSolicitud() { // Método para eliminar una solicitud

        int idSolicitud = leerEntero("ID de la solicitud a eliminar: "); // Pide el id

        if (solicitudDao.buscarSolicitudPorId(idSolicitud) == null) { // Verifica que exista

            System.out.println("No existe una solicitud con ese ID."); // Mensaje de error

            return; // Sale del método
        }

        if (solicitudDao.eliminarSolicitud(idSolicitud)) { // Elimina de la base de datos

            System.out.println("Solicitud eliminada correctamente."); // Mensaje informativo

        } else { // Si no se pudo eliminar

            System.out.println("No se pudo eliminar la solicitud."); // Mensaje de error
        }
    }

    private static Solicitud leerDatosSolicitud() { // Método que pide los datos de una solicitud

        Solicitud solicitud = new Solicitud(); // Crea objeto solicitud

        solicitud.setDescripcionProblema(leerTexto("Descripción del problema: ")); // Lee descripción del problema
        solicitud.setDireccionServicio(leerTexto("Dirección del servicio: ")); // Lee dirección del servicio
        solicitud.setPrioridad(leerTexto("Prioridad (Baja, Media, Alta): ")); // Lee prioridad
        solicitud.setObservaciones(leerTexto("Observaciones: ")); // Lee observaciones

        return solicitud; // Retorna la solicitud con los datos
    }

    private static void registrarCliente() { // Método para registrar un cliente

        Cliente cliente = leerDatosCliente(); // Pide los datos al usuario

        cliente.setActivo(true); // Define cliente activo

        if (clienteDao.insertarCliente(cliente)) { // Inserta en la base de datos

            System.out.println("Cliente registrado correctamente."); // Mensaje informativo

        } else { // Si no se pudo guardar

            System.out.println("No se pudo registrar el cliente. Verifique que el número de documento no esté repetido."); // Mensaje de error
        }
    }

    private static void listarClientes() { // Método para listar clientes

        List<Cliente> clientes = clienteDao.listarClientes(); // Consulta clientes

        if (clientes.isEmpty()) { // Valida si no hay registros

            System.out.println("No hay clientes registrados."); // Mensaje informativo

            return; // Sale del método
        }

        for (Cliente cliente : clientes) { // Recorre la lista

            System.out.println(cliente.getIdCliente() + " | " // Muestra id
                    + cliente.getNombres() + " " + cliente.getApellidos() + " | " // Muestra nombre completo
                    + cliente.getTipoDocumento() + " " + cliente.getNumeroDocumento() + " | " // Muestra documento
                    + cliente.getTelefono() + " | " // Muestra teléfono
                    + cliente.getCorreoElectronico() + " | " // Muestra correo
                    + cliente.getDireccion() + " | " // Muestra dirección
                    + (cliente.isActivo() ? "Activo" : "Inactivo")); // Muestra estado
        }
    }

    private static void actualizarCliente() { // Método para actualizar un cliente

        int idCliente = leerEntero("ID del cliente a actualizar: "); // Pide el id

        if (clienteDao.buscarClientePorId(idCliente) == null) { // Verifica que exista

            System.out.println("No existe un cliente con ese ID."); // Mensaje de error

            return; // Sale del método
        }

        Cliente cliente = leerDatosCliente(); // Pide los nuevos datos

        cliente.setIdCliente(idCliente); // Asigna el id a actualizar

        cliente.setActivo(true); // Mantiene cliente activo

        if (clienteDao.actualizarCliente(cliente)) { // Actualiza en la base de datos

            System.out.println("Cliente actualizado correctamente."); // Mensaje informativo

        } else { // Si no se pudo actualizar

            System.out.println("No se pudo actualizar el cliente. Verifique que el número de documento no esté repetido."); // Mensaje de error
        }
    }

    private static void eliminarCliente() { // Método para eliminar un cliente

        int idCliente = leerEntero("ID del cliente a eliminar: "); // Pide el id

        if (clienteDao.buscarClientePorId(idCliente) == null) { // Verifica que exista

            System.out.println("No existe un cliente con ese ID."); // Mensaje de error

            return; // Sale del método
        }

        if (clienteDao.eliminarCliente(idCliente)) { // Elimina de la base de datos

            System.out.println("Cliente eliminado correctamente."); // Mensaje informativo

        } else { // Si no se pudo eliminar

            System.out.println("No se pudo eliminar el cliente porque tiene solicitudes asociadas."); // Mensaje de error
        }
    }

    private static Cliente leerDatosCliente() { // Método que pide los datos de un cliente

        Cliente cliente = new Cliente(); // Crea objeto cliente

        cliente.setNombres(leerTexto("Nombres: ")); // Lee nombres
        cliente.setApellidos(leerTexto("Apellidos: ")); // Lee apellidos
        cliente.setTipoDocumento(leerTexto("Tipo de documento: ")); // Lee tipo de documento
        cliente.setNumeroDocumento(leerTexto("Número de documento: ")); // Lee número de documento
        cliente.setTelefono(leerTexto("Teléfono: ")); // Lee teléfono
        cliente.setCorreoElectronico(leerTexto("Correo electrónico: ")); // Lee correo
        cliente.setDireccion(leerTexto("Dirección: ")); // Lee dirección

        return cliente; // Retorna el cliente con los datos
    }

    private static String leerTexto(String mensaje) { // Método para leer texto

        System.out.print(mensaje); // Muestra el mensaje

        return scanner.nextLine().trim(); // Retorna el texto sin espacios extremos
    }

    private static int leerEntero(String mensaje) { // Método para leer un número entero

        while (true) { // Repite hasta recibir un número válido

            try { // Intenta convertir el texto

                return Integer.parseInt(leerTexto(mensaje)); // Retorna el número

            } catch (NumberFormatException e) { // Captura error de formato

                System.out.println("Debe ingresar un número válido."); // Mensaje de error
            }
        }
    }
}
