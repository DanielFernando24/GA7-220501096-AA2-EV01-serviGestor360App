USE serviGestor360Db;

CREATE TABLE IF NOT EXISTS solicitud (
    idSolicitud INT AUTO_INCREMENT PRIMARY KEY,
    idCliente INT NOT NULL,
    fechaCreacion DATETIME NOT NULL,
    descripcionProblema VARCHAR(255) NOT NULL,
    direccionServicio VARCHAR(150),
    prioridad VARCHAR(30),
    observaciones VARCHAR(255),
    FOREIGN KEY (idCliente) REFERENCES cliente(idCliente)
);
