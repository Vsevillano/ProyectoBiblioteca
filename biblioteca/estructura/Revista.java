package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Revista extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Editorial de la revista
	 */
	private String editorial;
	/**
	 * Periodo de la revista
	 */
	private Periodo periodo;
	/**
	 * Genero de la revista
	 */
	private GeneroRevista genero;

	/**
	 * Constructor de la revista
	 * 
	 * @param titulo
	 * @param editorial
	 * @param periodo
	 * @param genero
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EditorialNoValidaException
	 * @throws FechaNoValidaException
	 * @throws PeriodoNoValidoException
	 */
	public Revista(String titulo, String editorial, Periodo periodo, GeneroRevista genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			TituloNoValidoException, EditorialNoValidaException, FechaNoValidaException, PeriodoNoValidoException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setEditorial(editorial);
		setPeriodo(periodo);
		setGenero(genero);
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
			throw new EditorialNoValidaException("Editorial vacia");
	}

	/**
	 * Obitene el periodo
	 * 
	 * @return
	 */
	public Periodo getPeriodo() {
		return periodo;
	}

	/**
	 * Asigna el periodo
	 * 
	 * @param periodo
	 * @throws PeriodoNoValidoException
	 *             si e perido es null
	 */
	private void setPeriodo(Periodo periodo) throws PeriodoNoValidoException {
		if (periodo != null)
			this.periodo = periodo;
		else
			throw new PeriodoNoValidoException("El periodo no es válido");
	}

	/**
	 * Obtiene el genero de la revista
	 * 
	 * @return
	 */
	public GeneroRevista getGenero() {
		return genero;
	}

	/**
	 * Asigna el genero
	 * 
	 * @param genero
	 */
	private void setGenero(GeneroRevista genero) {
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
		return (numeroPaginas * 0.7) + (ChronoUnit.YEARS.between(hoy, fechaPublicacion) * 0.5);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Publicacion#toString()
	 */
	@Override
	public String toString() {
		return "Revista [editorial=" + getEditorial() + ", periodo=" + getPeriodo() + ", genero=" + getGenero()
				+ ", toString()=" + super.toString() + "]\n";
	}
}
