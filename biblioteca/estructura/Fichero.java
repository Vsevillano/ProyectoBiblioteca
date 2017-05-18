package biblioteca.estructura;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.regex.Pattern;

/**
 *
 * @author Victoriano Sevillano Vega
 * @version 1.0
 */
public class Fichero {
	/**
	 * Concesionario que se utilizara en todo el programa
	 */
	public static Biblioteca almacen = new Biblioteca();

	private static final Pattern patron = Pattern.compile("^((\\w)+(\\.obj))$");

	/**
	 * M�todo guardar un objeto pidiendo un nombre de archivo que se creara
	 *
	 * @param objeto
	 *            objeto que se le pasa para guardar
	 * @param nombre
	 * @throws IOException
	 *             Exception que lanza cuando el flujo acaba
	 */
	public static void guardarComo(Object objeto, File fichero) throws IOException {
		fichero = comprobarArchivo(fichero);
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * M�todo guardar que guarda el contenido de un archivo ya existente
	 *
	 * @param objeto
	 *            objeto que queremos guardar
	 * @throws IOException
	 *             Exception que lanza cuando el flujo acaba
	 */
	public static void guardar(Object objeto, File fichero) throws IOException {
		fichero = comprobarArchivo(fichero);
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fichero)))) {
			out.writeObject(objeto);
		}
	}

	/**
	 * M�todo que te carga un fichero que contiene un objeto
	 *
	 * @return Devuelve el contenido del archivo de tipo object
	 * @throws IOException
	 *             Exception que lanza cuando el flujo acaba
	 * @throws ClassNotFoundException
	 *             Exception que se lanza si el objeto destino pertenece a la
	 *             misma clase que el objeto del fichero
	 */
	public static void abrir(File fichero) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(fichero)))) {
			almacen = (Biblioteca) in.readObject();
		}
	}

	public static File comprobarArchivo(File fichero) {
		if (patron.matcher(fichero.getName()).matches()) {
			return fichero;
		} else {
			fichero = new File(fichero + ".obj");
			return fichero;
		}
	}

}