INSERT INTO tutor ("name", surname)
VALUES ('Victor', 'Rybak'),
        ('Roman', 'Habsburg'),
        ('Bogdan', 'Khmelnytskyi'),
        ('Max', 'NeMax'),
        ('Serhii', 'Yanovskiy');

INSERT INTO primary_skill ("name")
VALUES ('math'),
       ('chemistry'),
       ('literature'),
       ('sport'),
       ('physics');

SELECT generateteststudents();
SELECT generatetestphones();
SELECT generatetestsubjects();
SELECT generatetestmarks();