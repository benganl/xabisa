--liquibase formatted sql

--
-- Indexes for table customers
--changeset benganl:002-create-indexes-01-01
--
ALTER TABLE customers
  ADD PRIMARY KEY (id),
  ADD KEY FKrh1g1a20omjmn6kurd35o3eit (user_id);
  
--
-- Indexes for table location
--changeset benganl:002-create-indexes-01-02
--
ALTER TABLE location ADD PRIMARY KEY (id);

--
-- Indexes for table orders
--changeset benganl:002-create-indexes-01-03
--
ALTER TABLE orders ADD PRIMARY KEY (id), ADD KEY FKpxtb8awmi0dk6smoh2vp1litg (customer_id);

--
-- Indexes for table order_products
--changeset benganl:002-create-indexes-01-04
--
ALTER TABLE order_products ADD PRIMARY KEY (order_id,product_id), ADD KEY FKdxjduvg7991r4qja26fsckxv8 (product_id);

--
-- Indexes for table products
--changeset benganl:002-create-indexes-01-05
--
ALTER TABLE products ADD PRIMARY KEY (id), ADD KEY FK6i174ixi9087gcvvut45em7fd (supplier_id);

--
-- Indexes for table roles
--changeset benganl:002-create-indexes-01-06
--
ALTER TABLE roles ADD PRIMARY KEY (id), ADD UNIQUE KEY UK_ofx66keruapi6vyqpv6f2or37 (name);

--
-- Indexes for table suppliers
--changeset benganl:002-create-indexes-01-07
--
ALTER TABLE suppliers ADD PRIMARY KEY (id), ADD KEY FKja3xaqihwgllahrmo5op9ks4d (user_id);

--
-- Indexes for table users
--changeset benganl:002-create-indexes-01-08
--
ALTER TABLE users ADD PRIMARY KEY (id), ADD UNIQUE KEY UK_r43af9ap4edm43mmtq01oddj6 (username);

--
-- Indexes for table user_roles
--changeset benganl:002-create-indexes-01-09
--
ALTER TABLE user_roles ADD PRIMARY KEY (user_id,role_id), ADD KEY FKh8ciramu9cc9q3qcqiv4ue8a6 (role_id);

--
-- Constraints for table customers
--changeset benganl:002-create-indexes-01-10
--
ALTER TABLE customers ADD CONSTRAINT FKrh1g1a20omjmn6kurd35o3eit FOREIGN KEY (user_id) REFERENCES users (id);

--
-- Constraints for table orders
--changeset benganl:002-create-indexes-01-11
--
ALTER TABLE orders ADD CONSTRAINT FKpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES customers (id);

--
-- Constraints for table order_products
--changeset benganl:002-create-indexes-01-12
--
ALTER TABLE order_products
  ADD CONSTRAINT FKawxpt1ns1sr7al76nvjkv21of FOREIGN KEY (order_id) REFERENCES orders (id),
  ADD CONSTRAINT FKdxjduvg7991r4qja26fsckxv8 FOREIGN KEY (product_id) REFERENCES products (id);

--
-- Constraints for table products
--changeset benganl:002-create-indexes-01-13
--
ALTER TABLE products ADD CONSTRAINT FK6i174ixi9087gcvvut45em7fd FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

--
-- Constraints for table suppliers
--changeset benganl:002-create-indexes-01-14
--
ALTER TABLE suppliers ADD CONSTRAINT FKja3xaqihwgllahrmo5op9ks4d FOREIGN KEY (user_id) REFERENCES users (id);

--
-- Constraints for table user_roles
--changeset benganl:002-create-indexes-01-15
--
ALTER TABLE user_roles
  ADD CONSTRAINT FKh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id) REFERENCES roles (id),
  ADD CONSTRAINT FKhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id) REFERENCES users (id);
  
