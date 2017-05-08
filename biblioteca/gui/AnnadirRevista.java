package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.GeneroRevista;
import biblioteca.estructura.Periodo;
import biblioteca.excepciones.EditorialNoValidaException;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

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
public class AnnadirRevista extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEditorial;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnnadirRevista dialog = new AnnadirRevista();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private Periodo getPeriodo() {
		if (rdbtnDiario.isSelected()) {
			return Periodo.DIARIO;
		} else if (rdbtnSemanal.isSelected()) {
			return Periodo.SEMANAL;
		} else if (rdbtnMensual.isSelected()) {
			return Periodo.MENSUAL;
		} else if (rdbtnAnual.isSelected()) {
			return Periodo.ANUAL;
		} else {
			return null;
		}
	}

	/**
	 * Create the dialog.
	 */
	public AnnadirRevista() {
		btnEnviar.setText("A\u00F1adir");
		setTitle("A\u00F1adir revista");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Obtenemos fechas
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate dateIngreso = LocalDate.parse(formater.format(spinnerIngreso.getValue()));
					LocalDate datePublicacion = LocalDate.parse(formater.format(spinnerPublicacion.getValue()));

					// Añadimos revista
					Fichero.almacen.anadirRevista(textTitulo.getText(), textEditorial.getText(), getPeriodo(),
							(GeneroRevista) comboGenero.getSelectedItem(), dateIngreso, datePublicacion,
							Integer.parseInt(textNumeroPaginas.getText()));

					// Reseteamos campos de la ventana
					textEditorial.setText("");
					textTitulo.setText("");
					textNumeroPaginas.setText("");

					// Actualizamos estado del fichero
					Fichero.almacen.setModificado(true);

				} catch (PeriodoNoValidoException | FechaNoValidaException | NumeroPaginasNoValidoException
						| EditorialNoValidaException | TituloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel, "Numero de paginas no valido!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEnviar.setEnabled(true);
		cancelButton.setText("Aceptar");
		comboGenero.setModel(new DefaultComboBoxModel(GeneroRevista.values()));
		lblFechaIngreso.setLocation(10, 94);
		spinnerIngreso.setLocation(134, 94);
		spinnerPublicacion.setLocation(134, 128);
		lblFechaPublicacion.setLocation(10, 128);
		lblGenero.setLocation(10, 163);
		comboGenero.setLocation(80, 164);
		rdbtnAnual.setLocation(316, 196);
		rdbtnMensual.setLocation(234, 196);
		rdbtnSemanal.setLocation(148, 196);
		rdbtnDiario.setLocation(86, 196);
		lblPeriodo.setLocation(10, 196);
		lblNumeroPaginas.setLocation(10, 225);
		textNumeroPaginas.setLocation(86, 226);

		JLabel lblNewLabel = new JLabel("Editorial");
		lblNewLabel.setBounds(10, 60, 46, 14);
		contentPanel.add(lblNewLabel);

		textEditorial = new JTextField();
		textEditorial.setBounds(80, 57, 215, 20);
		contentPanel.add(textEditorial);
		textEditorial.setColumns(10);
		setBounds(100, 100, 450, 320);

		okButton.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);

	}
}
