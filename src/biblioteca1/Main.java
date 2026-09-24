package biblioteca1;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        LibroRepository repoActivo = seleccionarRepositorio(sc);

        if (repoActivo != null) {
            menuPrincipal(sc, repoActivo);
        }

        sc.close();
    }

    private static LibroRepository seleccionarRepositorio(Scanner sc) {
        System.out.println("=== BIENVENIDO AL SISTEMA DE BIBLIOTECA ===");
        System.out.println("Selecciona el tipo de repositorio con el que deseas trabajar:");
        System.out.println("1. Archivo de texto (.txt)");
        System.out.println("2. Base de datos MySQL");
        System.out.print("Opción: ");

        String opcion = sc.nextLine();

        if (opcion.equals("1")) {
            System.out.println("\n-> Trabando con Repositorio en Archivo.");
            return new LibroRepositoryArchivo();
        } else if (opcion.equals("2")) {
            System.out.println("\n-> Trabajando con Repositorio MySQL.");
            return new LibroRepositoryMySQL();
        } else {
            System.out.println("\nOpción no válida. Se usará el archivo por defecto.");
            return new LibroRepositoryArchivo();
        }
    }

    public static void menuPrincipal(Scanner sc, LibroRepository repo) {
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Mostrar todos los libros");
            System.out.println("2. Buscar libro por título");
            System.out.println("3. Buscar libros por autor");
            System.out.println("4. Buscar libros por rango de precios");
            System.out.println("5. Buscar libros por cantidad mínima en stock");
            System.out.println("6. Insertar nuevo libro");
            System.out.println("7. Eliminar libro por título");
            System.out.println("8. Hacer copia al otro repositorio");
            System.out.println("0. Salir");
            System.out.print("\nElige una opción (0-8): ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("\n--- LISTADO DE LIBROS ---");
                    List<Libro> todos = repo.obtenerTodos();
                    if (todos.isEmpty()) {
                        System.out.println("No hay libros disponibles.");
                    } else {
                        todos.forEach(l -> System.out.println(l)); // Muestra cada libro[cite: 15]
                    }
                    break;

                case "2":
                    System.out.print("Introduce el título o fragmento a buscar: ");
                    String t = sc.nextLine();
                    List<Libro> porTitulo = repo.buscarPorTitulo(t);
                    if (porTitulo.isEmpty()) {
                        System.out.println("No se encontraron libros con ese título.");
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
                        System.out.print("Introduce el precio mínimo: ");
                        double min = Double.parseDouble(sc.nextLine());
                        System.out.print("Introduce el precio máximo: ");
                        double max = Double.parseDouble(sc.nextLine());
                        List<Libro> porRango = repo.buscarPorRangoPrecio(min, max);
                        if (porRango.isEmpty()) {
                            System.out.println("No hay libros dentro de ese rango.");
                        } else {
                            porRango.forEach(l -> System.out.println(l));
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debes introducir un número válido.");
                    }
                    break;

                case "5":
                    try {
                        System.out.print("Introduce la cantidad mínima de stock: ");
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
                        System.out.print("ID (número entero): ");
                        int id = Integer.parseInt(sc.nextLine());
                        System.out.print("Título: ");
                        String titulo = sc.nextLine();
                        System.out.print("Autor: ");
                        String autor = sc.nextLine();
                        System.out.print("Precio: ");
                        double precio = Double.parseDouble(sc.nextLine());
                        System.out.print("Stock: ");
                        int stock = Integer.parseInt(sc.nextLine());

                        Libro nuevoLibro = new Libro(id, titulo, autor, precio, stock);
                        repo.insertar(nuevoLibro);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Asegúrate de introducir números válidos en ID, precio y stock.");
                    }
                    break;

                case "7":
                    System.out.print("Introduce el título del libro a eliminar: ");
                    String tituloEliminar = sc.nextLine();
                    repo.eliminarPorTitulo(tituloEliminar);
                    break;

                case "8":
                    // Si el activo es Archivo, la copia va hacia MySQL y viceversa[cite: 9, 16]
                    if (repo instanceof LibroRepositoryArchivo) {
                        System.out.println("Copiando todos los datos del Archivo a la base de datos MySQL...");
                        LibroRepository destino = new LibroRepositoryMySQL();
                        repo.copiarA(destino);
                    } else {
                        System.out.println("Copiando todos los datos de la base de datos MySQL al Archivo...");
                        LibroRepository destino = new LibroRepositoryArchivo();
                        repo.copiarA(destino);
                    }
                    break;

                case "0":
                    System.out.println("Saliendo de la aplicación...");
                    salir = true;
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, elige un número del 0 al 8.");
                    break;
            }
        }
    }

}