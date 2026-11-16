package com.servigestor360.dao; // Define el paquete DAO

import com.servigestor360.config.ConexionBD; // Importa la clase de conexión
import com.servigestor360.model.Cliente; // Importa el modelo Cliente

import java.sql.Connection; // Importa conexión SQL
import java.sql.PreparedStatement; // Permite consultas preparadas
import java.sql.ResultSet; // Permite leer resultados de consultas
import java.sql.SQLException; // Permite manejar errores SQL
import java.util.ArrayList; // Permite crear listas dinámicas
import java.util.List; // Permite trabajar con listas

public class ClienteDao { // Clase encargada de las operaciones con la tabla cliente

    public boolean insertarCliente(Cliente cliente) { // Método para insertar cliente, retorna true si se guardó

        String sql = "INSERT INTO cliente (nombres, apellidos, tipoDocumento, numeroDocumento, telefono, correoElectronico, direccion, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"; // Consulta SQL

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setString(1, cliente.getNombres()); // Asigna nombres
            statement.setString(2, cliente.getApellidos()); // Asigna apellidos
            statement.setString(3, cliente.getTipoDocumento()); // Asigna tipo documento
            statement.setString(4, cliente.getNumeroDocumento()); // Asigna número documento
            statement.setString(5, cliente.getTelefono()); // Asigna teléfono
            statement.setString(6, cliente.getCorreoElectronico()); // Asigna correo
            statement.setString(7, cliente.getDireccion()); // Asigna dirección
            statement.setBoolean(8, cliente.isActivo()); // Asigna estado

            return statement.executeUpdate() > 0; // Ejecuta inserción y confirma que se guardó

        } catch (SQLException e) { // Captura errores SQL, por ejemplo documento repetido

            System.out.println("Error al insertar cliente: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo guardar
        }
    }

    public List<Cliente> listarClientes() { // Método para listar clientes

        List<Cliente> clientes = new ArrayList<>(); // Crea lista de clientes

        String sql = "SELECT * FROM cliente"; // Consulta SQL

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql); // Prepara consulta
             ResultSet resultSet = statement.executeQuery()) { // Ejecuta consulta

            while (resultSet.next()) { // Recorre resultados

                Cliente cliente = new Cliente(); // Crea objeto Cliente

                cliente.setIdCliente(resultSet.getInt("idCliente")); // Asigna id
                cliente.setNombres(resultSet.getString("nombres")); // Asigna nombres
                cliente.setApellidos(resultSet.getString("apellidos")); // Asigna apellidos
                cliente.setTipoDocumento(resultSet.getString("tipoDocumento")); // Asigna tipo documento
                cliente.setNumeroDocumento(resultSet.getString("numeroDocumento")); // Asigna número documento
                cliente.setTelefono(resultSet.getString("telefono")); // Asigna teléfono
                cliente.setCorreoElectronico(resultSet.getString("correoElectronico")); // Asigna correo
                cliente.setDireccion(resultSet.getString("direccion")); // Asigna dirección
                cliente.setActivo(resultSet.getBoolean("activo")); // Asigna estado

                clientes.add(cliente); // Agrega cliente a la lista
            }

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al listar clientes: " + e.getMessage()); // Muestra error
        }

        return clientes; // Retorna lista
    }

    public Cliente buscarClientePorId(int idCliente) { // Método para buscar cliente por id

        Cliente cliente = null; // Inicializa cliente como nulo

        String sql = "SELECT * FROM cliente WHERE idCliente = ?"; // Consulta SQL con parámetro

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara consulta

            statement.setInt(1, idCliente); // Asigna id a buscar

            ResultSet resultSet = statement.executeQuery(); // Ejecuta consulta

            if (resultSet.next()) { // Verifica si encontró cliente

                cliente = new Cliente(); // Crea objeto cliente

                cliente.setIdCliente(resultSet.getInt("idCliente")); // Asigna id
                cliente.setNombres(resultSet.getString("nombres")); // Asigna nombres
                cliente.setApellidos(resultSet.getString("apellidos")); // Asigna apellidos
                cliente.setTipoDocumento(resultSet.getString("tipoDocumento")); // Asigna tipo documento
                cliente.setNumeroDocumento(resultSet.getString("numeroDocumento")); // Asigna número documento
                cliente.setTelefono(resultSet.getString("telefono")); // Asigna teléfono
                cliente.setCorreoElectronico(resultSet.getString("correoElectronico")); // Asigna correo
                cliente.setDireccion(resultSet.getString("direccion")); // Asigna dirección
                cliente.setActivo(resultSet.getBoolean("activo")); // Asigna estado
            }

        } catch (SQLException e) { // Captura errores SQL

            System.out.println("Error al buscar cliente: " + e.getMessage()); // Muestra error
        }

        return cliente; // Retorna cliente encontrado o null
    }

    public boolean actualizarCliente(Cliente cliente) { // Método para actualizar cliente, retorna true si se actualizó

        String sql = "UPDATE cliente SET nombres=?, apellidos=?, tipoDocumento=?, numeroDocumento=?, telefono=?, correoElectronico=?, direccion=?, activo=? WHERE idCliente=?"; // SQL update

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setString(1, cliente.getNombres()); // Asigna nombres
            statement.setString(2, cliente.getApellidos()); // Asigna apellidos
            statement.setString(3, cliente.getTipoDocumento()); // Asigna tipo documento
            statement.setString(4, cliente.getNumeroDocumento()); // Asigna número documento
            statement.setString(5, cliente.getTelefono()); // Asigna teléfono
            statement.setString(6, cliente.getCorreoElectronico()); // Asigna correo
            statement.setString(7, cliente.getDireccion()); // Asigna dirección
            statement.setBoolean(8, cliente.isActivo()); // Asigna estado
            statement.setInt(9, cliente.getIdCliente()); // Asigna id para actualizar

            return statement.executeUpdate() > 0; // Ejecuta actualización y confirma que se modificó un registro

        } catch (SQLException e) { // Captura errores SQL, por ejemplo documento repetido

            System.out.println("Error al actualizar cliente: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo actualizar
        }
    }

    public boolean eliminarCliente(int idCliente) { // Método para eliminar cliente, retorna true si se eliminó

        String sql = "DELETE FROM cliente WHERE idCliente=?"; // SQL delete

        try (Connection conexion = ConexionBD.obtenerConexion(); // Abre conexión
             PreparedStatement statement = conexion.prepareStatement(sql)) { // Prepara SQL

            statement.setInt(1, idCliente); // Asigna id a eliminar

            return statement.executeUpdate() > 0; // Ejecuta eliminación y confirma que se borró un registro

        } catch (SQLException e) { // Captura errores SQL, por ejemplo si el cliente tiene solicitudes asociadas

            System.out.println("Error al eliminar cliente: " + e.getMessage()); // Muestra error

            return false; // Indica que no se pudo eliminar
        }
    }
}