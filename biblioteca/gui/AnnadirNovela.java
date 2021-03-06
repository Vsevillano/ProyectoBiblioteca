package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.GeneroNovela;
import biblioteca.estructura.Publicacion;
import biblioteca.excepciones.AutorNovalidoException;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class AnnadirNovela extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textAutor;
	private JTextField textEditorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnadirNovela dialog = new AnnadirNovela();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AnnadirNovela() {
		textTitulo.setBounds(90, 23, 205, 20);
		spinnerPublicacion.setLocation(136, 156);
		spinnerIngreso.setLocation(136, 122);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					// Obtenemos las fechas
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate dateIngreso = LocalDate.parse(formater.format(spinnerIngreso.getValue()));
					LocalDate datePublicacion = LocalDate.parse(formater.format(spinnerPublicacion.getValue()));

					// A�adimos novela
					Fichero.almacen.anadirNovela(textTitulo.getText(), textAutor.getText(), textEditorial.getText(),
							(GeneroNovela) comboGenero.getSelectedItem(), dateIngreso, datePublicacion,
							Integer.parseInt(textNumeroPaginas.getText()), Integer.parseInt(textId.getText()));

					// Reseteamos campos de la ventana
					textAutor.setText("");
					textEditorial.setText("");
					textTitulo.setText("");
					textNumeroPaginas.setText("");

					SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
					spinnerIngreso.setModel(new SpinnerDateModel());
					spinnerIngreso.setEditor(new JSpinner.DateEditor(spinnerIngreso, model.toPattern()));
					spinnerPublicacion.setModel(new SpinnerDateModel());
					spinnerPublicacion.setEditor(new JSpinner.DateEditor(spinnerPublicacion, model.toPattern()));

					// Cambiamos el id de la ventana
					textId.setText((Integer.parseInt(textId.getText()) + 1) + "");

					// Actualizamos estado del fichero
					Fichero.almacen.setModificado(true);

				} catch (NumeroPaginasNoValidoException | EditorialNoValidaException | AutorNovalidoException
						| TituloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel, "Numero de paginas no valido!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (FechaNoValidaException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (DateTimeParseException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Fecha no valida!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		lblTitulo.setLocation(14, 22);
		textNumeroPaginas.setLocation(90, 226);
		lblNumeroPaginas.setLocation(14, 225);
		comboGenero.setLocation(90, 192);
		lblGenero.setLocation(14, 191);
		lblFechaIngreso.setLocation(14, 122);
		lblFechaPublicacion.setLocation(14, 156);


		comboGenero.setModel(new DefaultComboBoxModel(GeneroNovela.values()));
		btnEnviar.setEnabled(true);
		setResizable(false);
		setTitle("A\u00F1adir novela");
		btnEnviar.setText("A\u00F1adir");

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(14, 62, 46, 14);
		contentPanel.add(lblAutor);

		textAutor = new JTextField();
		textAutor.setBounds(90, 59, 205, 20);
		contentPanel.add(textAutor);
		textAutor.setColumns(10);

		textEditorial = new JTextField();
		textEditorial.setColumns(10);
		textEditorial.setBounds(90, 91, 205, 20);
		contentPanel.add(textEditorial);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(14, 94, 66, 14);
		contentPanel.add(lblEditorial);
		setBounds(100, 100, 450, 320);

		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		okButton.setVisible(false);
		cancelButton.setText("Aceptar");
		
		try {
			textId.setText((Fichero.almacen.get(Fichero.almacen.size() - 1).getIdentificador() + 1) + "");
		} catch (IndexOutOfBoundsException e) {
			Publicacion publicacion = new Publicacion("");
			textId.setText(publicacion.getIdentificador() + "");
		}
	}

}
