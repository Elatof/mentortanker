CREATE OR REPLACE FUNCTION reject_update() RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO student_address_temp (state, city, street, postal_code) 
		VALUES (NEW.state, NEW.city, NEW.street, NEW.postal_code);
    RETURN OLD;
END; $$
LANGUAGE plpgsql;

CREATE TRIGGER reject_update_trigger
    BEFORE UPDATE ON student_address
    FOR EACH ROW 
	EXECUTE PROCEDURE reject_update();
