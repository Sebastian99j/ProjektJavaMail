package pl.Java.App;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MailDAO {
    private Connection connection;

    public MailDAO() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userMail?serverTimezone=UTC", "root", "Master123!@#");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findMails() {
        final String sql = "SELECT mail FROM userMailTable";
        List<String> resultList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String mail = resultSet.getString("mail");
                resultList.add(mail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public List<String> findPassword() {
        final String sql = "SELECT password FROM userMailTable";
        List<String> resultList = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                resultList.add(password);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
