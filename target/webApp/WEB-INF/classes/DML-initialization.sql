INSERT INTO USERS ( NAME , SURNAME , EMAIL , AGE ) VALUES ('IVAN', 'IVANOV', 'IVANOV@MAIL.RU', 18);
INSERT INTO USERS ( NAME , SURNAME , EMAIL , AGE ) VALUES ('PETR', 'PETROV', 'PETROV@MAIL.RU', 19);

INSERT INTO ROLES ( ROLE ) VALUES ('ADMIN');
INSERT INTO ROLES ( ROLE ) VALUES ('USER');
INSERT INTO AUTHENTICATE ( LOGIN , PASSWORD, ROLEID) VALUES ('ADMIN', '12', 1);
INSERT INTO AUTHENTICATE ( LOGIN , PASSWORD, ROLEID) VALUES ('ADMIN1', '121', 1);



INSERT INTO USER_ROLES ( USER_ID , ROLE_ID ) VALUES (1,1);
INSERT INTO USER_ROLES ( USER_ID , ROLE_ID ) VALUES (2,1);

INSERT INTO GENRES (GENRENAME ) VALUES ('LIRIK');
INSERT INTO GENRES (GENRENAME ) VALUES ('HORROR');
INSERT INTO GENRES (GENRENAME ) VALUES ('FENTESY');
INSERT INTO GENRES (GENRENAME ) VALUES ('PUBLIC');
INSERT INTO AUTHORS (FIRSTNAME, LASTNAME ) VALUES ('Александр', 'Пушкин');
INSERT INTO AUTHORS (FIRSTNAME, LASTNAME ) VALUES ('Анре', 'Нортон');
INSERT INTO AUTHORS (FIRSTNAME, LASTNAME ) VALUES ('Михаил', 'Лермонтов');
INSERT INTO AUTHORS (FIRSTNAME, LASTNAME ) VALUES ('Агата', 'Кристи');
INSERT INTO AUTHORS (FIRSTNAME, LASTNAME ) VALUES ('Даниил', 'Корецкий');



INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (1, 'Sadko', 1);
INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (2, 'Мцыри', 1);
INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (2, 'Орел', 2);
INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (3, 'Золотой теленок', 3);
INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (4, 'Конан варвар', 4);
INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (4, 'Антикиллер', 4);

INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (1, 'Легенда о Тарзане', 2);

INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (2, 'Звездные войны', 3);

INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (3, 'Конан разрушитель', 4);

INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (5, 'Операция прикрытия', 1);

INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID ) VALUES (5, 'Основная операция', 2);
