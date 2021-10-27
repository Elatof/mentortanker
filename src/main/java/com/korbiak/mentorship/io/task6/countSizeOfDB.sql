create or replace procedure count_size_of_db(inout size_sum integer, inout count_tables integer,  inout tables_names varchar)
    language plpgsql
as $$
declare
    table_names varchar[];
    temp_table_name varchar;
begin
    SELECT ARRAY(SELECT table_schema || '.' || table_name from information_schema.tables) INTO table_names;
    FOREACH temp_table_name IN ARRAY table_names
        LOOP
            tables_names = tables_names || ',' || temp_table_name;
            count_tables = count_tables+1;
            size_sum = size_sum + pg_total_relation_size(temp_table_name);
        END LOOP;
end;
$$;

CALL count_size_of_db(0, 0, '')