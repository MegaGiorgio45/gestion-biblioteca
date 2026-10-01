package biblioteca1;

import java.util.List;

public interface LibroRepository {

     /**
     * Obtiene el listado completo de libros registrados en el origen de datos.
     *
     * @return una lista de objetos con todos los registros encontrados.
     */
    List<Libro> obtenerTodos();

       /**
     * Busca libros cuyo título contenga el fragmento especificado.
     *
     * @param titulo la cadena de texto con el título a buscar.
     * @return una lista de objetos que coinciden con el título facilitado.
     */
    List<Libro> buscarPorTitulo(String titulo);

    List<Libro> buscarPorAutor(String autor);

    List<Libro> buscarPorRangoPrecio(double minimo, double maximo);

    List<Libro> buscarPorStockMinimo(int stock);

    void insertar(Libro libro);

    void eliminarPorTitulo(String titulo);

    void copiarA(LibroRepository destino);
}