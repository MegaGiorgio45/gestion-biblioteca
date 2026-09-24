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
        return new ArrayList<>();
    }

    @Override
    public List<Libro> buscarPorAutor(String autor) {
        return new ArrayList<>();
    }

    @Override
    public List<Libro> buscarPorRangoPrecio(double minimo, double maximo) {
        return new ArrayList<>();
    }

    @Override
    public List<Libro> buscarPorStockMinimo(int stock) {
        return new ArrayList<>();
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
