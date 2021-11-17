-- Товар
create table product
(
    id      serial unique       not null,
    name    varchar(255) unique not null,
    unit_id integer             not null,
    foreign key (unit_id) references unit (id),
    primary key (id)
);

create index product_unit_id_idx on product using btree (unit_id);

-- Склад
create table storage
(
    id               serial unique       not null,
    address          varchar(255) unique not null,
    storekeeper_name varchar(255)        not null,
    primary key (id)
);

-- Торговая точка
create table market
(
    id      serial unique       not null,
    name    varchar(255) unique not null,
    address varchar(255) unique not null,
    primary key (id)
);

-- Товар склада
create table storage_product
(
    id         serial unique                           not null,
    storage_id integer                                 not null,
    product_id integer                                 not null,
    quantity   integer check (quantity >= 0) default 0 not null,
    price      float check (price > 0)                 not null,
    foreign key (storage_id) references storage (id)
        on DELETE no action
        on UPDATE cascade,
    foreign key (product_id) references product (id)
        on DELETE no action
        on UPDATE cascade,
    primary key (id)
);

create index storage_product_storage_id_idx on storage_product using btree (storage_id);
create index storage_product_product_id_idx on storage_product using btree (product_id);

-- Товар торговой точки
create table market_product
(
    id         serial unique                           not null,
    market_id  integer                                 not null,
    product_id integer                                 not null,
    quantity   integer check (quantity >= 0) default 0 not null,
    price      float check (price > 0)                 not null,
    foreign key (market_id) references market (id)
        on DELETE no action
        on UPDATE cascade,
    foreign key (product_id) references product (id)
        on DELETE no action
        on UPDATE cascade,
    primary key (id)
);

create index market_product_market_id_idx on market_product using btree (market_id);
create index market_product_product_id_idx on market_product using btree (product_id);

-- Ед. измерения
create table unit
(
    id   serial unique      not null,
    name varchar(10) unique not null,
    primary key (id)
);

insert into unit (name) VALUES ('кг.');
insert into unit (name) VALUES ('г.');
insert into unit (name) VALUES ('мг.');
insert into unit (name) VALUES ('шт.');
insert into unit (name) VALUES ('л.');
insert into unit (name) VALUES ('мл.');
insert into unit (name) VALUES ('м.');
insert into unit (name) VALUES ('см.');