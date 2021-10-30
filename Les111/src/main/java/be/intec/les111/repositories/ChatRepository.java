package be.intec.les111.repositories;

import be.intec.les111.models.entities.*;

import java.sql.*;
import java.util.*;

import be.intec.les111.exceptions.*;

public class ChatRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public int create(ChatEntity record) throws ChatException {

        int rowsEffected = 0;

        try {
            String query = "insert into chat ( from_user_id, to_user_id, subject, content ) values ( ?, ?, ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getFromUserId());
            statement.setInt(2, record.getToUserId());
            statement.setString(3, record.getSubject());
            statement.setString(4, record.getContent());

            rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public ChatEntity read(Integer id) throws ChatException {

        ChatEntity item = new ChatEntity();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if (results.next()) {
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));
            }
        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return item;
    }

    public List<ChatEntity> read(ChatEntity record) throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where from_user_id = ? OR to_user_id = ? OR subject = ? OR content = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getFromUserId());
            statement.setInt(2, record.getToUserId());
            statement.setString(3, record.getSubject());
            statement.setString(4, record.getContent());

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<ChatEntity> read() throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public int update(Integer id, ChatEntity record) throws ChatException {

        if (id < 0) {
            throw new ChatException("Chat ID is required.").requiredFields("id");
        }

        if (record == null) {
            throw new ChatException("Chat is required.").nullChatException();
        }

        int rowsEffected = 0;

        try {
            String query = "update chat set   from_user_id  = ?,   to_user_id  = ?,   subject  = ?,   content  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, record.getFromUserId());
            statement.setInt(2, record.getToUserId());
            statement.setString(3, record.getSubject());
            statement.setString(4, record.getContent());
            statement.setInt(5, id);

            rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete(Integer id) throws ChatException {

        int rowsEffected = 0;

        try {
            String query = "delete from chat where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateFromUserId(Integer id, Integer fromUserId) throws ChatException {

        if (id < 0) {
            throw new ChatException("Chat ID is required.").requiredFields("id");
        }

        if (fromUserId == null) {
            throw new ChatException("fromUserId is required.").nullChatException();
        }

        int rowsEffected = 0;

        try {
            String query = "update chat set  from_user_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, fromUserId);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateToUserId(Integer id, Integer toUserId) throws ChatException {

        if (id < 0) {
            throw new ChatException("Chat ID is required.").requiredFields("id");
        }

        if (toUserId == null) {
            throw new ChatException("toUserId is required.").nullChatException();
        }

        int rowsEffected = 0;

        try {
            String query = "update chat set  to_user_id  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, toUserId);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateSubject(Integer id, String subject) throws ChatException {

        if (id < 0) {
            throw new ChatException("Chat ID is required.").requiredFields("id");
        }

        if (subject == null) {
            throw new ChatException("subject is required.").nullChatException();
        }

        int rowsEffected = 0;

        try {
            String query = "update chat set  subject  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, subject);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public int updateContent(Integer id, String content) throws ChatException {

        if (id < 0) {
            throw new ChatException("Chat ID is required.").requiredFields("id");
        }

        if (content == null) {
            throw new ChatException("content is required.").nullChatException();
        }

        int rowsEffected = 0;

        try {
            String query = "update chat set  content  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, content);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public List<ChatEntity> searchByFromUserId(Integer fromUserId) throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where from_user_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, fromUserId);

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<ChatEntity> searchByToUserId(Integer toUserId) throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where to_user_id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, toUserId);

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<ChatEntity> searchBySubject(String subject) throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where subject LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, subject);

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<ChatEntity> searchByContent(String content) throws ChatException {

        final List<ChatEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, from_user_id, to_user_id, subject, content from chat where content LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, content);

            results = statement.executeQuery();
            while (results.next()) {
                ChatEntity item = new ChatEntity();
                item.setId(results.getInt("id"));
                item.setFromUserId(results.getInt("from_user_id"));
                item.setToUserId(results.getInt("to_user_id"));
                item.setSubject(results.getString("subject"));
                item.setContent(results.getString("content"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            ChatException ex = new ChatException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                ChatException ex = new ChatException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

}
