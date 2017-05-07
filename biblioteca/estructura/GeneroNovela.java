package biblioteca.estructura;


public enum GeneroNovela {
	POLICIAL, 
	ROMANTICA, 
	AVENTURA,
	TERROR,
	CIENCIA_FICCION, 
	INVESTIGACION, 
	BIOGRAFICA, 
	INFANTIL, 
	AUTOAYUDA, 
	EROTICA, 
	HOGAR, 
	ENCICLOPEDIA, 
	POLITICA, 
	ECONOMIA, 
	SOCIEDAD, 
	DEPORTES, 
	CULTURA, 
	VARIOS;
	
	public String[] generarOpcionesNovela() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (GeneroNovela genero : values()) {
			opcionesMenu[i++] = genero.name();
		}
		return opcionesMenu;
	}
	
	public GeneroNovela[] getValues() {
		return values();
	}
}


