package repository.daoImpl;

import bean.Bookimage;
import repository.dao.AbstractDao;
import repository.dao.BookimageDao;
import repository.database.DataBaseConnector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class BookimageDaoImpl extends AbstractDao<Bookimage> implements BookimageDao {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    protected String getAllSqlQuery() {
        return "SELECN * FROM BOOKIMAGE";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM BOOKIMAGE WHERE BOOKID = " + id;
    }

    @Override
    protected String getInsertQuery(Bookimage bookimage) {
        return "INSERT INTO BOOKIMAGE (BOOKID, FILENAME, BOOKIMAGE) VALUES (?, ?,? )";
    }

    @Override
    protected String getUpdateQuery(Bookimage bookimage) {
        return "UPDATE BOOKIMAGE SET BOOKID=" + bookimage.getBookId() + ", BOOKIMAGE= '" + bookimage.getBookimage() +
                "WHERE ID =" + bookimage.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM BOOKIMAGE WHERE id=" + id;
    }

    @Override
    protected Bookimage resultSetMapper(ResultSet k) throws SQLException {
        Bookimage bookimage = new Bookimage();
        bookimage.setId(k.getLong("id"));
        bookimage.setBookId(k.getLong("bookid"));
        bookimage.setFilename(k.getString("filename"));
        bookimage.setBookimage(k.getBytes("bookimage"));
        return bookimage;
    }

    @Override
    public Bookimage insert(Bookimage bookimage) {
        try (Connection cn = dataBaseConnector.getConnection()) {
            BufferedImage img = ImageIO.read(new File("d:/" + bookimage.getFilename()));
            Blob blb = cn.createBlob();
            try (OutputStream out = blb.setBinaryStream(1)) {
                ImageIO.write(img, "jpg", out);
            }

            PreparedStatement preparedStatement = cn.prepareStatement(getInsertQuery(bookimage), Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, bookimage.getBookId());
            preparedStatement.setString(2, bookimage.getFilename());
            preparedStatement.setBlob(3, blb);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                bookimage.setId(rs.getLong(1));
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return bookimage;
    }

    @Override
    public Bookimage getById(long id) {
        Bookimage bookimage = null;
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(getByIdSqlQuery(id))) {
            while (rs.next()) {
                bookimage = resultSetMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookimage;
    }

}
