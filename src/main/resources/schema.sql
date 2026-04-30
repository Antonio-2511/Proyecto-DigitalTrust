SET FOREIGN_KEY_CHECKS = 0;


-- -----------------------------------------------------
-- Plan
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Plan (
  Nombre_plan VARCHAR(45) NOT NULL,
  Beneficios VARCHAR(100),
  Precio DECIMAL(10,2),
  Fecha_inicio DATETIME,
  Fecha_expiracion DATETIME,
  PRIMARY KEY (Nombre_plan)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Roles
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS roles (
  id BIGINT AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL UNIQUE,
  display_name VARCHAR(100),
  PRIMARY KEY (id)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users (
  username VARCHAR(45) NOT NULL,
  Contrasenia VARCHAR(100),
  Fecha_creacion DATETIME,
  Telefono VARCHAR(15),
  Gmail VARCHAR(100),
  Plan_Nombre_plan VARCHAR(45) NOT NULL,
  role_id BIGINT,

  PRIMARY KEY (username),

  INDEX fk_users_Plan1_idx (Plan_Nombre_plan),
  INDEX fk_users_roles_idx (role_id),

  CONSTRAINT fk_users_Plan1
    FOREIGN KEY (Plan_Nombre_plan)
    REFERENCES Plan (Nombre_plan)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT fk_users_roles
    FOREIGN KEY (role_id)
    REFERENCES roles(id)
    ON DELETE SET NULL
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Advertencia
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Advertencia (
  Id INT AUTO_INCREMENT,
  Titulo VARCHAR(100),
  Nivel_Criticidad INTEGER,
  Descripcion VARCHAR(255),
  Fecha_de_envio DATETIME,
  Es_emergencia TINYINT,
  users_username VARCHAR(45) NOT NULL,

  PRIMARY KEY (Id),

  INDEX fk_Advertencia_users1_idx (users_username),

  CONSTRAINT fk_Advertencia_users1
    FOREIGN KEY (users_username)
    REFERENCES users (username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Fuente_Confiable
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Fuente_Confiable (
  Id_Fuente INT AUTO_INCREMENT,
  Nombre_entidad VARCHAR(100),
  Tipo VARCHAR(45),
  Telefono VARCHAR(15),
  Email VARCHAR(100),
  Dominio VARCHAR(45),
  Advertencia_Id INT NOT NULL,

  PRIMARY KEY (Id_Fuente),

  INDEX fk_Fuente_Confiable_Advertencia1_idx (Advertencia_Id),

  CONSTRAINT fk_Fuente_Confiable_Advertencia1
    FOREIGN KEY (Advertencia_Id)
    REFERENCES Advertencia (Id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Mensaje
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Mensaje (
  Id_mensaje INT AUTO_INCREMENT,
  Contenido_texto VARCHAR(255),
  Origen VARCHAR(45),
  Nivel_riesgo VARCHAR(45),
  Resultado_analisis VARCHAR(45),
  fecha_analisis DATETIME,
  users_username VARCHAR(45) NOT NULL,
  Fuente_Confiable_Id_Fuente INT NOT NULL,

  PRIMARY KEY (Id_mensaje),

  INDEX fk_Mensaje_users1_idx (users_username),
  INDEX fk_Mensaje_Fuente_Confiable1_idx (Fuente_Confiable_Id_Fuente),

  CONSTRAINT fk_Mensaje_users1
    FOREIGN KEY (users_username)
    REFERENCES users (username)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

  CONSTRAINT fk_Mensaje_Fuente_Confiable1
    FOREIGN KEY (Fuente_Confiable_Id_Fuente)
    REFERENCES Fuente_Confiable (Id_Fuente)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Tabla para los productos de la Tienda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Servicio (
    Id_servicio INT AUTO_INCREMENT,
    Nombre VARCHAR(100) NOT NULL,
    Descripcion TEXT,
    Precio DECIMAL(10,2) NOT NULL,
    Imagen_url VARCHAR(255),
    Categoria VARCHAR(50),
    PRIMARY KEY (Id_servicio)
    ) ENGINE=InnoDB;


-- -----------------------------------------------------
-- Tabla para registrar qué usuario compra qué servicio
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Compra_Servicio (
    Id_compra INT AUTO_INCREMENT,
    users_username VARCHAR(45) NOT NULL,
    servicio_id INT NOT NULL,
    Fecha_compra DATETIME DEFAULT CURRENT_TIMESTAMP,
    Precio_pagado DECIMAL(10,2),
    PRIMARY KEY (Id_compra),
    CONSTRAINT fk_compra_user FOREIGN KEY (users_username)
    REFERENCES users (username) ON DELETE CASCADE,
    CONSTRAINT fk_compra_servicio FOREIGN KEY (servicio_id)
    REFERENCES Servicio (Id_servicio) ON DELETE CASCADE
    ) ENGINE=InnoDB;
-- -----------------------------------------------------
-- Reporte
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Reporte (
  Id_reporte INT AUTO_INCREMENT,
  Titulo VARCHAR(100),
  Descripcion VARCHAR(255),
  Fecha_reporte DATETIME,
  users_username VARCHAR(45) NOT NULL,

  PRIMARY KEY (Id_reporte),

  INDEX fk_Reporte_users1_idx (users_username),

  CONSTRAINT fk_Reporte_users1
    FOREIGN KEY (users_username)
    REFERENCES users (username)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;


SET FOREIGN_KEY_CHECKS = 1;