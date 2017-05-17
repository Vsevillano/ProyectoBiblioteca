package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class PublicacionesPrestadas extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListIterator<Publicacion> it;
	private Publicacion publicacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PublicacionesPrestadas dialog = new PublicacionesPrestadas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Muestra el siguiente
	 */
	private void siguiente() {
		if (it.hasNext()) {
			publicacion = it.next();

		}
		mostrarPublicacion();
	}

	/**
	 * Muestra el anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();

		}
		mostrarPublicacion();
	}

	/**
	 * Comprueba los botones
	 */
	private void comprobarBotones() {
		if (!it.hasNext()) {
			buttonAdelante.setEnabled(false);
			publicacion = it.previous();
		} else
			buttonAdelante.setEnabled(true);
		if (!it.hasPrevious()) {
			btnAtras.setEnabled(false);
			publicacion = it.next();
		} else
			btnAtras.setEnabled(true);
	}

	/**
	 * Obtiene el genero en funcion de la clase
	 * 
	 * @param publicacion
	 */
	private void getGenero(Publicacion publicacion) {
		if (publicacion instanceof Novela)
			comboGenero.setSelectedItem(((Novela) publicacion).getGenero());
		else if (publicacion instanceof Revista)
			comboGenero.setSelectedItem(((Revista) publicacion).getGenero());
		else if (publicacion instanceof Periodico)
			comboGenero.setSelectedItem(((Periodico) publicacion).getGenero());
		else if (publicacion instanceof LibroTexto)
			comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
	}

	/**
	 * Muestra la publicacion
	 */
	private void mostrarPublicacion() {
		try {
			textId.setText(publicacion.getIdentificador() + "");
			textTitulo.setText(publicacion.getTitulo());
			getGenero(publicacion);
			textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

			Date dateDev = new SimpleDateFormat("yyyy-MM-dd").parse(publicacion.getFechaDevolucion().toString());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
			textFechaDevolucion.setText(model.format(dateDev));

			String dateIngreso = publicacion.getFechaIngreso().toString();
			String datePublicacion = publicacion.getFechaPublicacion().toString();

			Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
			Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
			spinnerIngreso.setValue(dateIng);
			spinnerPublicacion.setValue(datePub);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Formato de fecha no valido!", "Error!", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			textFechaDevolucion.setText("");
			JOptionPane.showMessageDialog(null, "No hay publicaciones!", "Error!", JOptionPane.ERROR_MESSAGE);

		}
		comprobarBotones();

	}

	/**
	 * Create the dialog.
	 */
	public PublicacionesPrestadas() {
		comboGenero.setEditable(true);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();
			}
		});
		setTitle("Publicaciones prestadas");
		textNumeroPaginas.setEnabled(false);
		comboGenero.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		spinnerPublicacion.setLocation(141, 97);
		spinnerIngreso.setLocation(141, 63);
		btnEnviar.setText("Prestar");
		btnEnviar.setEnabled(true);
		setBounds(100, 100, 450, 300);

		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);
		okButton.setVisible(false);
		cancelButton.setText("Aceptar");
		btnEnviar.setVisible(false);

		it = Fichero.almacen.listarPrestados();
		publicacion = it.next();
		mostrarPublicacion();
		btnAtras.setEnabled(false);

	}

}
