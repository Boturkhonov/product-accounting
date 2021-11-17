-------Изменение товара-------
create or replace procedure upd_product(
    p_id integer,
    p_name varchar(255),
    p_unit integer
)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*)
    from product
    where id = p_id
    into c;

    if (c = 0) then
        raise exception 'Ошибка! Товар с идентификатором % не найден', p_id;
    end if;

    update product
    set name    = p_name,
        unit_id = p_unit
    where id = p_id;

    select count(*)
    from product
    where name = p_name
    into c;

    if (c > 1) then
        raise exception 'Ошибка! Товар с названием % уже существует', p_name;
    end if;
    commit;
end;
$$;
-------Изменение склада-------
create or replace procedure upd_storage(
    p_id integer,
    p_address varchar(255),
    p_store_keeper_name varchar(255)
)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*)
    from storage
    where id = p_id
    into c;

    if (c = 0) then
        raise exception 'Ошибка! Склад с идентификатором % не найден', p_id;
    end if;

    update storage
    set address          = p_address,
        storekeeper_name = p_store_keeper_name
    where id = p_id;

    select count(*)
    from storage
    where address = p_address
    into c;

    if (c > 1) then
        raise exception 'Ошибка! Склад с адресом % уже существует', p_address;
    end if;
    commit;
end;
$$;
-------Изменение торг. точки-------
create or replace procedure upd_market(
    p_id integer,
    p_name varchar(255),
    p_address varchar(255)
)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*)
    from market
    where id = p_id
    into c;

    if (c = 0) then
        raise exception 'Ошибка! Торг. точка с идентификатором % не найдена', p_id;
    end if;

    update market
    set name    = p_name,
        address = p_address
    where id = p_id;

    select count(*)
    from market
    where address = p_address
      and name = p_name
    into c;

    if (c > 1) then
        raise exception 'Ошибка! Торг. точка с наименованием % и с адресом % уже существует', p_name, p_address;
    end if;
    commit;
end;
$$;
-------Изменение товара склада-------
create or replace procedure upd_storage_product(
    p_id integer,
    p_quantity integer,
    p_price float
)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*)
    from storage_product
    where id = p_id
    into c;

    if (c = 0) then
        raise exception 'Ошибка! Товар с идентификатором % не найден в складе', p_id;
    end if;

    update storage_product
    set quantity = p_quantity,
        price    = p_price
    where id = p_id;
    commit;
end;
$$;
-------Изменение товара торг. точки-------
create or replace procedure upd_market_product(
    p_id integer,
    p_quantity integer,
    p_price float
)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*)
    from market_product
    where id = p_id
    into c;

    if (c = 0) then
        raise exception 'Ошибка! Товар с идентификатором % не найден в торг. точке', p_id;
    end if;

    update market_product
    set quantity = p_quantity,
        price    = p_price
    where id = p_id;
    commit;
end;
$$