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
  customer_id int unsigned NOT NULL,
  CONSTRAINT fk_policies_policy_types FOREIGN KEY (policy_type_id) REFERENCES policy_types(id),
  CONSTRAINT fk_policies_customers FOREIGN KEY (customer_id) REFERENCES customers(id)
);

INSERT INTO policies(policy_number, policy_type_id, policy_date, customer_id) VALUES 
('C025060401', 1, '2018-01-01', 1),
('H055059402', 3, '2018-01-01', 2),
('L02F560401', 4, '2018-01-01', 3),
('C088550678', 1, '2018-01-01', 4),
('F0H88569K1', 2, '2018-01-01', 1),
('C0HY561598', 1, '2018-01-01', 5);

CREATE TABLE car_policies (
  id int unsigned NOT NULL primary key,
  brand varchar(50) NOT NULL,
  model varchar(50) NOT NULL,
  catalog_price decimal(10,2) NOT NULL,
  FOREIGN KEY (id) REFERENCES policies(id)
);

INSERT INTO car_policies (id, brand, model, catalog_price) VALUES
(1, 'Renault', 'Clio', 15000),
(4, 'Toyota', 'Avensis', 30000),
(6, 'Honda', 'Civic', 35000);

CREATE TABLE house_policies (
  id int unsigned NOT NULL primary key,
  price decimal(10,2) NOT NULL,
  FOREIGN KEY (id) REFERENCES policies(id)
);

INSERT INTO house_policies (id, price) VALUES
(2, 250000);

CREATE TABLE life_policies (
  id int unsigned NOT NULL primary key,
  amount decimal(10,2) NOT NULL,
  FOREIGN KEY (id) REFERENCES policies(id)
);

INSERT INTO life_policies (id, amount) VALUES
(3, 100000);

CREATE TABLE family_policies (
  id int unsigned NOT NULL primary key,
  members int unsigned NOT NULL,
  FOREIGN KEY (id) REFERENCES policies(id)
);

INSERT INTO family_policies (id, members) VALUES
(5, 4);

create user if not exists cursist identified by 'cursist';
grant select,insert,update,delete on locations to cursist;
grant select,insert,update,delete on customers to cursist;
grant select,insert,update,delete on policy_types to cursist;
grant select,insert,update,delete on policies to cursist;
grant select,insert,update,delete on car_policies to cursist;
grant select,insert,update,delete on family_policies to cursist;
grant select,insert,update,delete on life_policies to cursist;
grant select,insert,update,delete on house_policies to cursist;