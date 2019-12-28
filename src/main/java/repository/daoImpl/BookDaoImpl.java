package repository.daoImpl;

import bean.Author;
import bean.Book;
import bean.Genre;
import repository.dao.AbstractDao;
import repository.dao.BookDao;
import repository.database.DataBaseConnector;

import java.sql.*;

public class BookDaoImpl extends AbstractDao<Book> implements BookDao {
    DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    private static final String TAKE_BOOK = "UPDATE BOOKS SET STOCK=(STOCK-1) WHERE ID=?";
    private static final String RETURN_BOOK = "UPDATE BOOKS SET STOCK=(STOCK+1) WHERE ID=?";

    @Override
    protected String getAllSqlQuery() {
        return "SELECT BOOKS.ID AS BID, BOOKNAME, AUTHORS.ID AS AID," +
                " FIRSTNAME, LASTNAME, GENRES.ID AS GID, GENRENAME, STOCK  FROM BOOKS" +
                " INNER JOIN AUTHORS ON BOOKS.AUTHORID=AUTHORS.ID INNER JOIN GENRES ON BOOKS.GENREID=GENRES.ID";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT BOOKS.ID AS BID, BOOKNAME, AUTHORS.ID AS AID, FIRSTNAME, LASTNAME, GENRES.ID AS GID, GENRENAME," +
                " STOCK  FROM BOOKS INNER JOIN AUTHORS ON BOOKS.AUTHORID=AUTHORS.ID INNER JOIN GENRES ON " +
                "BOOKS.GENREID=GENRES.ID WHERE BOOKS.ID =" + id;
    }

    @Override
    protected String getInsertQuery(Book book) {
        return "INSERT INTO BOOKS (AUTHORID, BOOKNAME, GENREID) VALUES (" + book.getAuthor().getId()
                + ", '" + book.getBookname() + "' ," + book.getGenre().getId() + ")";
    }

    @Override
    protected String getUpdateQuery(Book book) {
        return "UPDATE BOOKS SET AUTHORID=" + book.getAuthor() + ", BOOKNAME= '" + book.getBookname() + "', GENRE=" +
                book.getGenre() + "WHERE ID =" + book.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM BOOKS WHERE id=" + id;
    }

    @Override
    protected Book resultSetMapper(ResultSet k) throws SQLException {
        Book book = new Book();
        book.setId(k.getLong("bid"));
        book.setBookname(k.getString("bookname"));
        book.setAuthor(new Author(k.getLong("aid"), k.getString("firstname"), k.getString("lastname")));
        book.setGenre(new Genre(k.getLong("gid"), k.getString("genrename")));
        book.setStock(k.getInt("stock"));
        return book;
    }

    public void takeBook(long id) {
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement(TAKE_BOOK)) {
            st.setLong(1, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(long id) {
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement(RETURN_BOOK)) {
            st.setLong(1, id);
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book insert(Book book) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();) {
            st.executeUpdate(getInsertQuery(book), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                book.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
