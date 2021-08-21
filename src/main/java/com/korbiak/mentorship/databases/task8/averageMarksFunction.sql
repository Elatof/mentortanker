CREATE OR REPLACE FUNCTION averageMarks(input_id int)
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
BEGIN
	RETURN SUM("result".mark)/COUNT("result".mark) AS mark
			FROM public.student
			JOIN public.result ON result.student_id = student.id
			WHERE student.id = input_id;
END
$$;

select averageMarks(1);