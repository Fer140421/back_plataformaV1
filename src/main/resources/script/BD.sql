--31/09/24--
-- public.menu definition

-- Drop table

-- DROP TABLE menu;

CREATE TABLE menu (
                      id serial4 NOT NULL,
                      description varchar(150) NULL,
                      "name" varchar(50) NOT NULL,
                      status bool DEFAULT true NULL,
                      CONSTRAINT menu_pkey PRIMARY KEY (id)
);


-- public."permission" definition

-- Drop table

-- DROP TABLE "permission";

CREATE TABLE "permission" (
                              id serial4 NOT NULL,
                              description varchar(150) NULL,
                              "name" varchar(50) NOT NULL,
                              status bool DEFAULT true NULL,
                              CONSTRAINT permission_pkey PRIMARY KEY (id)
);


-- public.rol definition

-- Drop table

-- DROP TABLE rol;

CREATE TABLE rol (
                     id serial4 NOT NULL,
                     description varchar(150) NULL,
                     "name" varchar(50) NOT NULL,
                     status bool DEFAULT true NULL,
                     CONSTRAINT rol_pkey PRIMARY KEY (id)
);


-- public.systems_user definition

-- Drop table

-- DROP TABLE systems_user;

CREATE TABLE systems_user (
                              id bigserial NOT NULL,
                              alias varchar(50) NULL,
                              email varchar(100) NOT NULL,
                              username varchar(100) NOT NULL,
                              "password" varchar(1024) NOT NULL,
                              cell varchar(12) NOT NULL,
                              code_cell varchar(12) NULL,
                              status bool DEFAULT true NOT NULL,
                              date_start_verification timestamp NULL,
                              date_end_verification timestamp NULL,
                              is_enabled bool DEFAULT true NOT NULL,
                              account_no_expired bool DEFAULT true NOT NULL,
                              account_no_locked bool DEFAULT true NOT NULL,
                              credential_no_exipred bool DEFAULT true NOT NULL,
                              CONSTRAINT systems_user_pkey PRIMARY KEY (id),
                              CONSTRAINT username_unique UNIQUE (username)
);


-- public.permision_menu definition

-- Drop table

-- DROP TABLE permision_menu;

CREATE TABLE permision_menu (
                                menu_id int4 NOT NULL,
                                permission_id int4 NOT NULL,
                                CONSTRAINT permision_menu_pkey PRIMARY KEY (permission_id, menu_id),
                                CONSTRAINT permision_menu_menu_id_fkey FOREIGN KEY (menu_id) REFERENCES menu(id),
                                CONSTRAINT permision_menu_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES "permission"(id)
);


-- public.person definition

-- Drop table

-- DROP TABLE person;

CREATE TABLE person (
                        id int8 NOT NULL,
                        ci varchar(25) NOT NULL,
                        first_lastename varchar(100) NOT NULL,
                        gender varchar(1) NOT NULL,
                        "name" varchar(100) NOT NULL,
                        second_lastname varchar(100) NULL,
                        status bool DEFAULT true NOT NULL,
                        id_system_user int8 NOT NULL,
                        CONSTRAINT person_id_system_user_key UNIQUE (id_system_user),
                        CONSTRAINT person_pkey PRIMARY KEY (id),
                        CONSTRAINT person_system_user_fk FOREIGN KEY (id_system_user) REFERENCES systems_user(id)
);


-- public.rol_permission definition

-- Drop table

-- DROP TABLE rol_permission;

CREATE TABLE rol_permission (
                                rol_id int4 NOT NULL,
                                permission_id int4 NOT NULL,
                                CONSTRAINT rol_permission_pkey PRIMARY KEY (rol_id, permission_id),
                                CONSTRAINT rol_permission_permission_id_fkey FOREIGN KEY (permission_id) REFERENCES "permission"(id),
                                CONSTRAINT rol_permission_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES rol(id)
);


-- public.user_rol definition

-- Drop table

-- DROP TABLE user_rol;

CREATE TABLE user_rol (
                          rol_id int4 NOT NULL,
                          system_user_id int8 NOT NULL,
                          CONSTRAINT user_rol_pkey PRIMARY KEY (rol_id, system_user_id),
                          CONSTRAINT user_rol_rol_id_fkey FOREIGN KEY (rol_id) REFERENCES rol(id),
                          CONSTRAINT user_rol_system_user_id_fkey FOREIGN KEY (system_user_id) REFERENCES systems_user(id)
);

-- Insertar en la tabla menu
INSERT INTO menu (description, "name", status) VALUES
                                                   ('Gestión de usuarios', 'User Management', true),
                                                   ('Configuración del sistema', 'System Settings', true),
                                                   ('Reportes', 'Reports', true);

-- Insertar en la tabla permission
INSERT INTO "permission" (description, "name", status) VALUES
                                                           ('Crear usuarios', 'CREATE_USER', true),
                                                           ('Modificar usuarios', 'UPDATE_USER', true),
                                                           ('Eliminar usuarios', 'DELETE_USER', true);

-- Insertar en la tabla rol
INSERT INTO rol (description, "name", status) VALUES
                                                  ('Administrador del sistema', 'ADMIN', true),
                                                  ('Usuario estándar', 'USER', true),
                                                  ('Invitado', 'GUEST', true);

-- Insertar en la tabla systems_user
INSERT INTO systems_user (alias, email, username, "password", cell, code_cell, status) VALUES
                                                                                           ('fdoe', 'fdoe@example.com', 'fdoe', 'hashed_password_1', '1234567890', '1234', true),
                                                                                           ('jdoe', 'jdoe@example.com', 'jdoe', 'hashed_password_2', '0987654321', '5678', true);

-- Insertar en la tabla permision_menu
INSERT INTO permision_menu (menu_id, permission_id) VALUES
                                                        (1, 1),
                                                        (1, 2),
                                                        (2, 3);

-- Insertar en la tabla person
INSERT INTO person (id, ci, first_lastename, gender, "name", second_lastname, status, id_system_user) VALUES
                                                                                                          (1, '12345678', 'Doe', 'M', 'John', 'Smith', true, 1),
                                                                                                          (2, '87654321', 'Roe', 'F', 'Jane', 'Johnson', true, 2);

-- Insertar en la tabla rol_permission
INSERT INTO rol_permission (rol_id, permission_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (1, 3),
                                                       (2, 1);

-- Insertar en la tabla user_rol
INSERT INTO user_rol (rol_id, system_user_id) VALUES
                                                  (1, 1),
                                                  (2, 2);
