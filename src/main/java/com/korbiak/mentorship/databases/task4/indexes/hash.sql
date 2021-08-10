CREATE INDEX name_h_index ON student USING hash ("name");
CREATE INDEX surname_h_index ON student USING hash (surname);
CREATE INDEX number_h_index ON phone_number USING hash ("number");