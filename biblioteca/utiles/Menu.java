package biblioteca.utiles;

/**
 * Clase utilizada para la gestión de un menú. Se dedica a:
 * 
 * <li>Mostrar las opciones del menú
 * 
 * <li>Recoger y devolver las opciones de un menú
 * 
 * @author mlmc
 * 
 */
public class Menu {
	public final int SALIR;
	/**
	 * Título del menú
	 */
	private String titulo;
	/**
	 * Opciones del menú
	 */
	private String opciones[];
	/**
	 * Número de opciones del menú
	 */
	private int numOpciones = 2;

	/**
	 * Crea un menú para comunicarse con el usuario a través del teclado y de la
	 * consola.
	 * 
	 * @param titulo
	 *            título del menú
	 * @param opciones
	 *            opciones del menú
	 */
	public Menu(String titulo, String[] opciones) {
		setTitulo(titulo);
		setOpciones(opciones);
		setNumOpciones();
		SALIR = getNumOpciones();
	}

	private int getNumOpciones() {

		return numOpciones;
	}

	private void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	private void setOpciones(String[] opciones) {
		this.opciones = new String[opciones.length + 1];
		int i = 0;
		for (String string : opciones) {
			this.opciones[i++] = string;
		}
		this.opciones[i] = "Salir";
	}

	private void setNumOpciones() {
		this.numOpciones = opciones.length;
	}

	/**
	 * Gestiona el menú. Consiste en mostrar las opcines y recoger la opción
	 * seleccionada por el usuario
	 * 
	 * @return opción válida del menú
	 */
	public int gestionar() {
		mostrar();
		return recogerOpcion();
	}

	/**
	 * Muestra las opciones del menú
	 */
	private void mostrar() {
		int i = 1;
		System.out.println("\n**" + titulo);
		for (String elemento : opciones)
			System.out.println("(" + (i++) + ") " + elemento);
	}

	/**
	 * Recoge la opción válida del menú, comenzando por 1.
	 * 
	 * @return opción válida
	 */
	private int recogerOpcion() {
		int opcion;
		do {
			opcion = Teclado.leerEntero();
		} while (opcion < 1 || opcion > numOpciones);
		return opcion;
	}
}
