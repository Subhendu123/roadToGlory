create database if not exists splitwise;
use splitwise;
drop table if exists user_details;
create table user_details
(
    id int NOT NULL primary key auto_increment,
    first_name varchar(255),
    last_name varchar(255) not null,
    email varchar(255) not null unique,
    phone_number varchar(13) not null unique,
    prm_curr_id varchar(3) not null,
    active_status boolean,
    created_on datetime,
    updated_on datetime
);

drop table if exists user_group;
create table user_group
(
	id int NOT NULL primary key auto_increment,
    user_id int not null,
    group_id_list varchar(255) not null
    );
    
   drop table if exists group_details;
create table group_details
(
	id int NOT NULL primary key auto_increment,
    type varchar(255) not null,
    name varchar(255) not null,
    description varchar(255),
    created_by int not null,
    active_status boolean,
    created_on datetime,
    updated_on datetime
);

 
