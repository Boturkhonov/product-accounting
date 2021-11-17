----------Добавление товара-------------
create or replace function add_product(
    in p_name varchar(255),
    in p_unit integer
)
    returns integer
    language plpgsql
as
$$
declare
    c      integer;
    new_id integer;
begin
    select count(*)
    from product
    where product.name = p_name
    into c;
    if c = 0 then
        insert into product(name, unit_id)
        values (p_name, p_unit)
        returning id into new_id;
    else
        raise exception 'Ошибка! Товар с названием % уже существует', p_name
            using hint = 'Пожалуйста введите другое название';
    end if;
    return new_id;
end;
$$;
----------Добавление склада-------------
create or replace function add_storage(
    in p_address varchar(255),
    in p_storekeeper_name varchar(255)
)
    returns integer
    language plpgsql
as
$$
declare
    c      integer;
    new_id integer;
begin
    select count(*)
    from storage
    where address = p_address
    into c;
    if c = 0 then
        insert into storage (address, storekeeper_name)
        values (p_address, p_storekeeper_name)
        returning id into new_id;
    else
        raise exception 'Ошибка! Склад с адресом % уже существует', p_address;
    end if;
    return new_id;
end;
$$;
--------Добавление торг. точки----------
create or replace function add_market(
    in p_name varchar(255),
    in p_address varchar(255)
)
    returns integer
    language plpgsql
as
$$
declare
    c      integer;
    new_id integer;
begin
    select count(*)
    from market
    where name = p_name
      and address = p_address
    into c;
    if c = 0 then
        insert into market (name, address)
        values (p_name, p_address)
        returning id into new_id;
    else
        raise exception 'Ошибка! Торг. точка с наименованием % и с адресом % уже существует', p_name, p_address;
    end if;
    return new_id;
end;
$$;
--------Добавление товара склада----------
create or replace function add_storage_product(
    in p_storage_id integer,
    in p_product_id integer,
    in p_quantity integer,
    in p_price float
)
    returns integer
    language plpgsql
as
$$
declare
    c      integer;
    new_id integer;
begin
    select count(*)
    from storage_product
    where storage_id = p_storage_id
      and product_id = p_product_id
    into c;
    if c = 0 then
        insert into storage_product(storage_id, product_id, quantity, price)
        values (p_storage_id, p_product_id, p_quantity, p_price)
        returning id into new_id;
    else
        raise exception 'Ошибка! Товар с идентификатором % уже имеется в данном складе', p_product_id;
    end if;
    return new_id;
end;
$$;
--------Добавление товара торг. точки----------
create or replace function add_market_product(
    in p_market_id integer,
    in p_product_id integer,
    in p_quantity integer,
    in p_price float
)
    returns integer
    language plpgsql
as
$$
declare
    c      integer;
    new_id integer;
begin
    select count(*)
    from market_product
    where market_id = p_market_id
      and product_id = p_product_id
    into c;
    if c = 0 then
        insert into market_product (market_id, product_id, quantity, price)
        values (p_market_id, p_product_id, p_quantity, p_price)
        returning id into new_id;
    else
        raise exception 'Ошибка! Товар с идентификатором % уже имеется в данной торг. точке', p_product_id;
    end if;
    return new_id;
end;
$$