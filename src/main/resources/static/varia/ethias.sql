set names utf8;
set charset utf8;

drop database if exists ethias;
create database ethias charset utf8;
use ethias;

CREATE TABLE locations(
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  postal_code smallint unsigned NOT NULL,
  place varchar(50) NOT NULL
);

INSERT INTO locations(postal_code, place) VALUES
(1000, 'BRUSSEL'),
(2000, 'ANTWERPEN'),
(3500, 'HASSELT'),
(3740, 'BILZEN'),
(3590, 'DIEPENBEEK'),
(4000, 'LUIK');

CREATE TABLE customers (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  first_name varchar(50) NOT NULL,
  surname varchar(50) NOT NULL,
  national_registry_number bigint unsigned NOT NULL unique,
  street varchar(50) NOT NULL,
  house_number varchar(50) NOT NULL,
  location_id int unsigned NOT NULL,
  email varchar(50) NOT NULL,
  CONSTRAINT fk_customers_locations FOREIGN KEY (location_id) REFERENCES locations(id)
);

INSERT INTO customers(first_name, surname, national_registry_number, street, house_number, location_id, email) VALUES
('Jimmy','Godin', 72092520938, 'Steenakkerstraat','26a', (SELECT id FROM locations WHERE PLACE='DIEPENBEEK'), 'jimmy.godin@hotmail.com'),
('Sonja','Convents', 73022745689, 'Steenakkerstraat','26a', (SELECT id FROM locations WHERE PLACE='DIEPENBEEK'), 'sonja.convents@hotmail.com'),
('Joachim','Perez', 67042832145, 'Langheidestraat','30', (SELECT id FROM locations WHERE PLACE='BILZEN'), 'joachim.perez@hotmail.com'),
('Raymond','Houbrechts', 55031183741, 'Koning Albertstraat','100', (SELECT id FROM locations WHERE PLACE='HASSELT'), 'raymond.houbrechts@hotmail.com'),
('Marc','Praet', 45111159423, 'Nieuwstraat','250', (SELECT id FROM locations WHERE PLACE='BRUSSEL'), 'marc.praet@hotmail.com');

CREATE TABLE policy_types (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  name varchar(50) NOT NULL 
);

INSERT INTO policy_types (name) VALUES
('CAR'),
('FAMILY'),
('HOUSE'),
('LIFE');

CREATE TABLE policies (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  policy_number varchar(50) NOT NULL,
  policy_type_id int unsigned NOT NULL,
  policy_date date NOT NULL,
  premium decimal(10,2) NOT NULL,
  customer_id int unsigned NOT NULL,
  CONSTRAINT fk_policies_policy_types FOREIGN KEY (policy_type_id) REFERENCES policy_types(id),
  CONSTRAINT fk_policies_customers FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO policies(policy_number, policy_type_id, policy_date, premium, customer_id) VALUES 
('CAR025060401', 1, '2018-01-01', 0.00, 1),
('CAR055059402', 1, '2018-01-01', 0.00, 1),
('CAR02F560401', 1, '2018-01-01', 0.00, 2),
('CAR088550678', 1, '2018-01-01', 0.00, 3),
('CAR0H88569K1', 1, '2018-01-01', 0.00, 4),
('CAR0HY561598', 1, '2018-01-01', 0.00, 5);

CREATE TABLE cars (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  serial_number varchar(50) NOT NULL,
  brand varchar(50) NOT NULL,
  model varchar(50) NOT NULL,
  catalog_price decimal(10,2) default 0.00
);

INSERT INTO cars(serial_number, brand, model, catalog_price) VALUES
('QS45J5698OP4523658', 'Mercedes', 'A-Klasse', 0.00),
('QS45J5698OP4523659', 'Mercedes', 'B-Klasse', 0.00),
('QS45J5698OP4523660', 'Mercedes', 'C-Klasse', 0.00),
('QS45J5698OP4523661', 'Toyota', 'Avensis', 0.00),
('QS45J5698OP4523662', 'Toyota', 'Auris', 0.00),
('QS45J5698OP4523663', 'Toyota', 'Yaris', 0.00),
('QS45J5698OP4523664', 'Renault', 'Clio', 0.00),
('QS45J5698OP4523665', 'Renault', 'Kadjar', 0.00),
('QS45J5698OP4523666', 'Renault', 'Captur', 0.00);


CREATE TABLE car_policies (
  id int unsigned NOT NULL primary key,
  omnium tinyint NOT NULL default 0,
  legal_assistance tinyint NOT NULL default 0,
  car_id int unsigned NOT NULL,
  FOREIGN KEY (id) REFERENCES policies(id),
  CONSTRAINT fk_car_policies_cars FOREIGN KEY (car_id) REFERENCES cars(id)
);

INSERT INTO car_policies (id, omnium, legal_assistance, car_id) VALUES
(1, 1, 1, 3),
(2, 1, 0, 6),
(3, 0, 0, 9),
(4, 0, 1, 7),
(5, 1, 0, 6),
(6, 1, 1, 4);

create user if not exists cursist identified by 'cursist';
grant select,insert,update,delete on locations to cursist;
grant select,insert,update,delete on customers to cursist;
grant select,insert,update,delete on policy_types to cursist;
grant select,insert,update,delete on policies to cursist;
grant select,insert,update,delete on cars to cursist;  
grant select,insert,update,delete on car_policies to cursist;