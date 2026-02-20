DROP DATABASE IF EXISTS digitaltrust;
CREATE DATABASE digitaltrust CHARACTER SET utf8mb4;
USE digitaltrust;

SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------
-- Plan
-- -----------------------------------------------------
CREATE TABLE Plan (
  Nombre_plan VARCHAR(45) NOT NULL,
  Beneficios VARCHAR(100),
  Precio DECIMAL(10,2),
  Fecha_inicio DATETIME,
  Fecha_expiracion DATETIME,
  PRIMARY KEY (Nombre_plan)
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- users (ANTES User)
-- -----------------------------------------------------
CREATE TABLE users (
  username VARCHAR(45) NOT NULL,
  Contrasenia VARCHAR(100),
  Fecha_creacion DATETIME,
  Telefono VARCHAR(15),
  Gmail VARCHAR(100),
  Plan_Nombre_plan VARCHAR(45) NOT NULL,
  PRIMARY KEY (username),
  INDEX fk_users_Plan1_idx (Plan_Nombre_plan),
  CONSTRAINT fk_users_Plan1
    FOREIGN KEY (Plan_Nombre_plan)
    REFERENCES Plan (Nombre_plan)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE=InnoDB;

-- -----------------------------------------------------
-- Advertencia
-- -----------------------------------------------------
CREATE TABLE Advertencia (
  Id INT AUTO_INCREMENT,
  Titulo VARCHAR(100),
  Nivel_Criticidad VARCHAR(45),
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
CREATE TABLE Fuente_Confiable (
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
CREATE TABLE Mensaje (
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
-- Reporte
-- -----------------------------------------------------
CREATE TABLE Reporte (
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