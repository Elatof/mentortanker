CREATE OR REPLACE FUNCTION averageSubjectMarks(input_id int)
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
	marks_count INTEGER :=0;
	marks_sum INTEGER :=0;
	temprow INTEGER;
BEGIN
	FOR temprow IN
        SELECT result.mark
			FROM public.subject
			JOIN public.result ON result.subject_id = subject.id
			where subject.id = input_id
    LOOP
		marks_count := marks_count + 1;
		marks_sum := marks_sum + temprow;
    END LOOP;
    return marks_sum/marks_count;
END
$$;

select averageMarks(56);