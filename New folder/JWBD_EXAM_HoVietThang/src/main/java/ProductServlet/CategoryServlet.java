package ProductServlet;

import dao.CategoryDAO;
import dao.ProductDao;
import product.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/category")

public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    ProductDao productDao = new ProductDao();
    CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProductType(request, response);
                    break;
                case "edit":
                    updateProductType(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

    private void listNoteType(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Category> listCategory1 = categoryDAO.selectAllProduct();
        request.setAttribute("listCategory1", listCategory1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listCategory.jsp");
        dispatcher.forward(request, response);
    }

    public void showEditType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDAO.getProductTypeById(id);
        List<Category> listCategory = categoryDAO.selectAllProduct();
        RequestDispatcher dispatcher = request.getRequestDispatcher("editCategory.jsp");
        request.setAttribute("category", category);
        request.setAttribute("listCategory", listCategory);
        dispatcher.forward(request, response);
    }
    public void showNewType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("createCategory.jsp");
        dispatcher.forward(request, response);
    }

    public void updateProductType(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String category = request.getParameter("category");
        String description_category = request.getParameter("description_category");
        Category category1 = new Category(id, category, description_category);
        categoryDAO.editProductTypeById(category1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editCategory.jsp");
        dispatcher.forward(request, response);
    }

    public void insertProductType(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        String category =  request.getParameter("category");
        String description_category = request.getParameter("description_category");
        Category category1=new Category(category,description_category);
        categoryDAO.insertProductStore(category1);
        RequestDispatcher dispatcher = request.getRequestDispatcher("createCategory.jsp");
        dispatcher.forward(request, response);
    }
}
