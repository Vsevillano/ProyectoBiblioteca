package biblioteca.estructura;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

public class Publicacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Identificador unico para cada publicacion
	 */
	private int identificador;
	/**
	 * Titulo que toda publicacion tiene
	 */
	private String titulo;
	/**
	 * Fecha de ingreso en la biblioteca
	 */
	protected LocalDate fechaIngreso;
	/**
	 * Estado del libro, prestado o no prestado
	 */
	private boolean prestado;

	private LocalDate fechaDevolucion;
	/**
	 * Fecha de la publicacion
	 */
	protected LocalDate fechaPublicacion;
	/**
	 * Numero de paginas dela publicacion
	 */
	protected int numeroPaginas;
	/**
	 * Contador estatico que genera el id de cada publicacion
	 */
	private static int contador = 0;
	/**
	 * Patron para comprobar que un campo no está vacio
	 */
	private static final Pattern patternField = Pattern.compile("([´,'\\-a-zA-ZáéíóúñÑ0-9]+\\s?){2,}");

	/**
	 * Patron para las fechas, acepta 11/12/2017
	 */
	private static final Pattern patronFecha = Pattern.compile("^\\d{2}[/]\\d{2}[/]\\d{4}$");

	/**
	 * Constructor de la publicacion
	 * 
	 * @param titulo
	 *            de la publicacion
	 * @param fechaIngreso
	 *            de la publicacion
	 * @param fechaPublicacion
	 *            de la publicacion
	 * @param numeroPaginas
	 *            de la publicacion
	 * @throws NumeroPaginasNoValidoException
	 *             si el numero de paginas es menor a 0.
	 * @throws CampoVacioException
	 *             Si el campo esta vacio o contiene un solo caracter
	 * @throws TituloNoValidoException 
	 * @throws FechaNoValidaException 
	 */
	public Publicacion(String titulo, LocalDate fechaIngreso, LocalDate fechaPublicacion, int numeroPaginas)
			throws NumeroPaginasNoValidoException, TituloNoValidoException, FechaNoValidaException {
		setIdentificador();
		setTitulo(titulo);
		setFechaIngreso(fechaIngreso);
		setPrestado(prestado);
		setFechaDevolucion(fechaDevolucion);
		setFechaPublicacion(fechaPublicacion);
		setNumeroPaginas(numeroPaginas);
	}

	/**
	 * Constructor por identificador
	 * 
	 * @param ident
	 */
	public Publicacion() {
		setIdentificador();
	}

	/**
	 * Constructor por titulo
	 * 
	 * @param titulo
	 */
	public Publicacion(String titulo) {
		this.titulo = titulo;
	}

	public static int getContador() {
		return contador;
	}

	/**
	 * Obitene el identificador unico
	 * 
	 * @return un entero
	 */
	public int getIdentificador() {
		return identificador;
	}

	protected LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	protected void setFechaDevolucion(LocalDate fechaDevolucion){
//		if (fechaValida(fechaDevolucion))
			this.fechaDevolucion = fechaDevolucion;
//		else
//			throw new FechaNoValidaException("La fecha de devolucion no es valida");
	}

	
	/**
	 * Asigna la fecha de ingreso en la biblioteca
	 * 
	 * @param fechaIngreso
	 * @throws FechaNoValidaException 
	 */
	private void setFechaIngreso(LocalDate fechaIngreso)  {
//		if (fechaValida(fechaIngreso))
			this.fechaIngreso = fechaIngreso;
//		else
//			throw new FechaNoValidaException("La fecha de ingreso no es valida");
	}
	

	/**
	 * Asigna la fecha de publicacion
	 * 
	 * @param fechaPublicacion
	 * @throws FechaNoValidaException 
	 */
	private void setFechaPublicacion(LocalDate fechaPublicacion) {
//		if (fechaValida(fechaPublicacion))
			this.fechaPublicacion = fechaPublicacion;
//		else
//			throw new FechaNoValidaException("La fecha de ingreso no es valida");
	}

	/**
	 * Asigna el valor del contador al identificador
	 */
	private void setIdentificador() {
		identificador = contador++;
	}

	/**
	 * Obtiene el titulo de la publicacion
	 * 
	 * @return una cadena
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Asigna el titulo a la publicacion
	 * 
	 * @param titulo
	 *            de la publicacion
	 * @throws CampoVacioException
	 *             Si el campo esta vacio o contiene un solo caracter
	 */
	private void setTitulo(String titulo) throws TituloNoValidoException {
		if (campoVacio(titulo))
			this.titulo = titulo;
		else
			throw new TituloNoValidoException("Titulo vacio");
	}

	/**
	 * Obtiene la fecha de ingreso de la publicacion
	 * 
	 * @return la fecha en el formato dd/mm/aaaa
	 */
	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}


	/**
	 * Obtiene el valor de prestado de una publicacion
	 * 
	 * @return True si lo esta y false si no.
	 */
	boolean isPrestado() {
		return prestado;
	}

	/**
	 * Asigna el valor de prestado a la publicacion
	 * 
	 * @param prestado
	 */
	void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}

	/**
	 * Obtiene la fecha de publicacion
	 * 
	 * @return una fecha
	 */
	public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
	}


	/**
	 * Obtiene el numero de paginas dela publicacion
	 * 
	 * @return
	 */
	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	/**
	 * Asigna el numero de paginas de la pubicacion
	 * 
	 * @param numeroPaginas
	 *            de dicha publicacion
	 * @throws NumeroPaginasNoValidoException
	 *             En caso de ser menor que 0
	 */
	private void setNumeroPaginas(int numeroPaginas) throws NumeroPaginasNoValidoException {
		if (numeroPaginas > 0)
			this.numeroPaginas = numeroPaginas;
		else
			throw new NumeroPaginasNoValidoException("El numero de paginas ha de ser al menos 1.");
	}

	/**
	 * Realiza la comprobacion a traves del patron de busqueda
	 * 
	 * @param campo
	 *            a comprobar
	 * @return True si coincide y false si no
	 */
	protected boolean campoVacio(String campo) {
		return patternField.matcher(campo).matches();
	}

	/**
	 * 
	 * @param fecha
	 * @return
	 */
	protected boolean fechaValida(LocalDate fecha) {
		return patronFecha.matcher(fecha.toString()).matches();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[titulo=" + getTitulo() + "identificador=" + getIdentificador() + ", fechaIngreso=" + getFechaIngreso()
				+ ", prestado=" + isPrestado() + ", fechaPublicacion=" + getFechaPublicacion() + ", NumeroPaginas="
				+ getNumeroPaginas() + ", fechaDevolucion=" + getFechaDevolucion() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identificador;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		Publicacion other = (Publicacion) obj;
		if (identificador != other.identificador)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	/**
	 * Calcula la fecha de devolucion de una publicacion
	 * @throws FechaNoValidaException 
	 */
	protected void calcularFechaDevolucion(double puntuacion) throws FechaNoValidaException {
		LocalDate hoy = LocalDate.now();
		if (puntuacion < 20) {
			setFechaDevolucion(hoy.plusMonths(1));
		} else if (puntuacion >= 20 && puntuacion < 40) {
			setFechaDevolucion(hoy.plusDays(15));
		} else if (puntuacion >= 40 && puntuacion < 60) {
			setFechaDevolucion(hoy.plusDays(7));
		} else if (puntuacion >= 60 && puntuacion < 100) {
			setFechaDevolucion(hoy.plusDays(1));
		} else {
			setFechaDevolucion(hoy);
		}
	}

}
