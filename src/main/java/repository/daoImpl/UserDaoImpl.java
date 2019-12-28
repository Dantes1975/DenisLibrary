package repository.daoImpl;

import bean.User;
import repository.dao.AbstractDao;
import repository.dao.UserDao;
import repository.database.DataBaseConnector;

import java.sql.*;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    protected String getAllSqlQuery() {
        return "SELECT * FROM USERS";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM USERS WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(User user) {
        return "INSERT INTO USERS (name, surname, email, age) VALUES (" + user.getName() + "," + user.getSurname() +
                "," + user.getEmail() + "," + user.getAge();
    }

    @Override
    protected String getUpdateQuery(User user) {
        return "UPDATE USERS SET NAME='" + user.getName() + "', SURNAME='" + user.getSurname() + "', AGE=" +
                user.getAge() + "EMAIL='" + user.getEmail()+"' WHERE ID=" + user.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM USERS WHERE id=" + id;
    }

    @Override
    protected User resultSetMapper(ResultSet k) throws SQLException {
        User user = new User();
        user.setId(k.getInt("id"));
        user.setName(k.getString("name"));
        user.setSurname(k.getString("surname"));
        user.setEmail(k.getString("email"));
        user.setAge(k.getInt("age"));
        return user;
    }


    @Override
    public User insert(User user) {
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement("INSERT INTO USERS (NAME, SURNAME, EMAIL, AGE) " +
                     "VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getAge());
            st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }
            return user;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public long getUserByLogin(String name, String surname) {
        try (Connection cn = dataBaseConnector.getConnection()) {
            PreparedStatement st = cn.prepareStatement("SELECT ID FROM USRES WHERE NAME = ? AND SURNAME=?");
            st.setString(1, name);
            st.setString(2, surname);
            ResultSet resultSet = st.executeQuery();
            return resultSet.getLong("id");

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
