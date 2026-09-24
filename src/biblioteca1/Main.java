package biblioteca1;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LibroRepository repo = new LibroRepositoryArchivo();

		menuPrincipal(sc, repo);

		sc.close();
	}

	public static void menuPrincipal(Scanner sc, LibroRepository repo) {

		boolean salir = false;

		while (!salir) {
			System.out.println("\n--- SISTEMA DE GESTIÓN DE BIBLIOTECA ---");
			System.out.println("Elije una de las siguientes opciones:\n");
			System.out.println(
					"1. Mostrar todos los libros: mostrará por pantalla todos los libros disponibles en el sistema.");
			System.out.println("2. Buscar libro por título: permite buscar un libro específico por su título.");
			System.out.println("3. Buscar libros por autor: permite buscar libros de un autor específico.");
			System.out.println(
					"4. Buscar libros por rango de precios: permite buscar libros dentro de un rango de precios indicado por el usuario.");
			System.out.println(
					"5. Buscar libros por cantidad mínima en stock: permite buscar libros con stock igual o mayor al especificado.");
			System.out.println(
					"6. Insertar nuevo libro: el usuario proporcionará id, título, autor, precio y stock del nuevo libro.");
			System.out.println(
					"7. Eliminar libro por título: elimina un libro por su título. Si hay varios con el mismo título, el usuario elige por id.");
			System.out.println(
					"8. Hacer copia: copia todos los datos del repositorio activo al otro (de archivo a MySQL o viceversa).");
			System.out.println("0. Salir.");
			System.out.print("\nOpción: ");

			String opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				System.out.println("\n--- LISTADO DE LIBROS ---");
				List<Libro> todos = repo.obtenerTodos();
				if (todos.isEmpty()) {
					System.out.println("No hay libros registrados.");
				} else {
					todos.forEach(l -> System.out.println(l)); // forEach con lambda[cite: 15, 16]
				}
				break;

			case "2":
				System.out.print("Introduce el título o fragmento a buscar: ");
				String t = sc.nextLine();
				List<Libro> porTitulo = repo.buscarPorTitulo(t);
				if (porTitulo.isEmpty()) {
					System.out.println("No se encontraron libros.");
				} else {
					porTitulo.forEach(l -> System.out.println(l));
				}
				break;

			case "3":
				System.out.print("Introduce el autor a buscar: ");
				String a = sc.nextLine();
				List<Libro> porAutor = repo.buscarPorAutor(a);
				if (porAutor.isEmpty()) {
					System.out.println("No se encontraron libros de ese autor.");
				} else {
					porAutor.forEach(l -> System.out.println(l));
				}
				break;

			case "4":
				try {
					System.out.print("Introduce precio mínimo: ");
					double min = Double.parseDouble(sc.nextLine());
					System.out.print("Introduce precio máximo: ");
					double max = Double.parseDouble(sc.nextLine());
					List<Libro> porRango = repo.buscarPorRangoPrecio(min, max);
					if (porRango.isEmpty()) {
						System.out.println("No hay libros en ese rango de precios.");
					} else {
						porRango.forEach(l -> System.out.println(l));
					}
				} catch (NumberFormatException e) {
					System.out.println("Error: Debes introducir un número válido.");
				}
				break;

			case "5":
				try {
					System.out.print("Introduce el stock mínimo: ");
					int minStock = Integer.parseInt(sc.nextLine());
					List<Libro> porStock = repo.buscarPorStockMinimo(minStock);
					if (porStock.isEmpty()) {
						System.out.println("No hay libros con ese stock mínimo.");
					} else {
						porStock.forEach(l -> System.out.println(l));
					}
				} catch (NumberFormatException e) {
					System.out.println("Error: Debes introducir un número entero.");
				}
				break;

			case "6":
				try {
					System.out.print("ID: ");
					int id = Integer.parseInt(sc.nextLine());
					System.out.print("Título: ");
					String titulo = sc.nextLine();
					System.out.print("Autor: ");
					String autor = sc.nextLine();
					System.out.print("Precio: ");
					double precio = Double.parseDouble(sc.nextLine());
					System.out.print("Stock: ");
					int stock = Integer.parseInt(sc.nextLine());

					Libro nuevo = new Libro(id, titulo, autor, precio, stock);
					repo.insertar(nuevo);
				} catch (NumberFormatException e) {
					System.out.println("Error en los datos numéricos introducidos.");
				}
				break;

			case "7":
				System.out.print("Introduce el título del libro a eliminar: ");
				String tituloEliminar = sc.nextLine();
				repo.eliminarPorTitulo(tituloEliminar);
				break;

			case "8":
				System.out.println("Copiar datos hacia MySQL...");
				System.out.println(
						"(Se requiere tener implementada la clase LibroRepositoryMySQL para completar este paso).");
				break;

			case "0":
				System.out.println("¡Hasta luego!");
				salir = true;
				break;

			default:
				System.out.println("\nOpción no válida. Por favor, elija una opción del 0 al 8.");
				break;
			}
		}
	}

}