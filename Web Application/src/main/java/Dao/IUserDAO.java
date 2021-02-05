package Dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public  void sortByName(String name) throws  SQLException;
    public  void saerchByCountry(String country) throws SQLException;

    public void insertUser(User user) throws SQLException;

    public User selectUser(int id);

    public List<User> selectAllUsers();

    public boolean deleteUser(int id) throws SQLException;

    public boolean updateUser(User user) throws SQLException;
}

