-- /*Addresses*/
insert into address (street, city, state) values ('Omladinska 19', 'Novi Sad', 'Srbija');
insert into address (street, city, state) values ('Jevrejska 44', 'Beograd', 'Srbija');
insert into address (street, city, state) values ('Bulevar kralja Aleksandra 37', 'Beograd', 'Srbija');
insert into address (street, city, state) values ('Fruskogorksa 14', 'Novi Sad', 'Srbija');
insert into address (street, city, state) values ('Bulevar oslobodjenja 119', 'Novi Sad', 'Srbija');
insert into address (street, city, state) values ('Somborska 39', 'Novi Sad', 'Srbija');
insert into address (street, city, state) values ('Lasla Gala 4', 'Beograd', 'Srbija');
insert into address (street, city, state) values ('Bulevar kralja Petra 137', 'Beograd', 'Srbija');
insert into address (street, city, state) values ('Fruskogorksa 60', 'Novi Sad', 'Srbija');
insert into address (street, city, state) values ('Mise Dimitrijevica 19', 'Novi Sad', 'Srbija');


insert into my_user (email, first_name, last_name, password, phone_number, address_id, deleted) values ('pera@gmail.com', 'Petar', 'Peric', '$2a$10$vxfy.kTR4mQxDTIQ6LmeCuoea46hQ9KmeBTTSW4BQay4QD60nXo5K', '124890', 1, false);
insert into my_user (email, first_name, last_name, password, phone_number, address_id, deleted) values ('mika@gmail.com', 'Mika', 'Mikic', '$2a$10$vxfy.kTR4mQxDTIQ6LmeCuoea46hQ9KmeBTTSW4BQay4QD60nXo5K', '123456', 2, false);

insert into client (client_category, penal, id) values (0, 3, 1);
insert into owner (owner_category, id) values (0, 1);

insert into instructor (biography, id) values ('Pacenje je moj hobi od detinjstva i mnogo volim da provodim vreme na vodi', 1);
