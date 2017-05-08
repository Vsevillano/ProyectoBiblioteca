package biblioteca.estructura;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public enum GeneroPeriodico {
	POLITICA, ECONOMIA, SOCIEDAD, DEPORTES, CULTURA, VARIOS;

	/**
	 * Genera las opciones del menu Periodico (Solo test)
	 * 
	 * @return
	 */
	public String[] generarOpcionesPeriodico() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroPeriodico genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}

	/**
	 * Devuelve el array de valores de GeneroPeriodico
	 * 
	 * @return
	 */
	public GeneroPeriodico[] getValues() {
		return values();
	}
}
