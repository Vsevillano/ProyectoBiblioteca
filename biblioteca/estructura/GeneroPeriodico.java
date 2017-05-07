package biblioteca.estructura;

public enum GeneroPeriodico {
	POLITICA, 
	ECONOMIA, 
	SOCIEDAD, 
	DEPORTES, 
	CULTURA, 
	VARIOS;
	
	public String[] generarOpcionesRevista() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroPeriodico genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}
	
	public GeneroPeriodico[] getValues() {
		return values();
	}
}
