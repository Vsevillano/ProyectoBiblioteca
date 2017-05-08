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
	 * @return
	 */
	public boolean isModificado() {
		return modificado;
	}

	/**
	 * Asigna valor a modificado
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
	 */
	public void anadirNovela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas)
			throws FechaNoValidaException, NumeroPaginasNoValidoException, EditorialNoValidaException,
			AutorNovalidoException, TituloNoValidoException {
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
			throws FechaNoValidaException, NumeroPaginasNoValidoException, TituloNoValidoException,
			EditorialNoValidaException, PeriodoNoValidoException {
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
			LocalDate fechaPublicacion, int numeroPaginas) throws FechaNoValidaException,
			NumeroPaginasNoValidoException, TituloNoValidoException, PeriodoNoValidoException {
		biblioteca.add(new Periodico(titulo, genero, periodo, fechaIngreso, fechaPublicacion, numeroPaginas));
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
			throws ISBNNoValidoException, FechaNoValidaException, NumeroPaginasNoValidoException,
			TituloNoValidoException, EditorialNoValidaException {
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
		lista = new ArrayList<Publicacion>();
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
		lista = new ArrayList<Publicacion>();
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
		lista = new ArrayList<Publicacion>();
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
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion instanceof LibroTexto)
				lista.add((LibroTexto) publicacion);
		}
		return lista;
	}

	/**
	 * Lista los libros prestados
	 * 
	 * @return
	 */
	public ArrayList<Publicacion> listarPrestados() {
		lista = new ArrayList<Publicacion>();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.isPrestado() == true)
				lista.add(publicacion);
		}
		return lista;
	}

	/**
	 * Lista los libros a devolver a fecha de hoy
	 * 
	 * @return
	 */
	public ArrayList<Publicacion> listarADevolverHoy() {
		lista = new ArrayList<Publicacion>();
		LocalDate hoy = LocalDate.now();
		for (Publicacion publicacion : biblioteca) {
			if (publicacion.getFechaDevolucion() != null && publicacion.getFechaDevolucion().equals(hoy)) {
				lista.add(publicacion);
			}
		}
		return lista;
	}

	/**
	 * Elimina por indice una publicacion
	 * 
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
	 * @throws LibroYaPrestadoException 
	 * @throws FechaNoValidaException
	 */
	public void prestarPublicacion(int id) throws LibroYaPrestadoException {
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
			throw new LibroYaPrestadoException("El libro ya se encuentra prestado");
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
			}
			else if (publicacion.getIdentificador() == id && publicacion.isPrestado() == false) {
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

}
