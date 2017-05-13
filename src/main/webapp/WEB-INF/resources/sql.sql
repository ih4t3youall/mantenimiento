drop schema mantenimiento;
create schema mantenimiento;
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mantenimiento
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mantenimiento
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mantenimiento` DEFAULT CHARACTER SET utf8 ;
USE `mantenimiento` ;

-- -----------------------------------------------------
-- Table `mantenimiento`.`app_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`app_user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `email` VARCHAR(255) NOT NULL COMMENT '',
  `first_name` VARCHAR(255) NOT NULL COMMENT '',
  `last_name` VARCHAR(255) NOT NULL COMMENT '',
  `password` VARCHAR(255) NOT NULL COMMENT '',
  `sso_id` VARCHAR(255) NOT NULL COMMENT '',
  `state` VARCHAR(255) NOT NULL COMMENT '',
  `empresa_id` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `UK_hqk6uc88j3imq8u9jhro36vt3` (`SSO_ID` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`user_profile` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `TYPE` VARCHAR(15) NOT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  UNIQUE INDEX `UK_cva7m2blp7ekclxworqxau1l7` (`TYPE` ASC)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`app_user_user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`app_user_user_profile` (
  `user_id` INT(11) NOT NULL COMMENT '',
  `user_profile_id` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`user_id`, `user_profile_id`)  COMMENT '',
  INDEX `FK_gs2lq4vmukguubh36utd4r2cl` (`user_profile_id` ASC)  COMMENT '',
  CONSTRAINT `FK_brmce0t584euix4wb4rursf1q`
    FOREIGN KEY (`user_id`)
    REFERENCES `mantenimiento`.`app_user` (`id`),
  CONSTRAINT `FK_gs2lq4vmukguubh36utd4r2cl`
    FOREIGN KEY (`user_profile_id`)
    REFERENCES `mantenimiento`.`user_profile` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`empresa` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `descripcion` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `nombre` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `urlImagen` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`epp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`epp` (
  `idEpp` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `descripcion` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `imagen` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `nombre` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idEpp`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`maquina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`maquina` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `descripcion` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `nombre` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `urlPdf` VARCHAR(250) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form` (
  `idForm` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `conclusion` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `equipo` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `fecha_programada` DATE NULL DEFAULT NULL COMMENT '',
  `fecha_realizacion` DATE NULL DEFAULT NULL COMMENT '',
  `nro_interno` INT(11) NULL DEFAULT NULL COMMENT '',
  `nro_orden` INT(11) NULL DEFAULT NULL COMMENT '',
  `observaciones` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `maquina_id` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idForm`)  COMMENT '',
  INDEX `FK_cbco4q47iy08jrlg2u8bd4qiw` (`maquina_id` ASC)  COMMENT '',
  CONSTRAINT `FK_cbco4q47iy08jrlg2u8bd4qiw`
    FOREIGN KEY (`maquina_id`)
    REFERENCES `mantenimiento`.`maquina` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 24
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_has_epp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_has_epp` (
  `idForm` INT(11) NOT NULL COMMENT '',
  `idEpp` INT(11) NOT NULL COMMENT '',
  `obligatorio` INT(11) NOT NULL COMMENT '',
  PRIMARY KEY (`idForm`, `idEpp`)  COMMENT '')
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_form_has_epp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_form_has_epp` (
  `form_idForm` INT(11) NOT NULL COMMENT '',
  `formHasEpps_idForm` INT(11) NOT NULL COMMENT '',
  `formHasEpps_idEpp` INT(11) NOT NULL COMMENT '',
  UNIQUE INDEX `UK_onvc31cqr8f4t8mut69gaa28k` (`formHasEpps_idForm` ASC, `formHasEpps_idEpp` ASC)  COMMENT '',
  INDEX `FK_g6povfu5lt38k09yoyr3kqnkk` (`form_idForm` ASC)  COMMENT '',
  CONSTRAINT `FK_g6povfu5lt38k09yoyr3kqnkk`
    FOREIGN KEY (`form_idForm`)
    REFERENCES `mantenimiento`.`form` (`idForm`),
  CONSTRAINT `FK_onvc31cqr8f4t8mut69gaa28k`
    FOREIGN KEY (`formHasEpps_idForm` , `formHasEpps_idEpp`)
    REFERENCES `mantenimiento`.`form_has_epp` (`idForm` , `idEpp`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_item` (
  `idform_item` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `estado` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `label` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `observaciones` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `posee` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `form_idForm` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idform_item`)  COMMENT '',
  INDEX `FK_eq6ltes78v217lr8w7d31mysp` (`form_idForm` ASC)  COMMENT '',
  CONSTRAINT `FK_eq6ltes78v217lr8w7d31mysp`
    FOREIGN KEY (`form_idForm`)
    REFERENCES `mantenimiento`.`form` (`idForm`))
ENGINE = InnoDB
AUTO_INCREMENT = 118
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_legacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_legacy` (
  `idFormLegacy` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `aptoServicio` BIT(1) NOT NULL COMMENT '',
  `fecha_programada` DATE NULL DEFAULT NULL COMMENT '',
  `fecha_realizacion` DATE NULL DEFAULT NULL COMMENT '',
  `idEmpresa` INT(11) NOT NULL COMMENT '',
  `idMaquina` INT(11) NOT NULL COMMENT '',
  `idProyecto` INT(11) NOT NULL COMMENT '',
  `last_modify_date` DATE NULL DEFAULT NULL COMMENT '',
  `observaciones` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `noConformidad` VARCHAR(1500) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idFormLegacy`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 23
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_item_legacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_item_legacy` (
  `idform_item_legacy` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `estado` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `label` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `observaciones` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `posee` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `formLegacy_idFormLegacy` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`idform_item_legacy`)  COMMENT '',
  INDEX `FK_g2cq1whdv8iu4i64ght1ba1cw` (`formLegacy_idFormLegacy` ASC)  COMMENT '',
  CONSTRAINT `FK_g2cq1whdv8iu4i64ght1ba1cw`
    FOREIGN KEY (`formLegacy_idFormLegacy`)
    REFERENCES `mantenimiento`.`form_legacy` (`idFormLegacy`))
ENGINE = InnoDB
AUTO_INCREMENT = 69
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`form_legacy_form_item_legacy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`form_legacy_form_item_legacy` (
  `form_legacy_idFormLegacy` INT(11) NOT NULL COMMENT '',
  `formItemsLegacy_idform_item_legacy` INT(11) NOT NULL COMMENT '',
  UNIQUE INDEX `UK_sr62cphrd0ga2dhn1srymkm3u` (`formItemsLegacy_idform_item_legacy` ASC)  COMMENT '',
  INDEX `FK_2k6ltuus11it7hdrws3kv3sgf` (`form_legacy_idFormLegacy` ASC)  COMMENT '',
  CONSTRAINT `FK_2k6ltuus11it7hdrws3kv3sgf`
    FOREIGN KEY (`form_legacy_idFormLegacy`)
    REFERENCES `mantenimiento`.`form_legacy` (`idFormLegacy`),
  CONSTRAINT `FK_sr62cphrd0ga2dhn1srymkm3u`
    FOREIGN KEY (`formItemsLegacy_idform_item_legacy`)
    REFERENCES `mantenimiento`.`form_item_legacy` (`idform_item_legacy`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`proyecto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `descripcion` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `nombre` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  `empresa_id` INT(11) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '',
  INDEX `FK_1mcsogsow9y69vp5tsjmd5ycd` (`empresa_id` ASC)  COMMENT '',
  CONSTRAINT `FK_1mcsogsow9y69vp5tsjmd5ycd`
    FOREIGN KEY (`empresa_id`)
    REFERENCES `mantenimiento`.`empresa` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`maquina_has_proyecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`maquina_has_proyecto` (
  `proyecto_id` INT(11) NOT NULL COMMENT '',
  `maquina_id` INT(11) NOT NULL COMMENT '',
  INDEX `FK_ixpjfjr1q4fdsp0y03qplvcmr` (`maquina_id` ASC)  COMMENT '',
  INDEX `FK_51ug23lnafeg8wb3mjtn3pwwu` (`proyecto_id` ASC)  COMMENT '',
  CONSTRAINT `FK_51ug23lnafeg8wb3mjtn3pwwu`
    FOREIGN KEY (`proyecto_id`)
    REFERENCES `mantenimiento`.`proyecto` (`id`),
  CONSTRAINT `FK_ixpjfjr1q4fdsp0y03qplvcmr`
    FOREIGN KEY (`maquina_id`)
    REFERENCES `mantenimiento`.`maquina` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`usuario_asignado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`usuario_asignado` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `sso_id` VARCHAR(255) NULL DEFAULT NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mantenimiento`.`proyecto_has_usuario_asignado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mantenimiento`.`proyecto_has_usuario_asignado` (
  `usuario_asignado_id` INT(11) NOT NULL COMMENT '',
  `proyecto_id` INT(11) NOT NULL COMMENT '',
  INDEX `FK_fl2fmo82u2vlq9ppufr0fs06e` (`proyecto_id` ASC)  COMMENT '',
  INDEX `FK_2ad5se881m0c7jk29kr6wl5kh` (`usuario_asignado_id` ASC)  COMMENT '',
  CONSTRAINT `FK_2ad5se881m0c7jk29kr6wl5kh`
    FOREIGN KEY (`usuario_asignado_id`)
    REFERENCES `mantenimiento`.`usuario_asignado` (`id`),
  CONSTRAINT `FK_fl2fmo82u2vlq9ppufr0fs06e`
    FOREIGN KEY (`proyecto_id`)
    REFERENCES `mantenimiento`.`proyecto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

/* Populate USER_PROFILE Table */
/*INSERT INTO USER_PROFILE(type)
VALUES ('USER');*/
 
INSERT INTO user_profile(type)
VALUES ('ADMIN');

INSERT INTO user_profile(type)
VALUES ('OPERARIO');

INSERT INTO user_profile(type)
VALUES ('USUARIO ');
 
INSERT INTO user_profile(type)
VALUES ('DBA');
 
/* Populate APP_USER Table */
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state)
VALUES ('admin','admin', 'admin','admin','bill@xyz.com', 'Active');
 
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state)
VALUES ('user','user', 'user','user','danny@xyz.com', 'Active');
 
INSERT INTO app_user(sso_id, password, first_name, last_name, email, state)
VALUES ('operario','operario', 'Sam','Smith','samy@xyz.com', 'Active');
 
 
/* Populate JOIN Table */
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile  
  where user.sso_id='admin' and profile.type='USUARIO';
 
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='admin' and profile.type='DBA';
 
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='admin' and profile.type='ADMIN';
  
 INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile  
  where user.sso_id='user' and profile.type='USUARIO';
 
INSERT INTO app_user_user_profile (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='operario' and profile.type='OPERARIO';
 
  
 --select * from APP_USER_USER_PROFILE inner join app_user on app_user.id = app_user_user_profile.USER_ID inner join user_profile on user_profile.id = app_user_user_profile.USER_PROFILE_ID; 
 --select * from app_user_user_profile;
