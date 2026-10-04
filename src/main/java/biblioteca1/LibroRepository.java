package biblioteca1;
import java.util.List;

/**
 * Interfaz que define las operaciones para gestionar y guardar libros.
 * <p>
 * Sirve de plantilla tanto si los datos se guardan en ficheros de texto 
 * como si se guardan en una base de datos MySQL.
 *
 * @author MegaGiorgio45
 * @version 1.0
 * @since 1.0
 */
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

     /**
     * Busca libros cuyo autor contenga el texto especificado.
     *
     * @param autor el nombre del autor a filtrar.
     * @return una lista de objetos asociados al autor indicado.
     */
    List<Libro> buscarPorAutor(String autor);

    /**
     * Filtra la lista de libros cuyo precio se encuentre dentro del rango inclusivo especificado.
     *
     * @param minimo el límite inferior de precio a consultar.
     * @param maximo el límite superior de precio a consultar.
     * @return una lista de objetos dentro del rango de precio indicado.
     */
    List<Libro> buscarPorRangoPrecio(double minimo, double maximo);

    /**
     * Busca libros cuya cantidad disponible en inventario sea igual o superior a la indicación.
     *
     * @param stock la cantidad mínima de stock requerida.
     * @return una lista de objetos que cumplen la condición de stock mínimo.
     */
    List<Libro> buscarPorStockMinimo(int stock);

      /**
     * Inserta un nuevo objeto en el almacenamiento persistente.
     *
     * @param libro el objeto que se va a almacenar.
     */
    void insertar(Libro libro);

    /**
     * Elimina del registro los datos del libro cuyo título coincida con el texto recibido.
     * 
     * En caso de existir múltiples coincidencias, se solicita su ID.
     *
     * @param titulo el título del libro que se desea eliminar.
     */

    void eliminarPorTitulo(String titulo);

     /**
     * Transfiere la totalidad de registros almacenados hacia un repositorio de destino.
     *
     * @param destino la implementación de donde se copiarán los datos.
     */
    void copiarA(LibroRepository destino);
}