-- we don't know how to generate root <with-no-name> (class Root) :(
create database Test;
create table Test
(
    id int auto_increment
        primary key,
    cuteName varchar(25) null,
    name varchar(50) null
);

