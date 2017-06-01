package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;



/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class PublicacionesPrestadas extends VentanaPadre {

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
					PublicacionesPrestadas dialog = new PublicacionesPrestadas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	@Override
	protected void mostrarPublicacion() {
		try {
			textId.setText(publicacion.getIdentificador() + "");
			textTitulo.setText(publicacion.getTitulo());
			getGenero(publicacion);
			textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

			Date dateDev = new SimpleDateFormat("yyyy-MM-dd").parse(publicacion.getFechaDevolucion().toString());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
			textFechaDevolucion.setText(model.format(dateDev));

			String dateIngreso = publicacion.getFechaIngreso().toString();
			String datePublicacion = publicacion.getFechaPublicacion().toString();

			Date dateIng = new SimpleDateFormat("yyyy-MM-dd").parse(dateIngreso);
			Date datePub = new SimpleDateFormat("yyyy-MM-dd").parse(datePublicacion);
			spinnerIngreso.setValue(dateIng);
			spinnerPublicacion.setValue(datePub);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Formato de fecha no valido!", "Error!", JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			textFechaDevolucion.setText("");
			JOptionPane.showMessageDialog(null, "No hay publicaciones!", "Error!", JOptionPane.ERROR_MESSAGE);

		}
		comprobarBotones();

	}

	/**
	 * Create the dialog.
	 */
	public PublicacionesPrestadas() {
		comboGenero.setEditable(true);

		setTitle("Publicaciones prestadas");
		textNumeroPaginas.setEnabled(false);
		comboGenero.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		spinnerPublicacion.setLocation(141, 97);
		spinnerIngreso.setLocation(141, 63);
		btnEnviar.setText("Prestar");
		btnEnviar.setEnabled(true);
		setBounds(100, 100, 450, 300);

		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);
		okButton.setVisible(false);
		cancelButton.setText("Aceptar");
		btnEnviar.setVisible(false);

		it = Fichero.almacen.listarPrestados();
		publicacion = it.next();
		mostrarPublicacion();
		btnAtras.setEnabled(false);

	}

}
