package biblioteca.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import biblioteca.estructura.Biblioteca;
import biblioteca.estructura.Fichero;
import biblioteca.estructura.Filtro;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;

/**
 * 
 * @author Victoriano Sevillano Vega
 * @version 1.0
 *
 */
public class Principal extends JFrame implements Serializable {

	private final Filtro filtro = new Filtro(".obj", "Objeto");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PanelFondo contentPane = new PanelFondo();
	private JFileChooser filechooser = new JFileChooser();
	private Ayuda ayuda = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		contentPane.setVisible(true);
		// favicon
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/biblioteca/imagenes/libros.png")));

		// Cerrar ventana en X y con alt + f4
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				salir();
			}

			@Override
			public void windowClosed(WindowEvent e) {
				salir();
			}
		});

		// Fichero inicial para el filechooser
		filechooser.setSelectedFile(new File("*.obj"));

		setTitle("Sin_titulo");

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 818, 496);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		mnArchivo.setMnemonic('A');
		menuBar.add(mnArchivo);

		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nuevo();
			}
		});
		mntmNuevo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmNuevo);

		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrir();

			}
		});

		mntmAbrir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmAbrir);

		JMenuItem mntmGuardar = new JMenuItem("Guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardar();

			}
		});
		mntmGuardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnArchivo.add(mntmGuardar);

		JMenuItem mntmGuardarComo = new JMenuItem("Guardar como...");
		mntmGuardarComo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarComoFile();
			}
		});
		mnArchivo.add(mntmGuardarComo);

		JSeparator separator = new JSeparator();
		mnArchivo.add(separator);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnEdicion = new JMenu("Edicion");
		mnEdicion.setMnemonic('E');
		menuBar.add(mnEdicion);

		JMenu mnAadir = new JMenu("A\u00F1adir  ");
		mnEdicion.add(mnAadir);

		JMenuItem mntmNovela = new JMenuItem("Novela");
		mntmNovela.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnadirNovela annadirNovela = new AnnadirNovela();
				annadirNovela.setVisible(true);
			}
		});
		mnAadir.add(mntmNovela);

		JMenuItem mntmRevista = new JMenuItem("Revista");
		mntmRevista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AnnadirRevista annadirRevista = new AnnadirRevista();
				annadirRevista.setVisible(true);
			}
		});
		mnAadir.add(mntmRevista);

		JMenuItem mntmPeriodico = new JMenuItem("Periodico");
		mntmPeriodico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnnadirPeriodico annadirPeriodico = new AnnadirPeriodico();
				annadirPeriodico.setVisible(true);
			}
		});
		mnAadir.add(mntmPeriodico);

		JMenuItem mntmLibroDeTexto = new JMenuItem("Libro de texto");
		mntmLibroDeTexto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnnadirLibroTexto annadirLibroTexto = new AnnadirLibroTexto();
				annadirLibroTexto.setVisible(true);
			}
		});
		mnAadir.add(mntmLibroDeTexto);

		JMenu mnNewMenu = new JMenu("Borrar  ");
		mnEdicion.add(mnNewMenu);

		JMenuItem mntmBorrarPorID = new JMenuItem("Borrar por ID");
		mnNewMenu.add(mntmBorrarPorID);

		JMenuItem mntmBorrarPorNombre = new JMenuItem("Borrar por nombre");
		mntmBorrarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BorrarPorNombre borrarPorNombre = new BorrarPorNombre();
				borrarPorNombre.setVisible(true);
			}
		});
		mnNewMenu.add(mntmBorrarPorNombre);
		mntmBorrarPorID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BorrarPorID borrarPorID = new BorrarPorID();
				borrarPorID.setVisible(true);
			}
		});

		JMenu mnBuscarr = new JMenu("Buscar  ");
		mnEdicion.add(mnBuscarr);

		JMenuItem mntmPorColor = new JMenuItem("Buscar por ID");
		mnBuscarr.add(mntmPorColor);

		JMenuItem mntmBuscarPorNombre = new JMenuItem("Buscar por nombre");
		mntmBuscarPorNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarPorNombre buscarPorNombre = new BuscarPorNombre();
				buscarPorNombre.setVisible(true);
			}
		});
		mnBuscarr.add(mntmBuscarPorNombre);
		mntmPorColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscarPorId buscarPorId = new BuscarPorId();
				buscarPorId.setVisible(true);
			}
		});

		JMenu mnListar = new JMenu("Listar");
		mnListar.setMnemonic('L');
		menuBar.add(mnListar);

		JMenuItem mntmListarNovelas = new JMenuItem("Listar novelas");
		mntmListarNovelas
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmListarNovelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarNovelas listarNovelas = new ListarNovelas();
					listarNovelas.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay novelas!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnListar.add(mntmListarNovelas);

		JMenuItem mntmListarRevistas = new JMenuItem("Listar revistas");
		mntmListarRevistas
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmListarRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarRevistas listarRevistas = new ListarRevistas();
					listarRevistas.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay revistas!", "Error!", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		mnListar.add(mntmListarRevistas);

		JMenuItem mntmListarPeriodicos = new JMenuItem("Listar periodicos");
		mntmListarPeriodicos
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmListarPeriodicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarPeriodicos listarPeriodicos = new ListarPeriodicos();
					listarPeriodicos.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay periodicos!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnListar.add(mntmListarPeriodicos);

		JMenuItem mntmListarLibrosDe = new JMenuItem("Listar libros de texto");
		mntmListarLibrosDe
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmListarLibrosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarLibrosTexto librosTexto = new ListarLibrosTexto();
					librosTexto.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay libros de texto!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnListar.add(mntmListarLibrosDe);

		JSeparator separator_1 = new JSeparator();
		mnListar.add(separator_1);

		JMenuItem mntmListarTodo = new JMenuItem("Listar todo");
		mntmListarTodo
				.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmListarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListarBiblioteca listarBiblioteca = new ListarBiblioteca();
					listarBiblioteca.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e) {
					JOptionPane.showMessageDialog(null, "Biblioteca vacia!", "Error!", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		mnListar.add(mntmListarTodo);

		JMenu mnPrestamosdevoluciones = new JMenu("Prestamos/Devoluciones");
		mnPrestamosdevoluciones.setMnemonic('P');
		menuBar.add(mnPrestamosdevoluciones);

		JMenuItem mntmRealizarPrstamo = new JMenuItem("Realizar pr\u00E9stamo");
		mntmRealizarPrstamo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrestarPublicacion prestarPublicacion = new PrestarPublicacion();
				prestarPublicacion.setVisible(true);
			}
		});
		mnPrestamosdevoluciones.add(mntmRealizarPrstamo);

		JMenuItem mntmRealizarDevoluin = new JMenuItem("Realizar devoluci\u00F3n");
		mntmRealizarDevoluin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DevolverPublicacion devolverPublicacion = new DevolverPublicacion();
				devolverPublicacion.setVisible(true);
			}
		});
		mnPrestamosdevoluciones.add(mntmRealizarDevoluin);

		JMenuItem mntmListarPrestados = new JMenuItem("Pubicaciones prestadas");
		mntmListarPrestados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					PublicacionesPrestadas publicacionesPrestadas = new PublicacionesPrestadas();
					publicacionesPrestadas.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay publicaciones prestadas!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnPrestamosdevoluciones.add(mntmListarPrestados);

		JMenuItem mntmListarADevolver = new JMenuItem("A devolver hoy");
		mntmListarADevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ADevolverHoy aDevolverHoy = new ADevolverHoy();
					aDevolverHoy.setVisible(true);
				} catch (IndexOutOfBoundsException | NoSuchElementException e1) {
					JOptionPane.showMessageDialog(null, "No hay libros a devolver hoy", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnPrestamosdevoluciones.add(mntmListarADevolver);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);

		JMenuItem mntmACercaDe = new JMenuItem("Acerca de la Biblioteca...");
		mntmACercaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AcercaDe acercaDe = new AcercaDe();
				acercaDe.setVisible(true);
			}
		});

		JMenuItem mntmVerAyuda = new JMenuItem("Ver la Ayuda");
		mntmVerAyuda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		mntmVerAyuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ayuda == null) {
					ayuda = new Ayuda();
					ayuda.setVisible(true);
				} else {
					ayuda.setVisible(true);
				}
			}
		});
		mnAyuda.add(mntmVerAyuda);
		mnAyuda.add(mntmACercaDe);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	/**
	 * Crea un nuevo biblioteca y comprueba los cambios del anterior
	 */
	private void nuevo() {
		if (Fichero.almacen.isModificado()) {
			switch (JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {
			case JOptionPane.YES_OPTION:
				// Si respondemos Aceptar
				guardarComoFile();
				Fichero.almacen = new Biblioteca();
				setTitle("Sin_titulo");
				Fichero.almacen.setModificado(false);
				break;
			case JOptionPane.NO_OPTION:
				// Si respondemos No
				Fichero.almacen = new Biblioteca();
				setTitle("Sin_titulo");
				Fichero.almacen.setModificado(false);
				break;
			default:
				break;
			}
		} else {
			Fichero.almacen = new Biblioteca();
			setTitle("Sin_titulo");
			Fichero.almacen.setModificado(false);
		}
	}

	/**
	 * Abre un archivo a partir de un filechoose
	 * 
	 * @throws HeadlessException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void abrirArchivo() throws HeadlessException, IOException, ClassNotFoundException {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);
		if (filechooser.showDialog(filechooser, "Abrir Archivo") == JFileChooser.APPROVE_OPTION) {
			Fichero.abrir(filechooser.getSelectedFile());
			setTitle(filechooser.getSelectedFile().getName());
			Fichero.almacen.setModificado(false);
		}
	}

	/**
	 * Abre una nueva biblioteca y comprueba si esta modificado
	 */
	private void abrir() {
		if (Fichero.almacen.isModificado()) {
			switch (JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {

			case JOptionPane.YES_OPTION:
				guardarComoFile();
				break;
			case JOptionPane.NO_OPTION:
				try {
					abrirArchivo();
				} catch (IOException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
				}
				break;
			default:
				break;
			}
		} else {
			try {
				abrirArchivo();
			} catch (IOException | ClassNotFoundException ex) {
				JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Guarda un archivo
	 */
	private void guardar() {
		if (getTitle().equalsIgnoreCase("Sin_titulo")) {
			guardarComoFile();
		} else {
			try {
				Fichero.guardar(Fichero.almacen, filechooser.getSelectedFile());
				Fichero.almacen.setModificado(false);
				setTitle(filechooser.getSelectedFile().getName());
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * Guarda un archivo y comprueba si existe
	 */
	private void guardarComoFile() {
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.addChoosableFileFilter(filtro);

		if (JFileChooser.APPROVE_OPTION == filechooser.showDialog(filechooser, "Guardar")) {
			filechooser.setAcceptAllFileFilterUsed(false);

			if (filechooser.getSelectedFile().exists()) {

				switch (JOptionPane.showConfirmDialog(filechooser, "El archivo ya existe, ¿Desea sobreescribir?", "!!",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {

				case JOptionPane.YES_OPTION:
					try {
						Fichero.guardarComo(Fichero.almacen, filechooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
						Fichero.almacen.setModificado(false);
						setTitle(filechooser.getSelectedFile().getName());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					break;
				// En caso de NO y CANCEL informamos de que no ha sido guardado
				default:
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
			// Si el archivo no existe guardamos
			else {
				try {
					Fichero.guardar(Fichero.almacen, filechooser.getSelectedFile());
					Fichero.almacen.setModificado(false);
					setTitle(filechooser.getSelectedFile().getName());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Sale del programa comprobando cambios
	 */
	private void salir() {
		if (Fichero.almacen.isModificado()) {
			switch (JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE)) {
			case JOptionPane.YES_OPTION:
				guardarComoFile();
				break;
			case JOptionPane.NO_OPTION:
				System.exit(0);
				break;
			default:
				break;
			}
		} else {
			System.exit(0);
		}
	}

}
