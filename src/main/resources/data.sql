insert into eoh.invoice (client, vat_rate, invoice_date) 
values ('Siyabonga Ntuli', 15, '2018-01-01');

insert into eoh.invoice (client, vat_rate, invoice_date)
values ('John Manganyi', 14, '2018-01-06');

insert into eoh.invoice (client, vat_rate, invoice_date)
values ('Ncumisa Majodina', 13, '2018-01-12');

insert into eoh.invoice (client, vat_rate, invoice_date)
values ('Portia Fakude', 12, '2018-01-23');

insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Claw hammer', 20, 239.89, 1);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Screwdriver', 4, 56.74, 1);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Tape Measures', 7, 86.50, 2);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Adjustable spanner', 3, 45.37, 2);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Locking pilers', 5, 76.89, 3);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Needle-nose pilers', 11, 67.98, 3);
insert into eoh.line_item (description, quantity, unit_price, invoice_id)
values ('Augers', 3, 368.65, 2);