create or replace procedure get_file(in input_file_id integer, inout output_expire_time date, inout output_file_content oid)
    language plpgsql
as $$
begin
    SELECT file_content into output_file_content FROM public.files WHERE id=input_file_id;
    SELECT expire_time into output_expire_time FROM public.files WHERE id=input_file_id;
end;
$$;