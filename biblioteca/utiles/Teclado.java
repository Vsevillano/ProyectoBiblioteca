package biblioteca.utiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Permite lectura desde teclado
 */
public class Teclado {

	/**
	 * Lee un carácter del teclado
	 * 
	 * @return carácter introducido por el usuario
	 */
	public static char leerCaracter() {
		char caracter;
		try {
			caracter = leerCadena().charAt(0);
		} catch (Exception e) {
			caracter = 0;
		}
		return caracter;
	}

	/**
	 * Lee un carácter del teclado
	 * 
	 * @param msj
	 *            mensaje mostrado al usuario
	 * @return carácter introducido por el usuario
	 */
	public static char leerCaracter(String msj) {
		System.out.println(msj);
		return leerCaracter();
	}

	/**
	 * Lee una cadena del teclado
	 * 
	 * @param msj
	 *            mensaje mostrado al usuario
	 * @return cadena introducida por el usuario
	 */
	public static String leerCadena(String msj) {
		System.out.println(msj);
		return leerCadena();
	}

	/**
	 * Lee una cadena del teclado
	 * 
	 * @return cadena introducida por el usuario
	 */

	public static String leerCadena() {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		String cadena;
		try {
			cadena = bReader.readLine(); // Lee una línea de texto (hasta intro)
		} catch (Exception e) {
			cadena = "";
		}
		return cadena;
	}

	/**
	 * Lee un entero del teclado
	 * 
	 * 
	 * @return entero introducido por el usuario
	 */
	public static int leerEntero() {
		int x;
		try {
			x = Integer.parseInt(leerCadena().trim()); // Quita los espacios del
														// String y convierte a
														// int
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}

	/**
	 * Lee una entero del teclado
	 * 
	 * @param msj
	 *            mensaje mostrado al usuario
	 * @return entero introducida por el usuario
	 */
	public static int leerEntero(String msj) {
		System.out.println(msj);
		return leerEntero();
	}

	/**
	 * Lee un decimal del teclado
	 * 
	 * @return decimal introducido por el usuario
	 */
	public static double leerDecimal() {
		double x;
		try {
			x = Double.parseDouble(leerCadena().trim()); // Quita los espacios
															// del String y
															// convierte a
															// double
		} catch (Exception e) {
			x = 0;
		}
		return x;
	}

	/**
	 * Lee una decimal del teclado
	 * 
	 * @param msj
	 *            mensaje mostrado al usuario
	 * @return decimal introducida por el usuario
	 */
	public static double leerDecimal(String msj) {
		System.out.println(msj);
		return leerDecimal();
	}

	/**
	 * Lee de teclado hasta que el usuario introduce s o n.
	 * 
	 * @return true si se introduce un sí (da igual la tilde o las mayúsculas).
	 *         false si se introduce un no (da igual las mayúsculas)
	 */
	public static boolean leerSiNo(String mensaje) {
		Character caracter;
		Character.valueOf('a');
		
		do {
			caracter = Character.valueOf(Teclado.leerCaracter(mensaje));
			caracter = Character.toLowerCase(caracter);
		} while (!caracter.equals('s') && !caracter.equals('n'));
		switch (caracter) {
		case 's':
			return true;
		default:// 'n'
			return false;
		}
	}

	/**
	 * Muestra un mensaje y lee de teclado hasta que el usuario introduce "s" o
	 * "n".
	 * 
	 * @param mensaje
	 *            cadena a mostrar
	 * @return true si se introduce una "s" (dan igual las mayúsculas). false si
	 *         se introduce una "n" (dan igual las mayúsculas)
	 * 
	 * 
	 */
	public static boolean siNo(String mensaje) {
		char character;
		System.out.println(mensaje + "(s/n)");
		do {
			character = Character.toUpperCase(Teclado.leerCaracter());
			if (character == 'S')
				return true;
			if (character == 'N')
				return false;
		} while (true);
	}
}
