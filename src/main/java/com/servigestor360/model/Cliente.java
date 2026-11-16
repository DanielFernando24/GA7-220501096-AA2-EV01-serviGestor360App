package com.servigestor360.model; // Define el paquete del modelo

public class Cliente { // Clase que representa la entidad cliente

    private int idCliente; // Identificador único del cliente
    private String nombres; // Nombres del cliente
    private String apellidos; // Apellidos del cliente
    private String tipoDocumento; // Tipo de documento del cliente
    private String numeroDocumento; // Número de documento del cliente
    private String telefono; // Teléfono del cliente
    private String correoElectronico; // Correo electrónico del cliente
    private String direccion; // Dirección del cliente
    private boolean activo; // Estado del cliente

    public Cliente() { // Constructor vacío
    }

    public Cliente(int idCliente, String nombres, String apellidos, String tipoDocumento,
                   String numeroDocumento, String telefono, String correoElectronico,
                   String direccion, boolean activo) { // Constructor con todos los datos

        this.idCliente = idCliente; // Asigna el id del cliente
        this.nombres = nombres; // Asigna nombres
        this.apellidos = apellidos; // Asigna apellidos
        this.tipoDocumento = tipoDocumento; // Asigna tipo de documento
        this.numeroDocumento = numeroDocumento; // Asigna número de documento
        this.telefono = telefono; // Asigna teléfono
        this.correoElectronico = correoElectronico; // Asigna correo electrónico
        this.direccion = direccion; // Asigna dirección
        this.activo = activo; // Asigna estado
    }

    public int getIdCliente() { return idCliente; } // Retorna idCliente

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } // Modifica idCliente

    public String getNombres() { return nombres; } // Retorna nombres

    public void setNombres(String nombres) { this.nombres = nombres; } // Modifica nombres

    public String getApellidos() { return apellidos; } // Retorna apellidos

    public void setApellidos(String apellidos) { this.apellidos = apellidos; } // Modifica apellidos

    public String getTipoDocumento() { return tipoDocumento; } // Retorna tipoDocumento

    public void setTipoDocumento(String tipoDocumento) { this.tipoDocumento = tipoDocumento; } // Modifica tipoDocumento

    public String getNumeroDocumento() { return numeroDocumento; } // Retorna numeroDocumento

    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; } // Modifica numeroDocumento

    public String getTelefono() { return telefono; } // Retorna telefono

    public void setTelefono(String telefono) { this.telefono = telefono; } // Modifica telefono

    public String getCorreoElectronico() { return correoElectronico; } // Retorna correoElectronico

    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; } // Modifica correoElectronico

    public String getDireccion() { return direccion; } // Retorna direccion

    public void setDireccion(String direccion) { this.direccion = direccion; } // Modifica direccion

    public boolean isActivo() { return activo; } // Retorna estado activo

    public void setActivo(boolean activo) { this.activo = activo; } // Modifica estado activo
}