package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.excepciones.PublicacionNoExisteException;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class BorrarPorNombre extends VentanaPadre {

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
					BorrarPorNombre dialog = new BorrarPorNombre();
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
	public BorrarPorNombre() {
		cancelButton.setText("Aceptar");
		textNumeroPaginas.setEnabled(false);
		btnEnviar.setEnabled(true);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					it = Fichero.almacen.buscarPorTitulo(textTitulo.getText());
					publicacion = it.next();
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
							"Se va a eliminar la publicacion, ¿Está seguro?", "!!", JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (respuesta == JOptionPane.YES_OPTION) {

						Fichero.almacen.borrarPorTitulo(textTitulo.getText());
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
				} catch (NullPointerException e3) {
					JOptionPane.showMessageDialog(null, "La publicacion no existe!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		setTitle("Borrar por nombre");
		btnEnviar.setText("Buscar");
		rdbtnAnual.setEnabled(false);
		rdbtnMensual.setEnabled(false);
		rdbtnSemanal.setEnabled(false);
		rdbtnDiario.setEnabled(false);
		comboGenero.setEditable(true);
		comboGenero.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		spinnerPublicacion.setLocation(140, 97);
		spinnerIngreso.setLocation(140, 63);
		setBounds(100, 100, 450, 300);

		btnAtras.setVisible(false);
		buttonAdelante.setVisible(false);
		okButton.setVisible(false);
		
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);

	}

}
