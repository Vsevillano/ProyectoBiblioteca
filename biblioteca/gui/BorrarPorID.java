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
public class BorrarPorID extends VentanaPadre {

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
					BorrarPorID dialog = new BorrarPorID();
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
	public BorrarPorID() {
		setTitle("Borrar por ID");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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

					int respuesta = JOptionPane.showConfirmDialog(null,
							"Se va a eliminar la publicacion, �Est� seguro?", "!!", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						Fichero.almacen.eliminarIdent(Integer.parseInt(textId.getText()));
						textTitulo.setText("");
						textNumeroPaginas.setText("");
						textId.setText("");
						// Actualizamos estado del fichero
						Fichero.almacen.setModificado(true);

					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "Id no valido!", "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (PublicacionNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(null, "La publicacion no existe!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnEnviar.setText("Borrar");
		spinnerIngreso.setSize(110, 20);
		spinnerPublicacion.setSize(110, 20);
		spinnerIngreso.setLocation(140, 64);
		spinnerPublicacion.setLocation(140, 98);
		btnEnviar.setEnabled(true);
		cancelButton.setText("Aceptar");
		textId.setEnabled(true);
		comboGenero.setEnabled(false);
		comboGenero.setEditable(true);
		textNumeroPaginas.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
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
