package main.java.biblioteca1;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Clase de utilidad para gestionar la conexión a la base de datos MySQL.
 * <p>
 * ConexionMySQL
 * @author Guillermo
 */
public class ConexionMySQL {

    private static final Dotenv dotenv = loadDotenv();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USUARIO = dotenv.get("DB_USER");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    /**
     * Carga las variables de entorno desde un archivo .env o variables.env.
     * @return un objeto Dotenv con las variables de entorno cargadas.
     * @throws IllegalStateException si no se encuentra ningún archivo de entorno válido.
     */
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

    /**
     * Establece una conexión con la base de datos MySQL utilizando las credenciales y URL especificadas en el archivo de entorno.
     * @return una conexión a la base de datos MySQL.
     * @throws SQLException si ocurre un error al establecer la conexión.
     */
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