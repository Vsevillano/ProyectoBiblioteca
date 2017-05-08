package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.Revista;
import biblioteca.estructura.Publicacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarRevistas extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEditorial;
	private int indicePublicacion = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarRevistas dialog = new ListarRevistas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void comprobarBotones() {
		if (indicePublicacion + 1 >= Fichero.almacen.listarRevistas().size()) {
			buttonAdelante.setEnabled(false);
		} else {
			buttonAdelante.setEnabled(true);
		}
		if (indicePublicacion - 1 == -1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
	}

	private void mostrarRevista(int indicePublicacion) {
		Publicacion publicacion = Fichero.almacen.listarRevistas().get(indicePublicacion);
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textEditorial.setText(((Revista) publicacion).getEditorial());
		comboGenero.setSelectedItem(((Revista) publicacion).getGenero());
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

		switch (((Revista) Fichero.almacen.listarRevistas().get(indicePublicacion)).getPeriodo()) {
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

	}

	/**
	 * Create the dialog.
	 */
	public ListarRevistas() {
		cancelButton.setText("Aceptar");
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRevista(++indicePublicacion);
				comprobarBotones();
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarRevista(--indicePublicacion);
				comprobarBotones();
			}
		});
		comboGenero.setEditable(true);
		textTitulo.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		comboGenero.setEnabled(false);
		rdbtnAnual.setEnabled(false);
		rdbtnMensual.setEnabled(false);
		rdbtnSemanal.setEnabled(false);
		rdbtnDiario.setEnabled(false);
		setTitle("Listar Revistas");
		lblFechaIngreso.setLocation(10, 83);
		spinnerIngreso.setLocation(135, 83);
		spinnerPublicacion.setLocation(135, 117);
		lblFechaPublicacion.setLocation(10, 117);
		lblGenero.setLocation(10, 152);
		comboGenero.setLocation(80, 153);
		rdbtnAnual.setLocation(316, 185);
		rdbtnMensual.setLocation(234, 185);
		rdbtnSemanal.setLocation(148, 185);
		rdbtnDiario.setLocation(86, 185);
		lblPeriodo.setLocation(10, 185);
		lblNumeroPaginas.setLocation(10, 214);
		textNumeroPaginas.setLocation(86, 215);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 56, 60, 23);
		contentPanel.add(lblEditorial);

		textEditorial = new JTextField();
		textEditorial.setEnabled(false);
		textEditorial.setBounds(80, 52, 215, 20);
		contentPanel.add(textEditorial);
		textEditorial.setColumns(10);
		setBounds(100, 100, 450, 320);
		okButton.setVisible(false);

		mostrarRevista(indicePublicacion);
		comprobarBotones();
	}

}
