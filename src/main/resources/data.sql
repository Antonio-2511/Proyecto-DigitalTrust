SET FOREIGN_KEY_CHECKS = 0;

-- ================================
-- PLAN
-- ================================
INSERT IGNORE INTO Plan (Nombre_plan, Beneficios, Precio, Fecha_inicio, Fecha_expiracion) VALUES
('Basico', 'Alertas basicas', 10.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Estandar', 'Alertas y reportes', 20.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Premium', 'Proteccion completa', 30.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Empresarial', 'Gestion avanzada', 40.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59');

-- ================================
-- ROLES
-- ================================
INSERT IGNORE INTO roles (name, display_name) VALUES
('ROLE_USER', 'Usuario'),
('ROLE_MODERATOR', 'Moderador'),
('ROLE_ADMIN', 'Administrador');

-- ================================
-- USERS
-- ================================
INSERT IGNORE INTO users (username, Contrasenia, Fecha_creacion, Telefono, Gmail, Plan_Nombre_plan) VALUES
('juan01', '$2a$10$m241PmcQs.9BZeAf9rH0NOzYCUeecn1MaQMyGIKetLEyY5m82BJN2', '2026-01-05 10:00:00', '600111222', 'juan@gmail.com', 'Basico'),
('maria02', '$2a$10$m241PmcQs.9BZeAf9rH0NOzYCUeecn1MaQMyGIKetLEyY5m82BJN2', '2026-01-06 11:30:00', '600333444', 'maria@gmail.com', 'Estandar'),
('carlos03', '$2a$10$m241PmcQs.9BZeAf9rH0NOzYCUeecn1MaQMyGIKetLEyY5m82BJN2', '2026-01-07 09:15:00', '600555666', 'carlos@gmail.com', 'Premium'),
('ana04', '$2a$10$m241PmcQs.9BZeAf9rH0NOzYCUeecn1MaQMyGIKetLEyY5m82BJN2', '2026-01-08 14:45:00', '600777888', 'ana@gmail.com', 'Empresarial');

-- ================================
-- ASIGNAR ROLES
-- ================================
UPDATE users SET role_id = (SELECT id FROM roles WHERE name = 'ROLE_USER')      WHERE username = 'juan01';
UPDATE users SET role_id = (SELECT id FROM roles WHERE name = 'ROLE_USER')      WHERE username = 'maria02';
UPDATE users SET role_id = (SELECT id FROM roles WHERE name = 'ROLE_MODERATOR') WHERE username = 'carlos03';
UPDATE users SET role_id = (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')     WHERE username = 'ana04';

-- ================================
-- REPORTE (antes que Advertencia porque Schema 2 la referencia)
-- ================================
INSERT IGNORE INTO Reporte (Titulo, Descripcion, Fecha_reporte, users_username) VALUES
('Reporte phishing', 'Correo fraudulento recibido', '2026-01-10 09:00:00', 'juan01'),
('Reporte SMS', 'Mensaje sospechoso', '2026-01-10 10:00:00', 'maria02'),
('Reporte llamada', 'Intento de estafa telefonica', '2026-01-10 11:00:00', 'carlos03'),
('Reporte web', 'Pagina insegura', '2026-01-10 12:00:00', 'ana04');

-- ================================
-- ADVERTENCIA
-- ================================
INSERT IGNORE INTO Advertencia (Titulo, Nivel_Criticidad, Descripcion, Fecha_de_envio, Es_emergencia, users_username) VALUES
('Phishing detectado', 3, 'Correo fraudulento', '2026-01-09 08:00:00', 1, 'juan01'),
('SMS sospechoso', 2, 'Mensaje con enlace', '2026-01-09 09:00:00', 0, 'maria02'),
('Intento de estafa', 3, 'Llamada falsa', '2026-01-09 10:00:00', 1, 'carlos03'),
('Web insegura', 1, 'Dominio no confiable', '2026-01-09 11:00:00', 0, 'ana04');

-- ================================
-- FUENTE CONFIABLE
-- ================================
INSERT IGNORE INTO Fuente_Confiable (Nombre_entidad, Tipo, Telefono, Email, Dominio, Advertencia_Id) VALUES
('INCIBE', 'Gobierno', '017', 'info@incibe.es', 'incibe.es', 1),
('Policia Nacional', 'Seguridad', '091', 'contacto@policia.es', 'policia.es', 2),
('Guardia Civil', 'Seguridad', '062', 'gc@guardiacivil.es', 'gc.es', 3),
('Google Safe', 'Tecnologia', '900123456', 'safe@google.com', 'google.com', 4);

-- ================================
-- MENSAJE
-- ================================
INSERT IGNORE INTO Mensaje (Contenido_texto, Origen, Nivel_riesgo, Resultado_analisis, fecha_analisis, users_username, Fuente_Confiable_Id_Fuente) VALUES
('Haz clic en este enlace', 'Email', 'Alto', 'Malicioso', '2026-01-09 12:00:00', 'juan01', 1),
('Gana un premio ahora', 'SMS', 'Medio', 'Sospechoso', '2026-01-09 12:10:00', 'maria02', 2),
('Verificacion de cuenta', 'Llamada', 'Alto', 'Malicioso', '2026-01-09 12:20:00', 'carlos03', 3),
('Oferta limitada', 'Web', 'Bajo', 'Seguro', '2026-01-09 12:30:00', 'ana04', 4);

-- ================================
-- SERVICIO
-- ================================
INSERT IGNORE INTO Servicio (Id_servicio, Nombre, Descripcion, Precio, Imagen_url, Categoria) VALUES
(1, 'Analisis de enlaces sospechosos', 'Analisis profundo de URLs para detectar phishing y malware en tiempo real.', 23.99, 'icon-url.png', 'Analisis'),
(2, 'Escaneo de anuncios falsos', 'Deteccion de publicidad enganosa y scripts maliciosos ocultos en banners.', 39.99, 'icon-ad.png', 'Publicidad'),
(3, 'Verificacion de mensajes', 'Validacion de remitentes de SMS y servicios de mensajeria instantanea.', 29.99, 'icon-msg.png', 'Mensajeria'),
(4, 'Comprobacion de identidad', 'Verificacion de perfiles en plataformas de venta de segunda mano.', 49.99, 'icon-user.png', 'Identidad'),
(5, 'Escaneo de archivos adjuntos', 'Analisis de documentos y ejecutables recibidos por correo para detectar virus ocultos.', 25.50, 'icon-file.png', 'Analisis'),
(6, 'Bloqueador de rastreadores', 'Eliminacion de cookies de seguimiento y publicidad invasiva mientras navegas.', 15.99, 'icon-target.png', 'Publicidad'),
(7, 'Filtro Anti-Spam avanzado', 'Sistema inteligente que bloquea mensajes fraudulentos antes de que lleguen a tu bandeja.', 19.99, 'icon-filter.png', 'Mensajeria'),
(8, 'Proteccion de huella digital', 'Rastreo y eliminacion de datos personales expuestos en bases de datos publicas.', 5.00, 'icon-fingerprint.png', 'Identidad');

SET FOREIGN_KEY_CHECKS = 1;