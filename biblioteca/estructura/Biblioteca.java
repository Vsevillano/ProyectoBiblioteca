package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.PublicacionNoExisteException;
import biblioteca.excepciones.PublicacionNoPrestadaException;
import biblioteca.excepciones.PublicacionYaPrestadaException;
import biblioteca.excepciones.TituloNoValidoException;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Biblioteca implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Estructura para almacenar publicaciones
	 */
	private ArrayList<Publicacion> biblioteca = new ArrayList<Publicacion>();;

	/**
	 * Estructura donde almacenaremos temporalmente ciertas publicaciones
	 */
	private ArrayList<Publicacion> lista;

	/**
	 * Bandera que me indica si el archivo ha sido modificado
	 */
	private boolean modificado = false;

	/**
	 * Devuelve el estado de modificado
	 * 
	 * @return
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Asigna valor a modificado
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
	 * @throws TituloNoValidoException
	 * @throws AutorNovalidoException
	 * @throws EditorialNoValidaException
	 * @throws FechaNoValidaException
	 */
	public void anadirNovela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			EditorialNoValidaException, AutorNovalidoException, TituloNoValidoException, FechaNoValidaException {
		biblioteca.add(new Novela(titulo, autor, editorial, genero, fechaIngreso, fechaPublicacion, numeroPaginas));

	}

	public void anadirNovela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas, int identificador) throws NumeroPaginasNoValidoException,
			EditorialNoValidaException, AutorNovalidoException, TituloNoValidoException, FechaNoValidaException {
		biblioteca.add(new Novela(titulo, autor, editorial, genero, fechaIngreso, fechaPublicacion, numeroPaginas,
				identificador));

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
	 * @throws FechaNoValidaException
	 */
	public void anadirRevista(String titulo, String editorial, Periodo periodo, GeneroRevista genero,
			LocalDate fechaIngreso, LocalDate fechaPublicacion, int numeroPaginas)
			throws NumeroPaginasNoValidoException, TituloNoValidoException, EditorialNoValidaException,
			PeriodoNoValidoException, FechaNoValidaException {
		biblioteca.add(new Revista(titulo, editorial, periodo, genero, fechaIngreso, fechaPublicacion, numeroPaginas));
	}

	public void anadirRevista(String titulo, String editorial, Periodo periodo, GeneroRevista genero,
			LocalDate fechaIngreso, LocalDate fechaPublicacion, int numeroPaginas, int identificador)
			throws NumeroPaginasNoValidoException, TituloNoValidoException, EditorialNoValidaException,
			PeriodoNoValidoException, FechaNoValidaException {
		biblioteca.add(new Revista(titulo, editorial, periodo, genero, fechaIngreso, fechaPublicacion, numeroPaginas,
				identificador));
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
	 * @throws FechaNoValidaException
	 */
	public void annadirPeriodico(String titulo, GeneroPeriodico genero, Periodo periodo, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			TituloNoValidoException, PeriodoNoValidoException, FechaNoValidaException {
		biblioteca.add(new Periodico(titulo, genero, periodo, fechaIngreso, fechaPublicacion, numeroPaginas));
	}

	public void annadirPeriodico(String titulo, GeneroPeriodico genero, Periodo periodo, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas, int identificador) throws NumeroPaginasNoValidoException,
			TituloNoValidoException, PeriodoNoValidoException, FechaNoValidaException {
		biblioteca.add(
				new Periodico(titulo, genero, periodo, fechaIngreso, fechaPublicacion, numeroPaginas, identificador));
	}

	/**
	 * Añade un libro de texto a la bilioteca
	 * 
	 * @param titulo
	 * @param editorial
	 * @param isbn
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @param materia
	 * @throws ISBNNoValidoException
	 * @throws FechaNoValidaException
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EditorialNoValidaException
	 */
	public void annadirLibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas, Materia materia)
			throws ISBNNoValidoException, NumeroPaginasNoValidoException, TituloNoValidoException,
			EditorialNoValidaException, FechaNoValidaException {
		biblioteca.add(new LibroTexto(titulo, editorial, isbn, fechaIngreso, fechaPublicacion, numeroPaginas, materia));
	}

	public void annadirLibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas, Materia materia, int identificador)
			throws ISBNNoValidoException, NumeroPaginasNoValidoException, TituloNoValidoException,
			EditorialNoValidaException, FechaNoValidaException {
		biblioteca.add(new LibroTexto(titulo, editorial, isbn, fechaIngreso, fechaPublicacion, numeroPaginas, materia,
				identificador));
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
	
	public ListIterator<Publicacion> listarBiblioteca() {
		return biblioteca.listIterator();
	}

	/**
	 * Lista las novelas de la biblioteca
	 * 
	 * @return un array de Novelas
	 */
	public ListIterator<Publicacion> listarNovelas() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Novela)
				lista.add((Novela) publicacion);
		}
		return lista.listIterator();
	}

	/**
	 * Lista los periodicos de la biblioteca
	 * 
	 * @return arrayList de periodicos
	 */
	public ListIterator<Publicacion> listarPeriodicos() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Periodico)
				lista.add((Periodico) publicacion);
		}
		return lista.listIterator();
	}

	/**
	 * Lista las revistas de la biblioteca
	 * 
	 * @return un arrayList de revistas
	 */
	public ListIterator<Publicacion> listarRevistas() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof Revista)
				lista.add((Revista) publicacion);
		}
		return lista.listIterator();
	}

	/**
	 * Lista los libros de texto en la biblioteca
	 * 
	 * @return un arrayList de libros de texto
	 */
	public ListIterator<Publicacion> listarLibrosTexto() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof LibroTexto)
				lista.add((LibroTexto) publicacion);
		}
		return lista.listIterator();
	}

	/**
	 * Lista los libros prestados
	 * 
	 * @return
	 */
	public ListIterator<Publicacion> listarPrestados() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.isPrestado() == true)
				lista.add(publicacion);
		}
		Collections.sort(lista);
		return lista.listIterator();
	}

	/**
	 * Lista los libros a devolver a fecha de hoy
	 * 
	 * @return
	 */
	public ListIterator<Publicacion> listarADevolverHoy() {
		lista = new ArrayList<Publicacion>();
		LocalDate hoy = LocalDate.now();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getFechaDevolucion() != null && publicacion.getFechaDevolucion().equals(hoy)) {
				lista.add(publicacion);
			}
		}
		return lista.listIterator();
	}

	/**
	 * Elimina una publicacion por titulo
	 * @param titulo
	 * @return
	 * @throws PublicacionNoExisteException
	 */
	public void borrarPorTitulo(String titulo) throws PublicacionNoExisteException {
		try {
			for (int i = 0; i < biblioteca.size(); i++) {
				if (biblioteca.get(i).getTitulo().equals(titulo))
					biblioteca.remove(biblioteca.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			throw new PublicacionNoExisteException("La publicacion no existe.");
		}

	}

	/**
	 * Elimina por identificador una publicacion
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
	public ListIterator<Publicacion> buscarPorTitulo(String titulo) throws PublicacionNoExisteException {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getTitulo().equals(titulo))
				lista.add(publicacion);
		}
		if (!lista.isEmpty())
			return lista.listIterator();
		else
			throw new PublicacionNoExisteException("La publicacion no existe.");

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
	 * @throws PublicacionYaPrestadaException
	 * @throws FechaNoValidaException
	 */
	public void prestarPublicacion(int id) throws PublicacionYaPrestadaException {
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getIdentificador() == id && publicacion.isPrestado() == false) {
				publicacion.setPrestado(true);
				if (publicacion instanceof Novela) {
					publicacion.calcularFechaDevolucion(((Novela) publicacion).calcularTiempoPrestado());
				} else if (publicacion instanceof Revista) {
					publicacion.calcularFechaDevolucion(((Revista) publicacion).calcularTiempoPrestado());
				} else if (publicacion instanceof Periodico) {
					publicacion.calcularFechaDevolucion(((Periodico) publicacion).calcularTiempoPrestado());
				} else if (publicacion instanceof LibroTexto) {
					publicacion.calcularFechaDevolucion(((LibroTexto) publicacion).calcularTiempoPrestado());
				}
			} else if (publicacion.getIdentificador() == id && publicacion.isPrestado() == true) {
				throw new PublicacionYaPrestadaException("El libro ya se encuentra prestado");
			}

		}
	}

	/**
	 * Devuelve una publicacion prestada
	 * 
	 * @param id
	 * @return
	 * @throws PublicacionYaPrestadaException
	 * @throws PublicacionNoPrestadaException
	 * @throws FechaNoValidaException
	 */
	public void devolverPublicacion(int id) throws PublicacionNoPrestadaException {
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getIdentificador() == id && publicacion.isPrestado() == true) {
				publicacion.setPrestado(false);
				publicacion.setFechaDevolucion(null);
			} else if (publicacion.getIdentificador() == id && publicacion.isPrestado() == false) {
				throw new PublicacionNoPrestadaException("La publicacion no esta prestada");
			}

		}
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

	public int indexOf(Publicacion publicacion) {
		return biblioteca.indexOf(publicacion);
	}

}
