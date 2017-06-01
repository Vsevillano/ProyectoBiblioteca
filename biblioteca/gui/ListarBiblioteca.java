package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;

import biblioteca.estructura.Fichero;


import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarBiblioteca extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarBiblioteca dialog = new ListarBiblioteca();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	protected void mostrarPublicacion() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");
		getGenero(publicacion);

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
	public ListarBiblioteca() {
		spinnerIngreso.setLocation(128, 63);
		spinnerPublicacion.setLocation(128, 97);

		setTitle("Listar todo");
		cancelButton.setText("Aceptar");
		textNumeroPaginas.setEnabled(false);
		setBounds(100, 100, 450, 300);
		comboGenero.setEditable(true);
		textTitulo.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		comboGenero.setEnabled(false);
		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		okButton.setVisible(false);
		btnEnviar.setVisible(false);

		it = Fichero.almacen.listarBiblioteca();
		publicacion = it.next();
		mostrarPublicacion();
		btnAtras.setEnabled(false);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(ListarBiblioteca.class.getResource("/biblioteca/imagenes/pasando_paginas.gif")));
		lblNewLabel.setBounds(326, 154, 86, 75);
		contentPanel.add(lblNewLabel);
	}
}
