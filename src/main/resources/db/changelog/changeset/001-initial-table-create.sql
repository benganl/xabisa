--liquibase formatted sql

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-01
--
-- Table structure for table customers
--
CREATE TABLE IF NOT EXISTS customers (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  mobile_number varchar(255) DEFAULT NULL,
  user_id binary(16) NOT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-02
--
-- Table structure for table location
--
CREATE TABLE IF NOT EXISTS location (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  latitude float DEFAULT NULL,
  longitude float DEFAULT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-03
--
-- Table structure for table orders
--
CREATE TABLE IF NOT EXISTS orders (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  customer_id binary(16) DEFAULT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-04
--
-- Table structure for table order_products
--
CREATE TABLE IF NOT EXISTS order_products (
  order_id binary(16) NOT NULL,
  product_id binary(16) NOT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-05
--
-- Table structure for table products
--
CREATE TABLE IF NOT EXISTS products (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  price float DEFAULT NULL,
  supplier_id binary(16) DEFAULT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-06
--
-- Table structure for table roles
--
CREATE TABLE IF NOT EXISTS roles (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  name varchar(255) DEFAULT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-07
--
-- Table structure for table suppliers
--
CREATE TABLE IF NOT EXISTS suppliers (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  description varchar(255) DEFAULT NULL,
  user_id binary(16) NOT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-08
--
-- Table structure for table users
--
CREATE TABLE IF NOT EXISTS users (
  id binary(16) NOT NULL,
  date_created datetime(6) DEFAULT NULL,
  date_updated datetime(6) DEFAULT NULL,
  pssword varchar(255) DEFAULT NULL,
  username varchar(255) DEFAULT NULL
);

-- --------------------------------------------------------
--changeset benganl:001-initial-table-create-09
--
-- Table structure for table user_roles
--
CREATE TABLE IF NOT EXISTS user_roles (
  user_id binary(16) NOT NULL,
  role_id binary(16) NOT NULL
);
