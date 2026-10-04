package biblioteca1;


/**
 * Clase que representa un libro en la biblioteca.
 * 
 * @author Guillermo
 */
public class Libro {
    public int id;
    public String titulo;
    public String autor;
    public double precio;
    public int stock;
    /**
     * Constructor de la clase Libro.
     * @param id identificador único del libro.
     * @param titulo título del libro.
     * @param autor autor del libro.
     * @param precio precio del libro.
     * @param stock cantidad disponible del libro.
     */
    public Libro(int id, String titulo, String autor, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        
    }
    /**
     * Obtiene el identificador único del libro.
     * @return el identificador único del libro.
     */
    public int getId() {
        return id;
    }
    /**
     * Obtiene el título del libro.
     * @return el título del libro.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Obtiene el autor del libro.
     * @return el autor del libro.
     */
    public String getAutor() {
        return autor;
    }
    /**
     * Obtiene el precio del libro.
     * @return el precio del libro.
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * Obtiene la cantidad disponible del libro en stock.
     * @return la cantidad disponible del libro en stock.
     */
    public int getStock() {
        return stock;
    }
    /**
     * Establece el identificador único del libro.
     * @param id el identificador único del libro a establecer.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Establece el título del libro.
     * @param titulo el título del libro a establecer.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    /**
     * Establece el autor del libro.
     * @param autor el autor del libro a establecer.
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    /**
     * Establece el precio del libro.
     * @param precio el precio del libro a establecer.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    /**
     * Establece la cantidad disponible del libro en stock.
     * @param stock la cantidad disponible del libro en stock a establecer.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return una representación en cadena del objeto Libro.
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                '}';
    }
}
