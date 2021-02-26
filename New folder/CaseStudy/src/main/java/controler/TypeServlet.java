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

@WebServlet(name = "TypeServlet", value = "/type")

public class TypeServlet extends HttpServlet {
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
                    insertNoteType(request, response);
                    break;
                case "edit":
                    updateNoteType(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showNewType(request, response);
                    break;
                case "edit":
                    showEditType(request, response);
                    break;
                default:
                    listNoteType(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    public void showEditType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        NoteType noteType = noteTypeDao.getNoteTypeById(id);
        List<NoteType> listNoteType = noteTypeDao.selectAllNoteType();
        RequestDispatcher dispatcher = request.getRequestDispatcher("editType.jsp");
        request.setAttribute("noteType", noteType);
        request.setAttribute("listNoteType", listNoteType);
        dispatcher.forward(request, response);
    }

    public void showNewType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("addType.jsp");
        dispatcher.forward(request, response);
    }

    private void listNoteType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<NoteType> listNoteType1 = noteTypeDao.selectAllNoteType();
        request.setAttribute("listNoteType1", listNoteType1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listType.jsp");
        dispatcher.forward(request, response);
    }

    public void insertNoteType(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String name_note =  request.getParameter("name_note");
        String description = request.getParameter("description");
        NoteType noteType=new NoteType(name_note,description);
        noteTypeDao.insertNoteStore(noteType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("addType.jsp");
        dispatcher.forward(request, response);
    }
    public void updateNoteType(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name_note = request.getParameter("name_note");
        String description = request.getParameter("description");
        NoteType noteType = new NoteType(id, name_note, description);
        noteTypeDao.editNoteTypeById(noteType);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editType.jsp");
        dispatcher.forward(request, response);
    }

}

