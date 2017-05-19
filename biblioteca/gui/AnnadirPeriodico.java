package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.GeneroPeriodico;
import biblioteca.estructura.Periodo;
import biblioteca.estructura.Publicacion;
import biblioteca.excepciones.FechaNoValidaException;
import biblioteca.excepciones.NumeroPaginasNoValidoException;
import biblioteca.excepciones.PeriodoNoValidoException;
import biblioteca.excepciones.TituloNoValidoException;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class AnnadirPeriodico extends VentanaPadre {

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
					AnnadirPeriodico dialog = new AnnadirPeriodico();
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
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AnnadirPeriodico() {
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					// Obtenemos fechas
					SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
					LocalDate dateIngreso = LocalDate.parse(formater.format(spinnerIngreso.getValue()));
					LocalDate datePublicacion = LocalDate.parse(formater.format(spinnerPublicacion.getValue()));

					// Añadimos periodico
					Fichero.almacen.annadirPeriodico(textTitulo.getText(),
							(GeneroPeriodico) comboGenero.getSelectedItem(), getPeriodo(), dateIngreso, datePublicacion,
							Integer.parseInt(textNumeroPaginas.getText()), Integer.parseInt(textId.getText()));

					// Reseteamos campos de la ventana
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

				} catch (NumeroPaginasNoValidoException | PeriodoNoValidoException | TituloNoValidoException e1) {
					JOptionPane.showMessageDialog(contentPanel, e1.getMessage(), "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(contentPanel, "Numero de paginas no valido!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				} catch (FechaNoValidaException e) {
					JOptionPane.showMessageDialog(contentPanel, e.getMessage(), "ERROR!!!!", JOptionPane.ERROR_MESSAGE);
				}catch (DateTimeParseException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Fecha no valida!", "ERROR!!!!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEnviar.setEnabled(true);
		spinnerIngreso.setLocation(136, 63);
		spinnerPublicacion.setLocation(136, 97);
		btnEnviar.setText("A\u00F1adir");
		cancelButton.setText("Aceptar");
		setTitle("A\u00F1adir peri\u00F3dico");
		comboGenero.setModel(new DefaultComboBoxModel(GeneroPeriodico.values()));
		setBounds(100, 100, 450, 320);

		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		okButton.setVisible(false);

		try {
			textId.setText((Fichero.almacen.get(Fichero.almacen.size() - 1).getIdentificador() + 1) + "");
		} catch (IndexOutOfBoundsException e) {
			Publicacion publicacion = new Publicacion("");
			textId.setText(publicacion.getIdentificador() + "");
		}
	}

}
