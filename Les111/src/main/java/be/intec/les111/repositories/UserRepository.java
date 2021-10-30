package be.intec.les111.repositories;

import be.intec.les111.models.entities.*;

import java.sql.*;
import java.util.*;

import be.intec.les111.exceptions.*;

public class UserRepository {

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet results = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = ConnectionFactory.getInstance().getConnection();
        return conn;
    }

    public int create(UserEntity record) throws UserException {

        int rowsEffected = 0;

        try {
            String query = "insert into user ( email, passcode ) values ( ?, ? )";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getEmail());
            statement.setString(2, record.getPasscode());

            rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public UserEntity read(Integer id) throws UserException {

        UserEntity item = new UserEntity();

        try {
            String query = "select id, email, passcode from user where id = ?";
            connection = getConnection();

            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            results = statement.executeQuery();
            if (results.next()) {
                item.setId(results.getInt("id"));
                item.setEmail(results.getString("email"));
                item.setPasscode(results.getString("passcode"));
            }
        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return item;
    }

    public List<UserEntity> read(UserEntity record) throws UserException {

        final List<UserEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, email, passcode from user where email = ? OR passcode = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getEmail());
            statement.setString(2, record.getPasscode());

            results = statement.executeQuery();
            while (results.next()) {
                UserEntity item = new UserEntity();
                item.setId(results.getInt("id"));
                item.setEmail(results.getString("email"));
                item.setPasscode(results.getString("passcode"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<UserEntity> read() throws UserException {

        final List<UserEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, email, passcode from user ";
            connection = getConnection();
            statement = connection.prepareStatement(query);

            results = statement.executeQuery();
            while (results.next()) {
                UserEntity item = new UserEntity();
                item.setId(results.getInt("id"));
                item.setEmail(results.getString("email"));
                item.setPasscode(results.getString("passcode"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public int update(Integer id, UserEntity record) throws UserException {

        if (id < 0) {
            throw new UserException("User ID is required.").requiredFields("id");
        }

        if (record == null) {
            throw new UserException("User is required.").nullUserException();
        }

        int rowsEffected = 0;

        try {
            String query = "update user set   email  = ?,   passcode  = ? where id = ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, record.getEmail());
            statement.setString(2, record.getPasscode());
            statement.setInt(3, id);

            rowsEffected = statement.executeUpdate();

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public int delete(Integer id) throws UserException {

        int rowsEffected = 0;

        try {
            String query = "delete from user where id = ? ";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            rowsEffected = statement.executeUpdate();
        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }

    public int updateEmail(Integer id, String email) throws UserException {

        if (id < 0) {
            throw new UserException("User ID is required.").requiredFields("id");
        }

        if (email == null) {
            throw new UserException("email is required.").nullUserException();
        }

        int rowsEffected = 0;

        try {
            String query = "update user set  email  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public int updatePasscode(Integer id, String passcode) throws UserException {

        if (id < 0) {
            throw new UserException("User ID is required.").requiredFields("id");
        }

        if (passcode == null) {
            throw new UserException("passcode is required.").nullUserException();
        }

        int rowsEffected = 0;

        try {
            String query = "update user set  passcode  = ? where id = ?";

            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, passcode);
            statement.setInt(2, id);

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return rowsEffected;
    }


    public List<UserEntity> searchByEmail(String email) throws UserException {

        final List<UserEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, email, passcode from user where email LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);

            results = statement.executeQuery();
            while (results.next()) {
                UserEntity item = new UserEntity();
                item.setId(results.getInt("id"));
                item.setEmail(results.getString("email"));
                item.setPasscode(results.getString("passcode"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

    public List<UserEntity> searchByPasscode(String passcode) throws UserException {

        final List<UserEntity> itemList = new ArrayList<>();

        try {
            String query = "select id, email, passcode from user where passcode LIKE ?";
            connection = getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, passcode);

            results = statement.executeQuery();
            while (results.next()) {
                UserEntity item = new UserEntity();
                item.setId(results.getInt("id"));
                item.setEmail(results.getString("email"));
                item.setPasscode(results.getString("passcode"));

                itemList.add(item);
            }

        } catch (SQLException sqlException) {
            UserException ex = new UserException(sqlException.getMessage());
            throw ex;
        } finally {
            try {
                if (statement != null)
                    statement.close();
                if (connection != null)
                    connection.close();
            } catch (Exception exception) {
                UserException ex = new UserException(exception.getMessage());
                throw ex;
            }
        }

        return itemList;
    }

}
