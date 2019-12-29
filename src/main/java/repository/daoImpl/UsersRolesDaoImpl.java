package repository.daoImpl;

import bean.UsersRoles;
import repository.dao.AbstractDao;
import repository.dao.JpaEntityManagerFactoryUtil;
import repository.dao.Users_rolesDao;
import repository.database.DataBaseConnector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.*;

public class UsersRolesDaoImpl extends AbstractDao<UsersRoles> implements Users_rolesDao {
    DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = JpaEntityManagerFactoryUtil.getEntityManagerFactory();

    protected EntityManager getEntityManager() {
        return ENTITY_MANAGER_FACTORY.createEntityManager();
    }

    @Override
    protected String getAllSqlQuery() {
        return "SELECT id, user_id, role_id FROM USER_ROLES";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT id, user_id, role_id FROM USER_ROLES WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(UsersRoles users_roles) {
        return "INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (" + users_roles.getRole_id() + "," + users_roles.getId() + ")";
    }

    @Override
    protected String getUpdateQuery(UsersRoles users_roles) {
        return "UPDATE USER_ROLES SET USER_ID=" + users_roles.getUser_id() + "ROLE_ID=" + users_roles.getRole_id() + "WHERE ID=" + users_roles.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM USER_ROLES WHERE USER_ID=" + id;
    }

    @Override
    protected UsersRoles resultSetMapper(ResultSet k) throws SQLException {
        UsersRoles users_roles = new UsersRoles();
        users_roles.setId(k.getLong("id"));
        users_roles.setUser_id(k.getLong("user_id"));
        users_roles.setRole_id(k.getLong("role_id"));
        return users_roles;
    }


//    @Override
//    public Users_roles insert(Users_roles users_roles) {
//        try (Connection cn = dataBaseConnector.getConnection();
//             PreparedStatement st = cn.prepareStatement("INSERT INTO USER_ROLES (USER_ID, ROLE_ID) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
//            st.setLong(1, users_roles.getUser_id());
//            st.setLong(2, users_roles.getRole_id());
//            st.executeUpdate();
//            ResultSet resultSet = st.getGeneratedKeys();
//            while (resultSet.next()) {
//                users_roles.setId(resultSet.getLong(1));
//            }
//            return users_roles;
//
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }


    public UsersRoles getByUserID(long id) {
        EntityManager em = getEntityManager();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<UsersRoles> criteria = cb.createQuery(UsersRoles.class);
        Root<UsersRoles> root = criteria.from(UsersRoles.class);
        Predicate expression = cb.equal(root.get("user_id"), id);
        criteria.select(root).where(expression);
        UsersRoles users_roles = em.createQuery(criteria).getSingleResult();
        return users_roles;
    }
}
