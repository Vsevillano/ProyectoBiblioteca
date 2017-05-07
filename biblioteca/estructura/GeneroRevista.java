package biblioteca.estructura;

public enum GeneroRevista { 
	INVESTIGACION, 
	INFANTIL, 
	EROTICA, 
	HOGAR, 
	POLITICA, 
	ECONOMIA, 
	SOCIEDAD, 
	DEPORTES, 
	CULTURA, 
	VARIOS;
	
	public String[] generarOpcionesRevista() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroRevista genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}
	
	public GeneroRevista[] getValues() {
		return values();
	}
}
