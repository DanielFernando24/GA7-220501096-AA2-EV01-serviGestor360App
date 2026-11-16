package com.servigestor360.dao; // Define el paquete DAO

import com.servigestor360.config.ConexionBD; // Importa la clase de conexión
import com.servigestor360.model.Solicitud; // Importa el modelo Solicitud

import java.sql.Connection; // Importa conexión SQL
import java.sql.PreparedStatement; // Permite consultas preparadas
import java.sql.ResultSet; // Permite leer resultados de consultas
import java.sql.SQLException; // Permite manejar errores SQL
import java.sql.Timestamp; // Permite enviar fecha y hora a SQL
import java.util.ArrayList; // Permite crear listas dinámicas
import java.util.List; // Permite trabajar con listas

public class SolicitudDao { // Clase encargada de las operaciones con la tabla solicitud

    public boolean insertarSolicitud(Solicitud solicitud) { // Método para insertar solicitud, retorna true si se guardó

        String sql = "INSERT INTO solicitud (idCliente, fechaCreacion, descripcionProblema, direccionServicio, prioridad, observaciones) VALUES (?, ?, ?, ?, ?, ?)"; // Consulta SQL

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setInt(1, solicitud.getIdCliente()); // Asigna cliente
            statement.setTimestamp(2, Timestamp.valueOf(solicitud.getFechaCreacion())); // Asigna fecha de creación
            statement.setString(3, solicitud.getDescripcionProblema()); // Asigna descripción del problema
            statement.setString(4, solicitud.getDireccionServicio()); // Asigna dirección del servicio
            statement.setString(5, solicitud.getPrioridad()); // Asigna prioridad
            statement.setString(6, solicitud.getObservaciones()); // Asigna observaciones

            return statement.executeUpdate() > 0; // Ejecuta inserción y confirma que se guardó

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al insertar solicitud: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo guardar
        }
    }

    public List<Solicitud> listarSolicitudes() { // Método para listar solicitudes

        List<Solicitud> solicitudes = new ArrayList<>(); // Crea lista de solicitudes

        String sql = "SELECT * FROM solicitud"; // Consulta SQL

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql); // Prepara consulta
             ResultSet resultSet = statement.executeQuery()) { // Ejecuta consulta

            while (resultSet.next()) { // Recorre resultados

                solicitudes.add(construirSolicitud(resultSet)); // Agrega solicitud a la lista
            }

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al listar solicitudes: " + e.getMessage()); // Muestra error
        }

        return solicitudes; // Retorna lista
    }

    public List<Solicitud> listarSolicitudesConCliente() { // Método para listar solicitudes con el nombre del cliente

        List<Solicitud> solicitudes = new ArrayList<>(); // Crea lista de solicitudes

        String sql = "SELECT s.*, CONCAT(c.nombres, ' ', c.apellidos) AS nombreCliente "
                + "FROM solicitud s INNER JOIN cliente c ON s.idCliente = c.idCliente"; // Consulta SQL con JOIN

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql); // Prepara consulta
             ResultSet resultSet = statement.executeQuery()) { // Ejecuta consulta

            while (resultSet.next()) { // Recorre resultados

                Solicitud solicitud = construirSolicitud(resultSet); // Crea la solicitud

                solicitud.setNombreCliente(resultSet.getString("nombreCliente")); // Asigna nombre del cliente

                solicitudes.add(solicitud); // Agrega solicitud a la lista
            }

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al listar solicitudes con cliente: " + e.getMessage()); // Muestra error
        }

        return solicitudes; // Retorna lista
    }

    public Solicitud buscarSolicitudPorId(int idSolicitud) { // Método para buscar solicitud por id

        Solicitud solicitud = null; // Inicializa solicitud como nula

        String sql = "SELECT * FROM solicitud WHERE idSolicitud = ?"; // Consulta SQL con parámetro

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara consulta

            statement.setInt(1, idSolicitud); // Asigna id a buscar

            try (ResultSet resultSet = statement.executeQuery()) { // Ejecuta consulta

                if (resultSet.next()) { // Verifica si encontró solicitud

                    solicitud = construirSolicitud(resultSet); // Crea la solicitud
                }
            }

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al buscar solicitud: " + e.getMessage()); // Muestra error
        }

        return solicitud; // Retorna solicitud encontrada o null
    }

    public boolean actualizarSolicitud(Solicitud solicitud) { // Método para actualizar solicitud, retorna true si se actualizó

        String sql = "UPDATE solicitud SET descripcionProblema=?, direccionServicio=?, prioridad=?, observaciones=? WHERE idSolicitud=?"; // SQL update

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setString(1, solicitud.getDescripcionProblema()); // Asigna descripción del problema
            statement.setString(2, solicitud.getDireccionServicio()); // Asigna dirección del servicio
            statement.setString(3, solicitud.getPrioridad()); // Asigna prioridad
            statement.setString(4, solicitud.getObservaciones()); // Asigna observaciones
            statement.setInt(5, solicitud.getIdSolicitud()); // Asigna id para actualizar

            return statement.executeUpdate() > 0; // Ejecuta actualización y confirma que se modificó un registro

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al actualizar solicitud: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo actualizar
        }
    }

    public boolean eliminarSolicitud(int idSolicitud) { // Método para eliminar solicitud, retorna true si se eliminó

        String sql = "DELETE FROM solicitud WHERE idSolicitud=?"; // SQL delete

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setInt(1, idSolicitud); // Asigna id a eliminar

            return statement.executeUpdate() > 0; // Ejecuta eliminación y confirma que se borró un registro

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al eliminar solicitud: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo eliminar
        }
    }

    private Solicitud construirSolicitud(ResultSet resultSet) throws SQLException { // Método que arma una solicitud desde una fila

        Solicitud solicitud = new Solicitud(); // Crea objeto solicitud

        solicitud.setIdSolicitud(resultSet.getInt("idSolicitud")); // Asigna id
        solicitud.setIdCliente(resultSet.getInt("idCliente")); // Asigna cliente
        solicitud.setFechaCreacion(resultSet.getTimestamp("fechaCreacion").toLocalDateTime()); // Asigna fecha de creación
        solicitud.setDescripcionProblema(resultSet.getString("descripcionProblema")); // Asigna descripción del problema
        solicitud.setDireccionServicio(resultSet.getString("direccionServicio")); // Asigna dirección del servicio
        solicitud.setPrioridad(resultSet.getString("prioridad")); // Asigna prioridad
        solicitud.setObservaciones(resultSet.getString("observaciones")); // Asigna observaciones

        return solicitud; // Retorna la solicitud
    }
}
