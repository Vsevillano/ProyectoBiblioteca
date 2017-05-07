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

public class Revista extends Publicacion implements Evaluable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String editorial;
	private Periodo periodo;
	private GeneroRevista genero;

	public Revista(String titulo, String editorial, Periodo periodo, GeneroRevista genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException, TituloNoValidoException, EditorialNoValidaException, FechaNoValidaException, PeriodoNoValidoException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setEditorial(editorial);
		setPeriodo(periodo);
		setGenero(genero);
	}

	public String getEditorial() {
		return editorial;
	}

	private void setEditorial(String editorial) throws EditorialNoValidaException {
		if (campoVacio(editorial))
			this.editorial = editorial;
		else
			throw new EditorialNoValidaException("Editorial vacia");
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

	public GeneroRevista getGenero() {
		return genero;
	}

	private void setGenero(GeneroRevista genero) {
		this.genero = genero;
	}

	@Override
	public double calcularTiempoPrestado() {
		LocalDate hoy = LocalDate.now();
		return (numeroPaginas * 0.7) + (ChronoUnit.YEARS.between(hoy, fechaPublicacion) * 0.5);
		
	}
	
	/**
	 * Calcula la fecha de devolucion de una publicacion
	 * @throws FechaNoValidaException 
	 */
	private void calcularFechaDevolucion() throws FechaNoValidaException {
		LocalDate hoy = LocalDate.now();

		if (calcularTiempoPrestado() < 20) {
			setFechaDevolucion(hoy.plusMonths(1));
		} else if (calcularTiempoPrestado() >= 20 && calcularTiempoPrestado() < 40) {
			setFechaDevolucion(hoy.plusDays(15));
		} else if (calcularTiempoPrestado() >= 40 && calcularTiempoPrestado() < 60) {
			setFechaDevolucion(hoy.plusDays(7));
		} else if (calcularTiempoPrestado() >= 60 && calcularTiempoPrestado() < 100) {
			setFechaDevolucion(hoy.plusDays(1));
		}
		else {
			setFechaDevolucion(hoy);
		}
	}

	@Override
	public String toString() {
		return "Revista [editorial=" + getEditorial() + ", periodo=" + getPeriodo() + ", genero=" + getGenero()
				+ ", toString()=" + super.toString() + "]\n";
	}
}
