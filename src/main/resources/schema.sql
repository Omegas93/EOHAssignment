create schema eoh;

create table eoh.invoice
(
	id bigint auto_increment not null,
	client varchar(255) not null,
	vat_rate bigint not null,
	invoice_date date not null,
	primary key (id)
);

create table eoh.line_item
(
	id bigint auto_increment not null,
	description varchar(255) not null,
	quantity bigint not null,
	unit_price decimal(5,2) not null,
	invoice_id bigint,
	foreign key (invoice_id) references invoice(id),
	primary key (id)
);