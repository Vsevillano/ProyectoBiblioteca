package biblioteca.gui;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Materia;
import biblioteca.estructura.Publicacion;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarLibrosTexto extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textEditorial;
	private JTextField textISBN;
	private ListIterator<Publicacion> it;
	private Publicacion publicacion;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarLibrosTexto dialog = new ListarLibrosTexto();
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
		mostrarLibroTexto();
	}

	/**
	 * Muestra el coche anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();
			
		}
		mostrarLibroTexto();

	}
	
	private void comprobarBotones() {
		if (!it.hasNext()) {
			buttonAdelante.setEnabled(false);
			publicacion = it.previous();
		}
		else
			buttonAdelante.setEnabled(true);
		if (!it.hasPrevious()) {
			btnAtras.setEnabled(false);
			publicacion = it.next();
		}
		else
			btnAtras.setEnabled(true);
	}



	private void mostrarLibroTexto() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textISBN.setText(((LibroTexto) publicacion).getIsbn());
		textEditorial.setText(((LibroTexto) publicacion).getEditorial());
		comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
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
	public ListarLibrosTexto() {
		comboGenero.setEnabled(false);
		comboGenero.setModel(new DefaultComboBoxModel(Materia.values()));
		cancelButton.setText("Aceptar");
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
		textNumeroPaginas.setEnabled(false);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		textTitulo.setEnabled(false);
		spinnerIngreso.setLocation(132, 111);
		lblFechaIngreso.setLocation(10, 111);
		lblFechaPublicacion.setLocation(10, 145);
		spinnerPublicacion.setLocation(132, 145);
		comboGenero.setLocation(80, 181);
		lblGenero.setLocation(10, 180);
		lblNumeroPaginas.setLocation(10, 214);
		textNumeroPaginas.setLocation(86, 215);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 60, 68, 14);
		contentPanel.add(lblEditorial);

		JLabel lblIsbn = new JLabel("ISBN:");
		lblIsbn.setBounds(10, 86, 46, 14);
		contentPanel.add(lblIsbn);

		textEditorial = new JTextField();
		textEditorial.setEnabled(false);
		textEditorial.setBounds(80, 57, 215, 20);
		contentPanel.add(textEditorial);
		textEditorial.setColumns(10);

		textISBN = new JTextField();
		textISBN.setEnabled(false);
		textISBN.setBounds(80, 83, 215, 20);
		contentPanel.add(textISBN);
		textISBN.setColumns(10);
		setTitle("Listar libros de texto");
		setBounds(100, 100, 450, 320);

		btnEnviar.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		lblPeriodo.setVisible(false);
		okButton.setVisible(false);

		it = Fichero.almacen.listarLibrosTexto();
		publicacion = it.next();
		mostrarLibroTexto();
		btnAtras.setEnabled(false);

	}

}
