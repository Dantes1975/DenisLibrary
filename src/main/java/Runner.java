import bean.*;
import config.PersistenceJPAConfig;
import config.RootConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repository.AuthenticateDao;
import repository.AuthorDao;
import repository.UserDao;
import repository.daoImpl.*;
import service.AuthenticateService;

public class Runner {
    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(RootConfig.class);
    public static void main(String[] args) {
        AuthenticateService authenticateService = APPLICATION_CONTEXT.getBean(AuthenticateService.class);


        User user = new User("Denis", "Rumyancev", "dantes1975@inbox.ru", 44);
        user.setRole(Role.ADMIN);
        Authenticate authenticate = new Authenticate("Dantes", "123", "ON");
        authenticate.setUser(user);
        user.setAuthenticate(authenticate);
        //authenticateService.save(authenticate);


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
