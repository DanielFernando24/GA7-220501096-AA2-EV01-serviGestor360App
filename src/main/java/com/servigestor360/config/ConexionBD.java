package com.servigestor360.config; // Define el paquete donde estará la clase de conexión

import java.sql.Connection; // Importa la clase que representa la conexión a la base de datos
import java.sql.DriverManager; // Permite crear la conexión con MySQL
import java.sql.SQLException; // Permite capturar errores de SQL

public class ConexionBD { // Clase encargada de establecer la conexión con MySQL

    private static final String URL = "jdbc:mysql://localhost:3306/serviGestor360Db"; // Ruta de conexión a la base de datos

    private static final String USUARIO = "root"; // Usuario de MySQL

    private static final String CLAVE = "123456"; // Contraseña de MySQL, debe cambiarse según el equipo

    public static Connection obtenerConexion() { // Método que retorna una conexión a la base de datos

        Connection conexion = null; // Variable donde se guardará la conexión

        try { // Intenta realizar la conexión

            Class.forName("com.mysql.cj.jdbc.Driver"); // Carga el driver JDBC de MySQL

            conexion = DriverManager.getConnection(URL, USUARIO, CLAVE); // Establece conexión con la base de datos

        } catch (ClassNotFoundException e) { // Captura error si no encuentra el driver

            System.out.println("Error: No se encontró el driver de MySQL."); // Mensaje de error del driver

        } catch (SQLException e) { // Captura error si falla la conexión

            System.out.println("Error al conectar con la base de datos: " + e.getMessage()); // Muestra detalle del error
        }

        return conexion; // Retorna la conexión creada
    }
}