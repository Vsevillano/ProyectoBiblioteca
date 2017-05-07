package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

public class LibroTexto extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String editorial;
	private String isbn;
	private Materia materia;

	private static final Pattern patternISBN = Pattern.compile("^(97(8|9))?\\d{9}(\\d|X)$");

	public LibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso, LocalDate fechaPublicacion,
			int numeroPaginas, Materia materia) throws ISBNNoValidoException, NumeroPaginasNoValidoException,
			TituloNoValidoException, EditorialNoValidaException, FechaNoValidaException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setEditorial(editorial);
		setIsbn(isbn);
		setMateria(materia);
	}

	public String getEditorial() {
		return editorial;
	}

	private void setEditorial(String editorial) throws EditorialNoValidaException {
		if (campoVacio(editorial)) {
			this.editorial = editorial;
		} else
			throw new EditorialNoValidaException("La editorial esta vacia");
	}

	public String getIsbn() {
		return isbn;
	}

	private void setIsbn(String isbn) throws ISBNNoValidoException {
		if (iSBNValido(isbn))
			this.isbn = isbn;
		else
			throw new ISBNNoValidoException("El ISBN no es correcto. Formato: 9781234567890 o 1234567890");
	}

	public Materia getMateria() {
		return materia;
	}

	private void setMateria(Materia materia) {
		this.materia = materia;
	}

	private static boolean iSBNValido(String isbn) {
		return patternISBN.matcher(isbn).matches();
	}

	@Override
	public double calcularTiempoPrestado() {
		return (numeroPaginas * 0.3);

	}

	@Override
	public String toString() {
		return "LibroTexto [editorial=" + getEditorial() + ", isbn=" + getIsbn() + ", materia=" + getMateria()
				+ ", toString()=" + super.toString() + "]\n";
	}

}
