--liquibase formatted sql
--changeset benganl:01-initial-create

--------------------------------------------------------------- SECURITY TABLES ----------------------------------------------------------------
--
-- Table structure for table users
--
CREATE TABLE IF NOT EXISTS users (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  pssword varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL,
  CONSTRAINT UK_r43af9ap4edm43mmtq01oddj6 UNIQUE (username)
);

--
-- Table structure for table roles
--
CREATE TABLE IF NOT EXISTS roles (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  CONSTRAINT UK_ofx66keruapi6vyqpv6f2or37 UNIQUE (name)
);

--
-- Table structure for table user_roles
--
CREATE TABLE IF NOT EXISTS user_roles (
  id java.util.UUID PRIMARY KEY NOT NULL,
  role_id UUID NOT NULL,
  user_id UUID NOT NULL,
  CONSTRAINT FKh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles (id),
  CONSTRAINT FKhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users (id)
);


--------------------------------------------------------------- CUSTOMER ----------------------------------------------------------------
--
-- Table structure for table customers
--
CREATE TABLE IF NOT EXISTS customers (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  mobile_number varchar(255) DEFAULT NULL,
  java.util.UUID NOT NULL,
  CONSTRAINT FKhfh9dx7w3ubf1co2vdevE5gGG FOREIGN KEY (user_id) REFERENCES users (id)
);

--------------------------------------------------------------- SUPPLIER ----------------------------------------------------------------
--
-- Table structure for table suppliers
--
CREATE TABLE IF NOT EXISTS suppliers (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  user_id binary(16) NOT NULL,
  CONSTRAINT FKhfh9dx7w3ubf2co3vdevf6Hhh FOREIGN KEY (user_id) REFERENCES users (id)
);

--------------------------------------------------------------- PRODUCT ----------------------------------------------------------------
--
-- Table structure for table products
--
CREATE TABLE IF NOT EXISTS products (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  price float DEFAULT NULL,
  supplier_id binary(16) DEFAULT NULL,
  CONSTRAINT FK6i174ixi9087gcvvut45em7fd FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);

--------------------------------------------------------------- ORDER ----------------------------------------------------------------
--
-- Table structure for table orders
--
CREATE TABLE IF NOT EXISTS orders (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  customer_id binary(16) DEFAULT NULL,
  CONSTRAINT FKpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES customers (id)
);

--
-- Table structure for table order_products
--
CREATE TABLE IF NOT EXISTS order_products (
  order_id java.util.UUID NOT NULL,
  product_id java.util.UUID NOT NULL,
  PRIMARY KEY (order_id, product_id),
  CONSTRAINT FKawxpt1ns1sr7al76nvjkv21of FOREIGN KEY (order_id) REFERENCES orders (id),
  CONSTRAINT FKdxjduvg7991r4qja26fsckxv8 FOREIGN KEY (product_id) REFERENCES products (id)
);

--------------------------------------------------------------- LOCATION ----------------------------------------------------------------
--
-- Table structure for table location
--
CREATE TABLE IF NOT EXISTS location (
  id java.util.UUID PRIMARY KEY NOT NULL,
  date_created datetime DEFAULT NULL,
  date_updated datetime DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  latitude float DEFAULT NULL,
  longitude float DEFAULT NULL
);
