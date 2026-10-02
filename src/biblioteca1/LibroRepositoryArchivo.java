package biblioteca1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LibroRepositoryArchivo implements LibroRepository {

	/**
     * Ruta relativa o absoluta del archivo de texto en disco.
     */
	private String rutaArchivo = "libros.txt";

	/**
     * Construye un repositorio asignando la ruta por defecto.
     * 
     * Verifica la existencia del fichero en el almacenamiento y lo crea en caso de no existir.
     */
	public LibroRepositoryArchivo() {
		crearArchivoSiNoExiste();
	}

	/**
     * Construye un repositorio especificando una ruta personalizada para el archivo de texto.
     *
     * @param rutaArchivo la ruta o nombre del archivo que se utilizará para especificar la ruta.
     */
	public LibroRepositoryArchivo(String rutaArchivo) {
		this.rutaArchivo = rutaArchivo;
		crearArchivoSiNoExiste();
	}

	/**
     * Verifica la existencia física del archivo de datos en disco.
     * 
     * En caso de que el archivo no exista, ejecuta para crearlo vacío.
     */
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

	/**
     * Lee y procesa las líneas del fichero formateadas con el separador.
     *
     * Parsea cada campo a sus tipos correspondientes y construye una lista.
     *
     * @return una lista con todos los objetos recuperados del archivo.
     * @throws NumberFormatException si la conversión de campos numéricos resulta inválida.
     */
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

	/**
     * Sobrescribe completamente el archivo de texto con los elementos de la lista recibida.
     *
     * @param libros la lista de objetos a serializar en el fichero.
     */
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

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Libro> obtenerTodos() {
		return leerTodos();
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Libro> buscarPorTitulo(String titulo) {
		return leerTodos().stream().filter(l -> l.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
				.sorted(Comparator.comparing(l -> l.getTitulo())).collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Libro> buscarPorAutor(String autor) {
		return leerTodos().stream().filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
				.sorted(Comparator.comparing(l -> l.getAutor())).collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Libro> buscarPorRangoPrecio(double minimo, double maximo) {
		return leerTodos().stream().filter(l -> l.getPrecio() >= minimo && l.getPrecio() <= maximo)
				.sorted(Comparator.comparingDouble(l -> l.getPrecio())).collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public List<Libro> buscarPorStockMinimo(int stock) {
		return leerTodos().stream().filter(l -> l.getStock() >= stock)
				.sorted(Comparator.comparingInt(l -> l.getStock())).collect(Collectors.toList());
	}

	/**
     * {@inheritDoc}
     * <p>
     * Escribe la nueva entrada al final del archivo configurando el parámetro {@code append} en {@code true}.
     */
	@Override
	public void insertar(Libro libro) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
			String linea = libro.getId() + "^" + libro.getTitulo() + "^" + libro.getAutor() + "^" + libro.getPrecio()
					+ "^" + libro.getStock();
			bw.write(linea);
			bw.newLine();
			System.out.println("Libro insertado correctamente en el archivo.");
		} catch (IOException e) {
			System.out.println("Error al insertar el libro: " + e.getMessage());
		}
	}

	/**
     * {@inheritDoc}
     * <p>
     * Si existen varios coincidencias, solicita la ID concreta por la entrada estándar.
     */
	@Override
	public void eliminarPorTitulo(String titulo) {
		List<Libro> todos = leerTodos();

		List<Libro> coincidentes = todos.stream().filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
				.collect(Collectors.toList());

		if (coincidentes.isEmpty()) {
			System.out.println("No se encontró ningún libro con el título: " + titulo);
			return;
		}

		int idAEliminar;

		if (coincidentes.size() > 1) {
			System.out.println("Se encontraron varios libros con ese título:");
			for (Libro l : coincidentes) {
				System.out.println(l);
			}
			java.util.Scanner sc = new java.util.Scanner(System.in);
			System.out.print("Introduce el ID del libro que deseas eliminar: ");
			idAEliminar = Integer.parseInt(sc.nextLine());
		} else {
			idAEliminar = coincidentes.get(0).getId();
		}

		final int idFinal = idAEliminar;
		List<Libro> filtrados = todos.stream().filter(l -> l.getId() != idFinal).collect(Collectors.toList());

		if (filtrados.size() < todos.size()) {
			guardarTodos(filtrados);
			System.out.println("Libro eliminado correctamente.");
		} else {
			System.out.println("No se encontró ningún libro con ese ID.");
		}
	}

	/**
     * {@inheritDoc}
     */
	@Override
	public void copiarA(LibroRepository destino) {
		List<Libro> todos = leerTodos();
		todos.forEach(l -> destino.insertar(l));
		System.out.println("Copia realizada con éxito.");
	}

}