package biblioteca1;

import java.util.List;

public interface LibroRepository {

    List<Libro> obtenerTodos();

    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> buscarPorAutor(String autor);

    List<Libro> buscarPorRangoPrecio(double minimo, double maximo);

    List<Libro> buscarPorStockMinimo(int stock);

    boolean insertar(Libro libro);

    boolean eliminarPorTitulo(String titulo);

    boolean copiarA(LibroRepository destino);
}