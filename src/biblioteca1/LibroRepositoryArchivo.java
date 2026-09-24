package biblioteca1;

public class LibroRepositoryArchivo implements LibroRepository {

	private String rutaArchivo = "libros.txt";

	public LibroRepositoryArchivo() {
		crearArchivoSiNoExiste();
	}
}