package biblioteca.estructura;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.PublicacionNoExisteException;
import biblioteca.excepciones.TituloNoValidoException;
import biblioteca.utiles.Menu;
import biblioteca.utiles.Teclado;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class TestBiblioteca {

	/**
	 * Biblioteca
	 */
	private static Biblioteca biblioteca = new Biblioteca();

	/**
	 * Menu general
	 */
	private static Menu menuGeneral = new Menu("*** Menu general ***", new String[] { "Añadir publicacion",
			"Borrar publicacion", "Listar publicaciones", "Buscar publicacion", "Prestamos/Devoluciones" });

	/**
	 * Menu añadir
	 */
	private static Menu menuAnnadir = new Menu("***Añadir publicacion ***",
			new String[] { "Añadir novela", "Añadir revista", "Añadir periodico", "Añadir libro de texto" });

	/**
	 * Menu borrar
	 */
	private static Menu menuBorrar = new Menu("***Borrar publicacion ***",
			new String[] { "Borrar por indice", "Borrar por identificador" });

	/**
	 * Menu Listar
	 */
	private static Menu menuListar = new Menu("***Listar publicacion ***", new String[] { "Listar todo",
			"Listar novelas", "Listar revistas", "Listar perdiodicos", "Listar libros de texto" });

	/**
	 * Menu buscar
	 */
	private static Menu menuBuscar = new Menu("***Buscar publicacion ***",
			new String[] { "Por titulo", "Por identificador" });

	/**
	 * Menu prestar
	 */
	private static Menu menuPrestar = new Menu("***Prestamos/Devoluciones ***",
			new String[] { "Prestar libro", "Devolver libro", "Listar prestados", "Libros a devolver hoy" });

	/**
	 * Menu genero novela
	 */
	private static Menu menuGeneroNovela = new Menu("Elija un Genero", GeneroNovela.AUTOAYUDA.generarOpcionesNovela());

	/**
	 * Menu periodo
	 */
	private static Menu menuPeriodo = new Menu("Elija un Periodo", Periodo.DIARIO.generarOpcionesPeriodo());

	/**
	 * Menu genero revista
	 */
	private static Menu menuGeneroRevista = new Menu("Elija un genero", GeneroRevista.CULTURA.generarOpcionesRevista());

	/**
	 * Menu genero periodico
	 */
	private static Menu menuGeneroPeriodico = new Menu("Elija un genero",
			GeneroPeriodico.CULTURA.generarOpcionesPeriodico());

	/**
	 * Menu figuras
	 */
	private static Menu menuFiguras;

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		cargaRapida();
		int opcion;
		do {
			opcion = menuGeneral.gestionar();
			gestionarOpcionesMenuGeneral(opcion);
		} while (opcion != menuGeneral.SALIR);

	}

	/**
	 * Gestiona las opciones del menu principal
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesMenuGeneral(int opcion) {
		switch (opcion) {
		case 1:
			// Añadir
			do {
				opcion = menuAnnadir.gestionar();
				gestionarOpcionesMenuAnnadir(opcion);
			} while (opcion != menuAnnadir.SALIR);
			break;
		case 2:
			// Borrar
			do {
				opcion = menuBorrar.gestionar();
				gestionarOpcionesMenuBorrar(opcion);
			} while (opcion != menuBorrar.SALIR);
			break;
		case 3:
			// Listar
			do {
				opcion = menuListar.gestionar();
				gestionarOpcionesListar(opcion);
			} while (opcion != menuListar.SALIR);
			break;
		case 4:
			// Buscar
			do {
				opcion = menuBuscar.gestionar();
				gestionarOpcionesBuscar(opcion);
			} while (opcion != menuBuscar.SALIR);
			break;
		case 5:
			// Prestamos/Devoluciones
			do {
				opcion = menuPrestar.gestionar();
				try {
					gestionarOpcionesPrestar(opcion);
				} catch (FechaNoValidaException e) {
					System.err.println(e.getMessage());
				}
			} while (opcion != menuPrestar.SALIR);
			break;
		default:
			System.out.println("Saliendo del programa...");
			break;
		}

	}

	/**
	 * Gestiona las opciones del menu prestar
	 * 
	 * @param opcion
	 * @throws FechaNoValidaException
	 */
	private static void gestionarOpcionesPrestar(int opcion) throws FechaNoValidaException {
		switch (opcion) {
		case 1:
			// Prestar libro
			biblioteca.prestarPublicacion(Teclado.leerEntero("ID de la publicacion:"));
			break;
		case 2:
			// Devolver libro
			biblioteca.devolverPublicacion(Teclado.leerEntero("ID de la publicacion:"));
			break;
		case 3:
			// Listar prestados
			System.out.println(biblioteca.listarPrestados());
			break;
		case 4:
			// Libros a devolver hoy
			System.out.println(biblioteca.listarADevolverHoy());
			break;
		}
	}

	/**
	 * Gestiona las opciones del menu Buscar
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesBuscar(int opcion) {
		switch (opcion) {
		case 1:
			// Buscar por titulo
			try {
				System.out
						.println(biblioteca.buscarPorTitulo(Teclado.leerCadena("Titulo de la publicacion")).toString());
			} catch (PublicacionNoExisteException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 2:
			// Buscar por id
			try {
				System.out.println(biblioteca.buscarPorID(Teclado.leerEntero("Itroduzca el ID:")));
			} catch (PublicacionNoExisteException e) {
				System.err.println(e.getMessage());
			}
			break;
		}

	}

	/**
	 * Gestiona las opciones del menu borrar
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesMenuBorrar(int opcion) {
		switch (opcion) {
		case 1:
			// Borrar por indice
			// Menu para elegir una figura, solo para el test
			menuFiguras = new Menu("** Elige una de las figuras a borrar", generarArrayPublicaciones());
			try {
				biblioteca.eliminarIndice(menuFiguras.gestionar());
			} catch (PublicacionNoExisteException e) {
				System.err.println(e.getMessage());
			}
			break;
		case 2:
			// Borrar por Identificador
			try {
				biblioteca.eliminarIdent(Teclado.leerEntero("Introduzca el ID:"));
			} catch (PublicacionNoExisteException e) {
				System.err.println(e.getMessage());
			}
			break;
		}

	}

	/**
	 * Gestiona las opciones del menu listar
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesListar(int opcion) {
		switch (opcion) {
		case 1:
			// Listar todo
			System.out.println(biblioteca.toString());
			break;
		case 2:
			// Listar novelas
			System.out.println(biblioteca.listarNovelas());
			break;
		case 3:
			// Listar revistas
			System.out.println(biblioteca.listarRevistas());
			break;
		case 4:
			// Listar periodicos
			System.out.println(biblioteca.listarPeriodicos());
			break;
		case 5:
			// Listar libros de texto
			System.out.println(biblioteca.listarLibrosTexto());
			break;
		}
	}

	/**
	 * Gestiona las opciones del menu añadir
	 * 
	 * @param opcion
	 */
	private static void gestionarOpcionesMenuAnnadir(int opcion) {
		switch (opcion) {
		case 1:
			// Añadir novela
			annadirNovela();
			break;
		case 2:
			// Añadir revista
			annadirRevista();
			break;
		case 3:
			// Añadir periodico
			annadirPeriodico();
			break;
		case 4:
			// Añadir libro texto
			annadirLibroTexto();
			break;
		}

	}

	/**
	 * Añade un libro de texto a la biblioteca
	 */
	private static void annadirLibroTexto() {
		String titulo = Teclado.leerCadena("Titulo del libro:");
		String editorial = Teclado.leerCadena("Editorial del libro:");
		String isbn = Teclado.leerCadena("ISBN del libro:");
		try {
			biblioteca.annadirLibroTexto(titulo, editorial, isbn, pedirFecha("Fecha de ingreso:"),
					pedirFecha("Fecha de publicacion:"), Teclado.leerEntero("Numero de paginas:"), pedirMateria());

		} catch (ISBNNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (NumeroPaginasNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (TituloNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (EditorialNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (FechaNoValidaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Pide una fecha por teclado
	 * 
	 * @param mensaje
	 * @return
	 */
	private static LocalDate pedirFecha(String mensaje) {
		LocalDate hoy;
		try {
			int anno = Teclado.leerEntero("Año:"); // año
			int mes = Teclado.leerEntero("Mes:"); // mes [1,...,12]
			int dia = Teclado.leerEntero("Dia:"); // día [1,...,31]

			// if (year < 1900) {
			// throw new IllegalArgumentException("Año inválido.");
			// }

			hoy = LocalDate.of(anno, mes, dia);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			// System.out.println(formatter.format(hoy)); // 01/01/2016
			return hoy;
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Añade un periodico a la biblioteca
	 */
	private static void annadirPeriodico() {
		String titulo = Teclado.leerCadena("Titulo del periodico:");
		try {
			biblioteca.annadirPeriodico(titulo, pedirGeneroPeriodico(), pedirPeriodo(), pedirFecha("Fecha de ingreso:"),
					pedirFecha("Fecha de publicacion:"), Teclado.leerEntero("Numero de paginas:"));
		} catch (NumeroPaginasNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (TituloNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (FechaNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (PeriodoNoValidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añade una revista a la biblioteca
	 */
	private static void annadirRevista() {
		String titulo = Teclado.leerCadena("Titulo de la revista:");
		String editorial = Teclado.leerCadena("Editorial de la revista:");
		try {
			biblioteca.anadirRevista(titulo, editorial, pedirPeriodo(), pedirGeneroRevista(),
					pedirFecha("Fecha de ingreso:"), pedirFecha("Fecha de publicacion:"),
					Teclado.leerEntero("Numero de paginas:"));
		} catch (NumeroPaginasNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (TituloNoValidoException e) {
			System.err.println(e.getMessage());
		} catch (EditorialNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (FechaNoValidaException e) {
			System.err.println(e.getMessage());
		} catch (PeriodoNoValidoException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Añade una novela a la biblioteca
	 */
	private static void annadirNovela() {
		String titulo = Teclado.leerCadena("Titulo de la novela:");
		String autor = Teclado.leerCadena("Autor de la novela:");
		String editorial = Teclado.leerCadena("Editorial de la novela:");
		GeneroNovela genero = pedirGeneroNovela();

		try {
			biblioteca.anadirNovela(titulo, autor, editorial, genero, pedirFecha("Fecha de ingreso:"),
					pedirFecha("Fecha de publicacion:"), Teclado.leerEntero("Numero de paginas:"));
		} catch (NumeroPaginasNoValidoException | EditorialNoValidaException | AutorNovalidoException
				| TituloNoValidoException | FechaNoValidaException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Pide un Genero de una novela
	 * 
	 * @return
	 */
	private static GeneroNovela pedirGeneroNovela() {
		int opcion = menuGeneroNovela.gestionar();
		GeneroNovela[] arrGeneros = GeneroNovela.AUTOAYUDA.getValues();
		if (opcion == arrGeneros.length + 1)
			return null;
		return arrGeneros[opcion - 1];
	}

	/**
	 * Pide un Genero de una revista
	 * 
	 * @return
	 */
	private static GeneroRevista pedirGeneroRevista() {
		int opcion = menuGeneroRevista.gestionar();
		GeneroRevista[] arrGeneros = GeneroRevista.CULTURA.getValues();
		if (opcion == arrGeneros.length + 1)
			return null;
		return arrGeneros[opcion - 1];
	}

	/**
	 * Pide el Periodo de un periodico o una revista
	 * 
	 * @return
	 */
	private static Periodo pedirPeriodo() {
		int opcion = menuPeriodo.gestionar();
		Periodo[] arrPeriodos = Periodo.DIARIO.getValues();
		if (opcion == arrPeriodos.length + 1)
			return null;
		return arrPeriodos[opcion - 1];
	}

	/**
	 * Pide el Genero de un periodico
	 * 
	 * @return
	 */
	private static GeneroPeriodico pedirGeneroPeriodico() {
		int opcion = menuGeneroPeriodico.gestionar();
		GeneroPeriodico[] arrGeneros = GeneroPeriodico.CULTURA.getValues();
		if (opcion == arrGeneros.length + 1)
			return null;
		return arrGeneros[opcion - 1];
	}

	/**
	 * Pide la Materia de un libro de texto
	 * 
	 * @return
	 */
	private static Materia pedirMateria() {
		int opcion = menuGeneroPeriodico.gestionar();
		Materia[] arrMaterias = Materia.FILOSOFIA.getValues();
		if (opcion == arrMaterias.length + 1)
			return null;
		return arrMaterias[opcion - 1];
	}

	private static void cargaRapida() {
		try {
			biblioteca.anadirNovela("Novela1", "Autor1", "Editorial1", GeneroNovela.AUTOAYUDA,
					LocalDate.of(1900, 01, 21), LocalDate.of(2012, 01, 22), 20);
			biblioteca.anadirNovela("Novela2", "Autor2", "Editorial2", GeneroNovela.AUTOAYUDA,
					LocalDate.of(2012, 01, 21), LocalDate.of(2012, 01, 22), 200);
			biblioteca.anadirNovela("Novela3", "Autor3", "Editorial3", GeneroNovela.AUTOAYUDA,
					LocalDate.of(1950, 01, 21), LocalDate.of(2012, 01, 22), 100);
			biblioteca.anadirRevista("Revista1", "Editorial1", Periodo.DIARIO, GeneroRevista.CULTURA,
					LocalDate.of(2011, 12, 14), LocalDate.of(2015, 11, 25), 50);
			biblioteca.anadirRevista("Revista2", "Editorial2", Periodo.DIARIO, GeneroRevista.CULTURA,
					LocalDate.of(2000, 12, 14), LocalDate.of(2015, 11, 25), 70);
			biblioteca.anadirRevista("Revista3", "Editorial3", Periodo.DIARIO, GeneroRevista.CULTURA,
					LocalDate.of(1950, 12, 14), LocalDate.of(2015, 11, 25), 1000);
			biblioteca.annadirLibroTexto("LibroText1", "Edit1", "1234567890", LocalDate.of(1999, 12, 22),
					LocalDate.of(2015, 12, 12), 500, Materia.FILOSOFIA);
			biblioteca.annadirLibroTexto("LibroText2", "Edit2", "1234567890", LocalDate.of(1999, 12, 22),
					LocalDate.of(2000, 12, 12), 50, Materia.FILOSOFIA);
			biblioteca.annadirLibroTexto("LibroText3", "Edit3", "1234567890", LocalDate.of(1999, 12, 22),
					LocalDate.of(1995, 12, 12), 5, Materia.FILOSOFIA);
			biblioteca.annadirPeriodico("Periodico1", GeneroPeriodico.CULTURA, Periodo.DIARIO,
					LocalDate.of(2017, 12, 11), LocalDate.of(1999, 02, 25), 30);
			biblioteca.annadirPeriodico("Periodico2", GeneroPeriodico.CULTURA, Periodo.DIARIO,
					LocalDate.of(1920, 12, 11), LocalDate.of(1999, 02, 25), 3000);
			biblioteca.annadirPeriodico("Periodico3", GeneroPeriodico.CULTURA, Periodo.DIARIO,
					LocalDate.of(1999, 12, 11), LocalDate.of(1999, 02, 25), 300);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Genera las opciones para elegir por indice una publicacion
	 * 
	 * @return
	 */
	private static String[] generarArrayPublicaciones() {
		String[] opcionesMenu = new String[biblioteca.size()];
		for (int i = 0; i < biblioteca.size(); i++) {
			opcionesMenu[i] = biblioteca.get(i).toString();
		}
		return opcionesMenu;
	}
}
