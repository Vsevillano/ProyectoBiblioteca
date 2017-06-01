package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import biblioteca.estructura.LibroTexto;
import biblioteca.estructura.Novela;
import biblioteca.estructura.Periodico;
import biblioteca.estructura.Publicacion;
import biblioteca.estructura.Revista;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ListIterator;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.awt.Toolkit;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class VentanaPadre extends JDialog {

	protected ListIterator<Publicacion> it;
	protected Publicacion publicacion;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected final JPanel contentPanel = new JPanel();
	protected JTextField textTitulo;
	protected JTextField textId;
	protected JTextField textFechaDevolucion;
	protected final ButtonGroup buttonGroup = new ButtonGroup();
	@SuppressWarnings("rawtypes")
	protected JComboBox comboGenero;
	protected JLabel lblPeriodo;
	protected JRadioButton rdbtnDiario;
	protected JRadioButton rdbtnSemanal;
	protected JRadioButton rdbtnMensual;
	protected JRadioButton rdbtnAnual;
	protected JButton btnEnviar;
	protected JLabel lblGenero;
	protected JButton btnAtras;
	protected JButton buttonAdelante;
	protected JLabel lblFechaDevolucion;
	protected JLabel lblId;
	protected JLabel lblNumeroPaginas;
	protected JLabel lblFechaPublicacion;
	protected JLabel lblFechaIngreso;
	protected JLabel lblTitulo;
	protected JButton okButton;
	protected JButton cancelButton;
	protected JFormattedTextField textNumeroPaginas;
	protected JSpinner spinnerIngreso;
	protected JSpinner spinnerPublicacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			VentanaPadre dialog = new VentanaPadre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Muestra el siguiente
	 */
	protected void siguiente() {
		if (it.hasNext()) {
			publicacion = it.next();

		}
		mostrarPublicacion();
	}

	/**
	 * Muestra el anterior
	 */
	protected void anterior() {
		if (it.hasPrevious()) {
			publicacion = it.previous();

		}
		mostrarPublicacion();

	}

	/**
	 * 
	 */
	protected void mostrarPublicacion() {
	}

	/**
	 * Comprueba los botones
	 */
	protected void comprobarBotones() {
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
	
	/**
	 * Obtiene el genero en funcion de la clase
	 * 
	 * @param publicacion
	 */
	protected void getGenero(Publicacion publicacion) {
		if (publicacion instanceof Novela)
			comboGenero.setSelectedItem(((Novela) publicacion).getGenero());
		else if (publicacion instanceof Revista)
			comboGenero.setSelectedItem(((Revista) publicacion).getGenero());
		else if (publicacion instanceof Periodico)
			comboGenero.setSelectedItem(((Periodico) publicacion).getGenero());
		else if (publicacion instanceof LibroTexto)
			comboGenero.setSelectedItem(((LibroTexto) publicacion).getMateria());
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("rawtypes")
	public VentanaPadre() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaPadre.class.getResource("/biblioteca/imagenes/libros.png")));
		setModal(true);
		setBounds(100, 100, 465, 307);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 22, 46, 23);
		contentPanel.add(lblTitulo);

		lblFechaIngreso = new JLabel("Fecha ingreso:");
		lblFechaIngreso.setBounds(10, 63, 86, 23);
		contentPanel.add(lblFechaIngreso);

		lblFechaPublicacion = new JLabel("Fecha publicacion:");
		lblFechaPublicacion.setBounds(10, 97, 108, 23);
		contentPanel.add(lblFechaPublicacion);

		lblNumeroPaginas = new JLabel("N\u00BA p\u00E1gs:");
		lblNumeroPaginas.setBounds(10, 194, 46, 23);
		contentPanel.add(lblNumeroPaginas);

		lblId = new JLabel("ID:");
		lblId.setBounds(316, 22, 24, 23);
		contentPanel.add(lblId);

		lblFechaDevolucion = new JLabel("Fecha devolucion:");
		lblFechaDevolucion.setBounds(316, 56, 108, 23);
		contentPanel.add(lblFechaDevolucion);

		textTitulo = new JTextField();
		textTitulo.setBounds(80, 23, 215, 20);
		contentPanel.add(textTitulo);
		textTitulo.setColumns(10);

		textId = new JTextField();
		textId.setEnabled(false);
		textId.setBounds(343, 23, 68, 20);
		contentPanel.add(textId);
		textId.setColumns(10);

		textFechaDevolucion = new JTextField();
		textFechaDevolucion.setEditable(false);
		textFechaDevolucion.setBounds(316, 79, 95, 20);
		contentPanel.add(textFechaDevolucion);
		textFechaDevolucion.setColumns(10);

		lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(10, 132, 46, 23);
		contentPanel.add(lblGenero);

		comboGenero = new JComboBox();
		comboGenero.setBounds(80, 133, 215, 20);
		contentPanel.add(comboGenero);

		lblPeriodo = new JLabel("Periodo:");
		lblPeriodo.setBounds(10, 165, 50, 23);
		contentPanel.add(lblPeriodo);

		rdbtnDiario = new JRadioButton("Diario");
		buttonGroup.add(rdbtnDiario);
		rdbtnDiario.setBounds(86, 165, 60, 23);
		contentPanel.add(rdbtnDiario);

		rdbtnSemanal = new JRadioButton("Semanal");
		buttonGroup.add(rdbtnSemanal);
		rdbtnSemanal.setBounds(148, 165, 84, 23);
		contentPanel.add(rdbtnSemanal);

		rdbtnMensual = new JRadioButton("Mensual");
		buttonGroup.add(rdbtnMensual);
		rdbtnMensual.setBounds(234, 165, 74, 23);
		contentPanel.add(rdbtnMensual);

		rdbtnAnual = new JRadioButton("Anual");
		buttonGroup.add(rdbtnAnual);
		rdbtnAnual.setBounds(316, 165, 79, 23);
		contentPanel.add(rdbtnAnual);

		btnEnviar = new JButton("Enviar");
		btnEnviar.setEnabled(false);
		btnEnviar.setBounds(316, 110, 95, 23);
		contentPanel.add(btnEnviar);

		btnAtras = new JButton("<");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anterior();
			}
		});
		btnAtras.setBounds(316, 135, 46, 23);
		contentPanel.add(btnAtras);

		buttonAdelante = new JButton(">");
		buttonAdelante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				siguiente();
			}
		});
		buttonAdelante.setBounds(366, 135, 45, 23);
		contentPanel.add(buttonAdelante);

		textNumeroPaginas = new JFormattedTextField();
		textNumeroPaginas.setBounds(86, 195, 60, 20);
		contentPanel.add(textNumeroPaginas);

		SimpleDateFormat model = new SimpleDateFormat("dd/MM/yyyy");
		spinnerIngreso = new JSpinner();
		spinnerIngreso.setModel(new SpinnerDateModel());
		spinnerIngreso.setEditor(new JSpinner.DateEditor(spinnerIngreso, model.toPattern()));
		spinnerIngreso.setBounds(112, 64, 120, 20);

		contentPanel.add(spinnerIngreso);

		spinnerPublicacion = new JSpinner();
		spinnerPublicacion.setModel(new SpinnerDateModel());
		spinnerPublicacion.setEditor(new JSpinner.DateEditor(spinnerPublicacion, model.toPattern()));
		spinnerPublicacion.setBounds(112, 98, 120, 20);
		contentPanel.add(spinnerPublicacion);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
