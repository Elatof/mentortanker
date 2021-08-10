CREATE OR REPLACE FUNCTION update_student_update_time() RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_datetime = CURRENT_TIMESTAMP;
    RETURN NEW;
END; $$
LANGUAGE plpgsql;

CREATE TRIGGER update_date
    BEFORE UPDATE ON student
    FOR EACH ROW 
	EXECUTE PROCEDURE update_student_update_time();
