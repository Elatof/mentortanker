create or replace procedure save_file(in input_file_name varchar(32), in input_expire_time date, in input_file_content oid)
    language plpgsql
as $$
begin
    INSERT INTO public.files(file_name, file_content, expire_time)
    VALUES (input_file_name, input_file_content, input_expire_time);
end;
$$;