package com.servigestor360.model; // Define el paquete del modelo

import java.time.LocalDateTime; // Permite manejar fecha y hora

public class Solicitud { // Clase que representa la entidad solicitud de servicio

    private int idSolicitud; // Identificador único de la solicitud
    private int idCliente; // Cliente al que pertenece la solicitud
    private LocalDateTime fechaCreacion; // Fecha y hora en que se creó la solicitud
    private String descripcionProblema; // Descripción del problema reportado
    private String direccionServicio; // Dirección donde se prestará el servicio
    private String prioridad; // Prioridad de la solicitud
    private String observaciones; // Observaciones adicionales
    private String nombreCliente; // Nombre del cliente, solo se llena al listar con cliente

    public Solicitud() { // Constructor vacío
    }

    public int getIdSolicitud() { return idSolicitud; } // Retorna idSolicitud

    public void setIdSolicitud(int idSolicitud) { this.idSolicitud = idSolicitud; } // Modifica idSolicitud

    public int getIdCliente() { return idCliente; } // Retorna idCliente

    public void setIdCliente(int idCliente) { this.idCliente = idCliente; } // Modifica idCliente

    public LocalDateTime getFechaCreacion() { return fechaCreacion; } // Retorna fechaCreacion

    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; } // Modifica fechaCreacion

    public String getDescripcionProblema() { return descripcionProblema; } // Retorna descripcionProblema

    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; } // Modifica descripcionProblema

    public String getDireccionServicio() { return direccionServicio; } // Retorna direccionServicio

    public void setDireccionServicio(String direccionServicio) { this.direccionServicio = direccionServicio; } // Modifica direccionServicio

    public String getPrioridad() { return prioridad; } // Retorna prioridad

    public void setPrioridad(String prioridad) { this.prioridad = prioridad; } // Modifica prioridad

    public String getObservaciones() { return observaciones; } // Retorna observaciones

    public void setObservaciones(String observaciones) { this.observaciones = observaciones; } // Modifica observaciones

    public String getNombreCliente() { return nombreCliente; } // Retorna nombreCliente

    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; } // Modifica nombreCliente
}
