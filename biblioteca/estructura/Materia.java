package biblioteca.estructura;

public enum Materia {
	LENGUAJE, 
	MATEMATIAS, 
	FISICA,
	QUIMICA,
	HISTORIA, 
	FILOSOFIA;
	
	public String[] generarOpcionesRevista() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (Materia materia : values()) {
			opcionesMenu[i++] = materia.name();
		}
		return opcionesMenu;
	}
	
	public Materia[] getValues() {
		return values();
	}
}
