package repository.daoImpl;

import bean.Genre;
import repository.dao.AbstractDao;
import repository.dao.GenreDao;
import repository.database.DataBaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenreDaoImpl extends AbstractDao<Genre> implements GenreDao {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    protected String getAllSqlQuery() {
        return "SELECT * FROM GENRES";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM GENRES WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(Genre genre) {
        return "INSERT INTO GENRES (GENRENAME) VALUES ('" + genre.getGenrename() + "')";
    }

    @Override
    protected String getUpdateQuery(Genre genre) {
        return "UPDATE GENRES SET GENRE='" + genre.getGenrename() + "' WHERE ID =" + genre.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM GENRES WHERE id=" + id;
    }

    @Override
    protected Genre resultSetMapper(ResultSet k) throws SQLException {
        Genre genre = new Genre();
        genre.setId(k.getLong("id"));
        genre.setGenrename(k.getString("genre"));
        return genre;
    }

    @Override
    public Genre insert(Genre genre) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();) {
            st.executeUpdate(getInsertQuery(genre), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                genre.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;
    }
}
