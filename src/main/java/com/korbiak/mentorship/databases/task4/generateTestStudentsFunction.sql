CREATE OR REPLACE FUNCTION generateTestStudents()
RETURNS int LANGUAGE plpgsql as
$$
DECLARE 
	users_count INTEGER;
	skill_size INTEGER;
	skill_count INTEGER := 1;
BEGIN
	skill_size := (SELECT COUNT(*) FROM primary_skill);
	FOR i IN 1..100000 LOOP
 	  INSERT INTO student ("name", surname, date_of_birth, primary_skill_id, created_datetime, updated_datetime) VALUES 
 	  (concat('Maksym', i), concat('Korbiak', i), '2017-03-14', skill_count, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
	  if skill_count = skill_size then
   		skill_count := 0;
	  end if;
	  skill_count := skill_count + 1;
      users_count := i;
   	END LOOP;
    return users_count;
END
$$;