package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

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
public class Periodico extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Genero del periodico
	 */
	private GeneroPeriodico genero;
	/**
	 * Periodo del periodico
	 */
	private Periodo periodo;

	/**
	 * Constructor del periodico
	 * 
	 * @param titulo
	 * @param genero
	 * @param periodo
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws FechaNoValidaException
	 * @throws PeriodoNoValidoException
	 */
	public Periodico(String titulo, GeneroPeriodico genero, Periodo periodo, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			TituloNoValidoException, PeriodoNoValidoException, FechaNoValidaException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setGenero(genero);
		setPeriodo(periodo);
	}

	/**
	 * Obtiene el Genero del periodico
	 * 
	 * @return
	 */
	public GeneroPeriodico getGenero() {
		return genero;
	}

	/**
	 * Asigna el genero del periodico
	 * 
	 * @param genero
	 */
	private void setGenero(GeneroPeriodico genero) {
		this.genero = genero;
	}

	/**
	 * Obtiene el periodo
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
	 *             si el periodo es null
	 */
	private void setPeriodo(Periodo periodo) throws PeriodoNoValidoException {
		if (periodo != null)
			this.periodo = periodo;
		else
			throw new PeriodoNoValidoException("El periodo no es válido");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Evaluable#calcularTiempoPrestado()
	 */
	@Override
	public double calcularTiempoPrestado() {
		LocalDate hoy = LocalDate.now();
		switch (getPeriodo()) {
		case DIARIO:
			return 30 + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);
		case SEMANAL:
			return 40 + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);
		case MENSUAL:
			return 50 + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);
		default:
			return 60 + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Publicacion#toString()
	 */
	@Override
	public String toString() {
		return "Periodico [genero=" + getGenero() + ", periodo=" + getPeriodo() + ", toString()=" + super.toString()
				+ "]\n";
	}

}
