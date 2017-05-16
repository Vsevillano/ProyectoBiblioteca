package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JDialog;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarPeriodicos extends VentanaPadre {

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
					ListarPeriodicos dialog = new ListarPeriodicos();
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
		mostrarPeriodico();
	}

	/**
	 * Muestra el anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();

		}
		mostrarPeriodico();

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
	 * Muestra el periodico
	 */
	private void mostrarPeriodico() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		comboGenero.setSelectedItem(((Periodico) publicacion).getGenero());
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

		switch (((Periodico) publicacion).getPeriodo()) {
		case DIARIO:
			rdbtnDiario.setSelected(true);
			break;
		case SEMANAL:
			rdbtnSemanal.setSelected(true);
			break;
		case MENSUAL:
			rdbtnMensual.setSelected(true);
		case ANUAL:
			rdbtnAnual.setSelected(true);

		}

		try {
			Date dateDev = new SimpleDateFormat("yyyy-MM-dd").parse(publicacion.getFechaDevolucion().toString());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
			textFechaDevolucion.setText(model.format(dateDev));

			String dateIngreso = publicacion.getFechaIngreso().toString();
			String datePublicacion = publicacion.getFechaPublicacion().toString();

			Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
			Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
			spinnerIngreso.setValue(dateIng);
			spinnerPublicacion.setValue(datePub);

		} catch (NullPointerException | ParseException e1) {
			textFechaDevolucion.setText("");

		}
		comprobarBotones();
	}

	/**
	 * Create the dialog.
	 */
	public ListarPeriodicos() {
		cancelButton.setText("Aceptar");
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();

			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		spinnerPublicacion.setLocation(147, 97);
		spinnerIngreso.setLocation(147, 63);
		textNumeroPaginas.setEnabled(false);
		rdbtnAnual.setEnabled(false);
		rdbtnMensual.setEnabled(false);
		rdbtnSemanal.setEnabled(false);
		rdbtnDiario.setEnabled(false);
		comboGenero.setEnabled(false);
		comboGenero.setEditable(true);
		textTitulo.setEnabled(false);
		setTitle("Listar peri\u00F3dicos");
		setBounds(100, 100, 450, 300);
		btnEnviar.setVisible(false);
		okButton.setVisible(false);

		it = Fichero.almacen.listarPeriodicos();
		publicacion = it.next();
		mostrarPeriodico();
		btnAtras.setEnabled(false);
	}

}
