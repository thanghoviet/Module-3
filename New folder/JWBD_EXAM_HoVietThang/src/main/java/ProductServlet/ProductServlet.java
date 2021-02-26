package ProductServlet;

import dao.CategoryDAO;
import dao.ProductDao;
import product.Category;
import product.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDao productDao = new ProductDao();
    private CategoryDAO categoryDAO = new CategoryDAO();

    public void innit() {
        productDao = new ProductDao();
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
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
                    deleteProduct(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        if (name != null) {
            List<Product> listProduct = productDao.searchByName(name);
            request.setAttribute("listProduct", listProduct);
            request.setAttribute("mess", "User of product: " + name);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }
    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDao.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
            productDao.deleteProduct(id);

        List<Product> listProduct = productDao.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);

    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        List<Category> categoryList = categoryDAO.selectAllProduct();
        request.setAttribute("categoryList", categoryList);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDao.getProductById(id);
        List<Category> categoryList = categoryDAO.selectAllProduct();
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        dispatcher.forward(request, response);

    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idcategory = Integer.parseInt(request.getParameter("idcategory"));
        Category category1 = new Category(idcategory);
        Product product = new Product(name, price, quantity, color, description, category1);
        productDao.insertProduct(product);

        List<Category> categoryList = categoryDAO.selectAllProduct();
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int idcategory = Integer.parseInt(request.getParameter("idcategory"));

        Product product = new Product(id, name, price, quantity, color, description, new Category(idcategory));
        productDao.updateProduct(product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        dispatcher.forward(request, response);

    }
}
