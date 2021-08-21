CREATE OR REPLACE FUNCTION studentsInRedZone()
RETURNS TABLE (name   varchar
               , surname varchar
			   , marks_count BIGINT) LANGUAGE plpgsql as
$$
DECLARE 
BEGIN
	RETURN QUERY SELECT student.name, student.surname, COUNT(result.mark) as marks_count FROM result
		JOIN student ON student.id = result.student_id
		WHERE mark < 30 
		GROUP BY student.name, student.surname
		HAVING COUNT(result.mark) > 2;
END
$$;

select studentsInRedZone();