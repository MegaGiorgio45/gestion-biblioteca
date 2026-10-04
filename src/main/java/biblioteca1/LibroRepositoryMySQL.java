package main.java.biblioteca1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Implementación de {@see LibroRepository} que utiliza una base de datos MySQL como almacenamiento persistente.
 * @author Guillermo
 */
public class LibroRepositoryMySQL implements LibroRepository {
    /**    
     * Obtiene el listado completo de libros registrados en la base de datos MySQL.
     * 
     * @return una lista de objetos {@see Libro} con todos los registros encontrados en la base de datos SQL.
     * @see main.java.biblioteca1.LibroRepository#obtenerTodos()
     */
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
    /**    
     * Busca libros cuyo título contenga el fragmento especificado en la base de datos MySQL.
     * @return una lista de objetos {@see Libro} cuyo título corresponde con el string proporcionado.
     * @see main.java.biblioteca1.LibroRepository#buscarPorTitulo(java.lang.String)
     */
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

    /**    
     * Busca libros cuyo autor contenga el fragmento especificado en la base de datos MySQL.
     * @return una lista de objetos {@see Libro} cuyo autor corresponde con el string proporcion
     * @see main.java.biblioteca1.LibroRepository#buscarPorAutor(java.lang.String)
     */
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

    /**
     * Filtra la lista de libros cuyo precio se encuentre dentro del rango inclusivo especificado en la base de datos MySQL.
     * @return una lista de objetos {@see Libro} cuyo precio se encuentra dentro del rango indicado.
     * @see main.java.biblioteca1.LibroRepository#buscarPorRangoPrecio(double, double)
     */
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

    /**
     * Busca libros cuyo stock sea menor al valor especificado en la base de datos MySQL.
     * @return una lista de objetos {@see Libro} cuyo stock es menor al valor indicado.
     * @see main.java.biblioteca1.LibroRepository#buscarPorStockMinimo(int)
     */
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
        String sql = "INSERT INTO libros (titulo, autor, precio, stock) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, libro.getTitulo());
            statement.setString(2, libro.getAutor());
            statement.setDouble(3, libro.getPrecio());
            statement.setInt(4, libro.getStock());

            statement.executeUpdate();
            System.out.println("Libro insertado correctamente en MySQL.");
        } catch (SQLException e) {
            System.out.println("Error al insertar en MySQL: " + e.getMessage());
        }
    }

    @Override
    public void eliminarPorTitulo(String titulo) {
        String sql = "DELETE FROM libros WHERE titulo = ?";

        try (Connection conexion = ConexionMySQL.conectar();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, titulo);
            statement.executeUpdate();
            System.out.println("Libro eliminado correctamente de MySQL.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar en MySQL: " + e.getMessage());
        }
    }

    @Override
    public void copiarA(LibroRepository destino) {
        List<Libro> libros = this.obtenerTodos();
        for (Libro libro : libros) {
            destino.insertar(libro);
        }
        System.out.println("Datos copiados correctamente a " + destino.getClass().getSimpleName());
    }
}
