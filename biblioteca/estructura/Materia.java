package biblioteca.estructura;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public enum Materia {
	LENGUAJE, MATEMATIAS, FISICA, QUIMICA, HISTORIA, FILOSOFIA;

	/**
	 * Genera las opciones del meni de Materia (Solo test)
	 * 
	 * @return
	 */
	public String[] generarOpcionesMateria() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (Materia materia : values()) {
			opcionesMenu[i++] = materia.name();
		}
		return opcionesMenu;
	}

	/**
	 * Obtiene el array de valores de Materia
	 * 
	 * @return
	 */
	public Materia[] getValues() {
		return values();
	}
}
