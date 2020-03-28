create database cars;

create table body(
	id serial primary key,
	type varchar(30)
);

create table engine(
	id serial primary key,
	type varchar(30),
	volume real
);

create table transmission(
	id serial primary key,
	type varchar(30),
	numberGears integer
);

create table car(
	id serial primary key,
	name varchar(30),
	body_id integer references body(id),
	engine_id integer references engine(id),
	transmission_id integer references transmission(id)
);

insert into body(type) values ('Седан'), ('Хэтчбэк'), ('Универсал');

insert into engine(type, volume) values ('Бензиновый', 1.6), ('Бензиновый', 2.0), ('Дизельный', 1.6), ('Дизельный', 2.0);

insert into transmission(type, numberGears) values ('Автоматическая', 6), ('Автоматическая', 7), ('Роботизированная', 6), ('Механиеская', 6), ('Механиеская', 7);

insert into car(name, body_id, engine_id, transmission_id) values ('FordFocus', 2, 2, 3), ('VolkswagenPassat', 1, 4, 5), ('ToyotaCorolla', 2, 1, 1);


--1. Вывести список всех машин и все привязанные к ним детали.
select 
	c.name, 
	b.type,
	e.type,
	e.volume,
	t.type,
	t.numberGears
from car as c
inner join body as b on b.id = c.body_id
inner join engine as e on e.id = c.engine_id
inner join transmission as t on t.id = c.transmission_id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.
select b.* from body as b
left outer join car as c on b.id = c.body_id
where c.body_id is null;

select e.* from engine as e 
left outer join car as c on e.id = c.engine_id
where c.engine_id is null;

select t.* from transmission as t 
left outer join car as c on t.id = c.transmission_id
where c.transmission_id is null;