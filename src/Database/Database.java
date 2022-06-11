package Database;

import java.sql.*;
import java.util.function.Function;

public class Database {
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/javaproject_db";
    private final static String DATABASE_USERNAME = "root";
    private final static String DATABASE_PASSWORD = "javajava";

    public static <T> T exec(String query, Function<ResultSet, T> callback, Function<SQLException, T> error) {
        try (
                Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);)
        {
            return callback.apply(resultSet);
        } catch (SQLException ex) {
            return error.apply(ex);
        }
    }
}
