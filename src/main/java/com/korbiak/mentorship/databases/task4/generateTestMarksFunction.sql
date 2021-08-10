CREATE OR REPLACE FUNCTION generateTestMarks()
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
	marks_count INTEGER;
	student_size INTEGER;
	subject_size INTEGER;
BEGIN
	subject_size := (SELECT COUNT(*) FROM subject);
	student_size := (SELECT COUNT(*) FROM student);
	FOR i IN 1..1000000 LOOP
 	  INSERT INTO "result" (mark, student_id, subject_id) VALUES 
 	  (floor(random() * 99 + 1)::int, floor(random() * student_size + 1)::int, floor(random() * subject_size + 1)::int);
      marks_count := i;
   	END LOOP;
    return marks_count;
END
$$;