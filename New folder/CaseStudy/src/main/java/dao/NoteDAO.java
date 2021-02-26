package dao;

import model.Note;
import model.NoteType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NoteDAO  {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/inotes";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "password";

    private final String ADD_NEW_NOTE = "INSERT INTO `inotes`.`note` (`title`, `content`, `type_id`) VALUES (?,?,?);";
    private final String DELETE_NOTE_BY_ID = "DELETE FROM `inotes`.`note` WHERE (`id` = ?);";
    private final String GET_NOTE_BY_ID = "SELECT n.*,nt.name_note as type_name FROM note n join note_type nt on n.type_id=nt.id where n.id=?;";
    private final String EDIT_NOTE_BY_ID = "UPDATE `inotes`.`note` SET `title` = ?, `content` = ?, `type_id` = ? WHERE (`id` = ?); ";
    private final String SELECT_ALL_USERS = "SELECT n.*, t.name_note as note, t.description as descrip FROM note n JOIN note_type t ON n.type_id = t.id";
    private final String SORT_BY_ID = "SELECT n.*, t.name_note as note, t.description as descrip FROM note n "+
            "JOIN note_type t ON n.type_id = t.id order by n.id desc";
    private final String SORT_BY_TITLE = "SELECT n.*, t.name_note as note, t.description as descrip FROM note n " +
                     "JOIN note_type t ON n.type_id = t.id order by n.title";
    private final String SORT_BY_DATETIME = "SELECT n.*, t.name_note as note, t.description as descrip FROM note n " +
            "JOIN note_type t ON n.type_id = t.id order by n.datetime";
    private  final String  SEARCH_NOTE_BY_TITLE ="SELECT n.*, t.name_note as note, t.description as descrip FROM note n JOIN note_type t ON n.type_id = t.id WHERE title LIKE (?);";
    private  final String  SEARCH_NOTE_TYPE_BY_TITLE ="SELECT * FROM note_type WHERE name_note LIKE '%?%';";

    public NoteDAO() {
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

    public List<Note> searchByTitle(String title) throws SQLException {
        List<Note> notes = new ArrayList<>();
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_NOTE_BY_TITLE)
        ) {
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int type_id = resultSet.getInt("type_id");
                String name_note = resultSet.getString("note");
                String description = resultSet.getString("descrip");
                NoteType noteType = new NoteType(name_note,description);
                notes.add(new Note(id,title,content,datetime,noteType,type_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public List<Note> sortByDate(){
        List<Note> notes = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_DATETIME)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int type_id = resultSet.getInt("type_id");
                String name_note = resultSet.getString("note");
                String description = resultSet.getString("descrip");
                NoteType noteType = new NoteType(name_note,description);
                notes.add(new Note(id,title,content,datetime,noteType,type_id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes;
    }

    public List<Note> sortByTitle(){
        List<Note> notes = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_TITLE)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int type_id = resultSet.getInt("type_id");
                String name_note = resultSet.getString("note");
                String description = resultSet.getString("descrip");
                NoteType noteType = new NoteType(name_note,description);
                notes.add(new Note(id,title,content,datetime,noteType,type_id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes;
    }

    public List<Note> sortById(){
        List<Note> notes = new ArrayList<>();
        try ( Connection connection = getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SORT_BY_ID)
        ){
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int type_id = resultSet.getInt("type_id");
                String name_note = resultSet.getString("note");
                String description = resultSet.getString("descrip");
                NoteType noteType = new NoteType(name_note,description);
                notes.add(new Note(id,title,content,datetime,noteType,type_id));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return notes;
    }

    public void addTheNewNote(Note note) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_NOTE)
        ) {
            preparedStatement.setString(1, note.getTitle());
            preparedStatement.setString(2, note.getContent());
            preparedStatement.setDate(3, note.getDatetime());
            preparedStatement.setInt(4,note.getType_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteNoteById(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_NOTE_BY_ID)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public Note getNoteById(int id) {
        Note note=new Note();
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(GET_NOTE_BY_ID)
        ) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int idType=resultSet.getInt("type_id");
                String nameType=resultSet.getString("type_name");
                note = new Note(id, title, content,datetime,new NoteType(idType,nameType));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return note;
    }

    public boolean editNoteById(Note note) throws SQLException {
        boolean rowEdit;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EDIT_NOTE_BY_ID)
        ) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3, note.getNoteType().getId());
            statement.setInt(4,note.getId());
            rowEdit = statement.executeUpdate() > 0;
        }
        return rowEdit;
    }

    public void insertNoteStore(Note note) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement=connection.prepareStatement(ADD_NEW_NOTE)
        ) {
            statement.setString(1, note.getTitle());
            statement.setString(2, note.getContent());
            statement.setInt(3,note.getNoteType().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void printSQLException(SQLException ex) {
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

    public List<Note> selectAllNote() {
        List<Note> notes = new ArrayList<>();
        List<NoteType> type = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                Date datetime = resultSet.getDate("datetime");
                int type_id = resultSet.getInt("type_id");
                String name_note = resultSet.getString("note");
                String description = resultSet.getString("descrip");
                NoteType noteType = new NoteType(name_note,description);
                notes.add(new Note(id,title,content,datetime,noteType,type_id));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(notes);
        return notes;
    }
}
