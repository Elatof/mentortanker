CREATE TABLE student_snapshot
as 
SELECT student.id, student.name, student.surname, subject.name AS subject_name, "result".mark
	FROM public.student
	JOIN public.result ON result.student_id = student.id
	JOIN public.subject ON subject.id = result.subject_id;