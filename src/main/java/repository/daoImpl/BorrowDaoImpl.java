package repository.daoImpl;

import bean.Book;
import bean.Borrow;
import repository.dao.AbstractDao;
import repository.dao.BorrowDao;
import repository.database.DataBaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDaoImpl extends AbstractDao<Borrow> implements BorrowDao {
    DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    private static final String INSERT_INTO_BORROW = "INSERT INTO BORROW (BOOKID, USERID, BORROWDATE, RETURNDATE) " +
            "VALUES (?,?,?,?)";
    private static final String SELECT_FROM_BORROW = "SELECT * FROM BORROW WHERE USERID=?";
    private static final String SELECT_BOOKID_FROM_BORROW = "SELECT BOOKID FROM BORROW WHERE USERID=?";


    @Override
    protected String getAllSqlQuery() {
        return "SELECT BORROW.ID AS BRID, BOOKS.ID AS BID, BOOKNAME, USERID, BORROWDATE, RETURNDATE  FROM BORROW INNER JOIN BOOKS ON BORROW.BOOKID=BOOKS.ID";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM BORROW WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(Borrow borrow) {
        return "INSERT INTO BORROW (BOOKID, USERID, BORROWDATE, RETURNDATE) VALUES (" + borrow.getBook()
                + "," + borrow.getUser() + "," + borrow.getBorrowDate() + borrow.getReturnDate() + ")";
    }

    @Override
    protected String getUpdateQuery(Borrow borrow) {
        return "UPDATE BORROW SET BOOKID=" + borrow.getBook() + ", USERID= '" + borrow.getUser() + "', BORROWDATE=" +
                borrow.getBorrowDate() + "', RETURNDATE=" + borrow.getReturnDate() + "WHERE ID =" + borrow.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM BORROW WHERE BOOKID=" + id;
    }

    @Override
    protected Borrow resultSetMapper(ResultSet k) throws SQLException {
        Borrow borrow = new Borrow();
        borrow.setId(k.getLong("brid"));
        borrow.setBook(new Book(k.getLong("bid"), k.getString("bookname")));
        borrow.setUser(k.getLong("userid"));
        borrow.setBorrowDate(k.getDate("borrowdate"));
        borrow.setReturnDate(k.getDate("returndate"));
        return borrow;
    }
    public  String getDeleteUserQuery(long id) {
        return "DELETE FROM BORROW WHERE USERID=" + id;
    }

    @Override
    public Borrow insert(Borrow borrow) {
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement(INSERT_INTO_BORROW, Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, borrow.getBook().getId());
            st.setLong(2, borrow.getUser());
            st.setDate(3, borrow.getBorrowDate());
            st.setDate(4, borrow.getReturnDate());
            st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            while (resultSet.next()) {
                borrow.setId(resultSet.getLong(1));
            }
            return borrow;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Borrow> getBooksByUserId(Long id) {
        List<Borrow> list = new ArrayList<>();
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement(SELECT_FROM_BORROW)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(resultSetMapper(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Long> getBooksIdByUserId(Long id) {
        List<Long> list = new ArrayList<>();
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement(SELECT_BOOKID_FROM_BORROW)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getLong("bookid"));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void deleteUserById(long id) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement()) {
            st.executeUpdate(getDeleteUserQuery(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

