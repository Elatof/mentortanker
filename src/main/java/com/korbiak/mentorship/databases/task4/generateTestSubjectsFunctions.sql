CREATE OR REPLACE FUNCTION generateTestSubjects()
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
	subjects_count INTEGER;
	tutor_size INTEGER;
	tutor_count INTEGER := 1;
BEGIN
	tutor_size := (SELECT COUNT(*) FROM tutor);
	FOR i IN 1..1000 LOOP
 	  INSERT INTO subject ("name", tutor_id) VALUES 
 	  (concat('Name-', i), tutor_count);
	  if tutor_count = tutor_size then
   		tutor_count := 0;
	  end if;
	  tutor_count := tutor_count + 1;
      subjects_count := i;
   	END LOOP;
    return subjects_count;
END
$$;