insert into USER (NAME, SURNAME, EMAIL, AGE) values ('Ivan', 'Ivanov', 'ivanov@mail.ru',18 );
insert into USER (NAME, SURNAME, EMAIL, AGE) values ('Petr', 'Petrov', 'prtrov@mail.ru',18 );

insert into ROLE (ROLE) values ('ADMIN' );
insert into ROLE (ROLE) values ('USER' );

insert into AUTHENTICATE (LOGIN, PASSWORD, PROFILE_ENABLE) values ('admin', '12', 'ON' );
insert into AUTHENTICATE (LOGIN, PASSWORD, PROFILE_ENABLE) values ('admin1', '12', 'ON' );

insert into USERSROLES (USER_ID, ROLE_ID) values ( 1, 1 );
insert into USERSROLES (USER_ID, ROLE_ID) values ( 2, 1 );

insert into AUTHOR (FIRSTNAME, LASTNAME) VALUES ( 'Danil', 'Koretski' );
insert into GENRE (GENRENAME) values ( 'detektiv' );

insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Osnovnaya opetratsiya', 5, 1, 1 );
insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Opetratsiya prikritiya', 5, 1, 1 );
insert into BOOK (BOOKNAME, STOCK, AUTHOR_ID, GENRE_ID) values ( 'Antikiller', 5, 1, 1 );
