package dao;

import model.NoteType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteTypeDao {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/inotes";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "password";

    private final String ADD_NEW_NOTE_TYPE = "INSERT INTO inotes.note_type (`id`, `name_note`, `description`) VALUES (?, ?, ?)";
    private final String GET_NOTE_TYPE_BY_ID = "SELECT * FROM inotes.note_type where id=?";
    private final String EDIT_NOTE_TYPE_BY_ID = "UPDATE inotes.note_type SET name_note = ?, description = ? WHERE (id = ?) ";
    private final String SELECT_ALL_NOTETYPE = "SELECT * FROM `inotes`.`note_type`";


    public NoteTypeDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addTheNewNotety(NoteType noteType) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_NOTE_TYPE)
        ) {
            preparedStatement.setInt(1,noteType.getId());
            preparedStatement.setString(2, noteType.getName_note());
            preparedStatement.setString(3, noteType.getDescription());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//
//    public void searchNoteById(int id) {
//        List<NoteType> type = new ArrayList<>();
//        try (
//                Connection connection = getConnection();
//                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NOTE_BY_ID)
//        ) {
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//
////                id = resultSet.getInt("id");
//                String name_note = resultSet.getString("name_note");
//                String description = resultSet.getString("description");
//                NoteType noteType = new NoteType(id,name_note,description);
//                type.add(noteType);
//
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public NoteType getNoteTypeById(int id) {
        NoteType noteType= null;
        try (
                Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(GET_NOTE_TYPE_BY_ID)
        ) {
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String name_note = resultSet.getString("name_note");
                String description = resultSet.getString("description");
                NoteType type = new NoteType(id,name_note,description);
                noteType = new  NoteType(id,name_note,description);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return noteType;
    }

    public boolean editNoteTypeById(NoteType noteType) throws SQLException {
        boolean rowEdit;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EDIT_NOTE_TYPE_BY_ID)
        ) {
            statement.setString(1, noteType.getName_note());
            statement.setString(2, noteType.getDescription());
            statement.setInt(3, noteType.getId());
            rowEdit = statement.executeUpdate() > 0;
        }
        return rowEdit;
    }

    public void insertNoteStore(NoteType noteType) throws SQLException {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(ADD_NEW_NOTE_TYPE)
        ) {
            callableStatement.setInt(1, noteType.getId());
            callableStatement.setString(2, noteType.getName_note());
            callableStatement.setString(3, noteType.getDescription());
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

    public List<NoteType> selectAllNoteType() {
        List<NoteType> noteTypes = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NOTETYPE)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name_note = resultSet.getString("name_note");
                String description = resultSet.getString("description");
                noteTypes.add(new NoteType(id,name_note,description));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return noteTypes;
    }
}