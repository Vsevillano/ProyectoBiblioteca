package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

public class Periodico extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GeneroPeriodico genero;
	private Periodo periodo;

	public Periodico(String titulo, GeneroPeriodico genero, Periodo periodo, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException,
			TituloNoValidoException, FechaNoValidaException, PeriodoNoValidoException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setGenero(genero);
		setPeriodo(periodo);
	}

	public GeneroPeriodico getGenero() {
		return genero;
	}

	private void setGenero(GeneroPeriodico genero) {
		this.genero = genero;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	private void setPeriodo(Periodo periodo) throws PeriodoNoValidoException {
		if (periodo != null)
			this.periodo = periodo;
		else
			throw new PeriodoNoValidoException("El periodo no es válido");
	}

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

	@Override
	public String toString() {
		return "Periodico [genero=" + getGenero() + ", periodo=" + getPeriodo() + ", toString()=" + super.toString() + "]\n";
	}

	

}
