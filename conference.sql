create database conference;

--1. Есть таблица встреч(id, name), есть таблица юзеров(id, name).
--Нужно доработать модель базы данных, так чтобы пользователи могли учавствовать во встречах. У каждого участника встречи может быть разный статус участия - например подтвердил участие, отклонил.

create table if not exists meet(
	id serial primary key,
	name varchar(30)
);

create table if not exists person(
	id serial primary key,
	name varchar(30)
);

create table if not exists state(
	id serial primary key,
	status varchar(30)
);

create table if not exists person_at_meeting(
	meet_id integer references meet(id),
	person_id integer references person(id),
	status_id integer references state(id)
);

insert into meet(name) values ('Java'), ('JavaScript'), ('C#'), ('Python');
insert into person(name) values ('Вася'), ('Коля'), ('Саша'), ('Сережа');
insert into state(status) values ('Подтвердил участие'), ('Отклонил');

insert into person_at_meeting(meet_id, person_id, status_id) values (1, 1, 1), (1, 2, 2), (1, 3, 2), (1, 4, 1),
(2, 1, 1), (2, 2, 1), (2, 3, 1), (2, 4, 2),
(3, 1, 2), (3, 2, 2), (3, 3, 2), (3, 4, 1),
(4, 1, 2), (4, 2, 2), (4, 3, 2), (4, 4, 2);


--2. Нужно написать запрос, который получит список всех заяков и количество подтвердивших участников.
select m.name, count(pm.meet_id) from meet as m, person_at_meeting as pm
where m.id = pm.meet_id and pm.status_id = 1
group by m.name;


--3. Нужно получить все совещания, где не было ни одной заявки на посещения
select m.name from meet as m
left outer join (select m.name from meet as m, person_at_meeting as pm
	where m.id = pm.meet_id and pm.status_id = 1) as res on m.name = res.name
group by m.name
having count(res) = 0;