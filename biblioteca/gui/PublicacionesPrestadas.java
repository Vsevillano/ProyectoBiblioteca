package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PublicacionesPrestadas extends VentanaPadre {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int indicePublicacion = 0;

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

	private void comprobarBotones() {
		if (indicePublicacion + 1 >= Fichero.almacen.listarPrestados().size()) {
			buttonAdelante.setEnabled(false);
		} else {
			buttonAdelante.setEnabled(true);
		}
		if (indicePublicacion - 1 == -1) {
			btnAtras.setEnabled(false);
		} else {
			btnAtras.setEnabled(true);
		}
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

	private void mostrarPublicacion(int indicePublicacion) {
		try {
			Publicacion publicacion = Fichero.almacen.listarPrestados().get(indicePublicacion);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			textFechaDevolucion.setText("");
			JOptionPane.showMessageDialog(null, "No hay publicaciones!", "Error!", JOptionPane.ERROR_MESSAGE);

		}

	}

	/**
	 * Create the dialog.
	 */
	public PublicacionesPrestadas() {
		comboGenero.setEditable(true);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPublicacion(--indicePublicacion);
				comprobarBotones();
			}
		});
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPublicacion(++indicePublicacion);
				comprobarBotones();
			}
		});
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
		btnAtras.setEnabled(false);
		buttonAdelante.setEnabled(false);
		cancelButton.setText("Aceptar");
		btnEnviar.setVisible(false);
		mostrarPublicacion(indicePublicacion);
		comprobarBotones();
	}

}
