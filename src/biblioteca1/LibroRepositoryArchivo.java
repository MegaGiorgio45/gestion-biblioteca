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

	@Override
	public List<Libro> buscarPorAutor(String autor) {
		return leerTodos().stream().filter(l -> l.getAutor().toLowerCase().contains(autor.toLowerCase()))
				.sorted(Comparator.comparing(l -> l.getAutor())).collect(Collectors.toList());
	}

	@Override
	public List<Libro> buscarPorRangoPrecio(double minimo, double maximo) {
		return leerTodos().stream().filter(l -> l.getPrecio() >= minimo && l.getPrecio() <= maximo)
				.sorted(Comparator.comparingDouble(l -> l.getPrecio())).collect(Collectors.toList());
	}

	@Override
	public List<Libro> buscarPorStockMinimo(int stock) {
		return leerTodos().stream().filter(l -> l.getStock() >= stock)
				.sorted(Comparator.comparingInt(l -> l.getStock())).collect(Collectors.toList());
	}

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

	@Override
	public void copiarA(LibroRepository destino) {
		List<Libro> todos = leerTodos();
		todos.forEach(l -> destino.insertar(l));
		System.out.println("Copia realizada con éxito.");
	}

}