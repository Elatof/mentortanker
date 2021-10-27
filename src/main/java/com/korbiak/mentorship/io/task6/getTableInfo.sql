create or replace procedure tableInfo(input_table_name varchar, inout count_columns integer, inout table_size integer,  inout tables_columns varchar)
    language plpgsql
as $$
declare
    table_columns varchar[];
    temp_table_column varchar;
begin
    SELECT ARRAY(SELECT column_name FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = input_table_name) INTO table_columns;
    FOREACH temp_table_column IN ARRAY table_columns
        LOOP
            tables_columns = tables_columns || ',' || temp_table_column;
        END LOOP;
    count_columns = array_length(table_columns, 1);
    table_size = pg_total_relation_size(input_table_name);
end;
$$;

CALL tableInfo('tutor', 0, 0, '')