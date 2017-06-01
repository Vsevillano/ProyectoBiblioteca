package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;

import biblioteca.estructura.Fichero;

public class ADevolverHoy extends VentanaPadre {

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
					ADevolverHoy dialog = new ADevolverHoy();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/*
	 * (non-Javadoc)
	 * @see biblioteca.gui.VentanaPadre#mostrarPublicacion()
	 */
	@Override
	protected void mostrarPublicacion() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		getGenero(publicacion);
		textNumeroPaginas.setText(publicacion.getNumeroPaginas() + "");

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
	public ADevolverHoy() {
		comboGenero.setEditable(true);

		setTitle("A devolver hoy");
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
		btnAtras.setEnabled(false);
		buttonAdelante.setEnabled(false);
		cancelButton.setText("Aceptar");
		btnEnviar.setVisible(false);

		it = Fichero.almacen.listarADevolverHoy();
		publicacion = it.next();
		mostrarPublicacion();
		btnAtras.setEnabled(false);

	}

}
