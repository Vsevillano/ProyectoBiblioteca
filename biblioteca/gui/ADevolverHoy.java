package biblioteca.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JDialog;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;

public class ADevolverHoy extends VentanaPadre {

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
					ADevolverHoy dialog = new ADevolverHoy();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Muestra el coche siguiente
	 */
	private void siguiente() {
		if (it.hasNext()) {
			publicacion = it.next();
		}
		mostrarPublicacion();
	}

	/**
	 * Muestra el coche anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();
		}
		mostrarPublicacion();

	}

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

	private void mostrarPublicacion() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		getGenero(publicacion);
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

		try {
			Date dateDev = new SimpleDateFormat("yyyy-MM-dd").parse(publicacion.getFechaDevolucion().toString());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
			textFechaDevolucion.setText(model.format(dateDev));
		} catch (NullPointerException | ParseException e1) {
			textFechaDevolucion.setText("");
		}

		String dateIngreso = publicacion.getFechaIngreso().toString();
		String datePublicacion = publicacion.getFechaPublicacion().toString();
		try {
			Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
			Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
			spinnerIngreso.setValue(dateIng);
			spinnerPublicacion.setValue(datePub);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		comprobarBotones();

	}

	/**
	 * Create the dialog.
	 */
	public ADevolverHoy() {
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
		setTitle("A devolver hoy");
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
		btnAtras.setEnabled(false);
		buttonAdelante.setEnabled(false);
		cancelButton.setText("Aceptar");
		btnEnviar.setVisible(false);

		it = Fichero.almacen.listarADevolverHoy();
		publicacion = it.next();
		mostrarPublicacion();
		btnAtras.setEnabled(false);

	}

}
