package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.Materia;
import biblioteca.estructura.Publicacion;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class AnnadirLibroTexto extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEditorial;
	private JTextField textISBN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnadirLibroTexto dialog = new AnnadirLibroTexto();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public AnnadirLibroTexto() {
		cancelButton.setText("Aceptar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Obtenemos las fechas
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate dateIngreso = LocalDate.parse(formater.format(spinnerIngreso.getValue()));
					LocalDate datePublicacion = LocalDate.parse(formater.format(spinnerPublicacion.getValue()));

					// Añadimos libro
					Fichero.almacen.annadirLibroTexto(textTitulo.getText(), textEditorial.getText(), textISBN.getText(),
							dateIngreso, datePublicacion, Integer.parseInt(textNumeroPaginas.getText()),
							(Materia) comboGenero.getSelectedItem(), Integer.parseInt(textId.getText()));

					// Reseteamos campos de la ventana
					textEditorial.setText("");
					textTitulo.setText("");
					textNumeroPaginas.setText("");
					textISBN.setText("");

					SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
					spinnerIngreso.setModel(new SpinnerDateModel());
					spinnerIngreso.setEditor(new JSpinner.DateEditor(spinnerIngreso, model.toPattern()));
					spinnerPublicacion.setModel(new SpinnerDateModel());
					spinnerPublicacion.setEditor(new JSpinner.DateEditor(spinnerPublicacion, model.toPattern()));

					// Cambiamos el id de la ventana
					textId.setText((Integer.parseInt(textId.getText()) + 1) + "");

					// Actualizamos estado del fichero
					Fichero.almacen.setModificado(true);

				} catch (NumeroPaginasNoValidoException | EditorialNoValidaException | ISBNNoValidoException
						| TituloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel, "Numero de paginas no valido!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (FechaNoValidaException e3) {
					JOptionPane.showMessageDialog(contentPanel, e3.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEnviar.setText("A\u00F1adir");
		btnEnviar.setEnabled(true);
		comboGenero.setModel(new DefaultComboBoxModel(Materia.values()));
		lblGenero.setText("Materia");
		spinnerIngreso.setLocation(127, 116);
		lblFechaIngreso.setLocation(10, 115);
		lblFechaPublicacion.setLocation(10, 146);
		spinnerPublicacion.setLocation(127, 148);
		comboGenero.setLocation(80, 181);
		lblGenero.setLocation(10, 180);
		lblNumeroPaginas.setLocation(10, 214);
		textNumeroPaginas.setLocation(86, 215);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 60, 68, 19);
		contentPanel.add(lblEditorial);

		textEditorial = new JTextField();
		textEditorial.setBounds(80, 57, 215, 20);
		contentPanel.add(textEditorial);
		textEditorial.setColumns(10);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(10, 95, 46, 14);
		contentPanel.add(lblIsbn);

		textISBN = new JTextField();
		textISBN.setBounds(80, 88, 215, 20);
		contentPanel.add(textISBN);
		textISBN.setColumns(10);
		setTitle("  A\u00F1adir Libro de texto");
		setBounds(100, 100, 450, 320);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		okButton.setVisible(false);
		lblPeriodo.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);

		try {
			textId.setText((Fichero.almacen.get(Fichero.almacen.size() - 1).getIdentificador() + 1) + "");
		} catch (IndexOutOfBoundsException e) {
			Publicacion publicacion = new Publicacion("");
			textId.setText(publicacion.getIdentificador() + "");
		}
	}
}
