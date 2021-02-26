package dao;

import product.Category;
import product.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao  {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/productmanagement";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "password";

    private final String INSERT_PRODUCT = "INSERT INTO `productmanagement`.`management` "+
            "(`name`, `price`, `quantity`, `color`, `description`, `idcategory`) "+
            "VALUES (?, ?, ?, ?, ?, ?);";
    private  final String SELECT_PRODUCT_BY_ID = "select id,name,price,quantity,color,`description`,idcategory from management where id =?;";
    private  final String SELECT_ALL_PRODUCT = "SELECT m.*, c.category  FROM management m INNER JOIN category c ON m.idcategory = c.id ;";
    private  final String DELETE_PRODUCT_SQL = "delete from `productmanagement`.`management` where id = ?;";
    private  final String UPDATE_PRODUCT_SQL = "update management set `name`= ?,price= ?, quantity =? ,color=?,description=?,idcategory=?" +
            "where id = ?;";
    private  final String SEARCH_PRODUCT_BY_NAME ="SELECT m.*, c.category  FROM productmanagement.management m INNER JOIN productmanagement.category c ON m.idcategory = c.id WHERE `name` LIKE (?);";
    private final String GET_PRODUCT_BY_ID = "SELECT m.*, c.category  FROM productmanagement.management m INNER JOIN productmanagement.category c ON m.idcategory = c.id WHERE m.id=?;";

    public ProductDao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(jdbcURL,jdbcUsername,jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void insertProduct(Product product) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCT)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getIdcategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //chưa sd
    public Product selectProduct(int id) throws SQLException {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                product = new Product(id, name, price, quantity, color, description, idcategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                String category = rs.getString("category");
//                String description_category= rs.getString("description_category");
                Category category1 = new Category(category);
                products.add(new Product(id, name, price, quantity, color, description,category1, idcategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public List<Product> searchByName(String name) throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection=getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_BY_NAME)
        ) {
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                String category = rs.getString("category");
                String description_category= rs.getString("description_category");
                Category category1 = new Category(category,description_category);
                products.add(new Product(id, name, price, quantity, color, description,category1, idcategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_SQL)
        ) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getColor());
            statement.setString(5, product.getDescription());
            statement.setInt(6, product.getIdcategory());
            statement.setInt(7,product.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }



    public Product getProductById(int id) throws SQLException {
        Product product =   new Product();
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(GET_PRODUCT_BY_ID);) {
            callableStatement.setInt(1, id);
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String color = rs.getString("color");
                String description = rs.getString("description");
                int idcategory = rs.getInt("idcategory");
                String category = rs.getString("category");
                product = new Product(id, name, price, quantity, color, description,new Category(idcategory,category));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return product;
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
}
