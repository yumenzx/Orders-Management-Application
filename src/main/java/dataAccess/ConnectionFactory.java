package dataAccess;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * clasa corespunzatoare pentru setarea conexiunilor cu baza de date
 */
public class ConnectionFactory {
    private static final Logger log = Logger.getLogger(ConnectionFactory.class.getName());

    private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static final String dbURL = "jdbc:mysql://localhost:3306/warehouse";
    private static final String user = "root";
    private static final String password = "qwert";

    private static ConnectionFactory connectionFactory = new ConnectionFactory();

    /**
     * constructorul face legatura cu driverul pentru a putea efectua operatii pe baza da date
     */
    private ConnectionFactory() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.log(Level.WARNING, "Eroare la constructorul conexiunii");
            e.printStackTrace();
        }
    }

    /**
     * meotda de creeare a conexiunii cu baza de date
     * @return returneaza conexiunea creaya
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, user, password);
        } catch (SQLException e) {
            log.log(Level.WARNING, "Eroare la conectarea la baze de date");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * metoda de returnare a conexinuii cu baza de date
     * @return returneaza conexiunea in urma crearii
     */
    public static Connection getConnection() {
        return connectionFactory.createConnection();
    }

    /**
     * metoda pentru inchiderea conexiunii cu baza de date
     * @param connection conexiunea propriu zisa
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.log(Level.WARNING, "Eroare la inchiderea conexiunii");
                e.printStackTrace();
            }
        }
    }

    /**
     * meotoda pentru inchiderea interogarii
     * @param statement interogarea
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.log(Level.WARNING, "Eroare la inchiderea interogarii");
            }
        }
    }

    /**
     * metoda pentru inchiderea rezultatelor returnate in urma unei interogari
     * @param resultSet rezultatele returnate
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.log(Level.WARNING, "Eroare la inchiderea rezultatelor interogarii");
            }
        }
    }
}
