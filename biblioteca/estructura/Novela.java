package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Novela extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Autor de la novela
	 */
	private String autor;
	/**
	 * Editorial de la novela
	 */
	private String editorial;
	/**
	 * Genero de la novela
	 */
	private GeneroNovela genero;

	/**
	 * Constructor de la novela
	 * 
	 * @param titulo
	 * @param autor
	 * @param editorial
	 * @param genero
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @throws NumeroPaginasNoValidoException
	 * @throws EditorialNoValidaException
	 * @throws AutorNovalidoException
	 * @throws TituloNoValidoException
	 * @throws FechaNoValidaException
	 */
	public Novela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			EditorialNoValidaException, AutorNovalidoException, TituloNoValidoException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setAutor(autor);
		setEditorial(editorial);
		setGenero(genero);
	}

	/**
	 * Constructor por identificador
	 * 
	 * @param ident
	 */
	public Novela(int ident) {
		super(ident);
	}

	/**
	 * Obtiene el autor
	 * 
	 * @return
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Asigna el autor
	 * 
	 * @param autor
	 * @throws AutorNovalidoException
	 */
	private void setAutor(String autor) throws AutorNovalidoException {
		if (campoVacio(autor))
			this.autor = autor;
		else
			throw new AutorNovalidoException("Autor vacio");
	}

	/**
	 * Obtiene la editorial
	 * 
	 * @return
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Asigna la editorial
	 * 
	 * @param editorial
	 * @throws EditorialNoValidaException
	 */
	private void setEditorial(String editorial) throws EditorialNoValidaException {
		if (campoVacio(editorial))
			this.editorial = editorial;
		else
			throw new EditorialNoValidaException("Editorial vacio");
	}

	/**
	 * Obtiene el genero
	 * 
	 * @return
	 */
	public GeneroNovela getGenero() {
		return genero;
	}

	/**
	 * Asigna el genero
	 * 
	 * @param genero
	 */
	private void setGenero(GeneroNovela genero) {
		this.genero = genero;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Evaluable#calcularTiempoPrestado()
	 */
	@Override
	public double calcularTiempoPrestado() {
		LocalDate hoy = LocalDate.now();
		return (numeroPaginas * 0.1) + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Publicacion#toString()
	 */
	@Override
	public String toString() {
		return "Novela [autor=" + getAutor() + ", editorial=" + getEditorial() + ", genero=" + getGenero()
				+ ", toString()=" + super.toString() + "]\n";
	}

}
