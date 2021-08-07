create table If not exists customer (id bigint not null, balance double, name varchar(255), surname varchar(255), primary key (id));
create table If not exists sub_account (id bigint not null, balance double, child_id bigint, primary key (id));
create table If not exists transaction (id bigint not null, amount double not null, transaction_type integer not null, sub_account_id_id bigint, customer_id bigint, primary key (id));
