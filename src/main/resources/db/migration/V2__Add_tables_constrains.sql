alter table sub_account add constraint FK_customer_id foreign key (customer_id) references customer;
alter table transaction add constraint FK_customer_id_subaccount foreign key (customer_id) references customer;
alter table transaction add constraint FK_subaccount_id foreign key (subaccount_id) references sub_account;
