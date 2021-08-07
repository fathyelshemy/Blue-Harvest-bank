alter table sub_account add constraint FK3g7b3b0xil5gs389pwyqo9kjx foreign key (child_id) references customer;
alter table transaction add constraint FKmtk6ij2qollxs7agfr14s5ax6 foreign key (sub_account_id_id) references sub_account;
alter table transaction add constraint FKnbpjofb5abhjg5hiovi0t3k57 foreign key (customer_id) references customer;
