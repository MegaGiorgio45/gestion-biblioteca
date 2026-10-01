package biblioteca1;

import java.util.List;

public interface LibroRepository {

     /**
     * Obtiene el listado completo de libros registrados en el origen de datos.
     *
     * @return una lista de objetos con todos los registros encontrados.
     */
    List<Libro> obtenerTodos();

    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> buscarPorAutor(String autor);

    List<Libro> buscarPorRangoPrecio(double minimo, double maximo);

    List<Libro> buscarPorStockMinimo(int stock);

    void insertar(Libro libro);

    void eliminarPorTitulo(String titulo);

    void copiarA(LibroRepository destino);
}