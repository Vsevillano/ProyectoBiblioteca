package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.PublicacionNoExisteException;
import biblioteca.excepciones.PublicacionYaExisteException;
import biblioteca.excepciones.TituloNoValidoException;

public class Biblioteca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	private ArrayList<Publicacion> biblioteca  = new ArrayList<Publicacion>();;
	
	private static ArrayList<Publicacion> lista = new ArrayList<Publicacion>();
	
	//private Publicacion publicacion;


	/**
	 * Bandera que me indica si el archivo ha sido modificado
	 */
	private boolean modificado = false;

	/**
	 * 
	 * @return
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * 
	 * @param modificado
	 */
	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}

	/**
	 * 
	 * @param titulo
	 * @param autor
	 * @param editorial
	 * @param genero
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @return
	 * @throws CampoVacioException
	 * @throws NumeroPaginasNoValidoException
	 * @throws PublicacionYaExisteException 
	 * @throws TituloNoValidoException 
	 * @throws AutorNovalidoException 
	 * @throws EditorialNoValidaException 
	 */
	public void anadirNovela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws FechaNoValidaException,NumeroPaginasNoValidoException, EditorialNoValidaException, AutorNovalidoException, TituloNoValidoException {
			biblioteca.add(new Novela(titulo, autor, editorial, genero, fechaIngreso, fechaPublicacion, numeroPaginas));

	}
	/**
	 * 
	 * @param titulo
	 * @param editorial
	 * @param periodo
	 * @param genero
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @return
	 * @throws CampoVacioException
	 * @throws NumeroPaginasNoValidoException
	 * @throws EditorialNoValidaException 
	 * @throws TituloNoValidoException 
	 * @throws PeriodoNoValidoException 
	 */
	public void anadirRevista(String titulo, String editorial, Periodo periodo, GeneroRevista genero,
			LocalDate fechaIngreso, LocalDate fechaPublicacion, int numeroPaginas)
			throws FechaNoValidaException, NumeroPaginasNoValidoException, TituloNoValidoException, EditorialNoValidaException, PeriodoNoValidoException {
		biblioteca.add(new Revista(titulo, editorial, periodo, genero, fechaIngreso, fechaPublicacion, numeroPaginas));
	}

	/**
	 * 
	 * @param titulo
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @param genero
	 * @param periodo
	 * @return
	 * @throws CampoVacioException
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws PeriodoNoValidoException
	 */
	public void annadirPeriodico(String titulo, GeneroPeriodico genero, Periodo periodo, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws FechaNoValidaException,NumeroPaginasNoValidoException, TituloNoValidoException, PeriodoNoValidoException {
		biblioteca.add(new Periodico(titulo, genero, periodo, fechaIngreso, fechaPublicacion, numeroPaginas));
	}

	/**
	 * Añade un libro de texto a nuestra biblioteca
	 * 
	 * @param titulo
	 * @param editorial
	 * @param isbn
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @param materia
	 * @return
	 * @throws ISBNNoValidoException
	 * @throws CampoVacioException
	 * @throws NumeroPaginasNoValidoException
	 * @throws EditorialNoValidaException 
	 * @throws TituloNoValidoException 
	 */
	public void annadirLibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas, Materia materia)
			throws ISBNNoValidoException, FechaNoValidaException, NumeroPaginasNoValidoException, TituloNoValidoException, EditorialNoValidaException {
		 biblioteca.add(new LibroTexto(titulo, editorial, isbn, fechaIngreso, fechaPublicacion, numeroPaginas, materia));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Biblioteca [biblioteca=" + biblioteca + "]\n";
	}

	/**
	 * Lista las novelas de la biblioteca
	 * 
	 * @return un array de Novelas
	 */
	public ArrayList<Publicacion> listarNovelas() {
		lista.clear();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Novela)
				lista.add((Novela) publicacion);
		}
		return lista;
	}

	/**
	 * Lista los periodicos de la biblioteca
	 * 
	 * @return arrayList de periodicos
	 */
	public ArrayList<Publicacion> listarPeriodicos() {
		lista.clear();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Periodico)
				lista.add((Periodico) publicacion);
		}
		return lista;
	}

	/**
	 * Lista las revistas de la biblioteca
	 * 
	 * @return un arrayList de revistas
	 */
	public ArrayList<Publicacion> listarRevistas() {
		lista.clear();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Revista)
				lista.add((Revista) publicacion);
		}
		return lista;
	}

	/**
	 * Lista los libros de texto en la biblioteca
	 * 
	 * @return un arrayList de libros de texto
	 */
	public ArrayList<Publicacion> listarLibrosTexto() {
		lista.clear();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof LibroTexto)
				lista.add((LibroTexto) publicacion);
		}
		return lista;
	}

	public ArrayList<Publicacion> listarPrestados() {
		lista.clear();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.isPrestado() == true)
				lista.add(publicacion);
		}
		return lista;
	}

	public ArrayList<Publicacion> listarADevolverHoy() {
		lista.clear();
		LocalDate hoy = LocalDate.now();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getFechaDevolucion() != null && publicacion.getFechaDevolucion().equals(hoy)) {
				lista.add(publicacion);
			}
		}
		return lista;
	}

	/**
	 * @throws PublicacionNoExisteException
	 * 
	 */
	void eliminarIndice(int indice) throws PublicacionNoExisteException {
		try {
			biblioteca.remove(indice - 1);
		} catch (IndexOutOfBoundsException e) {
			throw new PublicacionNoExisteException("La publicacion no existe");
		}
	}

	/**
	 * 
	 * @param ident
	 * @throws PublicacionNoExisteException
	 */
	public void eliminarIdent(int ident) throws PublicacionNoExisteException {
		try {
			biblioteca.remove(ident);
		} catch (IndexOutOfBoundsException e) {
			throw new PublicacionNoExisteException("La publicacion no existe");
		}

	}

	/**
	 * Busca una publicacion por titulo
	 * 
	 * @param titulo
	 * @return
	 * @throws PublicacionNoExisteException
	 */
	ArrayList<Publicacion> buscarPorTitulo(String titulo) throws PublicacionNoExisteException {
		lista.clear();
		try {
			for (Publicacion publicacion : biblioteca) {
				if (publicacion.getTitulo().equals(titulo))
					lista.add(publicacion);
			}
			if (!lista.isEmpty())
				return lista;
				else
					throw new PublicacionNoExisteException("La publicacion no existe.");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new PublicacionNoExisteException("La publicacion no existe.");
		}

	}

	/**
	 * Busca una publicacion por identificador
	 * 
	 * @param identificador
	 * @return
	 * @throws PublicacionNoExisteException
	 */
	public Publicacion buscarPorID(int identificador) throws PublicacionNoExisteException {
		try {
			Publicacion publi = null;
			for (Publicacion publicacion : biblioteca) {
				if (publicacion.getIdentificador() == identificador)
					publi = publicacion;
			}
			return publi;
		} catch (NullPointerException e) {
			throw new PublicacionNoExisteException("La publicacion no existe.");
		}

	}

	/**
	 * Presta una publicacion
	 * 
	 * @param id
	 * @return
	 * @throws FechaNoValidaException 
	 */
	boolean prestarPublicacion(int id) throws FechaNoValidaException {
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getIdentificador() == id && publicacion.isPrestado() == false) {
				publicacion.setPrestado(true);
				if (publicacion instanceof Novela) {
					publicacion.calcularFechaDevolucion(((Novela) publicacion).calcularTiempoPrestado());
					return true;
				} else if (publicacion instanceof Revista) {
					publicacion.calcularFechaDevolucion(((Revista) publicacion).calcularTiempoPrestado());
					return true;
				} else if (publicacion instanceof Periodico) {
					publicacion.calcularFechaDevolucion(((Periodico) publicacion).calcularTiempoPrestado());
					return true;

				} else if (publicacion instanceof LibroTexto) {
					publicacion.calcularFechaDevolucion(((LibroTexto) publicacion).calcularTiempoPrestado());
					return true;
				} else {
					return false;
				}
			}

		}
		return false;
	}

	/**
	 * Devuelve una publicacion prestada
	 * 
	 * @param id
	 * @return
	 * @throws FechaNoValidaException 
	 */
	boolean devolverPublicacion(int id) throws FechaNoValidaException {
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getIdentificador() == id && publicacion.isPrestado() == true) {
				publicacion.setPrestado(false);
				publicacion.setFechaDevolucion(null);
				return true;
			}

		}
		return false;
	}

	/**
	 * Numero de eleementos
	 * 
	 * @return
	 */
	public int size() {
		return biblioteca.size();
	}

	/**
	 * Obtiene el elemento de i
	 * 
	 * @param i
	 * @return
	 */
	public Publicacion get(int i) {
		return biblioteca.get(i);
	}


}
