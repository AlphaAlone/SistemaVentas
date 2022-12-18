-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sistemaventas
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sistemaventas
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sistemaventas` DEFAULT CHARACTER SET utf8mb4 ;
USE `sistemaventas` ;

-- -----------------------------------------------------
-- Table `sistemaventas`.`t_bitacora`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_bitacora` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `host` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `accion` VARCHAR(45) NOT NULL,
  `fecha` DATE NOT NULL,
  `tabla` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 114
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_concepto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_concepto` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_venta` BIGINT(20) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  `precioUnitario` DECIMAL(16,2) NOT NULL,
  `importe` DECIMAL(16,2) NOT NULL,
  `id_producto` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 73
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_login` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `folioUsuario` VARCHAR(45) NOT NULL,
  `usuario` VARCHAR(45) NOT NULL,
  `pasword` VARCHAR(45) NOT NULL,
  `estatus` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `folioUsuario` (`folioUsuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_pedido` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_producto` INT(11) NOT NULL,
  `cantidadpedir` INT(11) NOT NULL,
  `folioUsuario` VARCHAR(45) NOT NULL,
  `estatuspedido` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `folioUsuario` (`folioUsuario` ASC) VISIBLE,
  UNIQUE INDEX `id_producto` (`id_producto` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_producto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `precioUnitario` DECIMAL(16,2) NOT NULL,
  `costo` DECIMAL(16,2) NOT NULL,
  `inventario` INT(11) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_ususario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_ususario` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `ap_pat` VARCHAR(45) NOT NULL,
  `ap_mat` VARCHAR(45) NOT NULL,
  `curp` VARCHAR(45) NOT NULL,
  `fechaNacimiento` DATE NOT NULL,
  `tipoUsuario` VARCHAR(45) NOT NULL,
  `folioUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `folioUsuario` (`folioUsuario` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `sistemaventas`.`t_ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sistemaventas`.`t_ventas` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fechaVenta` DATE NOT NULL,
  `total` DECIMAL(16,2) NULL DEFAULT NULL,
  `folioUsuario` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `folioUsuario_2` (`folioUsuario` ASC) VISIBLE,
  INDEX `folioUsuario_3` (`folioUsuario` ASC) VISIBLE,
  INDEX `folioUsuario_5` (`folioUsuario` ASC) VISIBLE,
  FULLTEXT INDEX `folioUsuario_4` (`folioUsuario`) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 84
DEFAULT CHARACTER SET = utf8mb4;

USE `sistemaventas`;

DELIMITER $$
USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_login_INSERT`
AFTER INSERT ON `sistemaventas`.`t_login`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'UPDATE',
                                  now(),
                                  't_login')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_producto_DELETE`
AFTER DELETE ON `sistemaventas`.`t_producto`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'DELETE',
                                  now(),
                                  't_producto')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_producto_INSERT`
AFTER INSERT ON `sistemaventas`.`t_producto`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'INSERT',
                                  now(),
                                  't_producto')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_producto_UPDATE`
AFTER UPDATE ON `sistemaventas`.`t_producto`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'UPDATE',
                                  now(),
                                  't_producto')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_usuario_DELETE`
AFTER DELETE ON `sistemaventas`.`t_ususario`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'DELETE',
                                  now(),
                                  't_usuario')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_usuario_INSERT`
AFTER INSERT ON `sistemaventas`.`t_ususario`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'INSERT',
                                  now(),
                                  't_usuario')$$

USE `sistemaventas`$$
CREATE
DEFINER=`root`@`localhost`
TRIGGER `sistemaventas`.`bit_usuario_UPDATE`
AFTER UPDATE ON `sistemaventas`.`t_ususario`
FOR EACH ROW
Insert t_bitacora(host, usuario, accion, fecha, tabla)
                           value (substring(user(), instr(user(), '@')+1),
                                  substring(user(), 1, instr(user(), '@')-1),
                                  'UPDATE',
                                  now(),
                                  't_usuario')$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
