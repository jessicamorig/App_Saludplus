-- ============================================
-- BASE DE DATOS: Clínica Médica Saludplus
-- Pegar este SQL en phpMyAdmin > pestaña SQL
-- ============================================

CREATE DATABASE IF NOT EXISTS clinica_saludplus
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE clinica_saludplus;

-- ----------------------------
-- TABLA: especialidades
-- ----------------------------
CREATE TABLE IF NOT EXISTS especialidades (
    idespecialidad INT PRIMARY KEY AUTO_INCREMENT,
    nombre         VARCHAR(100) NOT NULL,
    descripcion    VARCHAR(255) NOT NULL,
    area           VARCHAR(100) NOT NULL
);

-- ----------------------------
-- TABLA: doctores
-- ----------------------------
CREATE TABLE IF NOT EXISTS doctores (
    iddoctor       INT PRIMARY KEY AUTO_INCREMENT,
    nombres        VARCHAR(100) NOT NULL,
    apellidos      VARCHAR(100) NOT NULL,
    idespecialidad INT NOT NULL,
    telefono       VARCHAR(20)  NOT NULL,
    correo         VARCHAR(100) NOT NULL,
    horario        VARCHAR(100),
    consultorio    VARCHAR(50),
    FOREIGN KEY (idespecialidad) REFERENCES especialidades(idespecialidad)
);

-- ----------------------------
-- DATOS: especialidades
-- ----------------------------
INSERT INTO especialidades (nombre, descripcion, area) VALUES
('Cardiología',   'Atención especializada del corazón y sistema cardiovascular',         'Medicina Interna'),
('Neurología',    'Diagnóstico y tratamiento de enfermedades del sistema nervioso',       'Medicina Interna'),
('Pediatría',     'Atención médica integral para niños y adolescentes',                   'Medicina General'),
('Traumatología', 'Tratamiento de lesiones del sistema músculo-esquelético',              'Cirugía'),
('Dermatología',  'Diagnóstico y tratamiento de enfermedades de la piel',                 'Medicina Interna'),
('Oftalmología',  'Diagnóstico y tratamiento de enfermedades de los ojos',                'Cirugía');

-- ----------------------------
-- DATOS: doctores
-- ----------------------------
INSERT INTO doctores (nombres, apellidos, idespecialidad, telefono, correo, horario, consultorio) VALUES
-- Cardiología (id=1)
('Carlos Alberto', 'Ramos Paredes',    1, '987654321', 'c.ramos@clinicasaludplus.pe',    'Lun-Vie 08:00-14:00',      'Consultorio 101'),
('María Elena',    'Torres Vega',      1, '987123456', 'm.torres@clinicasaludplus.pe',   'Mar-Sab 09:00-15:00',      'Consultorio 102'),
-- Neurología (id=2)
('Jorge Luis',     'Mendoza Quispe',   2, '976543210', 'j.mendoza@clinicasaludplus.pe',  'Lun-Vie 10:00-16:00',      'Consultorio 201'),
('Ana Sofía',      'Chávez Flores',    2, '965432109', 'a.chavez@clinicasaludplus.pe',   'Lun-Mie-Vie 08:00-13:00',  'Consultorio 202'),
-- Pediatría (id=3)
('Roberto',        'Huanca Mamani',    3, '954321098', 'r.huanca@clinicasaludplus.pe',   'Lun-Sab 07:00-13:00',      'Consultorio 301'),
('Lucía Beatriz',  'Vargas Ccori',     3, '943210987', 'l.vargas@clinicasaludplus.pe',   'Mar-Jue-Sab 09:00-15:00',  'Consultorio 302'),
-- Traumatología (id=4)
('Pedro Antonio',  'Sánchez Díaz',     4, '932109876', 'p.sanchez@clinicasaludplus.pe',  'Lun-Vie 11:00-17:00',      'Consultorio 401'),
('Diana Rosa',     'Gutierrez Palomino',4,'921098765', 'd.gutierrez@clinicasaludplus.pe','Lun-Mie-Vie 14:00-20:00',  'Consultorio 402'),
-- Dermatología (id=5)
('Fernando José',  'Castro Ríos',      5, '910987654', 'f.castro@clinicasaludplus.pe',   'Lun-Vie 08:00-14:00',      'Consultorio 501'),
('Claudia Milagros','Rojas Espinoza',  5, '909876543', 'c.rojas@clinicasaludplus.pe',    'Mar-Jue 10:00-16:00',      'Consultorio 502'),
-- Oftalmología (id=6)
('Miguel Ángel',   'Llanos Herrera',   6, '898765432', 'm.llanos@clinicasaludplus.pe',   'Lun-Mie-Vie 09:00-15:00',  'Consultorio 601'),
('Patricia',       'Morales Zuniga',   6, '887654321', 'p.morales@clinicasaludplus.pe',  'Mar-Jue-Sab 08:00-14:00',  'Consultorio 602');
