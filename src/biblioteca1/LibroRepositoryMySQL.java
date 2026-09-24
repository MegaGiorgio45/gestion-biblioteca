package biblioteca1;

import java.util.ArrayList;
import java.util.List;

public class LibroRepositoryMySQL implements LibroRepository {

    @Override
    public List<Libro> obtenerTodos() {
        return new ArrayList<>();
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
