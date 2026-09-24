package biblioteca1;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		LibroRepository repo = new LibroRepositoryArchivo();

		menuPrincipal(sc, repo);

		sc.close();
	}

	public static void menuPrincipal(Scanner sc, LibroRepository repo) {
		
		
	}
}