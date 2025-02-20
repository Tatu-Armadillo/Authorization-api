drop schema if exists hackaton cascade;

create schema hackaton;

create table hackaton.permission (
    id bigserial primary key,
    description varchar(255) not null
);

create table hackaton.users (
    id bigserial primary key,
    full_name varchar(255) not null,
    password varchar(255) not null,
    user_name varchar(255) not null,
    user_identifier varchar(255) not null unique,
    account_non_expired boolean not null default false,
    account_non_locked boolean not null default false,
    credentials_non_expired boolean not null default false,
    enabled boolean not null default false
);

create table hackaton.user_permission (
    id_permission bigint not null,
    id_user bigint not null,
    primary key(id_permission, id_user)
);

alter table hackaton.user_permission add constraint fk_permission_user_permission foreign key (id_permission) references hackaton.permission (id);
alter table hackaton.user_permission add constraint fk_user_user_permission foreign key (id_user) references hackaton.users (id);

insert into hackaton.permission (description) values ('ADMIN'), ('DOCTOR'), ('PATIENT');
insert into hackaton.users (user_name, full_name, password, user_identifier, account_non_expired, account_non_locked, credentials_non_expired, enabled) values ('adm','adm','$2a$10$PqsrFKSSRev9lL0BMAE.IOvDB4r6plBA7c45UDzz4v0Wu1Es9XMs.', '12456456', true, true, true, true);
insert into hackaton.user_permission (id_user, id_permission) values (1,1), (1,2), (1,3);