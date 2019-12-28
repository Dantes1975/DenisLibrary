package repository.daoImpl;

import bean.Message;
import repository.dao.AbstractDao;
import repository.dao.MessageDao;
import repository.database.DataBaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl extends AbstractDao<Message> implements MessageDao {
    private DataBaseConnector dataBaseConnector = DataBaseConnector.getInstance();
    @Override
    protected String getAllSqlQuery() {
        return "SELECT * FROM MESSAGES";
    }

    @Override
    protected String getByIdSqlQuery(long id) {
        return "SELECT * FROM MESSAGES WHERE ID =" + id;
    }

    @Override
    protected String getInsertQuery(Message message) {
        return "INSERT INTO MESSAGES (SENDER, RECIPIENT, TEXT) VALUES (" + message.getSender()
                + "," + message.getRecipient() + ", '" + message.getText() + "')";
    }

    @Override
    protected String getUpdateQuery(Message message) {
        return "UPDATE MESSAGES SET SENDER=" + message.getSender() + ", RECIPIENT= " + message.getRecipient() + ", TEXT='" +
                message.getText() + "' WHERE ID =" + message.getId();
    }

    @Override
    protected String getDeleteQuery(long id) {
        return "DELETE FROM MESSAGES WHERE id=" + id;
    }

    @Override
    protected Message resultSetMapper(ResultSet k) throws SQLException {
        Message message = new Message();
        message.setId(k.getLong("id"));
        message.setSender(k.getLong("sender"));
        message.setRecipient(k.getLong("recipient"));
        message.setText(k.getString("text"));
        return message;
    }
    public List<Message> getMyMessages(long recipientId) {
        List<Message> list = new ArrayList<>();
        try (Connection cn = dataBaseConnector.getConnection();
             PreparedStatement st = cn.prepareStatement("SELECT * FROM MESSAGES WHERE RECIPIENT=?")){
             st.setLong(1, recipientId);
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

    @Override
    public Message insert(Message message) {
        try (Connection cn = dataBaseConnector.getConnection();
             Statement st = cn.createStatement();) {
            st.executeUpdate(getInsertQuery(message), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                message.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }
}
