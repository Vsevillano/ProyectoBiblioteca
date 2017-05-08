package biblioteca.estructura;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public enum GeneroRevista {
	INVESTIGACION, INFANTIL, EROTICA, HOGAR, POLITICA, ECONOMIA, SOCIEDAD, DEPORTES, CULTURA, VARIOS;

	/**
	 * Genera las opciones del menu Revista (Solo test)
	 * 
	 * @return
	 */
	public String[] generarOpcionesRevista() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroRevista genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}

	/**
	 * Obtiene el array de valores de GeneroRevista
	 * @return
	 */
	public GeneroRevista[] getValues() {
		return values();
	}
}
