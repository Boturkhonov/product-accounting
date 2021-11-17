-------Удаление товара-------
create or replace procedure del_product(p_id integer)
    language plpgsql
as
$$
declare
    c1 integer;
    c2 integer;
    c3 integer;
begin
    select count(*)
    from market_product
    where product_id = p_id
    into c1;

    select count(*)
    from storage_product
    where product_id = p_id
    into c2;

    select count(*)
    from product
    where id = p_id
    into c3;

    if ((c1 <> 0) or (c2 <> 0)) then
        raise exception 'Ошибка! Нельзя удалить товар т.к. есть зависимые элементы';
    elseif c3 = 0 then
        raise exception 'Ошибка! Товар с идентификатором % не найден', p_id;
    else
        delete from product where id = p_id;
    end if;
    commit;
end;
$$;
-------Удаление склада-------
create or replace procedure del_storage(p_id integer)
    language plpgsql
as
$$
declare
    c1 integer;
    c2 integer;
begin
    select count(*)
    from storage_product
    where storage_id = p_id
    into c1;

    select count(*)
    from storage
    where id = p_id
    into c2;

    if (c1 <> 0) then
        raise exception 'Ошибка! Нельзя удалить склад т.к. есть зависимые элементы';
    elseif (c2 = 0) then
        raise exception 'Ошибка! Склад с идентификатором % не найден', p_id;
    else
        delete from storage where id = p_id;
    end if;
    commit;
end;
$$;
-------Удаление торг. точки-------
create or replace procedure del_market(p_id integer)
    language plpgsql
as
$$
declare
    c1 integer;
    c2 integer;
begin
    select count(*)
    from market_product
    where market_id = p_id
    into c1;

    select count(*)
    from market
    where id = p_id
    into c2;

    if (c1 <> 0) then
        raise exception 'Ошибка! Нельзя удалить торг. точку, т.к. есть зависимые элементы';
    elseif (c2 = 0) then
        raise exception 'Ошибка! Торг. точка с идентификатором % не найдена', p_id;
    else
        delete from market where id = p_id;
    end if;
    commit;
end;
$$;
-------Удаление товара со склада-------
create or replace procedure del_storage_product(p_id integer)
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
    else
        delete from storage_product where id = p_id;
    end if;
    commit;
end;
$$;
-------Удаление товара c торг. точки-------
create or replace procedure del_market_product(p_id integer)
    language plpgsql
as
$$
declare
    c integer;
begin
    select count(*) from market_product
    where id = p_id into c;
    if (c = 0) then
        raise exception 'Ошибка! Товар с идентификатором % не найден в торг. точке', p_id;
    else
        delete from market_product where id = p_id;
    end if;
    commit;
end;
$$