-- ================================
-- PLAN
-- ================================
INSERT INTO Plan (Nombre_plan, Beneficios, Precio, Fecha_inicio, Fecha_expiracion) VALUES
('Basico', 'Alertas basicas', 10.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Estandar', 'Alertas y reportes', 20.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Premium', 'Proteccion completa', 30.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59'),
('Empresarial', 'Gestion avanzada', 40.00, '2026-01-01 00:00:00', '2026-12-31 23:59:59');


-- ================================
-- USERS
-- ================================
INSERT INTO users (username, Contrasenia, Fecha_creacion, Telefono, Gmail, Plan_Nombre_plan) VALUES
('juan01', '1234', '2026-01-05 10:00:00', '600111222', 'juan@gmail.com', 'Basico'),
('maria02', 'abcd', '2026-01-06 11:30:00', '600333444', 'maria@gmail.com', 'Estandar'),
('carlos03', 'pass', '2026-01-07 09:15:00', '600555666', 'carlos@gmail.com', 'Premium'),
('ana04', 'segura', '2026-01-08 14:45:00', '600777888', 'ana@gmail.com', 'Empresarial');


-- ================================
-- ADVERTENCIA
-- ================================
INSERT INTO Advertencia 
(Titulo, Nivel_Criticidad, Descripcion, Fecha_de_envio, Es_emergencia, users_username) VALUES
('Phishing detectado', 'Alta', 'Correo fraudulento', '2026-01-09 08:00:00', 1, 'juan01'),
('SMS sospechoso', 'Media', 'Mensaje con enlace', '2026-01-09 09:00:00', 0, 'maria02'),
('Intento de estafa', 'Alta', 'Llamada falsa', '2026-01-09 10:00:00', 1, 'carlos03'),
('Web insegura', 'Baja', 'Dominio no confiable', '2026-01-09 11:00:00', 0, 'ana04');


-- ================================
-- FUENTE CONFIABLE
-- ================================
INSERT INTO Fuente_Confiable 
(Nombre_entidad, Tipo, Telefono, Email, Dominio, Advertencia_Id) VALUES
('INCIBE', 'Gobierno', '017', 'info@incibe.es', 'incibe.es', 1),
('Policia Nacional', 'Seguridad', '091', 'contacto@policia.es', 'policia.es', 2),
('Guardia Civil', 'Seguridad', '062', 'gc@guardiacivil.es', 'gc.es', 3),
('Google Safe', 'Tecnologia', '900123456', 'safe@google.com', 'google.com', 4);


-- ================================
-- MENSAJE
-- ================================
INSERT INTO Mensaje 
(Contenido_texto, Origen, Nivel_riesgo, Resultado_analisis, fecha_analisis, users_username, Fuente_Confiable_Id_Fuente) VALUES
('Haz clic en este enlace', 'Email', 'Alto', 'Malicioso', '2026-01-09 12:00:00', 'juan01', 1),
('Gana un premio ahora', 'SMS', 'Medio', 'Sospechoso', '2026-01-09 12:10:00', 'maria02', 2),
('Verificacion de cuenta', 'Llamada', 'Alto', 'Malicioso', '2026-01-09 12:20:00', 'carlos03', 3),
('Oferta limitada', 'Web', 'Bajo', 'Seguro', '2026-01-09 12:30:00', 'ana04', 4);


-- ================================
-- REPORTE
-- ================================
INSERT INTO Reporte 
(Titulo, Descripcion, Fecha_reporte, users_username) VALUES
('Reporte phishing', 'Correo fraudulento recibido', '2026-01-10 09:00:00', 'juan01'),
('Reporte SMS', 'Mensaje sospechoso', '2026-01-10 10:00:00', 'maria02'),
('Reporte llamada', 'Intento de estafa telefonica', '2026-01-10 11:00:00', 'carlos03'),
('Reporte web', 'Pagina insegura', '2026-01-10 12:00:00', 'ana04');