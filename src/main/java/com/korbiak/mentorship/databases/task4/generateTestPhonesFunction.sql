CREATE OR REPLACE FUNCTION generateTestPhones()
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
	student_size INTEGER;
	generated_phone VARCHAR(10);
	duplicate_count INTEGER;
BEGIN
	student_size := (SELECT COUNT(*) FROM student);
	FOR i IN 1..student_size LOOP
		duplicate_count := 1;
		WHILE duplicate_count > 0
		LOOP
			generated_phone := (SELECT format('%s%s%s%s%s%s%s%s%s%s'
		    , a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8], a[9], a[10])
			FROM  (SELECT ARRAY (
					  SELECT trunc(random() * 10)::int
					  FROM   generate_series(1, 10)
				  ) AS a ) sub);
			duplicate_count := (SELECT COUNT(*) FROM phone_number WHERE phone_number.number = generated_phone);
		END LOOP;
 	  	INSERT INTO phone_number (number, student_id) VALUES (generated_phone, i);
   	END LOOP;
    return student_size;
END
$$;