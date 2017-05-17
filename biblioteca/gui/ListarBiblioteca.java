package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDialog;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarBiblioteca extends VentanaPadre {

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
					ListarBiblioteca dialog = new ListarBiblioteca();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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

	private void comprobarBotones() {
		if (indicePublicacion + 1 >= Fichero.almacen.size()) {
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

	private void mostrarPublicaciones(int indicePublicacion) {
		Publicacion publicacion = Fichero.almacen.get(indicePublicacion);
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

	}

	/**
	 * Create the dialog.
	 */
	public ListarBiblioteca() {
		spinnerIngreso.setLocation(128, 63);
		spinnerPublicacion.setLocation(128, 97);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarPublicaciones(--indicePublicacion);
				comprobarBotones();
			}
		});
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mostrarPublicaciones(++indicePublicacion);
				comprobarBotones();
			}
		});
		buttonAdelante.setEnabled(false);
		btnAtras.setEnabled(false);
		setTitle("Listar todo");
		cancelButton.setText("Aceptar");
		textNumeroPaginas.setEnabled(false);
		setBounds(100, 100, 450, 300);
		comboGenero.setEditable(true);
		textTitulo.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		comboGenero.setEnabled(false);
		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		okButton.setVisible(false);
		btnEnviar.setVisible(false);

		mostrarPublicaciones(indicePublicacion);
		comprobarBotones();
	}

}
