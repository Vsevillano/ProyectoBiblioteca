package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

public class Novela extends Publicacion implements Evaluable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String autor;
	private String editorial;
	private GeneroNovela genero;

	public Novela(String titulo, String autor, String editorial, GeneroNovela genero, LocalDate fechaIngreso,
			LocalDate fechaPublicacion, int numeroPaginas) throws NumeroPaginasNoValidoException, EditorialNoValidaException, AutorNovalidoException, TituloNoValidoException, FechaNoValidaException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setAutor(autor);
		setEditorial(editorial);
		setGenero(genero);
	}

	public Novela(int ident) {
	}
	
	public String getAutor() {
		return autor;
	}
	

	private void setAutor(String autor) throws AutorNovalidoException {
		if (campoVacio(autor))
			this.autor = autor;
		else
			throw new AutorNovalidoException("Autor vacio");
	}

	public String getEditorial() {
		return editorial;
	}

	private void setEditorial(String editorial) throws EditorialNoValidaException {
		if (campoVacio(editorial))
			this.editorial = editorial;
		else
			throw new EditorialNoValidaException("Editorial vacio");
	}

	public GeneroNovela getGenero() {
		return genero;
	}

	private void setGenero(GeneroNovela genero) {
		this.genero = genero;
	}

	/**
	 * La puntuacion de una publicacion se determinara en funcion de la fecha de
	 * la publicacion y el periodo de esta.
	 */
	@Override
	public double calcularTiempoPrestado() {
		LocalDate hoy = LocalDate.now();
		return (numeroPaginas * 0.1) + (ChronoUnit.YEARS.between(hoy, fechaIngreso) * 0.5);
		 
	}

	@Override
	public String toString() {
		return "Novela [autor=" + getAutor() + ", editorial=" + getEditorial() + ", genero=" + getGenero()
				+ ", toString()=" + super.toString() + "]\n";
	}

}
