import bean.*;
import repository.daoImpl.*;

public class Runner {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        AuthenticateDaoImpl authenticateDao = new AuthenticateDaoImpl();
                AuthorDaoImpl authorDao = new AuthorDaoImpl();
        GenreDaoImpl genreDao = new GenreDaoImpl();
        BookDaoImpl bookDao = new BookDaoImpl();



        User user = new User("Denis", "Rumyancev", "dantes1975@inbox.ru", 44);
        user.setRole(Role.ADMIN);
        Authenticate authenticate = new Authenticate("Dantes", "123", "ON");
        authenticate.setUser(user);
        user.setAuthenticate(authenticate);
        authenticateDao.insert(authenticate);

        //  userDao.insert(user);
//        Author author = new Author();
//        author.setName("Nikolay");
//        author.setSurname("Andreevv");
//        Genre genre = new Genre();
//        genre.setGenrename("Fentesy");
//        Book book = new Book();
//        book.setBookname("Stalnaya Kozha");
//        book.setAuthor(author);
//        book.setGenre(genre);
//        book.setStock(5);
//        bookDao.insert(book);
//        //bookDao.delete(2);
//        authorDao.insert(author);
//        genreDao.insert(genre);
//        System.out.println(bookDao.getById(1).toString());
//        bookDao.takeBook(1);
//        System.out.println(bookDao.getById(1).toString());
//        bookDao.returnBook(1);
//        System.out.println(bookDao.getById(1).toString());
//        System.out.println(authenticateDao.getById(2));
//        System.out.println(authenticateDao.getByLogin("admin"));
//        long i = users_rolesDao.getByUserID(2).getRole_id();
//        System.out.println(i);
//        //Boolean bol = authenticateDao.isExistByLogin("dantes");
//        System.out.println(authenticateDao.isExistByLogin("dantes"));
//        System.out.println(authenticateDao.isExistByLogin("admin"));
    }
}
