package biblioteca1;

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

	}
}