package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Publicacion;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import biblioteca.estructura.Materia;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarLibrosTexto extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEditorial;
	private JTextField textISBN;
	private int indicePublicacion = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarLibrosTexto dialog = new ListarLibrosTexto();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void comprobarBotones() {
		if (indicePublicacion + 1 >= Fichero.almacen.listarLibrosTexto().size()) {
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

	private void mostrarLibroTexto(int indicePublicacion) {
		Publicacion publicacion = Fichero.almacen.listarLibrosTexto().get(indicePublicacion);
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textISBN.setText(((LibroTexto) publicacion).getIsbn());
		textEditorial.setText(((LibroTexto) publicacion).getEditorial());
		comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

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
	public ListarLibrosTexto() {
		comboGenero.setEnabled(false);
		comboGenero.setModel(new DefaultComboBoxModel(Materia.values()));
		cancelButton.setText("Aceptar");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLibroTexto(--indicePublicacion);
				comprobarBotones();
			}
		});
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarLibroTexto(++indicePublicacion);
				comprobarBotones();
			}
		});
		buttonAdelante.setEnabled(false);
		btnAtras.setEnabled(false);
		textNumeroPaginas.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		spinnerIngreso.setLocation(132, 111);
		lblFechaIngreso.setLocation(10, 111);
		lblFechaPublicacion.setLocation(10, 145);
		spinnerPublicacion.setLocation(132, 145);
		comboGenero.setLocation(80, 181);
		lblGenero.setLocation(10, 180);
		lblNumeroPaginas.setLocation(10, 214);
		textNumeroPaginas.setLocation(86, 215);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 60, 68, 14);
		contentPanel.add(lblEditorial);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 86, 46, 14);
		contentPanel.add(lblIsbn);

		textEditorial = new JTextField();
		textEditorial.setEnabled(false);
		textEditorial.setBounds(80, 57, 215, 20);
		contentPanel.add(textEditorial);
		textEditorial.setColumns(10);

		textISBN = new JTextField();
		textISBN.setEnabled(false);
		textISBN.setBounds(80, 83, 215, 20);
		contentPanel.add(textISBN);
		textISBN.setColumns(10);
		setTitle("Listar libros de texto");
		setBounds(100, 100, 450, 320);

		btnEnviar.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);
		okButton.setVisible(false);

		mostrarLibroTexto(indicePublicacion);
		comprobarBotones();

	}

}
