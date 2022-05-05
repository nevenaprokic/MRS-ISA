/*Addresses*/
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

/*ROLE*/
insert into role(name) values ('CLIENT');
insert into role(name) values ('ADMIN');
insert into role(name) values ('COTTAGE_OWNER');
insert into role(name) values ('SHIP_OWNER');
insert into role(name) values ('INSTRUCTOR');


/*CLIENTS*/

insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (1, true, 'pera@gmail.com', 'Petar', 'Peric', '$2a$10$vxfy.kTR4mQxDTIQ6LmeCuoea46hQ9KmeBTTSW4BQay4QD60nXo5K', '062-111-1111', 1, false);/*sifra*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (1, true, 'marko@gmail.com', 'Marko', 'Slavic', '$2a$10$.lyxc9BnJGhDQMcszD2/nuZsMdi1bjP1catCqpAa5AKc0rwtMaIJ.', '065-111-1112', 2, false);/*klijent2*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (1, true, 'sara@gmail.com', 'Sara', 'Mirkovic', '$2a$10$GLP6wpgzOsuq316wup3UlOX7HzPTT5F/86R5PcC/MHw.NYim3vD5a', '063-111-1113', 3, false);/*klijent3*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (1, true, 'lela@gmail.com', 'Lela', 'Mitic', '$2a$10$E6WPiiY.RPGnUNjUjOxMKONnwolnPF490yDx0ROx083y4rRwDgJum', '063-111-1114', 4, false);/*klijent4*/


insert into client (client_category, penal, id) values ('CASUAL_CLIENT', 3, 1);
insert into client (client_category, penal, id) values ('CASUAL_CLIENT', 0, 2);
insert into client (client_category, penal, id) values ('CLOSE_CLIENT', 1, 3);
insert into client (client_category, penal, id) values ('BEST_CLIENT', 0, 4);

/*COTTAGE OWNERS*/

insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (3, true, 'mile@gmail.com', 'Mile', 'Kostic', '$2a$10$6HD4yHeA6yMoWmTuiFLw8ewp2TlXRwLsdu6CSkzYLLvFl.A17i5Cq', '063-111-1115', 5, false);/*vlasnik1*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (3, true, 'mara@gmail.com', 'Mara', 'Dabovic', '$2a$10$ZFhTzzIdX6.j47kW/wCaA.xIJjpt6LCq4ASEa/iUr78LTGIfJSsEC', '063-211-1166', 6, false);/*vlasnik2*/


insert  into owner(owner_category, id) values ('REGULAR', 5);
insert  into owner(owner_category, id) values ('SILVER', 6);

insert into cottage_owner(id) values (5);
insert into cottage_owner(id) values (6);

/*SHIP OWNERS*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (4, true, 'nikola@gmail.com', 'Nikola', 'Nikic', '$2a$10$WVmXrwpduGmr0uLbVQ27zekrOvcpn9c6G3yEuTjbqkTBbD5xevs0i', '061-133-1113', 7, false);/*vlasnik3*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (4, true, 'ksenija@gmail.com', 'Ksenija', 'Sega', '$2a$10$zhlst/yvb/vPF7DXbLB0YeqjV2.tGRIQR1wftPMRYNRaHaPXKY7WW', '063-222-1113', 8, false);/*vlasnik4*/


insert  into owner(owner_category, id) values ('REGULAR', 7);
insert  into owner(owner_category, id) values ('GOLD', 8);

insert into ship_owner(id) values (7);
insert into ship_owner(id) values (8);

/*INSTRUCTORS*/

insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (5, true, 'milan@gmail.com', 'Milan', 'Lakovic', '$2a$10$/xjKdbSgVfsSHEeMbEZOJu7CVFb9dCoLcr/eParcvedbmKfOaeId2', '063-111-1333', 9, false);/*instr1*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (5, true, 'milica@gmail.com', 'Milica', 'Matic', '$2a$10$UwJxipMMlAZZRwxyEMuk/.2wn2UuFt.quxqu0Gj6vnGC.3goMKCu.', '063-111-1212', 10, false);/*instr2*/


insert  into owner(owner_category, id) values ('GOLD', 9);
insert  into owner(owner_category, id) values ('REGULAR', 10);

insert into instructor(biography, id) values ('Pecanjem se bavim od svoje 5 godine. Povedite drustvo i idemo na zabavu!',9);
insert into instructor(biography, id) values ('Svaki dan je nova avantura, pridruzi se mojoj!',10);

 /*ADMIN*/
insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (2, true, 'sava@gmail.com', 'Sava', 'Urosevic', '$2a$10$/6BuFPptMiQChPXx4GbDX.iVVpwXH9EMSVrAcbF3ZSjdt/JDMX826', '064-141-1113', 1, false);/*admin1*/

insert  into admin(id) values (11);

/*ADDITIONAL SERVICES*/
insert into additional_service(name,price) values ('dorucak', 200);
insert into additional_service(name,price) values ('rucak', 500);
insert into additional_service(name,price) values ('klima', 500);
insert into additional_service(name,price) values ('vecera', 300);
insert into additional_service(name,price) values ('fen', 80);
insert into additional_service(name,price) values ('dorucak', 200);
insert into additional_service(name,price) values ('rucak', 500);
insert into additional_service(name,price) values ('klima', 500);
insert into additional_service(name,price) values ('vecera', 300);
insert into additional_service(name,price) values ('fen', 80);

/*PHOTO*/
insert into photo(path) values('pecanje an dunavu1.jpg');
insert into photo(path) values('bela ladja1.jpg');
insert into photo(path) values('brvnara1.jpg');
insert into photo(path) values('brvnara2.jpg');
insert into photo(path) values('original.jpg');

/*COTTAGE*/
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Sjajan pogled na sumu i planinu! U blizini se nalazi ski staza.','Vikendica Raj',2,3000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',1);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Sjajan pogled na sumu i planinu!','Brvnara',4,4000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',2);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u miru i tisini u prirodi.','Jela',3,2500,'Nije dozvoljeno bilo kakvo unistavanje imovine.',3);

insert into cottage(bed_number, room_number, id, my_user_id) values (2,2,1,5);
insert into cottage(bed_number, room_number, id, my_user_id) values (4,3,2,5);
insert into cottage(bed_number, room_number, id, my_user_id) values (3,2,3,6);

/*SHIP*/
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Bela jahta na obali Dunava','Galeb',2,6000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',4);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivanje krstarenjem Dunava uz prijatnu palubu i kabinu za odmor.','Bela ladja',15,12000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',5);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u zvucima talasanja vode!','Sidro',3,5000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',6);

insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Ne poseduje pecarosku opremu',20,2,200,'GPS, VHF RADIO',8,'Jahta',4,7);
insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Ne poseduje pecarosku opremu',20,2,200,'GPS, VHF RADIO',12,'Jahta',5,8);
insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Poseduje pecarosku opremu',18,1,200,'GPS, VHF RADIO',7,'Jahta',6,8);

/*ADVENTURE*/
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u pecanju ribe.','Pecanje u Kikindi',5,6000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',7);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Pecanje kao vid opustanja','Avantura na jezeru',10,12000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',8);
insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u zvucima talasanja vode!','Avantura na Dunavu',3,5000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',9);

insert into adventure(additional_equipment, id, my_user_id) values ('Poseduje pecarosku opremu',7,9);
insert into adventure(additional_equipment, id, my_user_id) values ('Ne poseduje pecarosku opremu',8,9);
insert into adventure(additional_equipment, id, my_user_id) values ('Poseduje pecarosku opremu',9,10);




-- /*RESERVATION*/
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-18',4,6000,'2022-04-17',1,7);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-11',2,3000,'2022-04-10',2,1);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-25',3,2500,'2022-04-17',3,3);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-11',2,3000,'2022-04-10',2,1);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-25',3,2500,'2022-04-17',3,2);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-05-11',2,3000,'2022-05-10',2,5);
insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-05-25',3,2500,'2022-05-17',3,6);

-- /*MARK*/
insert into mark(approved, comment, mark, reservation_id) values (true,'Odlican ambijent!',5,2);
insert into mark(approved, comment, mark, reservation_id) values (true,'Odlican ambijent!',5,3);
insert into mark(approved, comment, mark, reservation_id) values (true,'Onako!',3,4);
insert into mark(approved, comment, mark, reservation_id) values (true,'Nista posebno!',4,5);
insert into mark(approved, comment, mark, reservation_id) values (true,'okej!',4,6);
insert into mark(approved, comment, mark, reservation_id) values (true,'Odlican ambijent!',5,7);
/*COMPLAINT*/
-- insert into complaint(text,reservation_id) values('Sve pohvale!',2);

/*DELETE REQUEST*/
insert into delete_request(description, my_user_id) values('Ne zelim vise da poslujem!',7);

-- -- -- /*MARK*/
-- -- -- insert into mark(approved, comment, mark, reservation_id) values (true,'Odlican ambijent!',5,2);

/*QUICK RESERVATION*/
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-26','2022-05-20',2,2500,'2022-05-23','2022-05-01',6);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-10','2022-05-01',10,10000,'2022-05-08','2022-04-25',8);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-26','2022-05-17',2,2500,'2022-05-23','2022-05-02',1);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-18','2022-05-10',10,10000,'2022-05-15','2022-05-02',2);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-06-26','2022-05-20',2,2500,'2022-06-23','2022-05-03',1);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-26','2022-05-20',2,2500,'2022-05-23','2022-05-01',5);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-26','2022-05-20',2,2500,'2022-05-23','2022-05-01',4);
/*REGISTRATION REQUEST*/


/*RESERVATION REPORT*/
-- insert into reservation_report(automatically_penal, comment, penal_option, reservation_id)values(false,'Jako prijatni ljudi',false,2);



/*RESERVATION*/
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-18',4,6000,'2022-04-17',1,6);
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-03-11',2,3000,'2022-03-10',2,6);
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-25',3,2500,'2022-04-17',3,3);

-- -- /*RESERVATION*/
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-18',4,6000,'2022-04-17',1,1);
-- -- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-11',2,3000,'2022-04-10',2,1);
-- -- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-25',3,2500,'2022-04-17',3,3);

-- -- /*MARK*/
-- -- insert into mark(approved, comment, mark, reservation_id) values (true,'Odlican ambijent!',5,2);
-- --
-- -- /*PHOTO*/
-- insert into photo(path) values('pecanje an dunavu1.jpg');
-- insert into photo(path) values('bela ladja1.jpg');
-- insert into photo(path) values('brvnara1.jpg');
-- insert into photo(path) values('brvnara2.jpg');
-- --
-- -- /*QUICK RESERVATION*/
-- -- insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-04-26','2022-04-20',2,2500,'2022-04-23','2022-04-11',1);
-- -- insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-10','2022-05-01',10,10000,'2022-05-08','2022-04-25',8);
-- --
-- -- /*REGISTRATION REQUEST*/
-- --
-- --
-- -- /*RESERVATION REPORT*/
-- -- insert into reservation_report(automatically_penal, comment, penal_option, reservation_id)values(false,'Jako prijatni ljudi',false,2);
-- --
-- /*ADMIN*/
-- insert into my_user (role_id, email_verified, email, first_name, last_name, password, phone_number, address_id, deleted) values (2, true, 'sava@gmail.com', 'Sava', 'Urosevic', '$2a$10$/6BuFPptMiQChPXx4GbDX.iVVpwXH9EMSVrAcbF3ZSjdt/JDMX826', '124899', 1, false);/*admin1*/
-- insert  into admin(id) values (11);
--
/*ADDITIONAL SERVICES*/
insert into additional_service(name,price) values ('dorucak', 200);
insert into additional_service(name,price) values ('rucak', 500);
insert into additional_service(name,price) values ('klima', 500);
insert into additional_service(name,price) values ('vecera', 300);
insert into additional_service(name,price) values ('fen', 80);
--
--
-- /*COTTAGE*/
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Sjajan pogled na sumu i planinu! U blizini se nalazi ski staza.','Vikendica Raj',2,3000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',1);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Sjajan pogled na sumu i planinu!','Brvnara',4,4000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',2);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u miru i tisini u prirodi.','Jela',3,2500,'Nije dozvoljeno bilo kakvo unistavanje imovine.',3);
--
-- insert into cottage(bed_number, room_number, id, my_user_id) values (2,2,1,5);
-- insert into cottage(bed_number, room_number, id, my_user_id) values (4,3,2,5);
-- insert into cottage(bed_number, room_number, id, my_user_id) values (3,2,3,6);
--
-- /*SHIP*/
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Bela jahta na obali Dunava','Galeb',2,6000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',4);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivanje krstarenjem Dunava uz prijatnu palubu i kabinu za odmor.','Bela ladja',15,12000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',5);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u zvucima talasanja vode!','Sidro',3,5000,'Nije dozvoljeno bilo kakvo unistavanje imovine.',6);
--
-- insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Ne poseduje pecarosku opremu',20,2,200,'GPS, VHF RADIO',8,'Jahta',4,7);
-- insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Ne poseduje pecarosku opremu',20,2,200,'GPS, VHF RADIO',12,'Jahta',5,8);
-- insert into ship(additional_equipment, max_speed, motor_number, motor_power, navigation_equipment, size, type, id, ship_owner_id) values ('Poseduje pecarosku opremu',18,1,200,'GPS, VHF RADIO',7,'Jahta',6,8);

-- /*ADVENTURE*/
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u pecanju ribe.','Pecanje u Kikindi',5,6000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',7);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Pecanje kao vid opustanja','Avantura na jezeru',10,12000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',8);
-- insert into offer(cancellation_conditions, deleted, description, name, number_of_person, price, rules_of_conduct, address_id) values ('Dozvoljeno je otkazivanje 3 dana pre.',false,'Uzivajte u zvucima talasanja vode!','Avantura na Dunavu',3,5000,'Nije dozvoljeno bilo kakvo unistavanje imovine i prirode.',9);
--
-- insert into adventure(additional_equipment, id, my_user_id) values ('Poseduje pecarosku opremu',7,9);
-- insert into adventure(additional_equipment, id, my_user_id) values ('Ne poseduje pecarosku opremu',8,9);
-- insert into adventure(additional_equipment, id, my_user_id) values ('Poseduje pecarosku opremu',9,10);
--
--
-- /*RESERVATION*/
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-18',4,6000,'2022-04-17',1,7);
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-11',2,3000,'2022-04-10',2,1);
-- insert into reservation(deleted,end_date,number_of_person,price,start_date,my_user_id,offer_id) values (false,'2022-04-25',3,2500,'2022-04-17',3,3);
--
--
-- /*COMPLAINT*/
-- insert into complaint(text,reservation_id) values('Sve pohvale!',2);
--
-- /*DELETE REQUEST*/
-- insert into delete_request(description, my_user_id) values('Ne zelim vise da poslujem!',7);
--

--
-- /*PHOTO*/
-- insert into photo(path) values('pecanje an dunavu1.jpg');
-- insert into photo(path) values('bela ladja1.jpg');
-- insert into photo(path) values('brvnara1.jpg');
-- insert into photo(path) values('brvnara2.jpg');
-- insert into photo(path) values('original.jpg');

/*QUICK RESERVATION*/
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-04-26','2022-04-20',2,2500,'2022-04-23','2022-04-11',7);
insert into quick_reservation(end_date,end_date_action,number_of_person,price,start_date,start_date_action,offer_id) values('2022-05-10','2022-05-01',10,10000,'2022-05-08','2022-04-25',7);
--
-- /*REGISTRATION REQUEST*/sz
--
--
-- /*RESERVATION REPORT*/
-- insert into reservation_report(automatically_penal, comment, penal_option, reservation_id)values(false,'Jako prijatni ljudi',false,2);



/*TRANSACTION*/
insert into transaction (date,price,reservation_id) values ('2022-04-01',6000,1);
insert into transaction (date,price,reservation_id) values ('2022-03-27',3000,2);
insert into transaction (date,price,reservation_id) values ('2022-03-29',2500,3);

/*OFFER ADDITIONAL SERVICES*/
insert into offer_additional_services(offer_id,additional_services_id) values (1,1);
insert into offer_additional_services(offer_id,additional_services_id) values (1,2);
insert into offer_additional_services(offer_id,additional_services_id) values (1,3);
insert into offer_additional_services(offer_id,additional_services_id) values (5,5);
insert into offer_additional_services(offer_id,additional_services_id) values (5,6);
insert into offer_additional_services(offer_id,additional_services_id) values (6,4);
insert into offer_additional_services(offer_id,additional_services_id) values (6,7);
insert into offer_additional_services(offer_id,additional_services_id) values (7,8);
--
-- /*OFFER PHOTO*/
insert into offer_photos(offer_id,photos_id) values (9,1);
insert into offer_photos(offer_id,photos_id) values (5,2);
insert into offer_photos(offer_id,photos_id) values (2,3);
insert into offer_photos(offer_id,photos_id) values (2,4);
insert into offer_photos(offer_id,photos_id) values (1,5);
--
-- /*OWNER TRANSACTION*/
-- insert into owner_transaction(owner_id,transaction_id) values (9,1);
-- insert into owner_transaction(owner_id,transaction_id) values (5,2);
-- insert into owner_transaction(owner_id,transaction_id) values (6,3);

-- -- /*TRANSACTION*/
-- -- insert into transaction (date,price,reservation_id) values ('2022-04-01',6000,1);
-- -- insert into transaction (date,price,reservation_id) values ('2022-03-27',3000,2);
-- -- insert into transaction (date,price,reservation_id) values ('2022-03-29',2500,3);
-- --
-- --
-- -- /*OFFER ADDITIONAL SERVICES*/
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (1,1);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (1,2);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (1,3);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (2,3);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (3,1);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (3,4);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (4,4);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (4,2);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (5,4);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (6,4);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (7,2);
-- -- insert into offer_additional_services(offer_id,additional_services_id) values (9,2);
-- --
-- --
-- -- /*OWNER TRANSACTION*/
-- -- insert into owner_transaction(owner_id,transaction_id) values (9,1);
-- -- insert into owner_transaction(owner_id,transaction_id) values (5,2);
-- -- insert into owner_transaction(owner_id,transaction_id) values (6,3);

-- --
-- -- /*QUICK RESERVATION ADDITIONAL SERVICES*/
-- -- insert into quick_reservation_additional_services(quick_reservation_id,additional_services_id) values (1,1);
-- -- insert into quick_reservation_additional_services(quick_reservation_id,additional_services_id) values (1,2);
-- -- insert into quick_reservation_additional_services(quick_reservation_id,additional_services_id) values (1,4);
-- -- insert into quick_reservation_additional_services(quick_reservation_id,additional_services_id) values (2,1);
-- --
-- -- /*RESERVATION ADDITIONAL SERVICES*/
-- -- insert into reservation_additional_services(reservation_id,additional_services_id) values (7,1);
-- -- insert into reservation_additional_services(reservation_id,additional_services_id) values (1,1);
-- -- insert into reservation_additional_services(reservation_id,additional_services_id) values (1,2);
-- -- insert into reservation_additional_services(reservation_id,additional_services_id) values (1,5);
-- -- insert into reservation_additional_services(reservation_id,additional_services_id) values (3,1);
-- --
-- -- /*SUBSCRIBE*/
-- -- insert into subscribe(my_user_id, offer_id) values (1,1);
-- -- insert into subscribe(my_user_id, offer_id) values (1,3);
-- -- insert into subscribe(my_user_id, offer_id) values (1,5);
-- -- insert into subscribe(my_user_id, offer_id) values (1,7);
-- -- insert into subscribe(my_user_id, offer_id) values (2,2);
-- -- insert into subscribe(my_user_id, offer_id) values (3,3);
-- -- insert into subscribe(my_user_id, offer_id) values (3,4);
-- -- insert into subscribe(my_user_id, offer_id) values (4,9);
-- -- insert into subscribe(my_user_id, offer_id) values (4,6);
-- --
