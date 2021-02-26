package dao;

import product.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/productmanagement";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "password";

    private final String ADD_NEW_PRODUCT = "INSERT INTO `productmanagement`.`category` (`id`, `category`, `description_category`) VALUES (?,?,?)";
    private final String GET_PRODUCT_BY_ID = "SELECT * FROM `productmanagement`.`category` where id=?";
    private final String EDIT_PRODUCT_BY_ID = "UPDATE `productmanagement`.`category` SET `category` = ?, `description_category` = ? WHERE (`id` = ?);";
    private final String SELECT_ALL_PRODUCT = "SELECT * FROM `productmanagement`.`category`";

    public List<Category> selectAllProduct() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String category = resultSet.getString("category");
                String description_category = resultSet.getString("description_category");
                categories.add(new Category(id,category,description_category));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    public boolean editProductTypeById(Category category) throws SQLException {
        boolean rowEdit;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(EDIT_PRODUCT_BY_ID)
        ) {
            statement.setString(1, category.getCategory());
            statement.setString(2, category.getDescription_category());
            statement.setInt(3, category.getId());
            rowEdit = statement.executeUpdate() > 0;
        }
        return rowEdit;
    }

    public Category getProductTypeById(int id) {
        Category category1= null;
        try (
                Connection connection = getConnection();
                CallableStatement callableStatement = connection.prepareCall(GET_PRODUCT_BY_ID)
        ) {
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                String category = resultSet.getString("category");
                String description_category = resultSet.getString("description_category");
                category1 = new Category(id,category,description_category);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category1;
    }

    public void insertProductStore(Category category) throws SQLException {
        try (Connection connection = getConnection();
             CallableStatement callableStatement = connection.prepareCall(ADD_NEW_PRODUCT)
        ) {
            callableStatement.setInt(1, category.getId());
            callableStatement.setString(2, category.getCategory());
            callableStatement.setString(3, category.getDescription_category());
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
    public CategoryDAO() {
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
}
