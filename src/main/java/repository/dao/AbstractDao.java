package repository.dao;

import repository.dao.CrudDao;
import repository.database.DataBaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> implements CrudDao<T> {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();

    protected abstract String getAllSqlQuery();

    protected abstract String getByIdSqlQuery(long id);

    protected abstract String getInsertQuery(T t);

    protected abstract String getUpdateQuery(T t);

    protected abstract String getDeleteQuery(long id);

    protected abstract T resultSetMapper(ResultSet k) throws SQLException;


    @Override
    public T getById(long id) {
        T t = null;
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(getByIdSqlQuery(id))) {
            while (rs.next()) {
                t = resultSetMapper(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public T getByIdInsert(long id) {
        T t = null;
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement()) {
            ResultSet rs = st.executeQuery(getByIdSqlQuery(id));
            t = resultSetMapper(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }


    @Override
    public T insert(T t) {

        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();) {
            st.executeUpdate(getInsertQuery(t), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                return getByIdInsert(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public T update(T t) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement()) {
            st.executeUpdate(getUpdateQuery(t));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public void delete(long id) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement()) {
            st.executeUpdate(getDeleteQuery(id));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<>();
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(getAllSqlQuery())) {
            while (rs.next()) {
                list.add(resultSetMapper(rs));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
