drop schema if exists hackaton cascade;

create schema hackaton;

create table hackaton.permission (
    id bigserial primary key,
    description varchar(255)
);

create table hackaton.users (
    id bigserial primary key,
    account_non_expired boolean not null,
    account_non_locked boolean not null,
    credentials_non_expired boolean not null,
    enabled boolean not null,
    person_id bigint not null unique,
    password varchar(255) not null,
    user_identifier varchar(255) not null,
    user_name varchar(255) not null
);
 
create table hackaton.person (
    id bigserial primary key,
    age integer not null,
    address varchar(255) not null,
    contact varchar(255) not null,
    cpf varchar(255) not null,
    full_name varchar(255) not null,
    gender varchar(255) not null
);

create table hackaton.user_permission (
    id_permission bigint not null,
    id_user bigint not null,
    primary key(id_permission, id_user)
);

alter table if exists hackaton.users add constraint fk_user_person foreign key (person_id) references hackaton.person;
alter table if exists hackaton.user_permission add constraint fk_permission_user_permission foreign key (id_permission) references hackaton.permission;
alter table if exists hackaton.user_permission add constraint fk_user_user_permission foreign key (id_user) references hackaton.users;

INSERT INTO hackaton.person (age, address, contact, cpf, full_name, gender) 
VALUES (30, 'Rua das Flores, 123', '11999999999', '123.456.789-00', 'Administrador do Sistema', 'M');

INSERT INTO hackaton.users (user_name, password, user_identifier, account_non_expired, account_non_locked, credentials_non_expired, enabled, person_id) 
VALUES ('adm', '$2a$10$PqsrFKSSRev9lL0BMAE.IOvDB4r6plBA7c45UDzz4v0Wu1Es9XMs.', '12456456', true, true, true, true, 1);

INSERT INTO hackaton.permission (description) VALUES ('ADMIN'), ('DOCTOR'), ('PATIENT');

INSERT INTO hackaton.user_permission (id_user, id_permission) VALUES (1,1), (1,2), (1,3);