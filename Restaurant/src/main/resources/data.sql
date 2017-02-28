--Restorani
insert into restaurant(description, name) values ('Melieur', 'MMRestoran');
insert into restaurant(description, name) values ('Good', 'SVRestoran');
insert into restaurant(description, name) values ('Goody', 'MNRestoran');
insert into restaurant(description, name) values ('Allrighty', 'MKRestoran');
insert into restaurant(description, name) values ('Greaty', 'MGRestoran');

--Person
insert into person(email, name, password, surname, username) values ('admin@gmail.com', 'Admin', 'admin', 'Admin', 'admin');
insert into person(email, name, password, surname, username) values ('mm@gmail.com', 'Mirko', 'mirko', 'Mikac', 'mirko');
insert into person(email, name, password, surname, username) values ('sv@gmail.com', 'Stefan', 'stefan', 'Varajic', 'stefan');
insert into person(email, name, password, surname, username) values ('mn@gmail.com', 'Milos', 'milos', 'Nisic', 'milos');
insert into person(email, name, password, surname, username) values ('mk@gmail.com', 'Marko', 'marko', 'Kljajic', 'kljajic');
insert into person(email, name, password, surname, username) values ('mg@gmail.com', 'Milan', 'milan', 'Gvero', 'gvero');
insert into person(email, name, password, surname, username) values ('vv@gmail.com', 'Vin', 'vin', 'Vin', 'vin');


--Personi za radnike
insert into person(email, name, password, surname, username) values ('r1@gmail.com', 'Mirko', 'mirko', 'Mikac', 'mirko');
insert into person(email, name, password, surname, username) values ('r2@gmail.com', 'Stefan', 'stefan', 'Varajic', 'stefan');
insert into person(email, name, password, surname, username) values ('r3@gmail.com', 'Milos', 'milos', 'Nisic', 'milos');
insert into person(email, name, password, surname, username) values ('r4@gmail.com', 'Marko', 'marko', 'Kljajic', 'kljajic');
insert into person(email, name, password, surname, username) values ('r5@gmail.com', 'Milan', 'milan', 'Gvero', 'gvero');
insert into person(email, name, password, surname, username) values ('r6@gmail.com', 'Milan', 'milan', 'Gvero', 'gvero');

--Personi za admine
insert into person(email, name, password, surname, username) values ('a1@gmail.com', 'Admin', 'admin', 'Admin', 'admin');
insert into person(email, name, password, surname, username) values ('a2@gmail.com', 'Admin', 'admin', 'Admin', 'admin');
insert into person(email, name, password, surname, username) values ('a3@gmail.com', 'Admin', 'admin', 'Admin', 'admin');
insert into person(email, name, password, surname, username) values ('a4@gmail.com', 'Admin', 'admin', 'Admin', 'admin');
insert into person(email, name, password, surname, username) values ('a5@gmail.com', 'Admin', 'admin', 'Admin', 'admin');


--Menadzer sistema
insert into administrator(supreme, id) values (true, 1);
insert into administrator(supreme, id) values (false, 14);
insert into administrator(supreme, id) values (false, 15);
insert into administrator(supreme, id) values (false, 16);
insert into administrator(supreme, id) values (false, 17);
insert into administrator(supreme, id) values (false, 18);


--Restaurant Manager
insert into restaurant_manager(id, restaurant_id) values (2, 1);
insert into restaurant_manager(id, restaurant_id) values (3, 2);
insert into restaurant_manager(id, restaurant_id) values (4, 3);
insert into restaurant_manager(id, restaurant_id) values (5, 4);
insert into restaurant_manager(id, restaurant_id) values (6, 5);

--Segmenti restorana
insert into restaurant_segment(name, type_of, restaurant_id) values('Balcony Hall', 'NO_SMOKING', 1);
insert into restaurant_segment(name, type_of, restaurant_id) values ('Lobby Hall', 'SMOKING', 1);
insert into restaurant_segment(name, type_of, restaurant_id) values ('Outside Hall', 'NO_SMOKING', 3);
insert into restaurant_segment(name, type_of, restaurant_id) values ('Main Hall', 'NO_SMOKING', 4);

--Menii restorana
insert into menu(restaurant_id) values (1);
insert into menu(restaurant_id) values (2);
insert into menu(restaurant_id) values (3);
insert into menu(restaurant_id) values (4);
insert into menu(restaurant_id) values (5);

--Podaci za meni restorana id = 1
insert into meal(name, description, price, menu_id) values ("Sarma", "MEAT", 400, 1);
insert into meal(name, description, price, menu_id) values ("Supa", "VEGAN", 200, 1);
insert into meal(name, description, price, menu_id) values ("Кumpijeri", "MEAT", 100, 1);

--Kartice pica za restorane
insert into drink_card(restaurant_id) values (1);
insert into drink_card(restaurant_id) values (2);
insert into drink_card(restaurant_id) values (3);
insert into drink_card(restaurant_id) values (4);
insert into drink_card(restaurant_id) values (5);

--Podaci za karticu pica restorana id = 1
insert into drink (name, description, price, drink_card_id) values ("Pivo", "pivo", 100, 1);
insert into drink (name, description, price, drink_card_id) values ("Vino", "vino", 120, 1);
insert into drink (name, description, price, drink_card_id) values ("Rakija", "rakija", 150, 1);

--POdaci za radnike
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date ,first_time_changepw) values(3, 0, 7, 7, 1, '43', '45', '1979-11-11 00:00:00', 0);
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date,first_time_changepw) values(3, 0, 8, 8, 1, '42', '43', '1966-11-11 00:00:00',0);
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date,first_time_changepw) values(3, 2, 9, 9, 1, '41', '42', '1970-11-11 00:00:00', 0);
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date,first_time_changepw) values(3, 2, 10, 10, 1, '44', '41', '1980-11-11 00:00:00', 0);
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date,first_time_changepw) values(3, 1, 11, 11, 1, '45', '40', '1982-11-11 00:00:00', 0);
insert into worker(shift, type, working_hours, id, restaurant_id, shoe_size, wear_size, birth_date,first_time_changepw) values(3, 1, 12, 12, 1, '41', '46', '1969-11-11 00:00:00', 0);

--Podaci za kuvare
insert into cook (uloga, id) values ('pekac', 7);
insert into cook (uloga, id) values ('sekac', 8);

--Podaci za kuvare
insert into waiter (id) values (9);
insert into waiter (id) values (10);

--Podaci za kuvare
insert into bartender (id) values (11);
insert into bartender(id) values (12);

--Podaci za raspored
insert into working_schedule(worker_id, segment_id, start, end, shift) values (7, 1, '2017-2-28 00:00:00', '2017-2-28 00:00:00', 2);
insert into working_schedule(worker_id, segment_id, start, end, shift) values (8, 1, '2017-2-28 00:00:00', '2017-2-28 00:00:00', 2);
insert into working_schedule(worker_id, segment_id, start, end, shift) values (9, 1, '2017-2-28 00:00:00', '2017-2-28 00:00:00', 2);
insert into working_schedule(worker_id, segment_id, start, end, shift) values (10, 1, '2017-2-28 00:00:00', '2017-2-28 00:00:00', 2);
insert into working_schedule(worker_id, segment_id, start, end, shift) values (11, 1, '2017-2-28 00:00:00', '2017-2-28 00:00:00', 2);
insert into working_schedule(worker_id, segment_id, start, end, shift) values (12, 1, '2017-2-28 00:00:00', '2017-3-3 00:00:00', 2);

--Unos rejtinga za (Stefan Varajic, Milos Nisic)
insert into rating(rating, restaurant_id) values (8, 1);
insert into rating(rating, restaurant_id) values (6, 1);
insert into rating(rating, restaurant_id) values (5, 1);
insert into rating(rating, restaurant_id) values (4, 1);
insert into rating(rating, restaurant_id) values (3, 1);


--Dodatni unosi
insert into rating(rating, waiter_id) values (8, 9);
insert into rating(rating, waiter_id) values (8, 10);
insert into rating(rating, waiter_id) values (8, 10);
insert into rating(rating, waiter_id) values (8, 9);
insert into rating(rating, waiter_id) values (9, 10);
insert into rating(rating, waiter_id) values (9, 10);


--Persons for guests
insert into person(email, name, password, surname, username) values ('a@a.com', 'Aladin', 'aa', 'Aleksic', 'a@a.com');
insert into person(email, name, password, surname, username) values ('b@b.com', 'Aladin', 'bb', 'Aleksic', 'b@b.com');
insert into person(email, name, password, surname, username) values ('c@c.com', 'Aladin', 'cc', 'Aleksic', 'c@c.com');
insert into person(email, name, password, surname, username) values ('d@d.com', 'Aladin', 'dd', 'Aleksic', 'd@d.com');
insert into person(email, name, password, surname, username) values ('e@e.com', 'Aladin', 'ee', 'Aleksic', 'e@e.com');

--Guests
insert into guest(id, active) values ('18', '1');
insert into guest(id, active) values ('19', '1');
insert into guest(id, active) values ('20', '1');
insert into guest(id, active) values ('21', '1');
insert into guest(id, active) values ('22', '1');

--Friends
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('19', '18');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('18', '19');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('19', '20');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('20', '19');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('19', '21');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('21', '19');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('19', '22');
INSERT INTO `git`.`friendship_table` (`g1_id`, `g2_id`) VALUES ('22', '19');

--Tables
INSERT INTO `git`.`table_table` (`id`, `restaurant_id`, `restaurant_segment_id`) VALUES ('2', '1', '2');
INSERT INTO `git`.`table_table` (`id`, `restaurant_id`, `restaurant_segment_id`) VALUES ('3', '1', '2');
INSERT INTO `git`.`table_table` (`id`, `restaurant_id`, `restaurant_segment_id`) VALUES ('4', '2', '1');
INSERT INTO `git`.`table_table` (`id`, `restaurant_id`, `restaurant_segment_id`) VALUES ('5', '2', '2');
