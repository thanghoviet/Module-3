package controler;

import dao.NoteDAO;
import dao.NoteTypeDao;
import model.Note;
import model.NoteType;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "NoteServlet", value = "/note")


public class NoteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    NoteDAO noteDAO = new NoteDAO();
    NoteTypeDao noteTypeDao = new NoteTypeDao();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertNote(request, response);
                    break;
                case "edit":
                    updateNote(request, response);
                    break;



            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteNote(request, response);
                    break;
                case "sortId":
                    sortWithId(request,response);
                    break;
                case "sort-Title":
                    sortWithTitle(request,response);
                    break;
                case "sort-Date":
                    sortWithDate(request,response);
                    break;
                case "search":
                    searchByTitle(request, response);
                    break;
                default:
                    listNote(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }


    private void searchByTitle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String title = request.getParameter("title");
        if (title != null) {
            List<Note> listNote = noteDAO.searchByTitle(title);
            request.setAttribute("listNote", listNote);
            request.setAttribute("mess", "User of title: " + title);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    private void sortWithDate(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Note> listNote = noteDAO.sortByDate();
        request.setAttribute("listNote", listNote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    private void sortWithTitle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Note> listNote = noteDAO.sortByTitle();
        request.setAttribute("listNote", listNote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    private void sortWithId(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Note> listNote = noteDAO.sortById();
        request.setAttribute("listNote", listNote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNote.jsp");
        List<NoteType> listNoteType = noteTypeDao.selectAllNoteType();
        request.setAttribute("listNoteType", listNoteType);
        dispatcher.forward(request, response);
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Note existingNote = noteDAO.getNoteById(id);
        List<NoteType> listNoteType = noteTypeDao.selectAllNoteType();
        RequestDispatcher dispatcher = request.getRequestDispatcher("editNote.jsp");
        request.setAttribute("note", existingNote);
        request.setAttribute("listNoteType", listNoteType);
        dispatcher.forward(request, response);
    }

    public void deleteNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        noteDAO.deleteNoteById(id);
        List<Note> listNote = noteDAO.selectAllNote();
        request.setAttribute("listNote", listNote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    private void listNote(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Note> listNote = noteDAO.selectAllNote();
        request.setAttribute("listNote", listNote);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listTable.jsp");
        dispatcher.forward(request, response);
    }

    public void insertNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int type_id = Integer.parseInt(request.getParameter("type"));
        NoteType noteType=new NoteType(type_id);
        Note newNote = new Note(title, content,noteType);
        noteDAO.insertNoteStore(newNote);

        List<NoteType> listNoteType = noteTypeDao.selectAllNoteType();
        request.setAttribute("listNoteType", listNoteType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addNote.jsp");
        dispatcher.forward(request, response);
    }

    public void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int idNote= Integer.parseInt(request.getParameter("type"));
        Note note = new Note(id, title, content,new NoteType(idNote));
        noteDAO.editNoteById(note);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editNote.jsp");
        dispatcher.forward(request, response);
    }


}
