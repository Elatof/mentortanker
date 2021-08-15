CREATE OR REPLACE FUNCTION studentsInRedZone()
RETURNS TABLE (student_id   INT   
               , name   varchar
               , surname varchar
			   , marks_count BIGINT) LANGUAGE plpgsql as
$$
DECLARE 
BEGIN
	RETURN QUERY SELECT result.student_id, student.name, student.surname, COUNT(result.mark) as marks_count FROM result
		JOIN student ON student.id = result.student_id
		WHERE mark < 30
		GROUP BY result.student_id, student.name, student.surname;
		END
$$;

select studentsInRedZone();