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
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;

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
    user_id_list varchar(255), -- to be checked for many to many mapping of user table
    created_by int not null,
    active_status boolean,
    created_on datetime,
    updated_on datetime
);

drop table if exists expense_details;
create table expense_details
(
	id int NOT NULL primary key auto_increment,
    category varchar(255) not null,
    description varchar(255) not null,
    amount double not null,
    prm_curr_id varchar(3) not null,
    active_status boolean,
    paid_by int,
    split_method varchar(10),
    group_id int,
    created_by int not null,
    updated_by int not null,
    created_on datetime,
    updated_on datetime
);

 drop table if exists user_expense_details;
create table user_expense_details
(
	id int NOT NULL primary key auto_increment,
	user_id int NOT NULL,
    exp_id int not null,
    user_share double not null,
    active_status boolean,
  --  primary key (user_id, exp_id),
  FOREIGN KEY (exp_id) REFERENCES expense_details(id)
);