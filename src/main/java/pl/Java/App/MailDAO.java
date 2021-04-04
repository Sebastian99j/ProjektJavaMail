package pl.Java.App;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MailDAO {
    private Connection connection;

    public MailDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/usermail?serverTimezone=UTC", "root", "admin");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findMails() {
        final String sql = "SELECT mail FROM userMailTable;";
        List<String> resultList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String mail = resultSet.getString("mail");
                resultList.add(mail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public String passToEmail(String mail) {
        final String sql = "SELECT password FROM userMailTable WHERE mail='"+mail+"'";
        String password = null;
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                password = resultSet.getString("password");
            }
        } catch (SQLException e) {
            System.out.println("Błąd połączenia");;
        }
        return password;
    }

    public List<Users> allUsers() {
        final String sql = "SELECT mail, password FROM usermailtable ORDER BY id asc;";
        List<Users> users = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int idUsers = resultSet.getInt("id");
                String mailUsers = resultSet.getString("mail");
                String passUsers = resultSet.getString("password");
                users.add(new Users(idUsers,mailUsers, passUsers));
            }
        } catch (SQLException e) {
            System.out.println("Błąd połączenia");;
        }
        return users;
    }

    public void addUser(Users user){
        final String sql = "INSERT INTO usermailtable (id,mail, password) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, user.getId().toString());
            statement.setString(2, user.getMail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()){
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(String mail) {
        final String sql = "DELETE FROM usermailtable WHERE mail='"+mail+"'";
        try(Statement statement = connection.createStatement()){
            int updatedRows = statement.executeUpdate(sql);
            return updatedRows != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getIdUsers(){
        final String sql = "SELECT id FROM usermailtable ORDER BY id ASC";
        int id = 0;
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                id = resultSet.getInt("id");
            }
            return id+1;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}