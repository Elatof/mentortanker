 EXPLAIN ANALYZE SELECT student.id, student."name", student.surname, student.date_of_birth, primary_skill.name AS primary_skill, student.created_datetime, student.updated_datetime, phone_number.number 
 FROM student
 JOIN primary_skill ON primary_skill.id = student.primary_skill_id
 JOIN phone_number ON phone_number.student_id = student.id
 JOIN student student2 ON student2.id = phone_number.student_id
 WHERE phone_number.number like '%666%'