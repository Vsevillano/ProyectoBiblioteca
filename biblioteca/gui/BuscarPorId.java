package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.Publicacion;

import biblioteca.excepciones.PublicacionNoExisteException;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class BuscarPorId extends VentanaPadre {

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
					BuscarPorId dialog = new BuscarPorId();
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
	public BuscarPorId() {
		comboGenero.setEditable(true);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Publicacion publicacion = Fichero.almacen.buscarPorID(Integer.parseInt(textId.getText()));
					textTitulo.setText(publicacion.getTitulo());
					textId.setText(publicacion.getIdentificador() + "");
					textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");
					getGenero(publicacion);

					String dateIngreso = publicacion.getFechaIngreso().toString();
					String datePublicacion = publicacion.getFechaPublicacion().toString();
					Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
					Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
					spinnerIngreso.setValue(dateIng);
					spinnerPublicacion.setValue(datePub);

				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Id no valido!", "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (PublicacionNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();

				} catch (NullPointerException e2) {
					JOptionPane.showMessageDialog(null, "La publicacion no existe", "Error!",
							JOptionPane.ERROR_MESSAGE);

				}

			}

		});
		btnEnviar.setEnabled(true);

		setTitle("Buscar por ID");
		textNumeroPaginas.setEnabled(false);
		comboGenero.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		textId.setEnabled(true);
		btnEnviar.setText("Buscar");
		cancelButton.setText("Aceptar");
		spinnerPublicacion.setLocation(128, 97);
		spinnerIngreso.setLocation(128, 63);
		setBounds(100, 100, 450, 300);
		okButton.setVisible(false);
		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);

		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
	}

}
