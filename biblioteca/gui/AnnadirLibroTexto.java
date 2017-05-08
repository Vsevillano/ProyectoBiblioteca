package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.GeneroNovela;
import biblioteca.estructura.Materia;
import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.ISBNNoValidoException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class AnnadirLibroTexto extends VentanaPadre {
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
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate dateIngreso = LocalDate.parse(formater.format(spinnerIngreso.getValue()));
					LocalDate datePublicacion = LocalDate.parse(formater.format(spinnerPublicacion.getValue()));
					Fichero.almacen.annadirLibroTexto(textTitulo.getText(), textEditorial.getText(), textISBN.getText(),
						dateIngreso, datePublicacion, 
							Integer.parseInt(textNumeroPaginas.getText()), (Materia) comboGenero.getSelectedItem());
					textEditorial.setText("");
					textTitulo.setText("");
					textNumeroPaginas.setText("");
					textISBN.setText("");
					int id = Integer.parseInt(textId.getText());
					Fichero.almacen.setModificado(true);

				} catch (FechaNoValidaException | NumeroPaginasNoValidoException | EditorialNoValidaException
						| ISBNNoValidoException | TituloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel, "Numero de paginas no valido!", "ERROR!!!!",
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
		setTitle("A\u00F1adir Libro de texto");
		setBounds(100, 100, 450, 320);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		okButton.setVisible(false);
		lblPeriodo.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		

	}
}
