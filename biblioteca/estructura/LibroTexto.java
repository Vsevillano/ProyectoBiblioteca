package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.regex.Pattern;

import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class LibroTexto extends Publicacion implements Evaluable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Editorial del libro
	 */
	private String editorial;
	/**
	 * ISBN real del libro
	 */
	private String isbn;
	/**
	 * Materia del libro
	 */
	private Materia materia;

	/**
	 * Patron que comprueba el isbn de 10 o 13 digitos (Si es de 13, empieza por
	 * 978 o 979)
	 */
	private static final Pattern patternISBN = Pattern.compile("^(97(8|9))?\\d{9}(\\d|X)$");

	/**
	 * Constructor del libro
	 * 
	 * @param titulo
	 * @param editorial
	 * @param isbn
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @param materia
	 * @throws ISBNNoValidoException
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EditorialNoValidaException
	 * @throws FechaNoValidaException
	 */
	public LibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso, LocalDate fechaPublicacion,
			int numeroPaginas, Materia materia) throws ISBNNoValidoException, NumeroPaginasNoValidoException,
			TituloNoValidoException, EditorialNoValidaException, FechaNoValidaException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas);
		setEditorial(editorial);
		setIsbn(isbn);
		setMateria(materia);
	}
	
	/**
	 * Sobrecarga para el gui
	 * @param titulo
	 * @param editorial
	 * @param isbn
	 * @param fechaIngreso
	 * @param fechaPublicacion
	 * @param numeroPaginas
	 * @param materia
	 * @param identificador
	 * @throws ISBNNoValidoException
	 * @throws NumeroPaginasNoValidoException
	 * @throws TituloNoValidoException
	 * @throws EditorialNoValidaException
	 * @throws FechaNoValidaException
	 */
	public LibroTexto(String titulo, String editorial, String isbn, LocalDate fechaIngreso, LocalDate fechaPublicacion,
			int numeroPaginas, Materia materia, int identificador) throws ISBNNoValidoException, NumeroPaginasNoValidoException,
			TituloNoValidoException, EditorialNoValidaException, FechaNoValidaException {
		super(titulo, fechaIngreso, fechaPublicacion, numeroPaginas, identificador);
		setEditorial(editorial);
		setIsbn(isbn);
		setMateria(materia);
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
		if (campoVacio(editorial)) {
			this.editorial = editorial;
		} else
			throw new EditorialNoValidaException("La editorial esta vacia");
	}

	/**
	 * Obtiene el isbn
	 * 
	 * @return
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * Asigna el isbn
	 * 
	 * @param isbn
	 * @throws ISBNNoValidoException
	 */
	private void setIsbn(String isbn) throws ISBNNoValidoException {
		if (iSBNValido(isbn))
			this.isbn = isbn;
		else
			throw new ISBNNoValidoException("El ISBN no es correcto. Formato: 9781234567890 o 1234567890");
	}

	/**
	 * Obtiene la materia del libro
	 * 
	 * @return
	 */
	public Materia getMateria() {
		return materia;
	}

	/**
	 * Asigna la materia al libro
	 * 
	 * @param materia
	 */
	private void setMateria(Materia materia) {
		this.materia = materia;
	}

	/**
	 * Comprueba la concordancia con el patron del isbn
	 * 
	 * @param isbn
	 * @return
	 */
	private static boolean iSBNValido(String isbn) {
		return patternISBN.matcher(isbn).matches();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Evaluable#calcularTiempoPrestado()
	 */
	@Override
	public double calcularTiempoPrestado() {
		return (numeroPaginas * 0.3);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biblioteca.estructura.Publicacion#toString()
	 */
	@Override
	public String toString() {
		return "LibroTexto [editorial=" + getEditorial() + ", isbn=" + getIsbn() + ", materia=" + getMateria()
				+ ", toString()=" + super.toString() + "]\n";
	}

}
