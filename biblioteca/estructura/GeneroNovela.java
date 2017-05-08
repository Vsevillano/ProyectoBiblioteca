package biblioteca.estructura;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public enum GeneroNovela {
	POLICIAL, ROMANTICA, AVENTURA, TERROR, CIENCIA_FICCION, INVESTIGACION, BIOGRAFICA, INFANTIL, AUTOAYUDA, EROTICA, HOGAR, ENCICLOPEDIA, POLITICA, ECONOMIA, SOCIEDAD, DEPORTES, CULTURA, VARIOS;

	/**
	 * Genera las opciones del menu Novela (Solo test)
	 * 
	 * @return
	 */
	public String[] generarOpcionesNovela() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroNovela genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}

	/**
	 * Devuelve un array de valores de GeneroNovela
	 * 
	 * @return
	 */
	public GeneroNovela[] getValues() {
		return values();
	}
}
