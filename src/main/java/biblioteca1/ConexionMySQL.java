package biblioteca1;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {

    private static final Dotenv dotenv = loadDotenv();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USUARIO = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    private static Dotenv loadDotenv() {
        String workingDir = System.getProperty("user.dir");

        try {
            return Dotenv.configure()
                    .directory(workingDir)
                    .filename("variables.env")
                    .load();
        } catch (DotenvException ignored) {
            try {
                return Dotenv.configure()
                        .directory(workingDir)
                        .filename(".env")
                        .load();
            } catch (DotenvException ex) {
                throw new IllegalStateException(
                        "No se encontró ningún archivo de entorno (.env o variables.env) en el proyecto.",
                        ex
                );
            }
        }
    }

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el controlador de MySQL.", e);
        }

        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }
}