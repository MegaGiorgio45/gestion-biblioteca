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

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


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
