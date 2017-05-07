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
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;

public class Principal extends JFrame implements Serializable {

	private final Filtro filtro = new Filtro(".obj", "Objeto");

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
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
		filechooser.setSelectedFile(new File("*.obj"));
		setTitle("Sin_titulo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

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

		JMenu mnAadir = new JMenu("A\u00F1adir");
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
		
				JMenuItem mntmPorIndice_1 = new JMenuItem("Borrar");
				mnEdicion.add(mntmPorIndice_1);
						
								JMenuItem mntmPorColor = new JMenuItem("Buscar");
								mnEdicion.add(mntmPorColor);

		JMenu mnListar = new JMenu("Listar");
		mnListar.setMnemonic('L');
		menuBar.add(mnListar);

		JMenuItem mntmListarNovelas = new JMenuItem("Listar novelas");
		mntmListarNovelas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarNovelas listarNovelas = new ListarNovelas();
					listarNovelas.setVisible(true);
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No hay novelas!", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnListar.add(mntmListarNovelas);

		JMenuItem mntmListarRevistas = new JMenuItem("Listar revistas");
		mntmListarRevistas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ListarRevistas listarRevistas = new ListarRevistas();
					listarRevistas.setVisible(true);
				} catch (IndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(null, "No hay revistas!", "Error!", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		mnListar.add(mntmListarRevistas);

		JMenuItem mntmListarPeriodicos = new JMenuItem("Listar periodicos");
		mntmListarPeriodicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPeriodicos listarPeriodicos = new ListarPeriodicos();
				listarPeriodicos.setVisible(true);
			}
		});
		mnListar.add(mntmListarPeriodicos);

		JMenuItem mntmListarLibrosDe = new JMenuItem("Listar libros de texto");
		mntmListarLibrosDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarLibrosTexto librosTexto = new ListarLibrosTexto();
				librosTexto.setVisible(true);
			}
		});
		mnListar.add(mntmListarLibrosDe);

		JSeparator separator_1 = new JSeparator();
		mnListar.add(separator_1);

		JMenuItem mntmListarTodo = new JMenuItem("Listar todo");
		mnListar.add(mntmListarTodo);

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

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	}

	/**
	 * Crea un nuevo biblioteca y comprueba los cambios del anterior
	 */
	private void nuevo() {
		if (Fichero.almacen.isModificado()) {
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				// Si respondemos Aceptar
				guardarComoFile();
				Fichero.almacen = new Biblioteca();
				setTitle("Sin_titulo");
				Fichero.almacen.setModificado(false);
			} else if (respuesta == JOptionPane.NO_OPTION) {
				// Si respondemos No
				Fichero.almacen = new Biblioteca();
				setTitle("Sin_titulo");
				Fichero.almacen.setModificado(false);
			} else {
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
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComoFile();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				try {
					abrirArchivo();
				} catch (IOException | ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null, "Error al abrir archivo", "!!", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// No hacemos nada en cado de cancelar
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
			// Si el archivo existe, informamos de ello y en funcion de la
			// respuesta ...
			if (filechooser.getSelectedFile().exists()) {

				int respuesta = JOptionPane.showConfirmDialog(filechooser,
						"El archivo ya existe, ¿Desea sobreescribir?", "!!", JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.WARNING_MESSAGE);
				// Si la respuesta es Si, guardamos el archivo con el nombre
				if (respuesta == JOptionPane.YES_OPTION) {
					try {
						Fichero.guardarComo(Fichero.almacen, filechooser.getSelectedFile());
						JOptionPane.showMessageDialog(null, "El archivo ha sido guardado", "Aceptar",
								JOptionPane.INFORMATION_MESSAGE);
						Fichero.almacen.setModificado(false);
						setTitle(filechooser.getSelectedFile().getName());
					} catch (IOException ex) {
						ex.printStackTrace();
					}
					// Si la respuesta es No, informaremos de que no se ha
					// guardado
				} else {
					JOptionPane.showMessageDialog(null, "El archivo no se ha guardado", "Error!",
							JOptionPane.ERROR_MESSAGE);
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
			int respuesta = JOptionPane.showConfirmDialog(filechooser, "No ha guardado, ¿Desea Guardar?", "!!",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
			if (respuesta == JOptionPane.YES_OPTION) {
				guardarComoFile();
			} else if (respuesta == JOptionPane.NO_OPTION) {
				System.exit(0);
			} else {
			}
		} else {
			System.exit(0);
		}
	}

}
