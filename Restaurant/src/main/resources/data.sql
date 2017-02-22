--Restorani
insert into restaurant(description, name) values ('Melieur', 'MMRestoran');
insert into restaurant(description, name) values ('Good', 'SVRestoran');
insert into restaurant(description, name) values ('Goody', 'MNRestoran');
insert into restaurant(description, name) values ('Allrighty', 'MKRestoran');
insert into restaurant(description, name) values ('Greaty', 'MGRestoran');

--Person
insert into person(email, name, password, surname, username) values ('mm@gmail.com', 'Mirko', 'mirko', 'Mikac', 'mirko');
insert into person(email, name, password, surname, username) values ('sv@gmail.com', 'Stefan', 'stefan', 'Varajic', 'stefan');
insert into person(email, name, password, surname, username) values ('mn@gmail.com', 'Milos', 'milos', 'Nisic', 'milos');
insert into person(email, name, password, surname, username) values ('mk@gmail.com', 'Marko', 'marko', 'Kljajic', 'kljajic');
insert into person(email, name, password, surname, username) values ('mg@gmail.com', 'Milan', 'milan', 'Gvero', 'gvero');
--for guests
insert into person(email, name, password, surname, username) values ('a@a.com', 'Aladin', 'aa', 'Aleksic', 'a@a.com');
insert into person(email, name, password, surname, username) values ('b@b.com', 'Aladin', 'bb', 'Aleksic', 'b@b.com');
insert into person(email, name, password, surname, username) values ('c@c.com', 'Aladin', 'cc', 'Aleksic', 'c@c.com');
insert into person(email, name, password, surname, username) values ('d@d.com', 'Aladin', 'dd', 'Aleksic', 'd@d.com');
insert into person(email, name, password, surname, username) values ('e@e.com', 'Aladin', 'ee', 'Aleksic', 'e@e.com');

--guests
insert into guest(id, active) values ('6', '1');
insert into guest(id, active) values ('7', '1');
insert into guest(id, active) values ('8', '1');
insert into guest(id, active) values ('9', '1');
insert into guest(id, active) values ('10', '1');
--Restaurant Manager
insert into restaurant_manager(id, restaurant_id) values (1, 1);
insert into restaurant_manager(id, restaurant_id) values (2, 2);
insert into restaurant_manager(id, restaurant_id) values (3, 3);
insert into restaurant_manager(id, restaurant_id) values (4, 4);
insert into restaurant_manager(id, restaurant_id) values (5, 5);

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