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
	
	private List<Libro> leerTodos() {
		List<Libro> libros = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				if (!linea.trim().isEmpty()) {
					String[] partes = linea.split("\\^");
					if (partes.length >= 5) {
						int id = Integer.parseInt(partes[0].trim());
						String titulo = partes[1].trim();
						String autor = partes[2].trim();
						double precio = Double.parseDouble(partes[3].trim());
						int stock = Integer.parseInt(partes[4].trim());

						Libro l = new Libro(id, titulo, autor, precio, stock);
						libros.add(l);
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error al leer el archivo: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Error al parsear datos del archivo: " + e.getMessage());
		}
		return libros;
	}
	
	private void guardarTodos(List<Libro> libros) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, false))) {
			for (Libro l : libros) {
				String linea = l.getId() + "^" + l.getTitulo() + "^" + l.getAutor() + "^" + l.getPrecio() + "^"
						+ l.getStock();
				bw.write(linea);
				bw.newLine();
			}
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}
	
	@Override
	public List<Libro> obtenerTodos() {
		return leerTodos();
	}

	@Override
	public List<Libro> buscarPorTitulo(String titulo) {
		return leerTodos().stream().filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
				.sorted(Comparator.comparing(l -> l.getTitulo())).collect(Collectors.toList());
	}

}