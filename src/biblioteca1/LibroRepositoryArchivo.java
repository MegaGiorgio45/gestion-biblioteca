package biblioteca1;

public class LibroRepositoryArchivo implements LibroRepository {

	private String rutaArchivo = "libros.txt";

	public LibroRepositoryArchivo() {
		crearArchivoSiNoExiste();
	}
	
	public LibroRepositoryArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
		crearArchivoSiNoExiste();
	}

	private void crearArchivoSiNoExiste() {
		try {
			File f = new File(rutaArchivo);
			if (!f.exists()) {
				f.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("Error al crear el archivo: " + e.getMessage());
		}
	}

}