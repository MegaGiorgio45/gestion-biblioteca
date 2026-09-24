package biblioteca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryMySQL implements LibroRepository {

    @Override
    public List<Libro> obtenerTodos() {

        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                
                Libro libro = new Libro(
                    resultado.getInt("id"),
                    resultado.getString("titulo"),
                    resultado.getString("autor"),
                    resultado.getDouble("precio"),
                    resultado.getInt("stock")
                );

                libros.add(libro);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar MySQL: " + e.getMessage());
        }

        return libros;
    }

    @Override
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE titulo LIKE ?";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, "%" + titulo + "%");

            try (ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    Libro libro = new Libro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                    );

                    libros.add(libro);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar MySQL: " + e.getMessage());
        }

        return libros;
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE autor LIKE ?";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, "%" + autor + "%");

            try (ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    Libro libro = new Libro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                    );

                    libros.add(libro);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar MySQL: " + e.getMessage());
        }

        return libros;
    }

    @Override
    public List<Libro> buscarPorRangoPrecio(double minimo, double maximo) {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE precio BETWEEN ? AND ?";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setDouble(1, minimo);
            statement.setDouble(2, maximo);

            try (ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    Libro libro = new Libro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                    );

                    libros.add(libro);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar MySQL: " + e.getMessage());
        }

        return libros;
    }

    @Override
    public List<Libro> buscarPorStockMinimo(int stock) {
        List<Libro> libros = new ArrayList<>();

        String sql = "SELECT * FROM libros WHERE stock < ?";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, stock);

            try (ResultSet resultado = statement.executeQuery()) {

                while (resultado.next()) {
                    Libro libro = new Libro(
                        resultado.getInt("id"),
                        resultado.getString("titulo"),
                        resultado.getString("autor"),
                        resultado.getDouble("precio"),
                        resultado.getInt("stock")
                    );

                    libros.add(libro);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar MySQL: " + e.getMessage());
        }

        return libros;
    }

    @Override
    public void insertar(Libro libro) {
        
    }

    @Override
    public void eliminarPorTitulo(String titulo) {
    }

    @Override
    public void copiarA(LibroRepository destino) {
    }
}
