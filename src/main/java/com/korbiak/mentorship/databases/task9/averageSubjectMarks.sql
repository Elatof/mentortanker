CREATE OR REPLACE FUNCTION averageSubjectMarks(input_id int)
RETURNS bigint LANGUAGE plpgsql as
$$
DECLARE 
BEGIN
	RETURN SUM(result.mark)/COUNT(result.mark)
			FROM public.subject
			JOIN public.result ON result.subject_id = subject.id
			where subject.id = input_id;
END
$$;

select averageMarks(56);