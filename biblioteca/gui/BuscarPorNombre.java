package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;

import biblioteca.excepciones.PublicacionNoExisteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPorNombre extends VentanaPadre {

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
					BuscarPorNombre dialog = new BuscarPorNombre();
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
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");
		getGenero(publicacion);

		try {
			Date dateDev = new SimpleDateFormat("yyyy-MM-dd").parse(publicacion.getFechaDevolucion().toString());
			SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
			textFechaDevolucion.setText(model.format(dateDev));
		} catch (NullPointerException | ParseException e1) {
			textFechaDevolucion.setText("");

		}

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
		comprobarBotones();

	}

	/**
	 * Create the dialog.
	 */
	public BuscarPorNombre() {
		cancelButton.setText("Aceptar");
		textNumeroPaginas.setEnabled(false);
		btnEnviar.setEnabled(true);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					it = Fichero.almacen.buscarPorTitulo(textTitulo.getText());
					publicacion = it.next();
					mostrarPublicacion();
					btnAtras.setEnabled(false);
				} catch (PublicacionNoExisteException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		setTitle("Buscar por nombre");
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

		btnAtras.setEnabled(false);
		buttonAdelante.setEnabled(false);
		okButton.setVisible(false);
		
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);

	}

}
