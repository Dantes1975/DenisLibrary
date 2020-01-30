insert into USER (NAME, SURNAME, EMAIL, AGE, ROLE) values ('Ivan', 'Ivanov', 'ivanov@mail.ru',18, 'ADMIN');
insert into USER (NAME, SURNAME, EMAIL, AGE, ROLE) values ('Petr', 'Petrov', 'prtrov@mail.ru',18, 'ADMIN');
insert into USER (NAME, SURNAME, EMAIL, AGE, ROLE) values ('Denis', 'Rumyancev', 'dantes@mail.ru',44, 'USER');


insert into AUTHENTICATE (LOGIN, PASSWORD, PROFILE_ENABLE) values ('admin', '12', 'ON' );
insert into AUTHENTICATE (LOGIN, PASSWORD, PROFILE_ENABLE) values ('admin1', '12', 'ON' );
insert into AUTHENTICATE (LOGIN, PASSWORD, PROFILE_ENABLE) values ('Denis1975', '12', 'ON' );

insert into AUTHOR (FIRSTNAME, LASTNAME) VALUES ( 'Danil', 'Koretski' );
insert into GENRE (GENRENAME) values ( 'detektiv' );

insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Osnovnaya opetratsiya', 5, 1, 1 );
insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Opetratsiya prikritiya', 5, 1, 1 );
insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Antikiller', 5, 1, 1 );
