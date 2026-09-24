package biblioteca1;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		menuPrincipal(sc);
		
		
		sc.close();
	}



	public static void menuPrincipal(Scanner sc){
		
		String k="";
		System.out.println("Elije una de las siguientes opciones:\n");
		System.out.println("1. Mostrar todos los libros: mostrará por pantalla todos los libros disponibles en el sistema.");
		System.out.println("2. Buscar libro por título: permite buscar un libro específico por su título.");			
		System.out.println("3. Buscar libros por autor: permite buscar libros de un autor específico.");
		System.out.println("4. Buscar libros por rango de precios: permite buscar libros dentro de un rango de precios indicado por el usuario.");
		System.out.println("5. Buscar libros por cantidad mínima en stock: permite buscar libros con stock igual o mayor al especificado.");
		System.out.println("6. Insertar nuevo libro: el usuario proporcionará id, título, autor, precio y stock del nuevo libro.");
		System.out.println("7. Eliminar libro por título: elimina un libro por su título. Si hay varios con el mismo título, el usuario elige por id.");
		System.out.println("8. Hacer copia: copia todos los datos del repositorio activo al otro (de archivo a MySQL o viceversa).");
		System.out.println("0. Salir.");
		k=sc.nextLine();
		
		if (k.equals("1")){

		}else if(k.equals("2")){

		}else if(k.equals("3")){
			
		}else if(k.equals("4")){
			
		}else if(k.equals("5")){
			
		}else if(k.equals("6")){
			
		}else if(k.equals("6")){
			
		}else if(k.equals("7")){
			
		}else if(k.equals("8")){
			
		}else if(k.equals("9")){
			
		}else if(k.equals("0")){

		}else{
			System.out.println("\n\nOpción no válida. Por favor, elija una opción del 0 al 8.");
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			menuPrincipal(sc);
		}
		

			
	}
}
