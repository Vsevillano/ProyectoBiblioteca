package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;
import biblioteca.excepciones.PublicacionNoExisteException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BuscarPorNombre extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ListIterator<Publicacion> it;
	private Publicacion publicacion;

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

	/**
	 * Muestra el coche siguiente
	 */
	private void siguiente() {
		if (it.hasNext()) {
			publicacion = it.next();

		}
		mostrarPublicacion();
	}

	/**
	 * Muestra el coche anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();

		}
		mostrarPublicacion();

	}

	private void comprobarBotones() {
		if (!it.hasNext()) {
			buttonAdelante.setEnabled(false);
			publicacion = it.previous();
		} else
			buttonAdelante.setEnabled(true);
		if (!it.hasPrevious()) {
			btnAtras.setEnabled(false);
			publicacion = it.next();
		} else
			btnAtras.setEnabled(true);
	}

	private void getGenero(Publicacion publicacion) {
		if (publicacion instanceof Novela)
			comboGenero.setSelectedItem(((Novela) publicacion).getGenero());
		else if (publicacion instanceof Revista)
			comboGenero.setSelectedItem(((Revista) publicacion).getGenero());
		else if (publicacion instanceof Periodico)
			comboGenero.setSelectedItem(((Periodico) publicacion).getGenero());
		else if (publicacion instanceof LibroTexto)
			comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
	}

	private void mostrarPublicacion() {
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
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();
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

	}

}
