create database products;

create table type(
	id serial primary key,
	name varchar(30)
);

create table product(
	id serial primary key, 
	name varchar(30), 
	type_id integer references type(id), 
	expired_date timestamp, 
	price real
);

insert into type(name) values('Сыр'), ('Молоко'), ('Колбаса');

insert into product(name, type_id, expired_date, price) values
('Пармезан', 1, '2020-03-29 01:00', 100.0), 
('Чеддер', 1, '2020-04-10 01:00', 120.0), 
('Гауда', 1, '2020-04-05 01:00', 110.0), 
('Рокфор', 1, '2020-03-31 01:00', 90.0);

insert into product(name, type_id, expired_date, price) values
('Простоквашино', 2, '2020-05-12 01:00', 45.5), 
('Домик в деревне', 2, '2020-04-07 01:00', 50.0);

insert into product(name, type_id, expired_date, price) values
('Черкизово', 3, '2020-04-20 01:00', 220.0), 
('Высший вкус', 3, '2020-04-17 01:00', 240.0), 
('Ремит', 3, '2020-04-08 01:00',300.8);


--1. Написать запрос получение всех продуктов с типом "Сыр"
select * from product as p
inner join type as t on p.type_id = t.id
where t.name = 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "Мороженное"
select * from product
where name = 'Мроженное';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
select * from product
where date_trunc('month', expired_date) = date_trunc('month', now() + '1 month');

--4. Написать запрос, который выводит самый дорогой продукт.
select * from product
where price = (select max(price) from product); 

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
select t.name, count(t.name) from type as t
inner join product as p on t.id = p.type_id
group by t.name;

--6. Написать запрос получение всех продуктов с типом "Сыр" и "Молоко"
select * from product as p
inner join type as t on p.type_id = t.id
where t.name in('Сыр', 'Молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
select t.name from type as t
inner join product as p on t.id = p.type_id
group by t.name
having count(t.name) < 10;

--8. Вывести все продукты и их тип.
select p.*, t.name from product as p
inner join type as t on p.type_id = t.id; 