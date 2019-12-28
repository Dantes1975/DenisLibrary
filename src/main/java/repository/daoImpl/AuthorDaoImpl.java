package repository.daoImpl;

import bean.Author;
import repository.dao.AbstractDao;
import repository.dao.AuthorDao;
import repository.database.DataBaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AuthorDaoImpl extends AbstractDao<Author> implements AuthorDao {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    protected String getAllSqlQuery() {
        return "SELECT * FROM AUTHORS";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM AUTHORS WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(Author author) {
        return "INSERT INTO AUTHORS (FIRSTNAME, LASTNAME) VALUES ('" + author.getName() +
                "', '" + author.getSurname() + "')";
    }

    @Override
    protected String getUpdateQuery(Author author) {
        return "UPDATE AUTHORS SET FIRSTNaME='" + author.getName() + "', BOOKNAME= '" + author.getSurname() +
                "' WHERE ID =" + author.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM AUTHORS WHERE id=" + id;
    }

    @Override
    protected Author resultSetMapper(ResultSet k) throws SQLException {
        Author author = new Author();
        author.setName(k.getString("firstname"));
        author.setSurname(k.getString("lastname"));
        return author;
    }

    @Override
    public Author insert(Author author) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement()) {
            st.executeUpdate(getInsertQuery(author), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                author.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }
}
