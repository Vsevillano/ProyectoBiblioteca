package biblioteca.gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import biblioteca.estructura.Fichero;
import biblioteca.estructura.GeneroNovela;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Publicacion;

import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ListIterator;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class ListarNovelas extends VentanaPadre {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textAutor;
	private JTextField textEditorial;

	private ListIterator<Publicacion> it;
	private Publicacion publicacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListarNovelas dialog = new ListarNovelas();
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
		mostrarNovela();
	}

	/**
	 * Muestra el coche anterior
	 */
	private void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();
			
		}
		mostrarNovela();

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


	private void mostrarNovela() {
		textId.setText(publicacion.getIdentificador() + "");
		textTitulo.setText(publicacion.getTitulo());
		textAutor.setText(((Novela) publicacion).getAutor());
		textEditorial.setText(((Novela) publicacion).getEditorial());
		comboGenero.setSelectedItem(((Novela) publicacion).getGenero());
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
			e.printStackTrace();
		}
		comprobarBotones();

	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListarNovelas() {
		setTitle("Listar novelas");
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguiente();
			}
		});
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		textNumeroPaginas.setEnabled(false);
		textTitulo.setEnabled(false);
		comboGenero.setEnabled(false);
		comboGenero.setEditable(true);
		spinnerPublicacion.setEnabled(false);
		spinnerIngreso.setEnabled(false);
		comboGenero.setModel(new DefaultComboBoxModel(GeneroNovela.values()));
		btnAtras.setLocation(316, 135);
		lblFechaIngreso.setLocation(10, 121);
		spinnerIngreso.setLocation(137, 121);
		spinnerPublicacion.setLocation(137, 155);
		lblFechaPublicacion.setLocation(10, 155);
		lblGenero.setLocation(10, 187);
		comboGenero.setLocation(80, 191);
		rdbtnAnual.setLocation(316, 185);
		rdbtnMensual.setLocation(234, 185);
		rdbtnSemanal.setLocation(148, 185);
		rdbtnDiario.setLocation(86, 185);
		lblPeriodo.setLocation(10, 195);
		lblNumeroPaginas.setLocation(10, 221);
		textNumeroPaginas.setLocation(86, 222);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(10, 60, 46, 23);
		contentPanel.add(lblAutor);

		textAutor = new JTextField();
		textAutor.setEnabled(false);
		textAutor.setBounds(80, 57, 215, 20);
		contentPanel.add(textAutor);
		textAutor.setColumns(10);

		textEditorial = new JTextField();
		textEditorial.setEnabled(false);
		textEditorial.setColumns(10);
		textEditorial.setBounds(80, 89, 215, 20);
		contentPanel.add(textEditorial);

		JLabel lblEditorial = new JLabel("Editorial:");
		lblEditorial.setBounds(10, 92, 46, 23);
		contentPanel.add(lblEditorial);
		setBounds(100, 100, 450, 327);

		lblPeriodo.setVisible(false);
		rdbtnAnual.setVisible(false);
		rdbtnDiario.setVisible(false);
		rdbtnMensual.setVisible(false);
		rdbtnSemanal.setVisible(false);
		okButton.setVisible(false);
		btnEnviar.setVisible(false);
		cancelButton.setText("Aceptar");

		it = Fichero.almacen.listarNovelas();
		publicacion = it.next();
		mostrarNovela();
		btnAtras.setEnabled(false);


	}

}
