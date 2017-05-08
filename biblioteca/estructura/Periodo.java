package biblioteca.estructura;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public enum Periodo {
	DIARIO, SEMANAL, MENSUAL, ANUAL;

	/**
	 * Genera las opciones del menu periodo (Solo test)
	 * 
	 * @return
	 */
	public String[] generarOpcionesPeriodo() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (Periodo periodo : values()) {
			opcionesMenu[i++] = periodo.name();
		}
		return opcionesMenu;
	}

	/**
	 * Obtiene el array de valores de Periodo
	 * 
	 * @return
	 */
	public Periodo[] getValues() {
		return values();
	}
}
