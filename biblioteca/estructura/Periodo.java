package biblioteca.estructura;

public enum Periodo {
	DIARIO, SEMANAL, MENSUAL, ANUAL;
	
	public String[] generarOpcionesPeriodo() {
		String[] opcionesMenu = new String[values().length];
		int i = 0;
		for (Periodo periodo : values()) {
			opcionesMenu[i++] = periodo.name();
		}
		return opcionesMenu;
	}
	
	public Periodo[] getValues() {
		return values();
	}
}
