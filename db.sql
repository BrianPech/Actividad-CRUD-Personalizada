CREATE TABLE IF NOT EXISTS tipo_poliza_vigencia(
    id INT PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    mensual DECIMAL(10,2) NOT NULL,
    trimestral DECIMAL(10,2) NOT NULL,
    anual DECIMAL(10,2) NOT NULL,
    descripcion TEXT,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE
);

INSERT INTO tipo_poliza_vigencia(tipo, mensual, trimestral, anual, descripcion, fecha_creacion, fecha_actualizacion, activo) VALUES
('Póliza de Vida', 250.00, 700.00, 2600.00, 'Cobertura para seguros de vida con distintas vigencias', NOW(), NOW(), TRUE),
('Póliza de Auto', 180.00, 500.00, 1900.00, 'Cobertura amplia para vehículos particulares', NOW(), NOW(), TRUE);