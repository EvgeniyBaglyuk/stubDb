package apps.demo.dbmanagement;

import apps.demo.models.User;


import java.sql.*;

public class worksDB {
    private static final String url = "jdbc:postgresql://192.168.0.104:5432/postgr-db";
    private static final String login = "Jeny";
    private static final String password = "Jeny_2002";
    public static User selectUserByLogin(String selectLogin) {
        Connection connection = null;
        try {
            String query = "SELECT * FROM user1 u1 JOIN user2 u2 ON u1.login = u2.login WHERE u1.login LIKE '" + selectLogin + "'";
            connection = DriverManager.getConnection(url, login, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                java.sql.Date date = resultSet.getDate("date");
                String email = resultSet.getString("email");
                return new User(login, password, date, email);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Метод для вставки данных
    public static int insertUser(User user) {
        String query = "INSERT INTO user1 (login, password, date) VALUES (?, ?, ?);\n" +
                "INSERT INTO user2 (login, email) VALUES (?, ?)";
        int res = 0;

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setDate(3, new java.sql.Date(user.getDate().getTime()));
                preparedStatement.setString(4, user.getLogin());
                preparedStatement.setString(5, user.getEmail());
                res += preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return res;
    }
}
