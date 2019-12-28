package repository.daoImpl;

import bean.Users_roles;
import repository.dao.AbstractDao;
import repository.dao.Users_rolesDao;
import repository.database.DataBaseConnector;

import java.sql.*;

public class Users_rolesDaoImpl extends AbstractDao<Users_roles> implements Users_rolesDao {
    DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    @Override
    protected String getAllSqlQuery() {
        return "SELECT id, user_id, role_id FROM USER_ROLES";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT id, user_id, role_id FROM USER_ROLES WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(Users_roles users_roles) {
        return "INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (" + users_roles.getRole_id() + "," + users_roles.getId() + ")";
    }

    @Override
    protected String getUpdateQuery(Users_roles users_roles) {
        return "UPDATE USER_ROLES SET USER_ID=" + users_roles.getUser_id() + "ROLE_ID=" + users_roles.getRole_id() + "WHERE ID=" + users_roles.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM USER_ROLES WHERE USER_ID=" + id;
    }

    @Override
    protected Users_roles resultSetMapper(ResultSet k) throws SQLException {
        Users_roles users_roles = new Users_roles();
        users_roles.setId(k.getLong("id"));
        users_roles.setUser_id(k.getLong("user_id"));
        users_roles.setRole_id(k.getLong("role_id"));
        return users_roles;
    }


    @Override
    public Users_roles insert(Users_roles users_roles) {
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement("INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
            st.setLong(1, users_roles.getUser_id());
            st.setLong(2, users_roles.getRole_id());
            st.executeUpdate();
            ResultSet resultSet = st.getGeneratedKeys();
            while (resultSet.next()) {
                users_roles.setId(resultSet.getLong(1));
            }
            return users_roles;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Users_roles getByUserID(long id) {
        Users_roles users_roles = null;
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement("SELECT * FROM USER_ROLES WHERE USER_ID = ?")) {
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();
           while (resultSet.next()) {
            users_roles =  resultSetMapper(resultSet);
           }
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return users_roles;
    }
}
