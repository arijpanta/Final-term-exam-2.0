package org.example;

public class Main {
    import java.sql.*;

    public class DatabaseManager {

        private static final String URL = "src/main/resources/database/Final term exam.db";

        public void createTables() {
            try (Connection conn = DriverManager.getConnection(URL)) {

                Statement stmt = conn.createStatement();
                stmt.execute("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
                stmt.execute("CREATE TABLE IF NOT EXISTS class (id INTEGER PRIMARY KEY AUTOINCREMENT, classname TEXT)");
                stmt.execute("CREATE TABLE IF NOT EXISTS attendance (id INTEGER PRIMARY KEY AUTOINCREMENT, classId INTEGER, userId INTEGER, FOREIGN KEY(classId) REFERENCES class(id), FOREIGN KEY(userId) REFERENCES user(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertUser(String username, String password) {
            try (Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertClass(String classname) {
            try (Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO class (classname) VALUES (?)");
                stmt.setString(1, classname);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public void insertAttendance(int classId, int userId) {
            try (Connection conn = DriverManager.getConnection(URL)) {
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendance (classId, userId) VALUES (?, ?)");
                stmt.setInt(1, classId);
                stmt.setInt(2, userId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public ResultSet selectAllUsers() {
            try (Connection conn = DriverManager.getConnection(URL)) {
                Statement stmt = conn.createStatement();
                return stmt.executeQuery("SELECT * FROM user");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }

        public ResultSet selectAllClasses() {
            try (Connection conn = DriverManager.getConnection(URL)) {
                Statement stmt = conn.createStatement();
                return stmt.executeQuery("SELECT * FROM class");
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

}
}