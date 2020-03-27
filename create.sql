create database store;

create table Roles (
	id serial primary key,
	role varchar(30)
);

create table Rules (
	id serial primary key,
	rule varchar(100)
);

create table Rules_For_Roles (
	id serial primary key,
	role_id integer references Roles(id),
	rule_id integer references Rules(id)
);

create table Users (
	id serial primary key,
	name varchar(30),
	role_id integer references Roles(id)
);

create table Categories (
	id serial primary key,
	category varchar(30)
);

create table States (
	id serial primary key,
	status varchar(30)
);

create table Items (
	id serial primary key,
	description varchar(200),
	user_id integer references Users(id),
	category_id integer references Categories(id),
	status_id integer references States(id)
);

create table Attaches (
	id serial primary key,
	link varchar(100),
	item_id integer references Items(id)
);

create table Comments (
	id serial primary key,
	comment text,
	item_id integer references Items(id)
);

insert into Roles(role) values ('Admin'), ('User');

insert into Rules(rule) values ('Create item'), ('Edit item'), ('Remove item'), ('Edit any item'), ('Remove any item');

insert into Rules_For_Roles(role_id, rule_id) values (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (2, 1), (2, 2), (2, 3);

insert into Users(name, role_id) values ('Vasya', 1), ('Kolya', 2), ('Sasha', 2);

insert into Categories(category) values ('Products'), ('Electronics'), ('Avto');

insert into States(status) values ('New'), ('Processed'), ('Completed');

insert into Items(description, user_id, category_id, status_id) values 
	('Get milk', 2, 1, 1), 
	('Get television', 2, 2, 3),
	('Get motor oil', 3, 3, 2);

insert into Attaches(link, item_id) values ('image.jpg', 2);

insert into Comments(comment, item_id) values ('pickup', 2);
